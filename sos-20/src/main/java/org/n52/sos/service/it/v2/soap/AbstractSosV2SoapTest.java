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

import static org.hamcrest.Matchers.is;
import static org.n52.sos.service.it.v2.ExceptionMatchers.invalidRequestMissingParameterExceptionFault;
import static org.n52.sos.service.it.v2.ExceptionMatchers.invalidServiceParameterValueExceptionFault;
import static org.n52.sos.service.it.v2.ExceptionMatchers.missingServiceParameterValueExceptionFault;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.junit.Test;
import org.n52.sos.service.it.Response;
import org.n52.sos.service.it.ServiceConstants;
import org.n52.sos.service.it.soap.AbstractSoapComplianceTest;

import net.opengis.swes.x20.ExtensibleRequestType;

/**
 * Abstract class for SOS 2.0 SOAP request tests
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 *
 */
public abstract class AbstractSosV2SoapTest extends AbstractSoapComplianceTest {
    public static final String SERVICE = ServiceConstants.SOS;
    public static final String VERSION = ServiceConstants.V20;
    public static final String SERVICE_PARAMETER = "service";

    protected void addServiceParameter(
            ExtensibleRequestType extensibleRequestType) {
        extensibleRequestType.setService(SERVICE);
    }

    protected void addVersionParameter(
            ExtensibleRequestType extensibleRequestType) {
        extensibleRequestType.setVersion(VERSION);
    }

    public void missingServiceParameter(ExtensibleRequestType req,
                                        XmlObject xmlDocument) {
        Response res = soap(xmlDocument);
        getErrors().checkThat(res.getStatus(), is(400));
        getErrors().checkThat(res.asNode(),
                              is(invalidRequestMissingParameterExceptionFault(SERVICE_PARAMETER)));
    }

    public void emptyServiceParameter(ExtensibleRequestType req,
                                      XmlObject xmlDocument) {
        req.setService("");
        Response res = soap(xmlDocument);
        getErrors().checkThat(res.getStatus(), is(400));
        getErrors()
                .checkThat(res.asNode(), is(missingServiceParameterValueExceptionFault()));
    }

    public void invalidServiceParameter(ExtensibleRequestType req,
                                        XmlObject xmlDocument) {
        req.setService("INVALID");
        Response res = soap(xmlDocument);
        getErrors().checkThat(res.getStatus(), is(400));
        getErrors()
                .checkThat(res.asNode(), is(invalidServiceParameterValueExceptionFault("INVALID")));
    }

    @Test
    public abstract void emptyServiceParameter() throws XmlException;

    @Test
    public abstract void missingServiceParameter() throws XmlException;

    @Test
    public abstract void invalidServiceParameter() throws XmlException;
}
