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

import java.util.List;

import org.junit.Rule;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class ComplianceSuiteTestRunner extends BlockJUnit4ClassRunner {
    private final ComplianceSuite parent;
    private final TestClass parentClass;
    private final RequestExecutor executor;

    public ComplianceSuiteTestRunner(RequestExecutor executor,
                                     ComplianceSuite parent,
                                     Class<? extends ComplianceSuiteTest> klass)
            throws InitializationError {
        super(klass);
        this.parent = parent;
        this.parentClass = new TestClass(parent.getClass());
        this.executor = executor;
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        ComplianceSuiteTest eTest = (ComplianceSuiteTest) test;
        eTest.setExecutor(executor);
        return new InvokeMethod(method, test);
    }

    @Override
    protected List<TestRule> getTestRules(Object target) {
        List<TestRule> rules = parentClass.getAnnotatedMethodValues(
                parent, Rule.class, TestRule.class);
        rules.addAll(parentClass.getAnnotatedFieldValues(
                parent, Rule.class, TestRule.class));
        rules.addAll(super.getTestRules(target));
        rules.add(new RequestExecutorRule());
        return rules;
    }

    @Override
    protected String testName(FrameworkMethod method) {
        return getTestClass().getName() + "#" + method.getName();
    }

    private class RequestExecutorRule extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            executor.before();
        }

        @Override
        protected void after() {
            executor.after();
        }
    }
}
