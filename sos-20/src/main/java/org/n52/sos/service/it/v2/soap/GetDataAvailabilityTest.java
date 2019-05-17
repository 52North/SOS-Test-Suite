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
import static org.n52.sos.service.it.v2.ExceptionMatchers.invalidServiceParameterValueExceptionFault;
import static org.n52.sos.service.it.v2.ExceptionMatchers.missingServiceParameterValueExceptionFault;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.junit.Ignore;
import org.junit.Test;
import org.n52.sos.service.it.Response;

/**
 * Test for SOAP SOS 2.0 GetDataAvailability request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetDataAvailabilityTest extends AbstractSosV2SoapTest {
    @Test
    @Override
    @Ignore("currently not supported due to missing XML schema")
    public void missingServiceParameter() throws XmlException {
        // XmlObject getDataAvailabilityDocument =
        // XmlObject.Factory
        // .parse("<sos:GetDataAvailability xmlns:sos=\"http://www.opengis.net/sos/2.0\" version=\"2.0.0\"/>");
        // MockHttpServletResponse res = execute(getDataAvailabilityDocument);
        // assertThat(res.getStatus(), is(400));
        // assertThat(getResponseAsNode(res),
        // is(missingServiceParameterValueExceptionFault()));
    }

    @Test
    @Override
    public void emptyServiceParameter() throws XmlException {
        XmlObject getDataAvailabilityDocument =
                XmlObject.Factory
                .parse("<sos:GetDataAvailability xmlns:sos=\"http://www.opengis.net/sos/2.0\" service=\"\" version=\"2.0.0\"/>");
        Response res = soap(getDataAvailabilityDocument);
        getErrors().checkThat(res.getStatus(), is(400));
        getErrors()
                .checkThat(res.asNode(), is(missingServiceParameterValueExceptionFault()));
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {

        XmlObject getDataAvailabilityDocument =
                XmlObject.Factory
                .parse("<sos:GetDataAvailability xmlns:sos=\"http://www.opengis.net/sos/2.0\" service=\"INVALID\" version=\"2.0.0\"/>");
        Response res = soap(getDataAvailabilityDocument);
        getErrors().checkThat(res.getStatus(), is(400));
        getErrors()
                .checkThat(res.asNode(), is(invalidServiceParameterValueExceptionFault("INVALID")));
    }
}
