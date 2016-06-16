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

import net.opengis.sos.x20.GetResultDocument;
import net.opengis.sos.x20.GetResultType;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

/**
 * Test for SOAP SOS 2.0 GetResult request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetResultTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        GetResultDocument getResultDocument =
                getRequest("offering", "observedProperty");
        addVersionParameter(getResultDocument.getGetResult());
        missingServiceParameter(getResultDocument.getGetResult(), getResultDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        GetResultDocument getResultDocument =
                getRequest("offering", "observedProperty");
        addVersionParameter(getResultDocument.getGetResult());
        emptyServiceParameter(getResultDocument.getGetResult(), getResultDocument);
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {
        GetResultDocument getResultDocument =
                getRequest("offering", "observedProperty");
        addVersionParameter(getResultDocument.getGetResult());
        invalidServiceParameter(getResultDocument.getGetResult(), getResultDocument);
    }

    protected GetResultDocument getRequest(String offering,
                                           String observedProperty) {
        GetResultDocument getResultDocument = GetResultDocument.Factory
                .newInstance();
        GetResultType getResultType = getResultDocument.addNewGetResult();
        getResultType.setOffering("offering");
        getResultType.setObservedProperty("observedProperty");
        return getResultDocument;
    }
}
