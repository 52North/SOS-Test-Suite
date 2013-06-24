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
import javax.xml.soap.SOAPConstants;

import org.n52.sos.ogc.filter.FilterConstants;
import org.n52.sos.ogc.gml.GMLConstants;
import org.n52.sos.ogc.om.OMConstants;
import org.n52.sos.ogc.om.features.SFConstants;
import org.n52.sos.ogc.ows.OWSConstants;
import org.n52.sos.ogc.sensorML.SensorMLConstants;
import org.n52.sos.ogc.sos.Sos2Constants;
import org.n52.sos.ogc.sos.SosConstants;
import org.n52.sos.ogc.swe.SWEConstants;
import org.n52.sos.util.CollectionHelper;
import org.n52.sos.util.W3CConstants;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class SosNamespaceContext implements NamespaceContext {
    private final Map<String, String> prefixForNamespace;

    private final Map<String, String> namespaceForPrefix;

    public SosNamespaceContext() {
        namespaceForPrefix = new HashMap<String, String>(15);
        namespaceForPrefix.put(FilterConstants.NS_FES_2_PREFIX, FilterConstants.NS_FES_2);
        namespaceForPrefix.put(GMLConstants.NS_GML_PREFIX, GMLConstants.NS_GML_32);
        namespaceForPrefix.put(OMConstants.NS_OM_PREFIX, OMConstants.NS_OM_2);
        namespaceForPrefix.put(OWSConstants.NS_OWS_PREFIX, OWSConstants.NS_OWS);
        namespaceForPrefix.put(SFConstants.NS_SAMS_PREFIX, SFConstants.NS_SAMS);
        namespaceForPrefix.put(SFConstants.NS_SA_PREFIX, SFConstants.NS_SA);
        namespaceForPrefix.put(SFConstants.NS_SF_PREFIX, SFConstants.NS_SF);
        namespaceForPrefix.put(SensorMLConstants.NS_SML_PREFIX, SensorMLConstants.NS_SML);
        namespaceForPrefix.put("soap", SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE);
        namespaceForPrefix.put(SosConstants.NS_SOS_PREFIX, Sos2Constants.NS_SOS_20);
        namespaceForPrefix.put("sosREST", "http://www.opengis.net/sosREST/1.0");
        namespaceForPrefix.put(SWEConstants.NS_SWE_PREFIX, SWEConstants.NS_SWE_20);
        namespaceForPrefix.put(SWEConstants.NS_SWES_PREFIX, SWEConstants.NS_SWES_20);
        namespaceForPrefix.put(W3CConstants.NS_XLINK_PREFIX, W3CConstants.NS_XLINK);
        namespaceForPrefix.put(W3CConstants.NS_XSI_PREFIX, W3CConstants.NS_XSI);
        namespaceForPrefix.put("xml", "http://www.w3.org/XML/1998/namespace");
        prefixForNamespace = CollectionHelper.reverse(namespaceForPrefix);

    }

    @Override
    public String getNamespaceURI(final String prefix) {
        return namespaceForPrefix.get(prefix);
    }

    @Override
    public String getPrefix(final String namespaceURI) {
        return prefixForNamespace.get(namespaceURI);
    }

    @Override
    public Iterator<String> getPrefixes(final String namespaceURI) {
        return Collections.singleton(getPrefix(namespaceURI)).iterator();
    }

}
