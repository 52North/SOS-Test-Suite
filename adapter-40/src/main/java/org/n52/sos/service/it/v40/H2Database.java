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
package org.n52.sos.service.it.v40;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.rules.ExternalResource;
import org.n52.sos.ds.hibernate.H2Configuration;
import org.n52.sos.ds.hibernate.entities.ObservationType;
import org.n52.sos.ds.hibernate.util.ScrollableIterable;
import org.n52.sos.ogc.ows.OwsExceptionReport;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class H2Database extends ExternalResource {
    private final String[] defaultObservationTypes = {
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_CountObservation",
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement",
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_SWEArrayObservation",
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TruthObservation",
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_CategoryObservation",
        "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation"
    };

    @Override
    protected void before() throws Throwable {
        H2Configuration.assertInitialized();
    }

    @Override
    protected void after() {
        H2Configuration.recreate();
    }

    /**
     * Removes all entries of entity {@link ObservationType} from the database.
     *
     * @throws OwsExceptionReport
     */
    protected void removeObservationTypes() throws OwsExceptionReport {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            final ScrollableIterable<ObservationType> i =
                    ScrollableIterable.fromCriteria(session
                    .createCriteria(ObservationType.class));
            for (final ObservationType o : i) {
                session.delete(o);
            }
            i.close();
            session.flush();
            transaction.commit();
        } catch (final HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw he;
        } finally {
            returnSession(session);
        }
    }

    /**
     * Add some default entries of entity {@link ObservationType} to the test
     * database.
     *
     * @throws OwsExceptionReport
     * @see {@link #defaultObservationTypes}
     */
    protected void addObservationTypes() throws OwsExceptionReport {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            for (int i = 0; i < defaultObservationTypes.length; i++) {
                final ObservationType ot = new ObservationType();
                ot.setObservationTypeId(i);
                ot.setObservationType(defaultObservationTypes[i]);
                session.save(ot);
            }
            session.flush();
            transaction.commit();
        } catch (final HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw he;
        } finally {
            returnSession(session);
        }
    }

    public void recreate() {
        H2Configuration.recreate();
    }

    public void truncate() {
        H2Configuration.truncate();
    }

    public Session getSession() {
        return H2Configuration.getSession();
    }

    public void returnSession(Session session) {
        H2Configuration.returnSession(session);
    }
}
