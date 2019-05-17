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
package org.n52.sos.service.it.v2.soap;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import net.opengis.om.x20.OMObservationType;
import net.opengis.sos.x20.InsertResultTemplateDocument;
import net.opengis.sos.x20.InsertResultTemplateType;
import net.opengis.sos.x20.InsertResultTemplateType.ProposedTemplate;
import net.opengis.sos.x20.ResultTemplateType;
import net.opengis.swe.x20.DataRecordDocument;
import net.opengis.swe.x20.DataRecordType.Field;
import net.opengis.swe.x20.TextEncodingDocument;
import net.opengis.swe.x20.TextEncodingType;
import net.opengis.swe.x20.TextType;

/**
 * Test for SOAP SOS 2.0 InsertResultTemplate request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class InsertResultTemplateTest extends AbstractSosV2SoapTest {
    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument =
                getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument
                .getInsertResultTemplate());
        missingServiceParameter(insertResultTemplateDocument
                .getInsertResultTemplate(), insertResultTemplateDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument =
                getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument
                .getInsertResultTemplate());
        emptyServiceParameter(insertResultTemplateDocument
                .getInsertResultTemplate(), insertResultTemplateDocument);
    }

    @Test
    @Override
    public void invalidServiceParameter() throws XmlException {
        InsertResultTemplateDocument insertResultTemplateDocument =
                getMinimalRequest();
        addVersionParameter(insertResultTemplateDocument
                .getInsertResultTemplate());
        invalidServiceParameter(insertResultTemplateDocument
                .getInsertResultTemplate(), insertResultTemplateDocument);
    }

    protected TextEncodingDocument createTextEncoding() {
        TextEncodingDocument textEncodingDocument = TextEncodingDocument.Factory
                .newInstance();
        TextEncodingType textEncodingType = textEncodingDocument
                .addNewTextEncoding();
        textEncodingType.setTokenSeparator(",");
        textEncodingType.setBlockSeparator(";");
        return textEncodingDocument;
    }

    protected InsertResultTemplateDocument getMinimalRequest() {
        InsertResultTemplateDocument insertResultTemplateDocument =
                InsertResultTemplateDocument.Factory.newInstance();
        InsertResultTemplateType insertResultTemplateType =
                insertResultTemplateDocument.addNewInsertResultTemplate();
        ProposedTemplate proposedTemplate = insertResultTemplateType
                .addNewProposedTemplate();
        ResultTemplateType resultTemplate = proposedTemplate
                .addNewResultTemplate();
        resultTemplate.setOffering("offering");
        OMObservationType observationType = resultTemplate
                .addNewObservationTemplate().addNewOMObservation();
        observationType.setId("id");
        observationType.addNewPhenomenonTime().setNilReason("template");
        observationType.addNewResultTime().setHref("#");
        observationType.addNewProcedure();
        observationType.addNewObservedProperty();
        observationType.addNewFeatureOfInterest().setHref("#");
        observationType.addNewResult();
        TextEncodingDocument textEncodingDocument = createTextEncoding();
        resultTemplate.addNewResultEncoding().set(textEncodingDocument);
        DataRecordDocument dataRecordDocument = DataRecordDocument.Factory
                .newInstance();
        Field field = dataRecordDocument.addNewDataRecord().addNewField();
        field.setName("field");
        TextType text = (TextType)field.addNewAbstractDataComponent().substitute(new QName("http://www.opengis.net/swe/2.0", "Text", "swe"), TextType.type);
        text.setDefinition("text-definition");
        resultTemplate.addNewResultStructure().set(dataRecordDocument);
        return insertResultTemplateDocument;
    }
}
