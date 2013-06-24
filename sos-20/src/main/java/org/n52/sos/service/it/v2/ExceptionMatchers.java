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
package org.n52.sos.service.it.v2;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.n52.sos.service.it.util.XPath.hasXPath;
import static org.n52.sos.service.it.v2.XPaths.*;

import org.hamcrest.Matcher;
import org.n52.sos.exception.ows.OwsExceptionCode;
import org.n52.sos.exception.swes.SwesExceptionCode;
import org.n52.sos.ogc.ows.OWSConstants;
import org.w3c.dom.Node;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class ExceptionMatchers {
    private static final String INVALID_PARAMETER_VALUE =
            "The request contained an invalid parameter value.";
    private static final String MISSING_PARAMETER_VALUE =
            "The request did not include a value for a required parameter and this server does not declare a default value for it.";
    private static final String MISSING_PARAMETER =
            "[XmlBeans validation error:] Expected attribute: ";
    private static final String INVALID_REQUEST =
            "The request did not conform to its XML Schema definition.";

    private ExceptionMatchers() {
    }

    public static Matcher<Node> invalidServiceParameterValueExceptionFault(
            final String parameter) {
        return soapFault(OwsExceptionCode.InvalidParameterValue, INVALID_PARAMETER_VALUE,
                         OWSConstants.RequestParams.service,
                         "The value of the mandatory parameter 'service' must be 'SOS'. Delivered value was: " +
                         parameter);
    }

    public static Matcher<Node> invalidRequestMissingParameterExceptionFault(
            final String parameter) {
        return soapFault(SwesExceptionCode.InvalidRequest, INVALID_REQUEST,
                         "Expected attribute: " + parameter,
                         MISSING_PARAMETER + parameter);
    }

    public static Matcher<Node> missingServiceParameterValueExceptionFault() {
        return soapFault(OwsExceptionCode.MissingParameterValue, MISSING_PARAMETER_VALUE,
                         OWSConstants.RequestParams.service,
                         "The value for the parameter 'service' is missing in the request!");
    }

    public static Matcher<Node> invalidVersionParameterValueExceptionFault(
            final String parameter) {
        return soapFault(OwsExceptionCode.InvalidParameterValue, INVALID_PARAMETER_VALUE,
                         OWSConstants.RequestParams.version,
                         "The requested version is not supported!");
    }

    public static Matcher<Node> missingVersionParameterValueExceptionFault() {
        return soapFault(OwsExceptionCode.MissingParameterValue, MISSING_PARAMETER_VALUE,
                         OWSConstants.RequestParams.version,
                         "The value for the parameter 'version' is missing in the request!");
    }

    public static Matcher<Node> missingParameterValueException(Enum<?> parameter) {
        String message = String.format(
                "The value for the parameter '%s' is missing in the request!",
                parameter.name());
        return exception(OwsExceptionCode.MissingParameterValue, parameter, message);
    }

    public static Matcher<Node> missingServiceParameterValueException() {
        return exception(OwsExceptionCode.MissingParameterValue, OWSConstants.RequestParams.service,
                         "The value for the parameter 'service' is missing in the request!");
    }

    public static Matcher<Node> invalidServiceParameterValueException(
            String value) {
        String message = String.format(
                "The value of the mandatory parameter 'service' must be 'SOS'. Delivered value was: %s", value);
        return exception(OwsExceptionCode.InvalidParameterValue, OWSConstants.RequestParams.service, message);
    }

    public static Matcher<Node> missingVersionParameterValueException() {
        return exception(OwsExceptionCode.MissingParameterValue, OWSConstants.RequestParams.version,
                         "The value for the parameter 'version' is missing in the request!");
    }

    public static Matcher<Node> invalidVersionParameterValueException(
            String value) {
        return exception(OwsExceptionCode.InvalidParameterValue, OWSConstants.RequestParams.version,
                         "The requested version is not supported!");
    }

    public static Matcher<Node> exception(Enum<?> code,
                                          Enum<?> locator,
                                          String text) {
        return allOf(
                hasXPath(EXCEPTION_CODE, is(code.name())),
                hasXPath(EXCEPTION_LOCATOR, is(locator.name())),
                hasXPath(EXCEPTION_TEXT, is(text)));
    }

    public static Matcher<Node> soapFault(final Enum<?> code,
                                          final String text,
                                          final Enum<?> locator,
                                          final String exceptionText) {
        return soapFault(code, text, locator.name(), exceptionText);
    }

    public static Matcher<Node> soapFault(final Enum<?> code,
                                          final String text,
                                          final String locator,
                                          final String exceptionText) {
        return allOf(
                hasXPath(SOAP_ENVELOPE_BODY_FAULT_CODE_SUBCODE_VALUE,
                         endsWith(":" + code.name())),
                hasXPath(ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_TEXT,
                         is(text)),
                hasXPath(ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_EXCEPTION_CODE,
                         is(code.name())),
                hasXPath(ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_LOCATOR,
                         startsWith(locator)),
                hasXPath(ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_EXCEPTION_TEXT,
                         startsWith(exceptionText)));
    }
}
