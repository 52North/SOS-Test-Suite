package org.n52.sos.service.it.v2.kvp;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.n52.sos.service.it.v2.ExceptionMatchers.*;

import org.junit.Test;
import org.n52.sos.service.it.kvp.AbstractKvpComplianceTest;
import org.n52.sos.service.it.ServiceConstants;
import org.w3c.dom.Node;

/**
 * . Abstract class for SOS 2.0 KVP tests. Contains tests for service and
 * version parameter
 *
 * @author Carsten Hollmann <c.hollmann@52north.org>
 * @since 4.0.0
 *
 */
public abstract class AbstractSosV2KvpTest
        extends AbstractKvpComplianceTest
        implements ServiceConstants {
    public static final String VERSION = ServiceConstants.V20;
    public static final String SERVICE = ServiceConstants.SOS;

    public abstract String getRequest();

    @Test
    @Override
    public void missingServiceParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(VERSION_PARAMETER, VERSION)
                .response().asNode();
        assertThat(node, is(missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void emptyServiceParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(SERVICE_PARAMETER, "")
                .query(VERSION_PARAMETER, VERSION)
                .response().asNode();
        assertThat(node, is(missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void invalidServiceParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(SERVICE_PARAMETER, "INVALID")
                .query(VERSION_PARAMETER, VERSION)
                .response().asNode();
        assertThat(node, is(invalidServiceParameterValueException("INVALID")));
    }

    @Test
    @Override
    public void missingVersionParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(SERVICE_PARAMETER, SERVICE)
                .response().asNode();
        assertThat(node, is(missingVersionParameterValueException()));
    }

    @Test
    @Override
    public void emptyVersionParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(SERVICE_PARAMETER, SERVICE)
                .query(VERSION_PARAMETER, "")
                .response().asNode();
        assertThat(node, is(missingVersionParameterValueException()));
    }

    @Test
    @Override
    public void invalidVersionParameter() {
        Node node = kvp()
                .query(REQUEST_PARAMETER, getRequest())
                .query(SERVICE_PARAMETER, SERVICE)
                .query(VERSION_PARAMETER, "1.2.3")
                .response().asNode();
        assertThat(node, is(invalidVersionParameterValueException("1.2.3")));
    }
}
