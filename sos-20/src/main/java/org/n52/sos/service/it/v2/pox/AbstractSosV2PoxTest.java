package org.n52.sos.service.it.v2.pox;

import org.n52.sos.service.it.ServiceConstants;
import org.n52.sos.service.it.pox.AbstractPoxComplianceTest;

/**
 * Abstract class for SOS 2.0 POX request tests
 *
 * @author Christian Autermann <c.autermann@52north.org>
 * @since 4.0.0
 *
 */
public abstract class AbstractSosV2PoxTest extends AbstractPoxComplianceTest {
    public static final String SERVICE = ServiceConstants.SOS;
    public static final String VERSION = ServiceConstants.V20;
    public static final String SERVICE_PARAMETER = "service";
}
