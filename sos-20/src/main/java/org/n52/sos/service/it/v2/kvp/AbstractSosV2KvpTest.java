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

import org.junit.Test;
import org.n52.sos.service.it.ServiceConstants;
import org.n52.sos.service.it.kvp.AbstractKvpComplianceTest;
import org.w3c.dom.Node;

/**
 * . Abstract class for SOS 2.0 KVP tests. Contains tests for service and
 * version parameter
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 *
 */
public abstract class AbstractSosV2KvpTest extends AbstractKvpComplianceTest implements ServiceConstants {
    public static final String VERSION = ServiceConstants.V20;

    public static final String SERVICE = ServiceConstants.SOS;

    private static final String INVALID_VERSION = "1.2.3";

    public abstract String getRequest();

    @Test
    @Override
    public void missingServiceParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(VERSION_PARAMETER, VERSION).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void emptyServiceParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(SERVICE_PARAMETER, "")
                .query(VERSION_PARAMETER, VERSION).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingServiceParameterValueException()));
    }

    @Test
    @Override
    public void invalidServiceParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(SERVICE_PARAMETER, INVALID)
                .query(VERSION_PARAMETER, VERSION).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.invalidServiceParameterValueException(INVALID)));
    }

    @Test
    @Override
    public void missingVersionParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(SERVICE_PARAMETER, SERVICE).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingVersionParameterValueException()));
    }

    @Test
    @Override
    public void emptyVersionParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(SERVICE_PARAMETER, SERVICE)
                .query(VERSION_PARAMETER, "").response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.missingVersionParameterValueException()));
    }

    @Test
    @Override
    public void invalidVersionParameter() {
        Node node = kvp().query(REQUEST_PARAMETER, getRequest()).query(SERVICE_PARAMETER, SERVICE)
                .query(VERSION_PARAMETER, INVALID_VERSION).response().asNode();
        Assert.assertThat(node, Matchers.is(ExceptionMatchers.invalidVersionParameterValueException(INVALID_VERSION)));
    }
}
