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

import org.n52.sos.service.it.ogc.SosConstants;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public enum SosExceptionCode implements ExceptionCode {
    ResponseExceedsSizeLimit(SosConstants.SOAP_REASON_RESPONSE_EXCEEDS_SIZE_LIMIT),
    InvalidPropertyOfferingCombination(SosConstants.SOAP_REASON_INVALID_PROPERTY_OFFERING_COMBINATION);
    private final String soapFaultReason;

    private SosExceptionCode(String soapFaultReason) {
        this.soapFaultReason = soapFaultReason;
    }

    @Override
    public String getSoapFaultReason() {
        return this.soapFaultReason;
    }
}
