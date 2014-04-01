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
package org.n52.sos.service.it.v2.rest;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;

import org.apache.xmlbeans.XmlException;
import org.junit.Ignore;
import org.junit.Test;
import org.n52.sos.service.it.Response;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public class OfferingsTest extends RestBindingTest {
    @Test
    public void should_return_status_ok() {
        final Response response = getOfferings();
        assertThat(response.getStatus(), is(SC_OK));
    }

    @Test
    public void should_return_correct_content_type() {
        final Response response = getOfferings();
        assertThat(response.getContentType(), is(CONTENT_TYPE));
    }

    @Test
    public void should_return_valid_sosRest_offeringslist() {
        final Node response = getOfferings().asNode();
        assertThat(response, allOf(
                hasXPath("//sosREST:OfferingCollection", NS_CTXT),
                hasXPath(selfLink(ResourceOfferings), NS_CTXT)));
    }

    @Test
    public void should_contain_self_link() {
        final Node response = getOfferings().asNode();
        assertThat(response, hasXPath(offeringsLink(
                ResourceRelationSelf, 
                ResourceOfferings), NS_CTXT));
    }

    @Test
    @Ignore("Init DB with testdata not possible atm")
    public void should_contain_all_offering_links() throws
            UnsupportedEncodingException, XmlException {
        final String sensorId = "test-sensor-id";
        final String offeringId = "test-offering-id";
        addSensor(sensorId, offeringId);
        // FIXME the offeringId is not contained in the cache after adding the sensor
        addMeasurement(sensorId, offeringId, System.currentTimeMillis(), 0.5, "test-feature", "test-observable-property");
        final Node xbResponse = getOfferings().asNode();

        assertThat(xbResponse, hasXPath("add count check here", NS_CTXT));
    }

    private String offeringsLink(final String relType,
                                 final String resType) {
        return "//sosREST:OfferingCollection/" + link(relType, resType);
    }

    private Response getOfferings() {
        return getResource(ResourceOfferings);
    }
}
