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
package org.n52.sos.service.it.exception;

import org.n52.sos.service.it.ogc.OWSConstants;

/**
 * ExceptionCodes as defined in the OWS Common Implementation Specification 1.1.0.
 */
public enum OwsExceptionCode implements ExceptionCode {
    InvalidParameterValue(OWSConstants.SOAP_REASON_INVALID_PARAMETER_VALUE),
    InvalidUpdateSequence(OWSConstants.SOAP_REASON_INVALID_UPDATE_SEQUENCES),
    MissingParameterValue(OWSConstants.SOAP_REASON_MISSING_PARAMETER_VALUE),
    NoApplicableCode(OWSConstants.SOAP_REASON_NO_APPLICABLE_CODE),
    OperationNotSupported(OWSConstants.SOAP_REASON_OPTION_NOT_SUPPORTED),
    OptionNotSupported(OWSConstants.SOAP_REASON_OPERATION_NOT_SUPPORTED),
    VersionNegotiationFailed(OWSConstants.SOAP_REASON_VERSION_NEGOTIATION_FAILED);
    private final String soapFaulReason;

    private OwsExceptionCode(String soapFaultReason) {
        this.soapFaulReason = soapFaultReason;
    }

    @Override
    public String getSoapFaultReason() {
        return soapFaulReason;
    }
}
