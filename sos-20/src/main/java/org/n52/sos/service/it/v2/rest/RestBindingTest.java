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
package org.n52.sos.service.it.v2.rest;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import net.opengis.gml.x32.MeasureType;
import net.opengis.gml.x32.TimeInstantType;
import net.opengis.gml.x32.TimePeriodType;
import net.opengis.om.x20.OMObservationDocument;
import net.opengis.om.x20.OMObservationType;
import net.opengis.sensorML.x101.CapabilitiesDocument.Capabilities;
import net.opengis.sensorML.x101.IdentificationDocument.Identification;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList.Identifier;
import net.opengis.sensorML.x101.InputsDocument.Inputs;
import net.opengis.sensorML.x101.IoComponentPropertyType;
import net.opengis.sensorML.x101.OutputsDocument.Outputs;
import net.opengis.sensorML.x101.PositionDocument.Position;
import net.opengis.sensorML.x101.SystemType;
import net.opengis.sensorML.x101.TermDocument.Term;
import net.opengis.sosREST.x10.LinkType;
import net.opengis.sosREST.x10.ObservationDocument;
import net.opengis.sosREST.x10.ObservationType;
import net.opengis.sosREST.x10.SensorDocument;
import net.opengis.swe.x101.AnyScalarPropertyType;
import net.opengis.swe.x101.ObservablePropertyDocument.ObservableProperty;
import net.opengis.swe.x101.QuantityDocument.Quantity;
import net.opengis.swe.x101.SimpleDataRecordType;
import net.opengis.swe.x101.VectorType;
import net.opengis.swe.x101.VectorType.Coordinate;

import org.joda.time.DateTime;

import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Response;
import org.n52.sos.service.it.v2.SosNamespaceContext;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 *         J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public abstract class RestBindingTest extends AbstractComplianceSuiteTest implements RestTestConstants {
    protected static final String REST_URL = "/rest";

    protected static final String CONTENT_TYPE = "application/gml+xml";

    protected static final NamespaceContext NS_CTXT = new SosNamespaceContext();

    protected String link(final String relType, final String resTypeWithOrWithoutId) {
        return String.format("sosREST:link[@rel='%s' and contains(@href, '%s') and @type='%s']",
                             EncodingNamespace + "/"+ relType,
                             REST_URL + "/" + resTypeWithOrWithoutId,
                             CONTENT_TYPE);
    }

    protected Response getResource(final String resType) {
        return get(REST_URL + "/" + resType).accept(CONTENT_TYPE).response();
    }

    /**
     * Creating example sensor with id <tt>sensorId</tt> and offering
     * <tt>offeringId</tt> observing <tt>test-observable-property</tt> with type
     * <tt>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</tt>
     * with feature
     * <tt>http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint</tt>
     */
    protected String createRestSensor(final String sensorId, final String offeringId) {
        final SensorDocument restSensor = SensorDocument.Factory.newInstance();
        final SystemType system =
                (SystemType) restSensor
                        .addNewSensor()
                        .addNewProcess()
                        .substitute(new QName("http://www.opengis.net/sensorML/1.0.1", "System", "sml"),
                                SystemType.type);
        system.setIdentificationArray(createIdentifications(sensorId, offeringId));
        system.setInputs(createInputList("test-observable-property"));
        system.setOutputs(createOutputList("test-observable-property"));
        // capabilities
        Capabilities capabilities = system.addNewCapabilities();
        capabilities.setName("InsertionMetadata");
        SimpleDataRecordType dataRecord =
                (SimpleDataRecordType) capabilities.addNewAbstractDataRecord().substitute(
                        new QName("http://www.opengis.net/swe/1.0.1", "SimpleDataRecord", "swe"),
                        SimpleDataRecordType.type);
        AnyScalarPropertyType field1 = dataRecord.addNewField();
        field1.setName("sos:ObservationType");
        field1.addNewText().setValue("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement");
        AnyScalarPropertyType field2 = dataRecord.addNewField();
        field2.setName("sos:FeatureOfInterestType");
        field2.addNewText().setValue("http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint");
        Position position = system.addNewPosition();
        position.setName("test-sensor-position");
        position.setVector(createCoordinates(52.0, 7.5, 42.0, "http://www.opengis.net/def/crs/EPSG/0/4326"));
        position.setName("test-sensor-position");
        // .setIdentifier(sensorId);
        return restSensor.xmlText();
    }

    private String createRestMeasurement(final String sensorId, final String offeringId, final long timestamp,
            final double value, final String featureId, final String observableProperty) {
        OMObservationDocument observationDoc = OMObservationDocument.Factory.newInstance();
        OMObservationType observation = observationDoc.addNewOMObservation();

        observation.addNewPhenomenonTime().addNewAbstractTimeObject().substitute(new QName("http://www.opengis.net/gml/3.2", "TimePeriod", "gml"), TimePeriodType.type);
        TimePeriodType timePeriodType = TimePeriodType.Factory.newInstance();
        timePeriodType.setId("tp_123");
        timePeriodType.addNewBeginPosition().setStringValue(new DateTime(timestamp).toString());
        timePeriodType.addNewEndPosition().setStringValue(new DateTime(timestamp).toString());

        TimeInstantType timeInstant = observation.addNewResultTime().addNewTimeInstant();
        timeInstant.setId("ti_123");
        timeInstant.addNewTimePosition().setStringValue(new DateTime(timestamp).toString());

        observation.addNewProcedure().setHref(sensorId);
        observation.addNewObservedProperty().setHref(observableProperty);
        observation.addNewFeatureOfInterest().setHref(featureId);

        MeasureType measureType = MeasureType.Factory.newInstance();
        measureType.setUom("test-unit");
        measureType.setDoubleValue(value);

        final ObservationDocument restObsDoc =
                ObservationDocument.Factory.newInstance();
        final ObservationType restObservation = restObsDoc.addNewObservation();
        restObservation.setOMObservation(observation);

        final LinkType link = restObservation.addNewLink();
        link.setType(CONTENT_TYPE);
        link.setRel(EncodingNamespace + "/" + ResourceRelationOfferingGet);
        link.setHref(/*ServiceUrl + */UrlPattern + "/"
                + ResourceOfferings + "/" + offeringId);
        return restObsDoc.xmlText();
    }

    private Identification[] createIdentifications(final String sensorId, final String offeringId) {
        Identification identification = Identification.Factory.newInstance();
        Identifier identifier1 = identification.addNewIdentifierList().addNewIdentifier();
        identifier1.setName("uniqueId");
        Term term1 = identifier1.addNewTerm();
        term1.setDefinition("urn:ogc:def:identifier:OGC:1.0:uniqueID");
        term1.setValue(sensorId);

        Identifier identifier2 = identification.addNewIdentifierList().addNewIdentifier();
        identifier2.setName("offerings");
        Term term2 = identifier1.addNewTerm();
        term2.setDefinition("urn:ogc:def:identifier:OGC:offeringID");
        term2.setValue(offeringId);

        Identification[] array = new Identification[1];
        array[0] = identification;
        return array;
    }

    private Outputs createOutputList(final String name) {
        Outputs outputs = Outputs.Factory.newInstance();
        IoComponentPropertyType output = outputs.addNewOutputList().addNewOutput();
        output.setName(name);
        output.setQuantity(createQuantity("test-observable-property",
                "http://www.52north.org/test/observableProperty/42", "m"));
        return outputs;
    }

    private Inputs createInputList(final String name) {
        Inputs inputs = Inputs.Factory.newInstance();
        ObservableProperty observableProperty = inputs.addNewInputList().addNewInput().addNewObservableProperty();
        observableProperty.addNewName().setStringValue("test-observable-property");
        observableProperty.setDefinition("http://www.52north.org/test/observableProperty/42");
        return inputs;
    }

    private VectorType createCoordinates(final double latitude, final double longitude, final double altitudeV,
            String referenceFrame) {
        VectorType vector = VectorType.Factory.newInstance();
        vector.setReferenceFrame(referenceFrame);
        Coordinate coordinate1 = vector.addNewCoordinate();
        coordinate1.setName("northing");
        coordinate1.setQuantity(createQuantity(latitude, "y", "deg"));

        Coordinate coordinate2 = vector.addNewCoordinate();
        coordinate2.setName("easting");
        coordinate2.setQuantity(createQuantity(longitude, "x", "deg"));

        Coordinate coordinate3 = vector.addNewCoordinate();
        coordinate3.setName("altitude");
        coordinate3.setQuantity(createQuantity(altitudeV, "z", "m"));
        return vector;
    }

    private Quantity createQuantity(final String identifier, final String definition, final String uom) {
        Quantity quantity = Quantity.Factory.newInstance();
        quantity.addNewName().setStringValue(identifier);
        quantity.setDefinition(definition);
        quantity.addNewUom().setCode(uom);
        return quantity;
    }

    private Quantity createQuantity(final Double value, final String asixID, final String uom) {
        Quantity quantity = Quantity.Factory.newInstance();
        quantity.setValue(value);
        quantity.setAxisID(asixID);
        quantity.addNewUom().setCode(uom);
        return quantity;
    }

    /**
     * @see #createRestSensor(String, String)
     */
    protected Response addSensor(final String sensorId, final String offeringId) {
        return post(REST_URL + "/" + ResourceSensors).accept(CONTENT_TYPE).contentType(CONTENT_TYPE)
                .entity(createRestSensor(sensorId, offeringId)).response();
    }

    protected String selfLink(final String resType) {
        return selfLink(resType, null);
    }

    protected String selfLink(final String resType, final String resourceId) {
        return link(ResourceRelationSelf, resType + (resourceId != null ? "/" + resourceId : ""));
    }

    protected String sensorLink(final String sensorId1) {
        return link(ResourceRelationSensorGet, ResourceSensors + "/" + sensorId1);
    }

    protected Response addMeasurement(final String sensorId, final String offeringId, final long timestamp,
            final double value, final String featureId, final String observableProperty) {
        return post(REST_URL + "/" + ResourceObservations).accept(CONTENT_TYPE).contentType(CONTENT_TYPE)
                .entity(createRestMeasurement(sensorId, offeringId, timestamp, value, featureId, observableProperty))
                .response();
    }

}
