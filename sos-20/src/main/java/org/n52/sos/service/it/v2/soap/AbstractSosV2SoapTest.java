package org.n52.sos.service.it.v2.soap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.n52.sos.service.it.v2.ExceptionMatchers.*;

import net.opengis.swes.x20.ExtensibleRequestType;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.junit.Test;
import org.n52.sos.service.it.ServiceConstants;
import org.n52.sos.service.it.Response;
import org.n52.sos.service.it.soap.AbstractSoapComplianceTest;

/**
 * Abstract class for SOS 2.0 SOAP request tests
 *
 * @author Carsten Hollmann <c.hollmann@52north.org>
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
        assertThat(res.getStatus(), is(400));
        assertThat(res.asNode(),
                   is(invalidRequestMissingParameterExceptionFault(SERVICE_PARAMETER)));
    }

    public void emptyServiceParameter(ExtensibleRequestType req,
                                      XmlObject xmlDocument) {
        req.setService("");
        Response res = soap(xmlDocument);
        assertThat(res.getStatus(), is(400));
        assertThat(res.asNode(), is(missingServiceParameterValueExceptionFault()));
    }

    public void invalidServiceParameter(ExtensibleRequestType req,
                                        XmlObject xmlDocument) {
        req.setService("INVALID");
        Response res = soap(xmlDocument);
        assertThat(res.getStatus(), is(400));
        assertThat(res.asNode(), is(invalidServiceParameterValueExceptionFault("INVALID")));
    }

    @Test
    public abstract void emptyServiceParameter() throws XmlException;

    @Test
    public abstract void missingServiceParameter() throws XmlException;

    @Test
    public abstract void invalidServiceParameter() throws XmlException;
}
