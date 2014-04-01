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

import org.n52.sos.service.it.ogc.SwesConstants;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public enum SwesExceptionCode implements ExceptionCode {
    InvalidRequest(SwesConstants.SOAP_REASON_INVALID_REQUEST),
    RequestExtensionNotSupported(SwesConstants.SOAP_REASON_REQUEST_EXTENSION_NOT_SUPPORTED);
    private final String soapFaultReason;

    private SwesExceptionCode(String soapFaultReason) {
        this.soapFaultReason = soapFaultReason;
    }

    @Override
    public String getSoapFaultReason() {
        return this.soapFaultReason;
    }
}
