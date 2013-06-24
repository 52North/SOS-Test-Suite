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
 * Foundation, Inc., 59 Temple Place - ITParentRunner 330, Boston, MA
 * 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
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
        final RequestExecutor executor = parent.createExecutor();
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
