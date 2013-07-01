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
package org.n52.sos.service.it;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.n52.sos.service.it.v2.rest.CapabilitiesTest;
import org.n52.sos.service.it.v2.rest.OfferingsTest;
import org.n52.sos.service.it.v2.rest.SensorsTest;
import org.n52.sos.service.it.v2.rest.ServiceEndpointTest;
import org.n52.sos.service.it.v40.H2Database;
import org.n52.sos.service.it.v40.SOS40Executor;

@RunWith(ComplianceSuiteRunner.class)
public class SosV2V4RestSuiteTest implements ComplianceSuite {
    @Rule
    public final H2Database database = new H2Database();

    @Override
    public Class<?>[] getTests() {
        return new Class<?>[] {
            CapabilitiesTest.class,
            OfferingsTest.class,
            SensorsTest.class,
            ServiceEndpointTest.class
        };
    }

    @Override
    public RequestExecutor createExecutor() {
        return new SOS40Executor();
    }
}
