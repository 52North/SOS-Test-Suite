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

import net.opengis.swes.x20.DeleteSensorDocument;
import net.opengis.swes.x20.DeleteSensorType;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

/**
 * Test for SOAP SOS 2.0 DeleteSensor request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 * @since 4.0.0
 */
public class DeleteSensorTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        DeleteSensorDocument deleteSensorDocument = getRequest("procedure");
        addVersionParameter(deleteSensorDocument.getDeleteSensor());
        missingServiceParameter(deleteSensorDocument.getDeleteSensor(), deleteSensorDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        DeleteSensorDocument deleteSensorDocument = getRequest("procedure");
        addVersionParameter(deleteSensorDocument.getDeleteSensor());
        emptyServiceParameter(deleteSensorDocument.getDeleteSensor(), deleteSensorDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        DeleteSensorDocument deleteSensorDocument = getRequest("procedure");
        addVersionParameter(deleteSensorDocument.getDeleteSensor());
        invalidServiceParameter(deleteSensorDocument.getDeleteSensor(), deleteSensorDocument);
    }

    protected DeleteSensorDocument getRequest(String procedure) {
        DeleteSensorDocument deleteSensorDocument = DeleteSensorDocument.Factory
                .newInstance();
        DeleteSensorType deleteSensorType = deleteSensorDocument
                .addNewDeleteSensor();
        deleteSensorType.setProcedure(procedure);
        return deleteSensorDocument;
    }
}
