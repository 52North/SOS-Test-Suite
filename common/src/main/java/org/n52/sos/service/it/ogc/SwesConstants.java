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
package org.n52.sos.service.it.ogc;

import javax.xml.namespace.QName;
//import org.n52.sos.ogc.swe.SWEConstants;
import org.n52.sos.service.it.w3c.SchemaLocation;

public interface SwesConstants {

    String EN_SOS_INSERTION_METADATA = "SosInsertionMetadata";
    
    String NS_SWES_20 = "http://www.opengis.net/swes/2.0";

    String NS_SWES_PREFIX = "swes";

    String SCHEMA_LOCATION_URL_SWES_20 = "http://schemas.opengis.net/swes/2.0/swes.xsd";

    String SCHEMA_LOCATION_URL_SWES_20_DESCRIBE_SENSOR = "http://schemas.opengis.net/swes/2.0/swesDescribeSensor.xsd";

    String SCHEMA_LOCATION_URL_SWES_20_DELETE_SENSOR = "http://schemas.opengis.net/swes/2.0/swesDeleteSensor.xsd";

    String SCHEMA_LOCATION_URL_SWES_20_INSERT_SENSOR = "http://schemas.opengis.net/swes/2.0/swesInsertSensor.xsd";

    String SCHEMA_LOCATION_URL_SWES_20_UPDATE_SENSOR_DESCRIPTION = "http://schemas.opengis.net/swes/2.0/swesUpdateSensorDescription.xsd";

    SchemaLocation SWES_20_SCHEMA_LOCATION = new SchemaLocation(NS_SWES_20, SCHEMA_LOCATION_URL_SWES_20);

    SchemaLocation SWES_20_DESCRIBE_SENSOR_SCHEMA_LOCATION = new SchemaLocation(NS_SWES_20, SCHEMA_LOCATION_URL_SWES_20_DESCRIBE_SENSOR);

    SchemaLocation SWES_20_INSERT_SENSOR_SCHEMA_LOCATION = new SchemaLocation(NS_SWES_20, SCHEMA_LOCATION_URL_SWES_20_INSERT_SENSOR);

    SchemaLocation SWES_20_UPDATE_SENSOR_DESCRIPTION_SCHEMA_LOCATION = new SchemaLocation(NS_SWES_20, SCHEMA_LOCATION_URL_SWES_20_UPDATE_SENSOR_DESCRIPTION);

    SchemaLocation SWES_20_DELETE_SENSOR_SCHEMA_LOCATION = new SchemaLocation(NS_SWES_20, SCHEMA_LOCATION_URL_SWES_20_DELETE_SENSOR);

    // element names
    String EN_ABSTRACT_OFFERING = "AbstractOffering";

    String EN_DELETE_SENSOR_RESPONSE = "DeleteSensorResponse";

    String EN_DESCRIBE_SENSOR = "DescribeSensor";

    String EN_DELETE_SENSOR = "DeleteSensor";

    String EN_DESCRIBE_SENSOR_RESPONSE = "DescribeSensorResponse";

    String EN_INSERT_SENSOR = "InsertSensor";

    String EN_INSERT_SENSOR_RESPONSE = "InsertSensorResponse";

    String EN_INSERTION_METADATA = "InsertionMetadata";

    String EN_METADATA = "metadata";

    String EN_OFFERING = "offering";

    String EN_UPDATE_SENSOR_DESCRIPTION = "UpdateSensorDescription";

    String EN_UPDATE_SENSOR_DESCRIPTION_RESPONSE = "UpdateSensorDescriptionResponse";

    QName QN_INSERTION_METADATA = new QName(SwesConstants.NS_SWES_20, SwesConstants.EN_INSERTION_METADATA, SwesConstants.NS_SWES_PREFIX);

    // QNames for elements
    QName QN_ABSTRACT_OFFERING = new QName(NS_SWES_20, EN_ABSTRACT_OFFERING, NS_SWES_PREFIX);

    QName QN_DELETE_SENSOR = new QName(NS_SWES_20, EN_DELETE_SENSOR, NS_SWES_PREFIX);

    QName QN_DELETE_SENSOR_RESPONSE = new QName(NS_SWES_20, EN_DELETE_SENSOR_RESPONSE, NS_SWES_PREFIX);

    QName QN_DESCRIBE_SENSOR = new QName(NS_SWES_20, EN_DESCRIBE_SENSOR, NS_SWES_PREFIX);

    QName QN_DESCRIBE_SENSOR_RESPONSE = new QName(NS_SWES_20, EN_DESCRIBE_SENSOR_RESPONSE, NS_SWES_PREFIX);

    QName QN_INSERT_SENSOR = new QName(NS_SWES_20, EN_INSERT_SENSOR, NS_SWES_PREFIX);

    QName QN_INSERT_SENSOR_RESPONSE = new QName(NS_SWES_20, EN_INSERT_SENSOR_RESPONSE, NS_SWES_PREFIX);

    QName QN_METADATA = new QName(NS_SWES_20, EN_METADATA, NS_SWES_PREFIX);

    QName QN_OFFERING = new QName(NS_SWES_20, EN_OFFERING, NS_SWES_PREFIX);

    QName QN_UPDATE_SENSOR_DESCRIPTION = new QName(NS_SWES_20, EN_UPDATE_SENSOR_DESCRIPTION, NS_SWES_PREFIX);

    QName QN_UPDATE_SENSOR_DESCRIPTION_RESPONSE = new QName(NS_SWES_20, EN_UPDATE_SENSOR_DESCRIPTION_RESPONSE, NS_SWES_PREFIX);

    String SOAP_REASON_INVALID_REQUEST = "The request did not conform to its XML Schema definition.";

    String SOAP_REASON_REQUEST_EXTENSION_NOT_SUPPORTED = ""; //FIXME emtpy constant

}
