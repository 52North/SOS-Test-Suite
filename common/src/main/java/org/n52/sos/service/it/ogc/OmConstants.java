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

import static java.util.Collections.unmodifiableSet;
import static org.n52.sos.service.it.util.CollectionHelper.set;

import java.util.Set;

import javax.xml.namespace.QName;

import org.n52.sos.service.it.ogc.OGCConstants;
import org.n52.sos.service.it.w3c.SchemaLocation;

/**
 * Class contains element names and namespaces used to encode the O&M responses.
 *
 */
public interface OmConstants {
    String PARAMETER_NOT_SET = "PARAMETER_NOT_SET";
    // //////////////////////////////
    // namespaces and schema locations
    String NS_OM = "http://www.opengis.net/om/1.0";
    String NS_OM_2 = "http://www.opengis.net/om/2.0";
    String NS_OM_PREFIX = "om";
    String NS_GMD = "http://www.isotc211.org/2005/gmd";
    String NS_GMD_PREFIX = "gmd";
    String NS_WV = "http://www.n52.org/wv";
    String SCHEMA_LOCATION_URL_OM = "http://schemas.opengis.net/om/1.0.0/om.xsd";
    String SCHEMA_LOCATION_URL_OM_CONSTRAINT =
           "http://schemas.opengis.net/om/1.0.0/extensions/observationSpecialization_constraint.xsd";
    String SCHEMA_LOCATION_URL_OM_20 = "http://schemas.opengis.net/om/2.0/observation.xsd";
    String SCHEMA_LOCATION_URL_OM_20_OM_OBSERVATION = SCHEMA_LOCATION_URL_OM_20 + "#OM_Observation";
    SchemaLocation OM_100_SCHEMA_LOCATION = new SchemaLocation(NS_OM, SCHEMA_LOCATION_URL_OM);
    SchemaLocation OM_20_SCHEMA_LOCATION = new SchemaLocation(NS_OM_2, SCHEMA_LOCATION_URL_OM_20);
    // //////////////////////////////////////////////////////////////////////
    // other
    String AN_ID = "id";
    String CONTENT_TYPE_OM = "text/xml;subtype=\"om/1.0.0\"";
    String CONTENT_TYPE_OM_2 = "text/xml;subtype=\"om/2.0.0\"";
    String RESPONSE_FORMAT_OM = "http://www.opengis.net/om/1.0.0";
    String RESPONSE_FORMAT_OM_2 = "http://www.opengis.net/om/2.0";
    // ///////////////////////////////////////////////////////////////////
    // names of elements in O&M documents
    String EN_ASCII_BLOCK = "AsciiBlock";
    String EN_ABSTRACT_DATA_GROUP = "_DataGroup";
    String EN_ABSTRACT_DATA_QUALITY = "AbstractDQ_Element";
    String EN_BOUNDED_BY = "boundedBy";
    String EN_CATEGORY_OBSERVATION = "CategoryObservation";
    String EN_COUNT_OBSERVATION = "CountObservation";
    String EN_TEXT_OBSERVATION = "TextObservation";
    String EN_TRUTH_OBSERVATION = "TruthObservation";
    String EN_GEOMETRY_OBSERVATION = "GeometryObservation";
    String EN_COMMON_OBSERVATION = "CommonObservation";
    String EN_COMPOSITE_PHENOMENON = "CompositePhenomenon";
    String EN_DATA_GROUP = "DataGroup";
    String EN_DQ_QUAN_ATTR_ACC = "DQ_QuantitativeAttributeAccuracy";
    String EN_DQ_NON_QUAN_ATTR_ACC = "DQ_NonQuantitativeAttributeAccuracy";
    String EN_DQ_COMPL_COMM = "DQ_CompletenessCommission";
    String EN_DQ_COMPL_OM = "DQ_CompletenessOmission";
    String EN_FEATURE = "Feature";
    String EN_FEATURE_COLLECTION = "FeatureCollection";
    String EN_GEOREF_FEATURE = "GeoReferenceableFeature";
    String EN_MEMBER = "member";
    String EN_MEASUREMENT = "Measurement";
    String EN_OBSERVED_PROPERTY = "observedProperty";
    String EN_OBSERVATION_COLLECTION = "ObservationCollection";
    String EN_OBSERVATION = "Observation";
    String EN_PHENOMENON = "Phenomenon";
    String EN_COMPOSITE_SURFACE = "CompositeSurface";
    String EN_RESULT = "result";
    String EN_WV_STATION = "WVStation";
    String EN_TEMPORAL_OPS = "temporalOps";
    String EN_PROCEDURE = "procedure";
    String EN_PHENOMENON_TIME = "phenomenonTime";
    String EN_FEATURE_OF_INTEREST = "featureOfInterest";
    String EN_PROCESS = "Process";
    // /////////////////////////////////////////////////////////////////////////////////
    // other constants
    String PHEN_SAMPLING_TIME = "http://www.opengis.net/def/property/OGC/0/SamplingTime";
    String PHENOMENON_TIME = "http://www.opengis.net/def/property/OGC/0/PhenomenonTime";
    String PHENOMENON_TIME_NAME = "phenomenonTime";
    String SAMPLING_TIME_NAME = "samplingTime";
    String PHEN_UOM_ISO8601 = "http://www.opengis.net/def/uom/ISO-8601/0/Gregorian";
    String PHEN_FEATURE_OF_INTEREST =
           "http://www.opengis.net/def/property/OGC/0/FeatureOfInterest";
    @ Deprecated
    String EN_ABSTRACT_DATA_RECORD = "DataRecord";
    @Deprecated
    String EN_SIMPLE_DATA_RECORD = "SimpleDataRecord";
    String ATTR_SRS_NAME = "srsName";
    String PARAM_NAME_SAMPLING_GEOMETRY = "http://www.opengis.net/def/param-name/OGC-OM/2.0/samplingGeometry";
    // observation types
    String OBS_TYPE_MEASUREMENT = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
    String OBS_TYPE_CATEGORY_OBSERVATION =
           "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_CategoryObservation";
    String OBS_TYPE_COMPLEX_OBSERVATION = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_ComplexObservation";
    String OBS_TYPE_COUNT_OBSERVATION = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_CountObservation";
    String OBS_TYPE_GEOMETRY_OBSERVATION =
           "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_GeometryObservation";
    // no Definition in O&M and not in Lightweight Profile
    String OBS_TYPE_TEXT_OBSERVATION = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation";
    String OBS_TYPE_TRUTH_OBSERVATION = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TruthObservation";
    String OBS_TYPE_OBSERVATION = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Observation";
    String OBS_TYPE_UNKNOWN = OGCConstants.UNKNOWN;
    String OBS_TYPE_SWE_ARRAY_OBSERVATION =
           "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_SWEArrayObservation";
    String OBS_RESULT_TYPE_OBSERVATION = "http://www.opengis.net/sensorML/2.0/DataArray";
    String SAMPLING_FEAT_TYPE_UNKNOWN = "http://www.opengis.net/def/samplingFeatureType/unknown";
    // ////////////////////////////////////////////////////////
    // resultModel constants; not possible to use enum because of
    QName RESULT_MODEL_MEASUREMENT = new QName(NS_OM, EN_MEASUREMENT, NS_OM_PREFIX);
    QName RESULT_MODEL_GEOMETRY_OBSERVATION = new QName(NS_OM, EN_GEOMETRY_OBSERVATION, NS_OM_PREFIX);
    QName RESULT_MODEL_CATEGORY_OBSERVATION = new QName(NS_OM, EN_CATEGORY_OBSERVATION, NS_OM_PREFIX);
    QName RESULT_MODEL_OBSERVATION = new QName(NS_OM, EN_OBSERVATION, NS_OM_PREFIX);
    QName RESULT_MODEL_COUNT_OBSERVATION = new QName(NS_OM, EN_COUNT_OBSERVATION, NS_OM_PREFIX);
    QName RESULT_MODEL_TRUTH_OBSERVATION = new QName(NS_OM, EN_TRUTH_OBSERVATION, NS_OM_PREFIX);
    QName RESULT_MODEL_TEXT_OBSERVATION = new QName(NS_OM, EN_TEXT_OBSERVATION, NS_OM_PREFIX);
    /**
     * Array of constants for result models.
     */
    Set<QName> RESULT_MODELS = unmodifiableSet(set(RESULT_MODEL_OBSERVATION,
                                                   RESULT_MODEL_MEASUREMENT,
                                                   RESULT_MODEL_CATEGORY_OBSERVATION,
                                                   RESULT_MODEL_GEOMETRY_OBSERVATION));
}
