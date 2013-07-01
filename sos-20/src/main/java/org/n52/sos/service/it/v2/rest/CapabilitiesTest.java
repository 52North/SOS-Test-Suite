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
package org.n52.sos.service.it.v2.rest;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.n52.sos.service.it.Response;
import org.springframework.mock.web.MockHttpServletResponse;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public class CapabilitiesTest extends RestBindingTest {
    @Test
    public void should_return_status_okay() {
        final MockHttpServletResponse response = getCapabilities();
        assertThat(response.getStatus(), is(SC_OK));
    }

    @Test
    public void should_return_correct_content_type() {
        final MockHttpServletResponse response = getCapabilities();
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

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationSelf(), REST_CONFIG
                .getResourceCapabilities()), NS_CTXT));
    }

    @Test
    public void should_contain_offerings_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationOfferingsGet(), REST_CONFIG
                .getResourceOfferings()), NS_CTXT));
    }

    @Test
    public void should_contain_features_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationFeaturesGet(), REST_CONFIG
                .getResourceFeatures()), NS_CTXT));
    }

    @Test
    public void should_contain_sensor_create_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationSensorCreate(), REST_CONFIG
                .getResourceSensors()), NS_CTXT));
    }

    @Test
    public void should_contain_sensors_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationSensorsGet(), REST_CONFIG
                .getResourceSensors()), NS_CTXT));
    }

    @Test
    public void should_contain_observation_create_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationObservationCreate(), REST_CONFIG
                .getResourceObservations()), NS_CTXT));
    }

    @Test
    public void should_contain_observation_link() {
        final Node response = getCapabilities().asNode();

        assertThat(response, hasXPath(capabilitiesLink(REST_CONFIG
                .getResourceRelationObservationGet(), REST_CONFIG
                .getResourceObservations()), NS_CTXT));
    }

    private Response getCapabilities() {
        return getResource(REST_CONFIG.getResourceCapabilities());
    }

    private String capabilitiesLink(final String relType, final String resType) {
        return "//sosREST:Capabilities/" + link(relType, resType);
    }
}
