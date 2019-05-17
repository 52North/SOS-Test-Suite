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
package org.n52.sos.service.it;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public abstract class AbstractComplianceSuiteTest implements ComplianceSuiteTest {
    public static final String PROCEDURE_PARAMETER = "procedure";

    public static final String OBSERVATION_PARAMETER = "observation";

    public static final String PROCEDURE_DESCRIPTION_FORMAT_PARAMETER = "procedureDescriptionFormat";

    public static final String OFFERING_PARAMETER = "offering";

    public static final String OBSERVED_PROPERTY_PARAMETER = "observedProperty";

    public static final String TEMPLATE_PARAMETER = "template";

    public static final String INVALID = "INVALID";

    private RequestExecutor executor;

    private final ErrorCollector errors = new ErrorCollector();

    public RequestExecutor getExecutor() {
        return executor;
    }

    @Override
    public void setExecutor(RequestExecutor executor) {
        this.executor = executor;
    }

    public Client get(String path) {
        return executor.get(path);
    }

    public Client post(String path) {
        return executor.post(path);
    }

    public Client put(String path) {
        return executor.put(path);
    }

    public Client delete(String path) {
        return executor.delete(path);
    }

    public Client head(String path) {
        return executor.head(path);
    }

    @Rule
    public ErrorCollector getErrors() {
        return errors;
    }
}
