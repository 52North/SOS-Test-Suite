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
package org.n52.sos.service.it.v2.pox;

import org.n52.sos.service.it.ServiceConstants;
import org.n52.sos.service.it.pox.AbstractPoxComplianceTest;

/**
 * Abstract class for SOS 2.0 POX request tests
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @since 4.0.0
 *
 */
public abstract class AbstractSosV2PoxTest extends AbstractPoxComplianceTest {
    public static final String SERVICE = ServiceConstants.SOS;
    public static final String VERSION = ServiceConstants.V20;
    public static final String SERVICE_PARAMETER = "service";
}
