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

import net.opengis.sos.x20.InsertObservationDocument;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;
import org.n52.sos.service.it.v2.RequestBuilder;

/**
 * Test for SOAP SOS 2.0 InsertObservation request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class InsertObservationTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        final InsertObservationDocument insertObservationDocument =
                RequestBuilder.getInsertObservationMinimalDocument();
        addVersionParameter(insertObservationDocument.getInsertObservation());
        missingServiceParameter(insertObservationDocument.getInsertObservation(),
                                insertObservationDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        final InsertObservationDocument insertObservationDocument =
                RequestBuilder.getInsertObservationMinimalDocument();
        addVersionParameter(insertObservationDocument.getInsertObservation());
        emptyServiceParameter(insertObservationDocument.getInsertObservation(),
                              insertObservationDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        final InsertObservationDocument insertObservationDocument =
                RequestBuilder.getInsertObservationMinimalDocument();
        addVersionParameter(insertObservationDocument.getInsertObservation());
        invalidServiceParameter(insertObservationDocument.getInsertObservation(),
                                insertObservationDocument);
    }
}
