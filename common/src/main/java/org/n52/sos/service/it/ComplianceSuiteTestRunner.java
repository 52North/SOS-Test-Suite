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
