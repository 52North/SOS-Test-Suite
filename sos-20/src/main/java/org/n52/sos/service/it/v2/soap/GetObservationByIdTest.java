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

import net.opengis.sos.x20.GetObservationByIdDocument;
import net.opengis.sos.x20.GetObservationByIdType;

/**
 * Test for SOAP SOS 2.0 GetObservationById request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetObservationByIdTest extends AbstractSosV2SoapTest {

    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        GetObservationByIdDocument getObservationByIdDocument = getRequest(OBSERVATION_PARAMETER);
        addVersionParameter(getObservationByIdDocument.getGetObservationById());
        missingServiceParameter(getObservationByIdDocument.getGetObservationById(), getObservationByIdDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        GetObservationByIdDocument getObservationByIdDocument = getRequest(OBSERVATION_PARAMETER);
        addVersionParameter(getObservationByIdDocument.getGetObservationById());
        emptyServiceParameter(getObservationByIdDocument.getGetObservationById(), getObservationByIdDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        GetObservationByIdDocument getObservationByIdDocument = getRequest(OBSERVATION_PARAMETER);
        addVersionParameter(getObservationByIdDocument.getGetObservationById());
        invalidServiceParameter(getObservationByIdDocument.getGetObservationById(), getObservationByIdDocument);
    }

    protected GetObservationByIdDocument getRequest(String observation) {
        GetObservationByIdDocument getObservationByIdDocument = GetObservationByIdDocument.Factory.newInstance();
        GetObservationByIdType getObservationByIdType = getObservationByIdDocument.addNewGetObservationById();
        getObservationByIdType.addObservation(OBSERVATION_PARAMETER);
        return getObservationByIdDocument;
    }
}
