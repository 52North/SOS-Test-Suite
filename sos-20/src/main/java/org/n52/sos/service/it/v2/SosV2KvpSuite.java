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
package org.n52.sos.service.it.v2;

import org.n52.sos.service.it.ComplianceSuite;
import org.n52.sos.service.it.v2.kvp.DeleteObservationTest;
import org.n52.sos.service.it.v2.kvp.DeleteSensorTest;
import org.n52.sos.service.it.v2.kvp.DescribeSensorTest;
import org.n52.sos.service.it.v2.kvp.GetCapabilitiesTest;
import org.n52.sos.service.it.v2.kvp.GetDataAvailabilityTest;
import org.n52.sos.service.it.v2.kvp.GetFeatureOfInterestTest;
import org.n52.sos.service.it.v2.kvp.GetObservationByIdTest;
import org.n52.sos.service.it.v2.kvp.GetObservationTest;
import org.n52.sos.service.it.v2.kvp.GetResultTemplateTest;
import org.n52.sos.service.it.v2.kvp.GetResultTest;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public abstract class SosV2KvpSuite implements ComplianceSuite {
    public Class<?>[] getTests() {
        return new Class<?>[] {
            DeleteObservationTest.class,
            DeleteSensorTest.class,
            DescribeSensorTest.class,
            GetCapabilitiesTest.class,
            GetDataAvailabilityTest.class,
            GetFeatureOfInterestTest.class,
            GetObservationByIdTest.class,
            GetObservationTest.class,
            GetResultTemplateTest.class,
            GetResultTest.class
        };
    }
}
