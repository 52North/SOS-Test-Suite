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

import net.opengis.om.x20.OMObservationType;
import net.opengis.sos.x20.InsertResultTemplateDocument;
import net.opengis.sos.x20.InsertResultTemplateType;
import net.opengis.sos.x20.InsertResultTemplateType.ProposedTemplate;
import net.opengis.sos.x20.ResultTemplateType;
import net.opengis.swe.x20.DataRecordDocument;
import net.opengis.swe.x20.TextEncodingDocument;
import net.opengis.swe.x20.TextEncodingType;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;
import org.n52.sos.ogc.sos.Sos2Constants;
import org.n52.sos.ogc.sos.SosConstants;
import org.n52.sos.util.XmlOptionsHelper;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Test for SOAP SOS 2.0 InsertResultTemplate request.
 * 
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 * @since 4.0.0
 */
public class InsertResultTemplateTest extends AbstractSosV2SoapTest {

    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument = getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument.getInsertResultTemplate());
        missingServiceParameter(insertResultTemplateDocument.getInsertResultTemplate(), insertResultTemplateDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument = getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument.getInsertResultTemplate());
        emptyServiceParameter(insertResultTemplateDocument.getInsertResultTemplate(), insertResultTemplateDocument);
    }

    @Test
    public void invalidServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument = getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument.getInsertResultTemplate());
        invalidServiceParameter(insertResultTemplateDocument.getInsertResultTemplate(), insertResultTemplateDocument);
    }

    protected TextEncodingDocument createTextEncoding() {
        TextEncodingDocument textEncodingDocument = TextEncodingDocument.Factory.newInstance();
        TextEncodingType textEncodingType = textEncodingDocument.addNewTextEncoding();
        textEncodingType.setTokenSeparator(",");
        textEncodingType.setBlockSeparator(";");
        return textEncodingDocument;
    }

    protected InsertResultTemplateDocument getMinimalRequest() {
        InsertResultTemplateDocument insertResultTemplateDocument = InsertResultTemplateDocument.Factory.newInstance();
        InsertResultTemplateType insertResultTemplateType = insertResultTemplateDocument.addNewInsertResultTemplate();
        ProposedTemplate proposedTemplate = insertResultTemplateType.addNewProposedTemplate();
        ResultTemplateType resultTemplate = proposedTemplate.addNewResultTemplate();
        resultTemplate.setOffering("offering");
        OMObservationType observationType = resultTemplate.addNewObservationTemplate().addNewOMObservation();
        observationType.setId("id");
        observationType.addNewPhenomenonTime().setNilReason("template");
        observationType.addNewResultTime().setHref("#");
        observationType.addNewProcedure();
        observationType.addNewObservedProperty();
        observationType.addNewFeatureOfInterest().setHref("#");
        observationType.addNewResult();
        TextEncodingDocument textEncodingDocument = createTextEncoding();
        resultTemplate.addNewResultEncoding().set(textEncodingDocument);
        DataRecordDocument dataRecordDocument = DataRecordDocument.Factory.newInstance();
        dataRecordDocument.addNewDataRecord().addNewField().setName("field");
        resultTemplate.addNewResultStructure().set(dataRecordDocument);
        return insertResultTemplateDocument;
    }
}
