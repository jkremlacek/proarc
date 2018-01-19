/*
 * Copyright (C) 2018 Jakub Kremlacek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package cz.cas.lib.proarc.common.imports;

import cz.cas.lib.proarc.common.dao.Batch;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

import static cz.cas.lib.proarc.common.imports.ImportFileScanner.IMPORT_STATE_FILENAME;
import static cz.cas.lib.proarc.common.imports.ImportProcess.TMP_DIR_NAME;

/**
 * Process for final cleanup of import batch directory after ingest into remote storage
 *
 * @author Jakub Kremlacek
 */
public class ImportPostProcess implements Runnable {

    public enum ProcessType { NONE, DELETE, MOVE }

    private static final Logger LOG = Logger.getLogger(ImportPostProcess.class.getName());

    private Batch batch;
    private ImportProfile profile;
    private File usersHome;

    public ImportPostProcess(ImportProfile profile, Batch batch, File usersHome) {
        if (profile == null) {
            throw new IllegalArgumentException("Import profile cannot be null.");
        }

        if (batch == null) {
            throw new IllegalArgumentException("Batch cannot be null.");
        }

        if (usersHome == null) {
            throw new IllegalArgumentException("UsersHome cannot be null.");
        }

        if (!usersHome.exists() || !usersHome.isDirectory()) {
            throw new IllegalArgumentException("UsersHome directory must exist.");
        }

        this.batch = batch;
        this.profile = profile;
        this.usersHome = usersHome;
    }

    @Override
    public void run() {
        start();
    }

    /**
     * starts the cleanup process
     * parameters are read from ImportProfile given in constructor
     *
     * @return cleanup success result value
     */
    public boolean start() {
        File batchFolder = usersHome.toPath().resolve(batch.getFolder()).toFile();

        switch (profile.getPostProcessType()) {
            case NONE:
                break;
            case MOVE:
                String postProcessPath = profile.getPostProcessPath();

                if (postProcessPath == null) {
                    LOG.log(Level.SEVERE, "Post-process target directory not specified within configuration");
                    return false;
                }

                File targetDirParent = new File(postProcessPath);

                if (!targetDirParent.exists()) {
                    LOG.log(Level.SEVERE, "Target directory for import post-process: " + targetDirParent.getAbsolutePath() + " does not exist.");
                    return false;
                }

                File targetDir = targetDirParent.toPath().toFile();

                try {
                    FileUtils.moveDirectoryToDirectory(batchFolder,targetDir, true);
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "Moving import batch directory \"" + batchFolder.getName() + "\" failed. Reason: " + e.getMessage());
                    return false;
                }

                //remove files/directories created by app
                try {
                    FileUtils.deleteDirectory(targetDir.toPath().resolve(TMP_DIR_NAME).toFile());
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "Removing batch tmp directory \"" + batchFolder.getName() + "/" + TMP_DIR_NAME + "\" failed. Reason: " + e.getMessage());
                    return false;
                }

                FileUtils.deleteQuietly(targetDir.toPath().resolve(IMPORT_STATE_FILENAME).toFile());

                break;
            case DELETE:
                //remove entire batch dir
                try {
                    FileUtils.deleteDirectory(batchFolder);
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "Removing batch directory \"" + batchFolder.getName() + "\" failed. Reason: " + e.getMessage());
                    return false;
                }
                break;
            default:
                LOG.log(Level.WARNING, "Unknown import post-process type. ");
                return false;
        }

        return true;
    }
}
