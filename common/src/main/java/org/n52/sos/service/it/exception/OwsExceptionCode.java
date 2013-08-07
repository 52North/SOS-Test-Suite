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
