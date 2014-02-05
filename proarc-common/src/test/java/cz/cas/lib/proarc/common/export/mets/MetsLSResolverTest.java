/*
 * Copyright (C) 2014 Robert Simonovsky
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

package cz.cas.lib.proarc.common.export.mets;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import org.junit.Test;

public class MetsLSResolverTest {
    @Test
    public void checkLSResolvers() {
        MetsLSResolver metsLSResolver = new MetsLSResolver();
        for (String resource : MetsLSResolver.urlMap.keySet()) {
            InputStream is = metsLSResolver.getClass().getResourceAsStream(MetsLSResolver.urlMap.get(resource));
            assertNotNull(is);
        }
    }
}
