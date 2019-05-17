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
import org.n52.sos.service.it.v2.RequestBuilder;

import net.opengis.swes.x20.InsertSensorDocument;

/**
 * Test for SOAP SOS 2.0 InsertSensor request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 *         J&uuml;rrens</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class InsertSensorTest extends AbstractSosV2SoapTest {
    @Test
    @Override
    public void missingServiceParameter() throws XmlException {
        final InsertSensorDocument insertSensorDocument = RequestBuilder.getInsertSensorMinimalDocument();
        addVersionParameter(insertSensorDocument.getInsertSensor());
        missingServiceParameter(insertSensorDocument.getInsertSensor(), insertSensorDocument);
    }

    @Test
    @Override
    public void emptyServiceParameter() throws XmlException {
        final InsertSensorDocument insertSensorDocument = RequestBuilder.getInsertSensorMinimalDocument();
        addVersionParameter(insertSensorDocument.getInsertSensor());
        emptyServiceParameter(insertSensorDocument.getInsertSensor(), insertSensorDocument);
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {
        final InsertSensorDocument insertSensorDocument = RequestBuilder.getInsertSensorMinimalDocument();
        addVersionParameter(insertSensorDocument.getInsertSensor());
        invalidServiceParameter(insertSensorDocument.getInsertSensor(), insertSensorDocument);
    }
}
