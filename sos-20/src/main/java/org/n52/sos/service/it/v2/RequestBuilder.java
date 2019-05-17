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
package org.n52.sos.service.it.v2;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.n52.shetland.ogc.om.OmConstants;
import org.n52.shetland.ogc.sensorML.SensorMLConstants;

import net.opengis.gml.x32.TimeInstantDocument;
import net.opengis.gml.x32.TimeInstantType;
import net.opengis.om.x20.OMObservationType;
import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML;
import net.opengis.sensorML.x101.SystemDocument;
import net.opengis.sos.x20.InsertObservationDocument;
import net.opengis.sos.x20.InsertObservationType;
import net.opengis.swes.x20.InsertSensorDocument;
import net.opengis.swes.x20.InsertSensorType;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public class RequestBuilder {
    private RequestBuilder() {
    }

    public static InsertSensorDocument getInsertSensorMinimalDocument() {
        final InsertSensorDocument insertSensorDocument =
                InsertSensorDocument.Factory.newInstance();
        final InsertSensorType insertSensorType = insertSensorDocument
                .addNewInsertSensor();
        insertSensorType
                .setProcedureDescriptionFormat(SensorMLConstants.SENSORML_OUTPUT_FORMAT_URL);
        final SensorMLDocument sensorMLDocument = SensorMLDocument.Factory
                .newInstance();
        final SensorML sensorML = sensorMLDocument.addNewSensorML();
        sensorML.setVersion(SensorMLConstants.VERSION_V101);
        final SystemDocument systemDocument = SystemDocument.Factory
                .newInstance();
        systemDocument.addNewSystem();
        sensorML.addNewMember().set(systemDocument);
        insertSensorType.addNewProcedureDescription().set(sensorMLDocument);
        insertSensorType.addNewObservableProperty();
        return insertSensorDocument;
    }

    public static InsertObservationDocument getInsertObservationMinimalDocument() {
        final InsertObservationDocument insertObservationDocument =
                InsertObservationDocument.Factory.newInstance();
        final InsertObservationType insertObservationType =
                insertObservationDocument.addNewInsertObservation();
        insertObservationType.addOffering("offering");
        final OMObservationType observation = insertObservationType
                .addNewObservation().addNewOMObservation();
        observation.setId("id");
        final TimeInstantDocument timeInstantDocument =
                TimeInstantDocument.Factory.newInstance();
        final TimeInstantType timeInstantType = timeInstantDocument
                .addNewTimeInstant();
        timeInstantType.setId("phenomenonTime");
        timeInstantType.addNewTimePosition()
                .setObjectValue("2000-01-01T00:00:00Z");
        observation.addNewPhenomenonTime().set(timeInstantDocument);
        observation.addNewResultTime().setHref("#phenomenonTime");
        observation.addNewProcedure();
        observation.addNewObservedProperty();
        observation.addNewFeatureOfInterest().setHref("featureOfInterest");
        XmlObject result = observation.addNewResult();
        result = result
                .substitute(new QName(OmConstants.NS_OM_2, OmConstants.EN_RESULT), XmlString.type);
        result.set(XmlString.Factory.newValue("value"));
        return insertObservationDocument;
    }
}
