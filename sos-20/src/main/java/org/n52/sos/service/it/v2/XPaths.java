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

import javax.xml.namespace.NamespaceContext;

import org.n52.sos.service.it.util.XPath;
import org.n52.sos.service.it.util.XPath;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class XPaths {
    public static final NamespaceContext CTX = new SosNamespaceContext();
    public static final XPath CAPABILITIES =
            new XPath("//sos:Capabilities", CTX);
    public static final XPath SERVICE_IDENTIFICATION_TITLE =
            new XPath("//sos:Capabilities/ows:ServiceIdentification/ows:Title", CTX);
    public static final XPath SERVICE_IDENTIFICATION_ABSTRACT =
            new XPath("//sos:Capabilities/ows:ServiceIdentification/ows:Abstract", CTX);
    public static final XPath SERVICE_IDENTIFICATION_SERVICE_TYPE =
            new XPath("//sos:Capabilities/ows:ServiceIdentification/ows:ServiceType", CTX);
    public static final XPath SERVICE_IDENTIFICATION_FEES =
            new XPath("//sos:Capabilities/ows:ServiceIdentification/ows:Fees", CTX);
    public static final XPath SERVICE_IDENTIFICATION_ACCESS_CONSTRAINTS =
            new XPath("//sos:Capabilities/ows:ServiceIdentification/ows:AccessConstraints", CTX);
    public static final XPath SERVICE_PROVIDER_PROVIDER_NAME =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ProviderName", CTX);
    public static final XPath SERVICE_PROVIDER_PROVIDER_SITE =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ProviderSite/@xlink:href", CTX);
    public static final XPath SERVICE_PROVIDER_INDIVIDUAL_NAME =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:IndividualName", CTX);
    public static final XPath SERVICE_PROVIDER_POSITION_NAME =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:PositionName", CTX);
    public static final XPath SERVICE_PROVIDER_VOICE =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Phone/ows:Voice", CTX);
    public static final XPath SERVICE_PROVIDER_DELIVERY_POINT =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:DeliveryPoint", CTX);
    public static final XPath SERVICE_PROVIDER_CITY =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:City", CTX);
    public static final XPath SERVICE_PROVIDER_ADMINISTRATIVE_AREA =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:AdministrativeArea", CTX);
    public static final XPath SERVICE_PROVIDER_POSTAL_CODE =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:PostalCode", CTX);
    public static final XPath SERVICE_PROVIDER_COUNTRY =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:Country", CTX);
    public static final XPath SERVICE_PROVIDER_EMAIL_ADDRESS =
            new XPath("//sos:Capabilities/ows:ServiceProvider/ows:ServiceContact/ows:ContactInfo/ows:Address/ows:ElectronicMailAddress", CTX);
    public static final XPath EXCEPTION_CODE =
            new XPath("//ows:ExceptionReport/ows:Exception/@exceptionCode", CTX);
    public static final XPath EXCEPTION_LOCATOR =
            new XPath("//ows:ExceptionReport/ows:Exception/@locator", CTX);
    public static final XPath EXCEPTION_TEXT =
            new XPath("//ows:ExceptionReport/ows:Exception/ows:ExceptionText", CTX);
    public static final XPath SOAP_ENVELOPE_BODY_FAULT_CODE_SUBCODE_VALUE =
            new XPath("//soap:Envelope/soap:Body/soap:Fault/soap:Code/soap:Subcode/soap:Value", CTX);
    public static final XPath ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_TEXT =
            new XPath("//soap:Envelope/soap:Body/soap:Fault/soap:Reason/soap:Text[@xml:lang='en']", CTX);
    public static final XPath ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_EXCEPTION_CODE =
            new XPath("//soap:Envelope/soap:Body/soap:Fault/soap:Detail/ows:Exception/@exceptionCode", CTX);
    public static final XPath ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_LOCATOR =
            new XPath("//soap:Envelope/soap:Body/soap:Fault/soap:Detail/ows:Exception/@locator", CTX);
    public static final XPath ENVELOPE_BODY_FAULT_DETAIL_EXCEPTION_EXCEPTION_TEXT =
            new XPath("//soap:Envelope/soap:Body/soap:Fault/soap:Detail/ows:Exception/ows:ExceptionText", CTX);

    private XPaths() {
    }
}
