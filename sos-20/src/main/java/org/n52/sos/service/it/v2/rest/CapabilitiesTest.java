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
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.w3c.dom.Node;

import org.n52.sos.service.it.Response;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public class CapabilitiesTest extends RestBindingTest {
    @Test
    public void should_return_status_okay() {
        final Response response = getCapabilities();
        assertThat(response.getStatus(), is(SC_OK));
    }

    @Test
    public void should_return_correct_content_type() {
        final Response response = getCapabilities();
        assertThat(response.getContentType(), is(CONTENT_TYPE));
    }

    @Test
    public void should_return_valid_sosRest_capabilities() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath("//sosREST:Capabilities/sos:Capabilities", NS_CTXT));
    }

    @Test
    public void should_contain_owsServiceIdentification() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath("//sosREST:Capabilities/sos:Capabilities/ows:ServiceIdentification", NS_CTXT));
    }

    @Test
    public void should_contain_owsServiceProvider() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath("//sosREST:Capabilities/sos:Capabilities/ows:ServiceProvider", NS_CTXT));
    }

    @Test
    public void should_contain_sosfilterCapabilities() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath("//sosREST:Capabilities/sos:Capabilities/sos:filterCapabilities", NS_CTXT));
    }

    @Test
    public void should_contain_self_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationSelf,
                ResourceCapabilities), NS_CTXT));
    }

    @Test
    public void should_contain_offerings_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationOfferingsGet,
                ResourceOfferings), NS_CTXT));
    }

    @Test
    public void should_contain_features_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationFeaturesGet,
                ResourceFeatures), NS_CTXT));
    }

    @Test
    public void should_contain_sensor_create_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationSensorCreate,
                ResourceSensors), NS_CTXT));
    }

    @Test
    public void should_contain_sensors_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationSensorsGet,
                ResourceSensors), NS_CTXT));
    }

    @Test
    public void should_contain_observation_create_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationObservationCreate,
                ResourceObservations), NS_CTXT));
    }

    @Test
    public void should_contain_observation_link() {
        final Node response = getCapabilities().asNode();
        assertThat(response, hasXPath(capabilitiesLink(
                ResourceRelationObservationGet,
                ResourceObservations), NS_CTXT));
    }

    private Response getCapabilities() {
        return getResource(ResourceCapabilities);
    }

    private String capabilitiesLink(final String relType, final String resType) {
        return "//sosREST:Capabilities/" + link(relType, resType);
    }
}
