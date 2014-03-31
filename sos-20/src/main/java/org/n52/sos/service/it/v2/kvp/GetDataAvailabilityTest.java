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

import static org.n52.sos.service.it.ServiceConstants.GET_DATA_AVAILABILITY;

/**
 * Test for KVP SOS 2.0 GetDataAvailability request.
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Carsten Hollmann <c.hollmann@52north.org>
 * @since 4.0.0
 */
public class GetDataAvailabilityTest extends AbstractSosV2KvpTest {
    @Override
    public String getRequest() {
        return GET_DATA_AVAILABILITY;
    }
}
