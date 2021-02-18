/*
 * Copyright (C) 2020 Lukas Sykora
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cas.lib.proarc.common.export;

import org.apache.commons.configuration.Configuration;

/**
 * Settings for export
 *
 * @author Lukas Sykora
 */
public class ExportOptions {
    static final String PROP_DELETE_PACKAGE = "export.deletePackageIfUrnNbnIsMissing";
    static final String PROP_OVERWRITE_PACKAGE = "export.overwritePackage";

    public static ExportOptions getOptions(Configuration config) {
        ExportOptions options = new ExportOptions();

        String deletePackage = config.getString(PROP_DELETE_PACKAGE);
        options.setDeletePackage(Boolean.TRUE.equals(Boolean.parseBoolean(deletePackage)));

        String overwritePackage = config.getString(PROP_OVERWRITE_PACKAGE);
        options.setOverwritePackage(Boolean.TRUE.equals(Boolean.parseBoolean(overwritePackage)));

        return options;
    }

    public boolean isDeletePackage() {
        return deletePackage;
    }

    public void setDeletePackage(boolean deletePackage) {
        this.deletePackage = deletePackage;
    }

    public boolean isOverwritePackage() {
        return overwritePackage;
    }

    public void setOverwritePackage(boolean overwritePackage) {
        this.overwritePackage = overwritePackage;
    }

    private boolean deletePackage;
    private boolean overwritePackage;
}