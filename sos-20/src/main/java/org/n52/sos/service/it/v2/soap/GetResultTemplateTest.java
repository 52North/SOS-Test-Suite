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

import net.opengis.sos.x20.GetResultTemplateDocument;
import net.opengis.sos.x20.GetResultTemplateType;

/**
 * Test for SOAP SOS 2.0 GetResultTemplate request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetResultTemplateTest extends AbstractSosV2SoapTest {

    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        GetResultTemplateDocument getResultTemplateDocument = getRequest("offering", "observedProperty");
        addVersionParameter(getResultTemplateDocument.getGetResultTemplate());
        missingServiceParameter(getResultTemplateDocument.getGetResultTemplate(), getResultTemplateDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        GetResultTemplateDocument getResultTemplateDocument = getRequest("offering", "observedProperty");
        addVersionParameter(getResultTemplateDocument.getGetResultTemplate());
        emptyServiceParameter(getResultTemplateDocument.getGetResultTemplate(), getResultTemplateDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        GetResultTemplateDocument getResultTemplateDocument = getRequest("offering", "observedProperty");
        addVersionParameter(getResultTemplateDocument.getGetResultTemplate());
        invalidServiceParameter(getResultTemplateDocument.getGetResultTemplate(), getResultTemplateDocument);
    }

    protected GetResultTemplateDocument getRequest(String offering, String observedProperty) {
        GetResultTemplateDocument getResultTemplateDocument = GetResultTemplateDocument.Factory.newInstance();
        GetResultTemplateType getResultTemplateType = getResultTemplateDocument.addNewGetResultTemplate();
        getResultTemplateType.setOffering(offering);
        getResultTemplateType.setObservedProperty(observedProperty);
        return getResultTemplateDocument;
    }
}
