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

import static org.n52.sos.ogc.swe.SWEConstants.SweCoordinateName.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.NamespaceContext;

import net.opengis.om.x20.OMObservationType;
import net.opengis.sensorML.x101.SystemType;
import net.opengis.sosREST.x10.LinkType;
import net.opengis.sosREST.x10.ObservationDocument;
import net.opengis.sosREST.x10.ObservationType;
import net.opengis.sosREST.x10.SensorDocument;

import org.joda.time.DateTime;
import org.n52.sos.binding.rest.Constants;
import org.n52.sos.encode.OmEncoderv20;
import org.n52.sos.encode.SensorMLEncoderv101;
import org.n52.sos.ogc.gml.CodeWithAuthority;
import org.n52.sos.ogc.gml.time.TimeInstant;
import org.n52.sos.ogc.gml.time.TimePeriod;
import org.n52.sos.ogc.om.AbstractPhenomenon;
import org.n52.sos.ogc.om.OmObservation;
import org.n52.sos.ogc.om.OmObservationConstellation;
import org.n52.sos.ogc.om.SingleObservationValue;
import org.n52.sos.ogc.om.features.samplingFeatures.SamplingFeature;
import org.n52.sos.ogc.om.values.QuantityValue;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sensorML.SensorMLConstants;
import org.n52.sos.ogc.sensorML.System;
import org.n52.sos.ogc.sensorML.elements.SmlCapabilities;
import org.n52.sos.ogc.sensorML.elements.SmlIdentifier;
import org.n52.sos.ogc.sensorML.elements.SmlIo;
import org.n52.sos.ogc.sensorML.elements.SmlPosition;
import org.n52.sos.ogc.sos.SosProcedureDescriptionUnknowType;
import org.n52.sos.ogc.swe.SweCoordinate;
import org.n52.sos.ogc.swe.SweField;
import org.n52.sos.ogc.swe.SweSimpleDataRecord;
import org.n52.sos.ogc.swe.simpleType.SweAbstractSimpleType;
import org.n52.sos.ogc.swe.simpleType.SweObservableProperty;
import org.n52.sos.ogc.swe.simpleType.SweQuantity;
import org.n52.sos.ogc.swe.simpleType.SweText;
import org.n52.sos.service.ServiceConfiguration;
import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Response;
import org.n52.sos.service.it.v2.SosNamespaceContext;
import org.n52.sos.util.CollectionHelper;
import org.n52.sos.util.JavaHelper;
import org.n52.sos.util.XmlOptionsHelper;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public abstract class RestBindingTest extends AbstractComplianceSuiteTest {
    protected static final String REST_URL = "/rest";
    protected static final String CONTENT_TYPE = "application/gml+xml";
    protected static final NamespaceContext NS_CTXT = new SosNamespaceContext();

    protected String link(final String relType,
                          final String resTypeWithOrWithoutId) {
        return String
                .format("sosREST:link[@rel='%s' and @href='%s' and @type='%s']",
                        getConstants().getEncodingNamespace() + "/" + relType,
                        getConstants().getServiceUrl() + REST_URL + "/" +
                        resTypeWithOrWithoutId,
                        CONTENT_TYPE);
    }

    protected Response getResource(final String resType) {
        return get(REST_URL + "/" + resType).accept(CONTENT_TYPE).asResponse();
    }

    /**
     * Creating example sensor with id <tt>sensorId</tt> and offering
     * <tt>offeringId</tt> observing <tt>test-observable-property</tt>
     * with type
     * <tt>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</tt>
     * with feature
     * <tt>http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint</tt>
     */
    protected String createRestSensor(final String sensorId,
                                      final String offeringId) throws
            OwsExceptionReport {
        final System system = (System) new System()
                .setPosition(new SmlPosition("test-sensor-position", true, ServiceConfiguration
                .getInstance()
                .getSrsNamePrefixSosV2() + 4326, createCoordinates(52.0, 7.5, 42.0)))
                .setInputs(createInputList("test-observable-property"))
                .setOutputs(createOutputList("test-observable-property"))
                .setIdentifications(createIdentifications(sensorId, offeringId))
                .addCapabilities(new SmlCapabilities("InsertionMetadata",
                                                     new SweSimpleDataRecord()
                .addField(new SweField("sos:ObservationType", new SweText()
                .setValue("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement")))
                .addField(new SweField("sos:FeatureOfInterestType", new SweText()
                .setValue("http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint")))))
                .setIdentifier(sensorId);
        final SystemType xbSystem = (SystemType) new SensorMLEncoderv101()
                .encode(system);
        final SensorDocument restSensor = SensorDocument.Factory
                .newInstance(XmlOptionsHelper.getInstance().getXmlOptions());
        final SystemType substitute = (SystemType) restSensor.addNewSensor()
                .addNewProcess()
                .substitute(SensorMLConstants.SYSTEM_QNAME, SystemType.type);
        substitute.set(xbSystem);
        return restSensor.xmlText();
    }

    private String createRestMeasurement(final String sensorId,
                                         final String offeringId,
                                         final long timestamp,
                                         final double value,
                                         final String featureId,
                                         final String observableProperty) throws
            OwsExceptionReport {
        final OmObservation o = new OmObservation();
        o.setValidTime(new TimePeriod(new DateTime(timestamp),
                                      new DateTime(timestamp)));
        o.setObservationConstellation(
                new OmObservationConstellation(
                new SosProcedureDescriptionUnknowType(sensorId, null, null),
                new AbstractPhenomenon(observableProperty),
                new SamplingFeature(new CodeWithAuthority(featureId))));
        o.setResultTime(new TimeInstant(new DateTime(timestamp)));
        final QuantityValue sosValue = new QuantityValue(new BigDecimal(value));
        sosValue.setUnit("test-unit");
        o.setValue(
                new SingleObservationValue<BigDecimal>(
                new TimeInstant(new DateTime(timestamp)),
                sosValue));
        final OMObservationType xbObservation =
                (OMObservationType) new OmEncoderv20().encode(o);
        final ObservationDocument restObsDoc = ObservationDocument.Factory
                .newInstance(XmlOptionsHelper.getInstance().getXmlOptions());
        final ObservationType restObservation = restObsDoc.addNewObservation();
        restObservation.setOMObservation(xbObservation);
        final LinkType link = restObservation.addNewLink();
        link.setType(CONTENT_TYPE);
        link.setRel(getConstants().getEncodingNamespace() + "/" + getConstants()
                .getResourceRelationOfferingGet());
        link.setHref(getConstants().getServiceUrl() + getConstants()
                .getUrlPattern() +
                     "/" + getConstants().getResourceOfferings() + "/" +
                     offeringId);
        return restObsDoc.xmlText();
    }

    private List<SmlIdentifier> createIdentifications(final String sensorId,
                                                      final String offeringId) {
        return CollectionHelper
                .asList(new SmlIdentifier("uniqueId", "urn:ogc:def:identifier:OGC:1.0:uniqueID", sensorId),
                        new SmlIdentifier("offerings", "urn:ogc:def:identifier:OGC:offeringID", offeringId));
    }

    private List<SmlIo<?>> createOutputList(final String string) {
        final SmlIo<Double> quanti = new SmlIo<Double>(
                (SweQuantity) new SweQuantity()
                .setUom("m")
                .setDefinition("http://www.52north.org/test/observableProperty/42")
                .setIdentifier("test-observable-property"));
        final List<SmlIo<?>> outputs = new ArrayList<SmlIo<?>>(1);
        outputs.add(quanti);
        return Collections.unmodifiableList(outputs);
    }

    private List<SmlIo<?>> createInputList(final String string) {
        final SmlIo<String> io = new SmlIo<String>(
                (SweObservableProperty) new SweObservableProperty()
                .setDefinition("http://www.52north.org/test/observableProperty/42")
                .setIdentifier("test-observable-property"));
        final List<SmlIo<?>> inputs = new ArrayList<SmlIo<?>>(1);
        inputs.add(io);
        return Collections.unmodifiableList(inputs);
    }

    private List<SweCoordinate<?>> createCoordinates(final double latitude,
                                                     final double longitude,
                                                     final double altitudeV) {
        final List<SweCoordinate<?>> sweCoordinates =
                new ArrayList<SweCoordinate<?>>(3);
        sweCoordinates.add(new SweCoordinate<Double>(
                northing, createSweQuantity(latitude, "y", "deg")));
        sweCoordinates.add(new SweCoordinate<Double>(
                easting, createSweQuantity(longitude, "x", "deg")));
        sweCoordinates.add(new SweCoordinate<Double>(
                altitude, createSweQuantity(altitudeV, "z", "m")));
        return sweCoordinates;
    }

    private SweAbstractSimpleType<Double> createSweQuantity(final Double value,
                                                            final String asixID,
                                                            final String uom) {
        return new SweQuantity()
                .setValue(JavaHelper.asDouble(value))
                .setAxisID(asixID)
                .setUom(uom);
    }

    /**
     * @see #createRestSensor(String, String)
     */
    protected Response addSensor(final String sensorId,
                                 final String offeringId)
            throws OwsExceptionReport {
        return post(REST_URL + "/" + getConstants().getResourceSensors())
                .accept(CONTENT_TYPE)
                .contentType(CONTENT_TYPE)
                .entity(createRestSensor(sensorId, offeringId))
                .asResponse();
    }

    protected String selfLink(final String resType) {
        return selfLink(resType, null);
    }

    protected String selfLink(final String resType,
                              final String resourceId) {
        return link(getConstants().getResourceRelationSelf(), resType +
                                                              (resourceId !=
                                                               null
                                                               ? "/" +
                                                                 resourceId
                                                               : ""));
    }

    protected String sensorLink(final String sensorId1) {
        return link(getConstants().getResourceRelationSensorGet(),
                    getConstants().getResourceSensors() + "/" + sensorId1);
    }

    protected Response addMeasurement(final String sensorId,
                                      final String offeringId,
                                      final long timestamp,
                                      final double value,
                                      final String featureId,
                                      final String observableProperty)
            throws OwsExceptionReport {
        return post(REST_URL + "/" + getConstants()
                .getResourceObservations())
                .accept(CONTENT_TYPE)
                .contentType(CONTENT_TYPE)
                .entity(createRestMeasurement(sensorId, offeringId, timestamp, value, featureId, observableProperty))
                .asResponse();
    }

    protected Constants getConstants() {
        return Constants.getInstance();
    }
}
