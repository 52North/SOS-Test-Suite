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

import static org.hamcrest.Matchers.*;

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
