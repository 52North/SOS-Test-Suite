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

import static java.util.Collections.unmodifiableSet;
import static org.n52.sos.service.it.util.CollectionHelper.set;

import java.util.Set;

/**
 * SosConstants holds all important and often used constants of this SOS (e.g. name of the getCapabilities operation)
 * that are global between all supported versions
 *
 */
public interface SosConstants {

    String NS_SOS_PREFIX = "sos";
    /**
     * Constant for the content type of the response
     */
    String CONTENT_TYPE_XML = "text/xml";
    /**
     * Constant for the content type of the response
     */
    String CONTENT_TYPE_ZIP = "application/zip";
    /**
     * Constant for the content types of the accept formats
     */
    Set<String> ACCEPT_FORMATS = unmodifiableSet(set(CONTENT_TYPE_XML, CONTENT_TYPE_ZIP));
    String PROCEDURE_STANDARD_DESC_URL = "standardURL";
    /**
     * Constant for the service name of the SOS
     */
    String SOS = "SOS";
    /**
     * String representing parameter value, if parameter is not set in an operation request
     */
    @Deprecated
    String PARAMETER_NOT_SET = "NOT_SET";
    /**
     * String representing parameter value, if parameter is any in an operation request
     */
    String PARAMETER_ANY = "ANY";
    /**
     * String representing parameter value, if parameter is no values in an operation request
     */
    String PARAMETER_NO_VALUES = "NoValues";
    String NOT_DEFINED = "NOT_DEFINED";
    /**
     * request timeout in ms for split requests to SOS instances
     */
    long UPDATE_TIMEOUT = 10000;
    /**
     * Constant for actual implementing version Measurement
     */
    String OBS_ID_PREFIX = "o_";
    /**
     * Constant for actual implementing version OvservationCollection
     */
    String OBS_GENERIC_ID_PREFIX = "go_";
    /**
     * Constant for actual implementing version OvservationCollection
     */
    String OBS_COL_ID_PREFIX = "oc_";
    /**
     * Constant for actual implementing version ObservationTemplate
     */
    String OBS_TEMP_ID_PREFIX = "ot_";
    /**
     * Constant 'out-of-bands' for response mode, which means that the results in an observation response appear
     * external to the observation element
     */
    String RESPONSE_MODE_OUT_OF_BANDS = "out-of-bands";
    /**
     * Constant 'resultTemplate' for response mode, which means that the result is an ObservationTemplate for subsequent
     * calls to GetResult operation
     */
    String RESPONSE_RESULT_TEMPLATE = "resultTemplate";
    /**
     * Constant 'inline' for response mode, which means that results are contained inline the Observation elements of an
     * observation response document
     */
    String RESPONSE_MODE_INLINE = "inline";
    /**
     * Constant 'attached' for response mode, which means that result values of an observation response are attached as
     * MIME attachments
     */
    String RESPONSE_MODE_ATTACHED = "attached";
    /**
     * Array of constants for response mode.
     */
    Set<String> RESPONSE_MODES = unmodifiableSet(set(RESPONSE_MODE_INLINE, RESPONSE_RESULT_TEMPLATE));
    String HTTP_GET = "GET";
    String HTTP_POST = "POST";
    String HTTP_PUT = "PUT";
    String HTTP_DELETE = "DELETE";
    String MIN_VALUE = "MinValue";
    String MAX_VALUE = "MaxValue";
    String ALL_RELATED_FEATURES = "allFeatures";
    String SEPARATOR_4_REL_FEAT = "_._";
    String SEPARATOR_4_OFFERINGS = "_._";
    String SOAP_REASON_RESPONSE_EXCEEDS_SIZE_LIMIT =
                               "The requested result set exceeds the response size limit of this service and thus cannot be delivered.";
    String SOAP_REASON_INVALID_PROPERTY_OFFERING_COMBINATION =
                               "Observations for the requested combination of observedProperty and offering do not use SWE Common encoded results.";
    String GENERATED_IDENTIFIER_PREFIX = "generated_";

    /**
     * the names of the operations supported by all versions of the SOS specification
     */
    enum Operations {
        GetCapabilities,
        GetObservation,
        GetObservationById,
        DescribeSensor,
        InsertObservation,
        GetResult,
        GetFeatureOfInterest;
    }

    enum Filter {
        ValueReference;
    }

    /**
     * enum with names of Capabilities sections supported by all versions
     */
    enum CapabilitiesSections {
        ServiceIdentification,
        ServiceProvider,
        OperationsMetadata,
        Contents,
        All;
    }

    /**
     * enum with parameter names for getCapabilities request
     */
    enum GetCapabilitiesParams {
        Sections,
        AcceptVersions,
        updateSequence,
        AcceptFormats,
        service,
        request,
        Section;
    }

    /**
     * enum with parameter names for getObservation request supported by all versions
     */
    enum GetObservationParams {
        srsName,
        resultType,
        startPosition,
        maxRecords,
        offering,
        procedure,
        observedProperty,
        featureOfInterest,
        result,
        responseFormat,
        resultModel,
        responseMode,
        SortBy,
        BBOX;
    }

    /**
     * enum with parameter names for getObservation request supported by all versions
     */
    enum DescribeSensorParams {
        procedure;
    }

    enum IndeterminateTime {
        first,
        latest,
        now;

        public static boolean contains(final String timeString) {
            return timeString.equalsIgnoreCase(first.name())
                   || timeString.equalsIgnoreCase(latest.name())
                   || timeString.equalsIgnoreCase(now.name());
        }

        public static IndeterminateTime getEnumForString(final String value) {
            if (value.equalsIgnoreCase(first.name())) {
                return first;
            } else if (value.equalsIgnoreCase(latest.name())) {
                return latest;
            } else if (value.equalsIgnoreCase(now.name())) {
                return now;
            }
            return null;
        }
    }

    // TODO add javadoc for each value
    enum HelperValues {
        GMLID,
        EXIST_FOI_IN_DOC,
        VERSION,
        TYPE,
        /**
         * Encode the given 'object to encode' in a <tt>*Document</tt> object and not <tt>*Type</tt>.
         */
        DOCUMENT,
        FOR_OBSERVATION,
        ENCODE,
        ENCODE_NAMESPACE,
        /**
         * Encode the given <tt>OwsExceptionReport</tt> not into an <tt>ows:ExceptionReport</tt> but one
         * <tt>ows:Exception</tt>.
         */
        ENCODE_OWS_EXCEPTION_ONLY
    }
}