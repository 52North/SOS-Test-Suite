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

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.n52.sos.service.it.Response;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public class ServiceEndpointTest extends RestBindingTest {
    @Test
    public void should_redirect_to_capabilities_resource_with_status_and_location_header() {
        final Response response = get(REST_URL)
                .accept(CONTENT_TYPE).response();
        final Response response2 = get(REST_URL + "/")
                .accept(CONTENT_TYPE).response();

        getErrors().checkThat(response.getStatus(),
                              is(HttpServletResponse.SC_SEE_OTHER));
        getErrors().checkThat(response.getHeader("Location"),
                              endsWith(REST_URL + "/capabilities"));
        getErrors().checkThat(response2.getStatus(),
                              is(HttpServletResponse.SC_SEE_OTHER));
        getErrors().checkThat(response2.getHeader("Location"),
                              endsWith(REST_URL + "/capabilities"));
    }
}
