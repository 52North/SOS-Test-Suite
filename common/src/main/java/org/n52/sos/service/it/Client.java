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

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public interface Client {
    Client accept(String type);

    Client contentType(String type);

    Client entity(String content);

    Client header(String header, String value);

    Client query(String key, String value);

    Client query(Enum<?> key, String value);

    Client query(Enum<?> key, Enum<?> value);

    Client query(String key, Enum<?> value);

    Response response();

    void execute();
}
