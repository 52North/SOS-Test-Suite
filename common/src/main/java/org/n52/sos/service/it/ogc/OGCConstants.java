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

/**
 * Constants for OGC
 * 
 */
public interface OGCConstants {

    String NS_OGC = "http://www.opengis.net/ogc";

    String NS_OGC_PREFIX = "ogc";

    String SCHEMA_LOCATION_OGC = "http://schemas.opengis.net/sos/1.0.0/ogc4sos.xsd";

    String UNKNOWN = "http://www.opengis.net/def/nil/OGC/0/unknown";

	/** Constant for prefixes of FOIs */
	String URN_FOI_PREFIX = "urn:ogc:def:object:feature:";

	String URN_IDENTIFIER_IDENTIFICATION = "urn:ogc:def:identifier:OGC::identification";

	String URN_OFFERING_ID = "urn:ogc:def:identifier:OGC:offeringID";

	/** Constant for prefixes of procedures */
	String URN_PHENOMENON_PREFIX = "urn:ogc:def:phenomenon:OGC:1.0.30:";

	/** Constant for prefixes of procedures */
	String URN_PROCEDURE_PREFIX = "urn:ogc:object:feature:Sensor:IFGI:";

	String URN_PROPERTY_NAME_LOCATION = "urn:ogc:data:location";

	String URN_PROPERTY_NAME_SAMPLING_GEOMETRY = "urn:ogc:data:samplingGeometry";

	String URN_PROPERTY_NAME_SPATIAL_VALUE = "urn:ogc:data:spatialValue";

	String URN_UNIQUE_IDENTIFIER = "urn:ogc:def:identifier:OGC:uniqueID";

	String URN_UNIQUE_IDENTIFIER_END = "uniqueID";

	String URN_UNIQUE_IDENTIFIER_START = "urn:ogc:def:identifier:OGC:";

	String URN_OBSERVED_BBOX = "urn:ogc:def:property:OGC:1.0:observedBBOX";
}
