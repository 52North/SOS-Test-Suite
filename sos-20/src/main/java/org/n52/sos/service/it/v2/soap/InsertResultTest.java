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

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlString;
import org.junit.Test;

import net.opengis.sos.x20.InsertResultDocument;
import net.opengis.sos.x20.InsertResultType;

/**
 * Test for SOAP SOS 2.0 InsertResult request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
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
    @Override
    public void invalidServiceParameter() throws XmlException {
        InsertResultDocument insertResultDocument = getRequest("template");
        addVersionParameter(insertResultDocument.getInsertResult());
        invalidServiceParameter(insertResultDocument.getInsertResult(), insertResultDocument);

    }

    public InsertResultDocument getRequest(String template) {
        InsertResultDocument insertResultDocument = InsertResultDocument.Factory
                .newInstance();
        InsertResultType insertResultType = insertResultDocument
                .addNewInsertResult();
        insertResultType.setTemplate(template);
        insertResultType.addNewResultValues().set(XmlString.Factory
                .newValue("values"));
        return insertResultDocument;
    }
}
