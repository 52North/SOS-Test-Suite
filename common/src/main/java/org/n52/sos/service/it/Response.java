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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletResponse;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class Response extends MockHttpServletResponse {
    private final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();

    public InputStream asInputStream() {
        byte[] response = getContentAsByteArray();
        Assert.assertThat(getStatus(), is(not(503)));
        Assert.assertThat(response, is(not(nullValue())));
        Assert.assertThat(response.length, is(not(0)));
        return new ByteArrayInputStream(response);
    }

    public XmlObject asXmlObject() throws XmlException,
                                          UnsupportedEncodingException {
        return XmlObject.Factory.parse(getContentAsString());
    }

    public Element asNode() {
        InputStream in = null;
        try {
            in = asInputStream();
            return this.factory.newDocumentBuilder()
                    .parse(in).getDocumentElement();
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
                }
            }
        }
    }
}
