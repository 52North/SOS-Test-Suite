/**
 * Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.sos.service.it.v2.soap;

import static org.hamcrest.Matchers.*;
import static org.n52.sos.service.it.v2.ExceptionMatchers.*;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.junit.Ignore;
import org.junit.Test;
import org.n52.sos.service.it.Response;

/**
 * Test for SOAP SOS 2.0 GetDataAvailability request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Carsten Hollmann <c.hollmann@52north.org>
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
