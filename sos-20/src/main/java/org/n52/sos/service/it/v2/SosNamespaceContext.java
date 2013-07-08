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

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

import org.n52.sos.util.CollectionHelper;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class SosNamespaceContext implements NamespaceContext {
    private final Map<String, String> prefixForNamespace;
    private final Map<String, String> namespaceForPrefix;

    public SosNamespaceContext() {
        namespaceForPrefix = new HashMap<String, String>(16);
        namespaceForPrefix.put("fes", "http://www.opengis.net/fes/2.0");
        namespaceForPrefix.put("gml", "http://www.opengis.net/gml/3.2");
        namespaceForPrefix.put("om", "http://www.opengis.net/om/2.0");
        namespaceForPrefix.put("ows", "http://www.opengis.net/ows/1.1");
        namespaceForPrefix.put("sams", "http://www.opengis.net/samplingSpatial/2.0");
        namespaceForPrefix.put("sa", "http://www.opengis.net/sampling/1.0");
        namespaceForPrefix.put("sf", "http://www.opengis.net/sampling/2.0");
        namespaceForPrefix.put("sml", "http://www.opengis.net/sensorML/1.0.1");
        namespaceForPrefix.put("soap", "http://www.w3.org/2003/05/soap-envelope");
        namespaceForPrefix.put("sos", "http://www.opengis.net/sos/2.0");
        namespaceForPrefix.put("sosREST", "http://www.opengis.net/sosREST/1.0");
        namespaceForPrefix.put("swe", "http://www.opengis.net/swe/2.0");
        namespaceForPrefix.put("swes", "http://www.opengis.net/swes/2.0");
        namespaceForPrefix.put("xlink", "http://www.w3.org/1999/xlink");
        namespaceForPrefix.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        namespaceForPrefix.put("xml", "http://www.w3.org/XML/1998/namespace");
        prefixForNamespace = CollectionHelper.reverse(namespaceForPrefix);
    }

    @Override
    public String getNamespaceURI(final String prefix) {
        return namespaceForPrefix.get(prefix);
    }

    @Override
    public String getPrefix(final String namespace) {
        return prefixForNamespace.get(namespace);
    }

    @Override
    public Iterator<String> getPrefixes(final String namespace) {
        return Collections.singleton(getPrefix(namespace)).iterator();
    }
}
