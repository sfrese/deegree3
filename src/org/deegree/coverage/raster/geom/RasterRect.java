//$HeadURL:svn+ssh://rbezema@svn.wald.intevation.org/deegree/deegree3/commons/trunk/src/org/deegree/model/coverage/raster/data/RasterRect.java $
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
   Department of Geography, University of Bonn
 and
   lat/lon GmbH

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package org.deegree.coverage.raster.geom;

/**
 * Simple data structure for a raster rectangle. Stores upper-left pixel coordinate and width and height.
 *
 * @author <a href="mailto:tonnhofer@lat-lon.de">Oliver Tonnhofer</a>
 * @author last edited by: $Author:rbezema $
 *
 * @version $Revision:11404 $, $Date:2008-04-23 15:38:27 +0200 (Mi, 23 Apr 2008) $
 */
public class RasterRect {
    /**
     * The x pixel position.
     */
    public int x;

    /**
     * The y pixel position.
     */
    public int y;

    /**
     * The width in pixel.
     */
    public int width;

    /**
     * The height in pixel.
     */
    public int height;

    /**
     * Creates a new RasterRect
     *
     * @param x
     *            upper-left pixel
     * @param y
     *            upper-left pixel
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     */
    public RasterRect( int x, int y, int width, int height ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new RasterRect with position 0, 0 and size 0, 0.
     */
    public RasterRect() {
        this( 0, 0, 0, 0 );
    }

    @Override
    public String toString() {
        return "{x=" + x + ",y=" + y + ", width=" + width + ", height=" + height + "}";
    }

}
