/*
 * Copyright (C) 2013
 *
 * 52°North Initiative for Geospatial Open Source Software GmbH
 * Contact: Andreas Wytzisk
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.n52.sos.service.it.v2.soap;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML;
import net.opengis.sensorML.x101.SystemDocument;
import net.opengis.swes.x20.UpdateSensorDescriptionDocument;
import net.opengis.swes.x20.UpdateSensorDescriptionType;

/**
 * Test for SOAP SOS 2.0 UpdateSensorDescription request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class UpdateSensorDescriptionTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        UpdateSensorDescriptionDocument updateSensorDescriptionDocument = getMinimalRequest();
        addVersionParameter(updateSensorDescriptionDocument.getUpdateSensorDescription());
        missingServiceParameter(updateSensorDescriptionDocument.getUpdateSensorDescription(),
                updateSensorDescriptionDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        UpdateSensorDescriptionDocument updateSensorDescriptionDocument = getMinimalRequest();
        addVersionParameter(updateSensorDescriptionDocument.getUpdateSensorDescription());
        emptyServiceParameter(updateSensorDescriptionDocument.getUpdateSensorDescription(),
                updateSensorDescriptionDocument);
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {
        UpdateSensorDescriptionDocument updateSensorDescriptionDocument = getMinimalRequest();
        addVersionParameter(updateSensorDescriptionDocument.getUpdateSensorDescription());
        invalidServiceParameter(updateSensorDescriptionDocument.getUpdateSensorDescription(),
                updateSensorDescriptionDocument);
    }

    protected UpdateSensorDescriptionDocument getMinimalRequest() {
        UpdateSensorDescriptionDocument insertSensorDocument = UpdateSensorDescriptionDocument.Factory.newInstance();
        UpdateSensorDescriptionType updateSensorDescriptionType = insertSensorDocument.addNewUpdateSensorDescription();
        updateSensorDescriptionType.setProcedure("procedure");
        updateSensorDescriptionType.setProcedureDescriptionFormat("http://www.opengis.net/sensorML/1.0.1");
        SensorMLDocument sensorMLDocument = SensorMLDocument.Factory.newInstance();
        SensorML sensorML = sensorMLDocument.addNewSensorML();
        sensorML.setVersion("1.0.1");
        SystemDocument systemDocument = SystemDocument.Factory.newInstance();
        systemDocument.addNewSystem();
        sensorML.addNewMember().set(systemDocument);
        updateSensorDescriptionType.addNewDescription().addNewSensorDescription().addNewData().set(sensorMLDocument);

        return insertSensorDocument;
    }
}
