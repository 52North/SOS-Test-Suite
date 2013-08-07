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
package org.n52.sos.service.it.ogc;

import javax.xml.namespace.QName;

import org.n52.sos.service.it.w3c.SchemaLocation;

/**
 * Constants class for SensorML
 * 
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author ShaneStClair
 * 
 * @since 4.0.0
 */
public interface SensorMLConstants {

    // namespaces and schema locations
    String NS_SML = "http://www.opengis.net/sensorML/1.0.1";

    String NS_SML_PREFIX = "sml";

    String SCHEMA_LOCATION_URL_SML_101 = "http://schemas.opengis.net/sensorML/1.0.1/sensorML.xsd";
    
    SchemaLocation SML_101_SCHEMA_LOCATION = new SchemaLocation(NS_SML_PREFIX, SCHEMA_LOCATION_URL_SML_101);

    String SENSORML_OUTPUT_FORMAT_MIME_TYPE = "text/xml;subtype=\"sensorML/1.0.1\"";

    String SENSORML_OUTPUT_FORMAT_URL = NS_SML;

    String SENSORML_CONTENT_TYPE = "text/xml;subtype=\"sensorML/1.0.1\"";

    String EN_SYSTEM = "System";

    String EN_PROCESS_MODEL = "ProcessModel";
    
    String EN_COMPONENT = "Component";

    String EN_ABSTRACT_PROCESS = "AbstractProcess";
    
    QName SYSTEM_QNAME = new QName(NS_SML, EN_SYSTEM, NS_SML_PREFIX);

    QName PROCESS_MODEL_QNAME = new QName(NS_SML, EN_PROCESS_MODEL, NS_SML_PREFIX);
    
    QName COMPONENT_QNAME = new QName(NS_SML, EN_COMPONENT, NS_SML_PREFIX);

    QName ABSTRACT_PROCESS_QNAME = new QName(NS_SML, EN_ABSTRACT_PROCESS, NS_SML_PREFIX);

    String VERSION_V101 = "1.0.1";

	/**
	 * Name of a SensorML element describing the offerings a procedure/sensor is related to or should be inserted into
	 */
    String ELEMENT_NAME_OFFERINGS = "offerings";

    // FIXME use a proper URI/URN for this, e.g. from settings
    String OFFERING_FIELD_DEFINITION = "http://www.opengis.net/def/offering/identifier";
    
    /**
     * name of System capabilities containing parent procedures
     */
    String ELEMENT_NAME_PARENT_PROCEDURES = "parentProcedures";
    
    // FIXME use a proper URI/URN for this, e.g. from settings
    String PARENT_PROCEDURE_FIELD_DEFINITION = "http://www.opengis.net/def/procedure/identifier";
    
    String PARENT_PROCEDURE_FIELD_NAME = "parentProcedureID";
        
    /**
     * name of System components containing child procedures
     */
    String ELEMENT_NAME_CHILD_PROCEDURES = "childProcedure";
    
    /**
     * name of System capabilities containing featureOfInterest
     */
    String ELEMENT_NAME_FEATURES_OF_INTEREST = "featuresOfInterest";
    
    // FIXME use a proper URI/URN for this, e.g. from settings
    String FEATURE_OF_INTEREST_FIELD_DEFINITION = "http://www.opengis.net/def/featureOfInterest/identifier";

    String FEATURE_OF_INTEREST_FIELD_NAME = "featureOfInterestID";

	String ELEMENT_NAME_SHORT_NAME = "shortName";
	
	String ELEMENT_NAME_LONG_NAME = "longName";
	
	String ELEMENT_NAME_OBSERVED_BBOX = "observedBBOX";
}
