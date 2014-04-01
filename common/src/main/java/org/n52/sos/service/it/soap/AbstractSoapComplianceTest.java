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
package org.n52.sos.service.it.soap;

import org.apache.xmlbeans.XmlObject;
import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Response;
import org.w3.x2003.x05.soapEnvelope.EnvelopeDocument;

/**
 * Abstract class for SOS SOAP requests tests
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 *
 * @since 4.0.0
 */
public abstract class AbstractSoapComplianceTest extends AbstractComplianceSuiteTest {
    public static final String APPLICATION_SOAP_XML = "application/soap+xml";

    protected XmlObject envelope(final XmlObject r) {
        final EnvelopeDocument envDoc = EnvelopeDocument.Factory.newInstance();
        envDoc.addNewEnvelope().addNewBody().set(r);
        return envDoc;
    }

    protected Response soap(XmlObject xml) {
        return getExecutor()
                .soap()
                .accept(APPLICATION_SOAP_XML)
                .contentType(APPLICATION_SOAP_XML)
                .entity(envelope(xml).xmlText())
                .response();
    }
}
