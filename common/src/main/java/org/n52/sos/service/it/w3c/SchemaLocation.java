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
package org.n52.sos.service.it.w3c;

/**
 * Class represents a XML schema location with namespace and schema fileURL.
 * @author CarstenHollmann
 * @since 4.0.0
 */
public class SchemaLocation implements Comparable<SchemaLocation> {

    private static final String SPACE = " ";

    private String namespace;

    private String schemaFileUrl;
    
    /**
     * Constructor
     * @param namespace Namespace
     * @param schemaFile Schema file URL
     */
    public SchemaLocation(String namespace, String schemaFileUrl) {
        super();
        this.namespace = namespace;
        this.schemaFileUrl = schemaFileUrl;
    }

    /**
     * Get namespace of schema location
     * 
     * @return namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Set namespace of schema location
     * 
     * @param namespace
     *            Namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Get schema file URL
     * 
     * @return schema file URL
     */
    public String getSchemaFileUrl() {
        return schemaFileUrl;
    }

    /**
     * Set schema file URL
     * 
     * @param schemaFile
     *            Schema file URL
     */
    public void setSchemaFileUrl(String schemaFileUrl) {
        this.schemaFileUrl = schemaFileUrl;
    }

    /**
     * @return
     */
    public String getSchemaLocationString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getNamespace());
        builder.append(SPACE);
        builder.append(getSchemaFileUrl());
        return builder.toString();
    }

    @Override
    public int compareTo(SchemaLocation o) {
        if (o instanceof SchemaLocation) {
            if (getNamespace().equals(o.getNamespace()) && getSchemaFileUrl().equals(o.getSchemaFileUrl())) {
                return 0;
            }
            return 1;
        }
        return -1;
    }

}
