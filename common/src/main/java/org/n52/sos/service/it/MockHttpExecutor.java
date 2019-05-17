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
package org.n52.sos.service.it;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.junit.rules.ExternalResource;
import org.n52.iceland.service.Service;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
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

    public MockHttpExecutor(final Class<? extends Service> klass) {
        this.servletFactory = new ServletFactory() {
            @Override
            public Service create() {
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
