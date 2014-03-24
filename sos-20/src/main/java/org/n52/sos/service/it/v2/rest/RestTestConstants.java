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

public interface RestTestConstants {

    String ResourceRelationSelf = "self";

    String ResourceRelationSensorGet = "sensor-get";

    String ResourceSensors = "sensors";

    String ResourceObservations = "observations";

    String EncodingNamespace = "http://www.opengis.net/sosREST/1.0";

    String ResourceOfferings = "offerings";

    String ResourceRelationOfferingGet = "offering-get";

    String ResourceRelationOfferingsGet = "offerings-get";

    String UrlPattern = "/sos/rest";

    String ResourceCapabilities = "capabilities";

    String ResourceRelationSensorsGet = "sensors-get";

    String ResourceRelationSensorCreate = "sensor-create";

    String ResourceRelationObservationGet = "observation-get";

    String ResourceRelationObservationCreate = "observation-create";

    String ResourceRelationFeaturesGet = "features-get";

    String ResourceFeatures = "features";

    String HttpGetParameterNameProcedure = "procedures";

    String ResourceRelationObservationsGet = "observations-get";

}
