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
import org.junit.Test;

import net.opengis.sos.x20.GetFeatureOfInterestDocument;

/**
 * Test for SOAP SOS 2.0 GetFeatureOfInterest request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetFeatureOfInterestTest extends AbstractSosV2SoapTest {

    @Override
    @Test
    public void missingServiceParameter() throws XmlException {
        GetFeatureOfInterestDocument getFeatureOfInterestDocument = getRequest();
        addVersionParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest());
        invalidServiceParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest(), getFeatureOfInterestDocument);
    }

    @Override
    @Test
    public void emptyServiceParameter() throws XmlException {
        GetFeatureOfInterestDocument getFeatureOfInterestDocument = getRequest();
        addVersionParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest());
        emptyServiceParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest(), getFeatureOfInterestDocument);
    }

    @Override
    @Test
    public void invalidServiceParameter() throws XmlException {
        GetFeatureOfInterestDocument getFeatureOfInterestDocument = getRequest();
        addVersionParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest());
        invalidServiceParameter(getFeatureOfInterestDocument.getGetFeatureOfInterest(), getFeatureOfInterestDocument);
    }

    protected GetFeatureOfInterestDocument getRequest() {
        GetFeatureOfInterestDocument getFeatureOfInterestDocument = GetFeatureOfInterestDocument.Factory.newInstance();
        getFeatureOfInterestDocument.addNewGetFeatureOfInterest();
        return getFeatureOfInterestDocument;
    }

}
