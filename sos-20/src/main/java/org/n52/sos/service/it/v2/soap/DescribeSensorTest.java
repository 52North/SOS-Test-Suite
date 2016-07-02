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

import net.opengis.swes.x20.DescribeSensorDocument;
import net.opengis.swes.x20.DescribeSensorType;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

/**
 * Test for SOAP SOS 2.0 DescribeSensor request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class DescribeSensorTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        DescribeSensorDocument describeSensorDocument =
                getRequest("procedure", "procedureDescriptionFormat");
        addVersionParameter(describeSensorDocument.getDescribeSensor());
        missingServiceParameter(describeSensorDocument.getDescribeSensor(), describeSensorDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        DescribeSensorDocument describeSensorDocument =
                getRequest("procedure", "procedureDescriptionFormat");
        addVersionParameter(describeSensorDocument.getDescribeSensor());
        emptyServiceParameter(describeSensorDocument.getDescribeSensor(), describeSensorDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        DescribeSensorDocument describeSensorDocument =
                getRequest("procedure", "procedureDescriptionFormat");
        addVersionParameter(describeSensorDocument.getDescribeSensor());
        invalidServiceParameter(describeSensorDocument.getDescribeSensor(), describeSensorDocument);
    }

    protected DescribeSensorDocument getRequest(String procedure,
                                                String procedureDescriptionFormat) {
        DescribeSensorDocument describeSensorDocument =
                DescribeSensorDocument.Factory.newInstance();
        DescribeSensorType describeSensorType = describeSensorDocument
                .addNewDescribeSensor();
        describeSensorType.setProcedure(procedure);
        describeSensorType
                .setProcedureDescriptionFormat(procedureDescriptionFormat);
        return describeSensorDocument;
    }
}
