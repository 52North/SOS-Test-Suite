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

import org.hamcrest.Matchers;
import org.n52.sos.service.it.v2.ExceptionMatchers;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;
import org.n52.sos.service.it.Response;

import net.opengis.sos.x20.GetCapabilitiesDocument;
import net.opengis.sos.x20.GetCapabilitiesType;

/**
 * Test for SOAP SOS 2.0 GetCapabilities request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetCapabilitiesTest extends AbstractSosV2SoapTest {

    private static final String VERSION = "2.0.0";

    @Test
    @Override
    public void missingServiceParameter() throws XmlException {
        GetCapabilitiesDocument getCapabilitiesDocument = GetCapabilitiesDocument.Factory.newInstance();
        GetCapabilitiesType getCapabilitiesType = getCapabilitiesDocument.addNewGetCapabilities2();
        getCapabilitiesType.addNewAcceptVersions().addNewVersion().setStringValue(VERSION);
        Response res = soap(getCapabilitiesDocument);
        getErrors().checkThat(res.getStatus(), Matchers.is(200));
        // TODO check if response is a sos:Capabilities document
    }

    @Test
    @Override
    public void emptyServiceParameter() throws XmlException {
        GetCapabilitiesDocument getCapabilitiesDocument = GetCapabilitiesDocument.Factory.newInstance();
        GetCapabilitiesType getCapabilitiesType = getCapabilitiesDocument.addNewGetCapabilities2();
        getCapabilitiesType.addNewAcceptVersions().addNewVersion().setStringValue(VERSION);
        getCapabilitiesType.setService("");
        Response res = soap(getCapabilitiesDocument);
        getErrors().checkThat(res.getStatus(), Matchers.is(400));
        getErrors().checkThat(res.asNode(),
                Matchers.is(ExceptionMatchers.missingServiceParameterValueExceptionFault()));
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {
        GetCapabilitiesDocument getCapabilitiesDocument = GetCapabilitiesDocument.Factory.newInstance();
        GetCapabilitiesType getCapabilitiesType = getCapabilitiesDocument.addNewGetCapabilities2();
        getCapabilitiesType.addNewAcceptVersions().addNewVersion().setStringValue(VERSION);
        getCapabilitiesType.setService(INVALID);
        Response res = soap(getCapabilitiesDocument);
        getErrors().checkThat(res.getStatus(), Matchers.is(400));
        getErrors().checkThat(res.asNode(),
                Matchers.is(ExceptionMatchers.invalidServiceParameterValueExceptionFault(INVALID)));
    }
}
