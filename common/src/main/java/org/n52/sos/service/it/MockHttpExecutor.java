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
package org.n52.sos.service.it;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.junit.rules.ExternalResource;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 */
public abstract class MockHttpExecutor
        extends ExternalResource
        implements RequestExecutor {
    public static final String ENCODING = "UTF-8";
    private final ServletFactory servletFactory;
    private final ServletContext servletContext =
            new MockServletContext();
    private final ServletConfig servletConfig =
            new MockServletConfig(servletContext);
    private HttpServlet servlet;

    public MockHttpExecutor(ServletFactory servletFactory) {
        this.servletFactory = servletFactory;
    }

    public MockHttpExecutor(final Class<? extends HttpServlet> klass) {
        this.servletFactory = new ServletFactory() {
            @Override
            public HttpServlet create() {
                try {
                    return klass.newInstance();
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

    }

    @Override
    public void before() throws Throwable {
        servlet = servletFactory.create();
        servlet.init(servletConfig);
    }

    @Override
    public void after() {
        servlet.destroy();
        servlet = null;
    }

    @Override
    public Client get(String path) {
        return new MockHttpClient(servlet, "GET", path);
    }

    @Override
    public Client post(String path) {
        return new MockHttpClient(servlet, "POST", path);
    }

    @Override
    public Client put(String path) {
        return new MockHttpClient(servlet, "PUT", path);
    }

    @Override
    public Client delete(String path) {
        return new MockHttpClient(servlet, "DELETE", path);
    }

    @Override
    public Client options(String path) {
        return new MockHttpClient(servlet, "OPTIONS", path);
    }

    @Override
    public Client head(String path) {
        return new MockHttpClient(servlet, "HEAD", path);
    }
}
