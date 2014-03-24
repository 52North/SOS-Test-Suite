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

import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.io.UnsupportedEncodingException;

import net.opengis.sosREST.x10.SensorDocument;

import org.apache.xmlbeans.XmlException;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;

import org.n52.sos.service.it.Response;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk
 * J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public class SensorsTest extends RestBindingTest {
    @Test
    @Ignore("the sensor was not added")
    public void should_return_valid_sosREST_Sensor()
            throws UnsupportedEncodingException, XmlException {
        final String sensorId = "test-sensor-id";
        Response response = addSensor(sensorId, "test-offering-id");
        getErrors().checkThat(response.asXmlObject(),
                              instanceOf(SensorDocument.class));
        final Node node = response.asNode();
        getErrors().checkThat(node,
                              hasXPath("//sosREST:Sensor/sml:System", NS_CTXT));
        getErrors().checkThat(node, hasXPath(selfLink(
                ResourceSensors, sensorId), NS_CTXT));
        getErrors().checkThat(node, hasXPath(link(
                ResourceRelationFeaturesGet,
                ResourceFeatures + "?" +
                HttpGetParameterNameProcedure + "=" + sensorId), NS_CTXT));
        getErrors().checkThat(node, hasXPath(link(
                ResourceRelationObservationsGet,
                ResourceObservations + "?" +
                HttpGetParameterNameProcedure + "=" + sensorId), NS_CTXT));
    }

    @Test
    public void should_return_list_with_sosRest_Sensors() {
        final String sensorId1 = "test-sensor-1";
        final String sensorId2 = "test-sensor-2";

        addSensor(sensorId1, "test-offering-1");
        addSensor(sensorId2, "test-offering-2");

        final Node response =
                getResource(ResourceSensors).asNode();

        getErrors().checkThat(response,
                              hasXPath("//sosREST:SensorCollection", NS_CTXT));
        getErrors().checkThat(response,
                              hasXPath(selfLink(
                ResourceSensors), NS_CTXT));
        //FIXME these were not added...
//        getErrors().checkThat(response,
//                              hasXPath(sensorLink(sensorId1), NS_CTXT));
//        getErrors().checkThat(response,
//                              hasXPath(sensorLink(sensorId2), NS_CTXT));
    }

    @Ignore("TODO implement DELETE sos/sensors/$SENSOR$")
    @Test
    public void should_delete_sensor_from_sensors_list() {
        final String sensorId = "sensor-1";
        addSensor(sensorId, "test-offering-1");
        final Node responseBeforeDelete =
                getResource(ResourceSensors).asNode();
        delete(REST_URL + "/" + ResourceSensors + "/" +
               sensorId).accept(CONTENT_TYPE).contentType(CONTENT_TYPE)
                .execute();
        final Node responseAfterDelete =
                getResource(ResourceSensors).asNode();

        getErrors().checkThat(responseBeforeDelete,
                              hasXPath(sensorLink(sensorId), NS_CTXT));
        getErrors().checkThat(responseAfterDelete,
                              hasXPath("count(" + sensorLink(sensorId) + ")",
                                       NS_CTXT, is("0")));
    }
//    @Before
//    public void initTestDatabase() throws OwsExceptionReport {
//        addObservationTypes();
//    }
}
