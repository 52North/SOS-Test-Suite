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

package org.n52.sos.service.it.v2.soap;

import net.opengis.sos.x20.InsertResultDocument;
import net.opengis.sos.x20.InsertResultType;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlString;
import org.junit.Test;
import org.n52.sos.ogc.sos.Sos2Constants;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Test for SOAP SOS 2.0 InsertResult request.
 * 
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 * @since 4.0.0
 */
public class InsertResultTest extends AbstractSosV2SoapTest {

    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        InsertResultDocument insertResultDocument = getRequest("template");
        addVersionParameter(insertResultDocument.getInsertResult());
        missingServiceParameter(insertResultDocument.getInsertResult(), insertResultDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        InsertResultDocument insertResultDocument = getRequest("template");
        addVersionParameter(insertResultDocument.getInsertResult());
        emptyServiceParameter(insertResultDocument.getInsertResult(), insertResultDocument);
    }

    @Test
    public void invalidServiceParameter() throws XmlException {
        InsertResultDocument insertResultDocument = getRequest("template");
        addVersionParameter(insertResultDocument.getInsertResult());
        invalidServiceParameter(insertResultDocument.getInsertResult(), insertResultDocument);

    }

    public InsertResultDocument getRequest(String template) {
        InsertResultDocument insertResultDocument = InsertResultDocument.Factory.newInstance();
        InsertResultType insertResultType = insertResultDocument.addNewInsertResult();
        insertResultType.setTemplate(template);
        insertResultType.addNewResultValues().set(XmlString.Factory.newValue("values"));
        return insertResultDocument;
    }

}
