//$HeadURL$
/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001-2009 by:
 EXSE, Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 Contact:

 Andreas Poth  
 lat/lon GmbH 
 Aennchenstr. 19
 53115 Bonn
 Germany
 E-Mail: poth@lat-lon.de

 Prof. Dr. Klaus Greve
 Department of Geography
 University of Bonn
 Meckenheimer Allee 166
 53115 Bonn
 Germany
 E-Mail: greve@giub.uni-bonn.de


 ---------------------------------------------------------------------------*/
package org.deegree.feature.xpath;

import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

/**
 * <a href="http://jaxen.codehaus.org/">Jaxen</a> XPath implementation for {@link Feature} objects.
 * <p>
 * This is the main entry point for matching an XPath expression against a {@link Feature}. Let <code>fc</code> be a
 * {@link Feature} (or a {@link FeatureCollection} object) that you want to match against. Create a compiled XPath
 * object, then match it against one or more context nodes using the {@link #selectNodes(Object)} method, as in the
 * following example:
 * </p>
 * 
 * <pre>
 * XPath xpath = new FeatureXPath( &quot;gml:featureMember/app:Philosopher/app:friend/app:Philosopher//app:name&quot; );
 * xpath.setNamespaceContext( nsContext );
 * List&lt;Node&gt; selectedNodes = xpath.selectNodes( new FeatureNode( null, fc ) );
 * </pre>
 * 
 * @see FeatureNode
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author:$
 * 
 * @version $Revision:$, $Date:$
 */
public class FeatureXPath extends BaseXPath {

    private static final long serialVersionUID = 2352279998281119079L;

    /**
     * Create a new <code>FeatureXPath</code> from an XPath expression string.
     * 
     * @param xpathExpr
     *            the XPath expression
     * @throws JaxenException
     *             if there is a syntax error in the expression
     */
    public FeatureXPath( String xpathExpr ) throws JaxenException {
        super( xpathExpr, FeatureNavigator.getInstance() );
    }
}
