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

import java.util.Arrays;
import java.util.List;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class ComplianceSuiteRunner extends Suite {
    public ComplianceSuiteRunner(Class<?> klass) throws InitializationError {
        super(runnerBuilder(klass), klass, getTestClasses(klass));
    }

    private static Class[] getTestClasses(Class<?> klass) throws
            InitializationError {
        return instantiate(klass).getTests();
    }

    private static ComplianceSuite instantiate(Class<?> klass) throws
            InitializationError {
        if (ComplianceSuite.class.isAssignableFrom(klass)) {
            try {
                return (ComplianceSuite) klass.newInstance();
            } catch (InstantiationException ex) {
                throw new InitializationError(ex);
            } catch (IllegalAccessException ex) {
                throw new InitializationError(ex);
            }
        } else {
            throw new InitializationError(String
                    .format("Class %s has to instance of %s", klass, ComplianceSuite.class));
        }
    }

    private static RunnerBuilder runnerBuilder(Class<?> klass)
            throws InitializationError {
        final ComplianceSuite parent = instantiate(klass);
        final RequestExecutor executor = parent.getExecutor();
        return new AllDefaultPossibilitiesBuilder(true) {
            @Override
            public Runner runnerForClass(Class<?> testClass) throws Throwable {
                List<RunnerBuilder> builders = Arrays.asList(
                        ignoredBuilder(),
                        annotatedBuilder(),
                        suiteMethodBuilder(),
                        junit3Builder(),
                        executorInjectingBuilder(),
                        junit4Builder());

                for (RunnerBuilder each : builders) {
                    Runner runner = each.safeRunnerForClass(testClass);
                    if (runner != null) {
                        return runner;
                    }
                }
                return null;
            }

            private RunnerBuilder executorInjectingBuilder() {
                return new RunnerBuilder() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public Runner runnerForClass(Class<?> testClass)
                            throws Throwable {
                        if (ComplianceSuiteTest.class
                                .isAssignableFrom(testClass)) {
                            Class<? extends ComplianceSuiteTest> cst =
                                    (Class<? extends ComplianceSuiteTest>) testClass;
                            return new ComplianceSuiteTestRunner(executor, parent, cst);
                        }
                        return null;
                    }
                };
            }
        };
    }
}
