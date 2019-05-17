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
package org.n52.sos.service.it.v2.kvp;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.n52.sos.service.it.v2.ExceptionMatchers;

import org.junit.Ignore;
import org.junit.Test;
import org.n52.shetland.ogc.ows.OWSConstants;
import org.n52.shetland.ogc.ows.OWSConstants.GetCapabilitiesParams;
import org.n52.shetland.ogc.ows.exception.OwsExceptionCode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Test for KVP SOS 2.0 GetCapabilities request.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public class GetCapabilitiesTest extends AbstractSosV2KvpTest {
    @Test
    @Override
    public void missingServiceParameter() {
        Node node = kvp().query(OWSConstants.RequestParams.request, getRequest()).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void emptyServiceParameter() {
        Node node = kvp().query(OWSConstants.RequestParams.request, getRequest())
                .query(OWSConstants.RequestParams.service, "").response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void invalidServiceParameter() {
        Node node = kvp().query(OWSConstants.RequestParams.request, getRequest())
                .query(OWSConstants.RequestParams.service, INVALID).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.invalidServiceParameterValueException(INVALID)));
    }

    @Test
    @Override
    public void missingVersionParameter() {
        // not a GetCapabilities parameter
    }

    @Test
    @Override
    public void emptyVersionParameter() {
        // not a GetCapabilities parameter
    }

    @Test
    @Override
    public void invalidVersionParameter() {
        // not a GetCapabilities parameter
    }

    @Test
    @Ignore
    public void emptySectionParameter() {
        Node node = kvp().query(OWSConstants.RequestParams.request, getRequest())
                .query(OWSConstants.RequestParams.service, SERVICE)
                .query(OWSConstants.GetCapabilitiesParams.Sections, "").response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.exception(OwsExceptionCode.MissingParameterValue,
                GetCapabilitiesParams.Sections, "The value for the parameter 'sections' is missing in the request!")));
    }

    @Test
    public void invalidSectionParameter() {
        // FIXME temporary deactivated due to issue with ectended capabilities
        // loading
        // Node node = kvp()
        // .query(OWSConstants.RequestParams.request,
        // SosConstants.Operations.GetCapabilities)
        // .query(OWSConstants.RequestParams.service, SosConstants.SOS)
        // .query(SosConstants.GetCapabilitiesParams.Sections, "INVALID")
        // .response().asNode();
        // Assert.assertThat(
        // node,
        // is(exception(OwsExceptionCode.InvalidParameterValue,
        // GetCapabilitiesParams.Section,
        // "The requested section 'INVALID' does not exist or is not
        // supported!")));
    }

    // @Test
    // //FIXME SOS 4.0 specific
    // public void checkServiceIdentification() {
    // Element node = getCapabilities();
    // Assert.assertThat(node, allOf(
    // hasXPath(SERVICE_IDENTIFICATION_TITLE,
    // is(TITLE_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_IDENTIFICATION_ABSTRACT,
    // is(ABSTRACT_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_IDENTIFICATION_SERVICE_TYPE,
    // is(SERVICE_TYPE_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_IDENTIFICATION_FEES,
    // is(FEES_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_IDENTIFICATION_ACCESS_CONSTRAINTS,
    // is(ACCESS_CONSTRAINTS_DEFINITION
    // .getDefaultValue()))));
    // }
    //
    // @Test
    // //FIXME SOS 4.0 specific
    // @SuppressWarnings("unchecked")
    // public void checkServiceProvider() {
    // Element node = getCapabilities();
    // Assert.assertThat(node, allOf(
    // hasXPath(SERVICE_PROVIDER_PROVIDER_NAME,
    // is(NAME_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_PROVIDER_SITE,
    // is(SITE_DEFINITION.getDefaultValue().toString())),
    // hasXPath(SERVICE_PROVIDER_INDIVIDUAL_NAME,
    // is(INDIVIDUAL_NAME_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_POSITION_NAME,
    // is(POSITION_NAME_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_VOICE,
    // is(PHONE_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_DELIVERY_POINT,
    // is(DELIVERY_POINT_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_CITY,
    // is(CITY_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_ADMINISTRATIVE_AREA,
    // is(ADMINISTRATIVE_AREA_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_POSTAL_CODE,
    // is(POSTAL_CODE_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_COUNTRY,
    // is(COUNTRY_DEFINITION.getDefaultValue())),
    // hasXPath(SERVICE_PROVIDER_EMAIL_ADDRESS,
    // is(MAIL_ADDRESS_DEFINITION.getDefaultValue()))));
    // }

    protected Element getCapabilities() {
        return kvp().query(OWSConstants.RequestParams.request, getRequest())
                .query(OWSConstants.RequestParams.service, SERVICE).response().asNode();
    }

    @Override
    public String getRequest() {
        return GET_CAPABILITIES;
    }
}
