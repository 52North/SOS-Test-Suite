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
package org.n52.sos.service.it.soap;

import org.apache.xmlbeans.XmlObject;
import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Response;
import org.w3.x2003.x05.soapEnvelope.EnvelopeDocument;

/**
 * Abstract class for SOS SOAP requests tests
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 *
 * @since 4.0.0
 */
public abstract class AbstractSoapComplianceTest extends AbstractComplianceSuiteTest {
    public static final String APPLICATION_SOAP_XML = "application/soap+xml";

    protected XmlObject envelope(final XmlObject r) {
        final EnvelopeDocument envDoc = EnvelopeDocument.Factory.newInstance();
        envDoc.addNewEnvelope().addNewBody().set(r);
        return envDoc;
    }

    protected Response soap(XmlObject xml) {
        return getExecutor()
                .soap()
                .accept(APPLICATION_SOAP_XML)
                .contentType(APPLICATION_SOAP_XML)
                .entity(envelope(xml).xmlText())
                .asResponse();
    }
}
