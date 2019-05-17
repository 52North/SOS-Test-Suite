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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletResponse;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public class MockHttpResponse extends MockHttpServletResponse implements Response {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    @Override
    public InputStream asInputStream() {
        byte[] response = getContentAsByteArray();
        Assert.assertThat(getStatus(), Matchers.is(Matchers.not(503)));
        Assert.assertThat(response, Matchers.is(Matchers.not(Matchers.nullValue())));
        Assert.assertThat(response.length, Matchers.is(Matchers.not(0)));
        return new ByteArrayInputStream(response);
    }

    @Override
    public XmlObject asXmlObject() {
        try {
            return XmlObject.Factory.parse(getContentAsString());
        } catch (XmlException ex) {
            throw new AssertionError();
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError();
        }
    }

    @Override
    public Element asNode() {
        InputStream in = null;
        try {
            in = asInputStream();
            return this.factory.newDocumentBuilder().parse(in).getDocumentElement();
        } catch (ParserConfigurationException ex) {
            // FIXME message
            throw new AssertionError();
        } catch (SAXException ex) {
            // FIXME message
            throw new AssertionError();
        } catch (IOException ex) {
            // FIXME message
            throw new AssertionError();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }
}
