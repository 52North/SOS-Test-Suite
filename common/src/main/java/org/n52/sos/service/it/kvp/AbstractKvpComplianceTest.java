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
package org.n52.sos.service.it.kvp;

import org.junit.Test;
import org.n52.sos.service.it.AbstractComplianceSuiteTest;
import org.n52.sos.service.it.Client;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public abstract class AbstractKvpComplianceTest
        extends AbstractComplianceSuiteTest {
    public static final String REQUEST_PARAMETER = "request";
    public static final String VERSION_PARAMETER = "version";
    public static final String SERVICE_PARAMETER = "service";

    protected Client kvp() {
        return getExecutor().kvp();
    }

    /**
     * Definition for missing service parameter
     */
    @Test
    public abstract void missingServiceParameter();

    /**
     * Definition for empty service parameter value
     */
    @Test
    public abstract void emptyServiceParameter();

    /**
     * Definition for invalid service parameter value
     */
    @Test
    public abstract void invalidServiceParameter();

    /**
     * Definition for missing version parameter
     */
    @Test
    public abstract void missingVersionParameter();

    /**
     * Definition for empty version parameter value
     */
    @Test
    public abstract void emptyVersionParameter();

    /**
     * Definition for invalid version parameter value
     */
    @Test
    public abstract void invalidVersionParameter();
}
