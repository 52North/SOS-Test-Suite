/**
 * Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.sos.service.it.kvp;

import org.junit.Test;
import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Client;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public abstract class AbstractKvpComplianceTest
        extends AbstractComplianceSuiteTest {
    public static final String REQUEST_PARAMETER = "request";
    public static final String VERSION_PARAMETER = "version";
    public static final String SERVICE_PARAMETER = "service";

    protected Client kvp() {
        return getExecutor().kvp();
    }

    /**
     * Definition for missing service parameter
     */
    @Test
    public abstract void missingServiceParameter();

    /**
     * Definition for empty service parameter value
     */
    @Test
    public abstract void emptyServiceParameter();

    /**
     * Definition for invalid service parameter value
     */
    @Test
    public abstract void invalidServiceParameter();

    /**
     * Definition for missing version parameter
     */
    @Test
    public abstract void missingVersionParameter();

    /**
     * Definition for empty version parameter value
     */
    @Test
    public abstract void emptyVersionParameter();

    /**
     * Definition for invalid version parameter value
     */
    @Test
    public abstract void invalidVersionParameter();
}
