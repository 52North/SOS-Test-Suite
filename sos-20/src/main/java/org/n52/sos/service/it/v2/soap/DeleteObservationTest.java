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

import net.opengis.sosdo.x10.DeleteObservationDocument;
import net.opengis.sosdo.x10.DeleteObservationType;

import org.apache.xmlbeans.XmlException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test for SOAP SOS 2.0 DeleteObservation request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class DeleteObservationTest extends AbstractSosV2SoapTest {
    @Test
    @Override
    @Ignore("currently not supported due to some validation issues")
    public void missingServiceParameter() throws XmlException {
        DeleteObservationDocument deleteObservationDocument =
                getRequest("observation");
        addVersionParameter(deleteObservationDocument.getDeleteObservation());
        missingServiceParameter(deleteObservationDocument.getDeleteObservation(),
                                deleteObservationDocument);
    }

    @Test
    @Override
    @Ignore("REVIEW: Returns NoApplicableCode instead of InvalidParameterValue")
    public void emptyServiceParameter() throws XmlException {
        DeleteObservationDocument deleteObservationDocument =
                getRequest("observation");
        addVersionParameter(deleteObservationDocument.getDeleteObservation());
        invalidServiceParameter(deleteObservationDocument.getDeleteObservation(), deleteObservationDocument);
    }

    @Test
    @Override
    @Ignore("REVIEW: Returns NoApplicableCode instead of InvalidParameterValue")
    public void invalidServiceParameter() throws XmlException {
        DeleteObservationDocument deleteObservationDocument =
                getRequest("observation");
        addVersionParameter(deleteObservationDocument.getDeleteObservation());
        invalidServiceParameter(deleteObservationDocument.getDeleteObservation(),
                                deleteObservationDocument);
    }

    protected DeleteObservationDocument getRequest(String observation) {
        DeleteObservationDocument deleteObservationDocument =
                DeleteObservationDocument.Factory.newInstance();
        DeleteObservationType deleteObservationType = deleteObservationDocument
                .addNewDeleteObservation();
        deleteObservationType.setObservation("observation");
        return deleteObservationDocument;
    }
}
