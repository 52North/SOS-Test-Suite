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
package org.n52.sos.service.it.util;

import javax.xml.namespace.NamespaceContext;

import org.hamcrest.Matcher;
import org.hamcrest.xml.HasXPath;
import org.w3c.dom.Node;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class XPath {
    private final String path;
    private final NamespaceContext ctx;

    public XPath(String path, NamespaceContext ctx) {
        this.path = path;
        this.ctx = ctx;
    }

    public String getPath() {
        return path;
    }

    public NamespaceContext getContext() {
        return ctx;
    }

    public Matcher<Node> matcher(Matcher<String> m) {
        return HasXPath.hasXPath(getPath(), getContext(), m);
    }

    public Matcher<Node> matcher() {
        return HasXPath.hasXPath(getPath(), getContext());
    }

    public static Matcher<Node> hasXPath(XPath path, Matcher<String> m) {
        return path.matcher(m);
    }

    public static Matcher<Node> hasXPath(XPath path) {
        return path.matcher();
    }
}
