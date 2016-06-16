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

import net.opengis.sos.x20.GetObservationDocument;
import net.opengis.sos.x20.GetObservationType;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

/**
 * Test for SOAP SOS 2.0 GetObservation request.
 * 
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetObservationTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        GetObservationDocument getObservationDocument = getRequest();
        addVersionParameter(getObservationDocument.getGetObservation());
        missingServiceParameter(getObservationDocument.getGetObservation(), getObservationDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        GetObservationDocument getObservationDocument = getRequest();
        addVersionParameter(getObservationDocument.getGetObservation());
        emptyServiceParameter(getObservationDocument.getGetObservation(), getObservationDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        GetObservationDocument getObservationDocument = getRequest();
        addVersionParameter(getObservationDocument.getGetObservation());
        invalidServiceParameter(getObservationDocument.getGetObservation(), getObservationDocument);
    }

    protected GetObservationDocument getRequest() {
        GetObservationDocument getObservationDocument = GetObservationDocument.Factory.newInstance();
        GetObservationType getObservationType = getObservationDocument.addNewGetObservation();
        return getObservationDocument;
    }
}
