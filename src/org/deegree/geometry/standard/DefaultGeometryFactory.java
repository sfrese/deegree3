//$HeadURL: svn+ssh://mschneider@svn.wald.intevation.org/deegree/deegree3/commons/trunk/src/org/deegree/model/geometry/jtswrapper/JTSWrapperGeometryFactory.java $
/*----------------    FILE HEADER  ------------------------------------------
 This file is part of deegree.
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 Contact:

 Andreas Poth
 lat/lon GmbH
 Aennchenstr. 19
 53177 Bonn
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

package org.deegree.geometry.standard;

import java.util.Arrays;
import java.util.List;

import org.deegree.commons.types.Length;
import org.deegree.crs.CRS;
import org.deegree.feature.gml.Angle;
import org.deegree.geometry.AbstractGeometryFactory;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.composite.CompositeCurve;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.composite.CompositeSolid;
import org.deegree.geometry.composite.CompositeSurface;
import org.deegree.geometry.multi.MultiCurve;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiLineString;
import org.deegree.geometry.multi.MultiPoint;
import org.deegree.geometry.multi.MultiPolygon;
import org.deegree.geometry.multi.MultiSolid;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.Curve;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.LineString;
import org.deegree.geometry.primitive.LinearRing;
import org.deegree.geometry.primitive.OrientableCurve;
import org.deegree.geometry.primitive.OrientableSurface;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.PolyhedralSurface;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Solid;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.primitive.Tin;
import org.deegree.geometry.primitive.TriangulatedSurface;
import org.deegree.geometry.primitive.curvesegments.Arc;
import org.deegree.geometry.primitive.curvesegments.ArcByBulge;
import org.deegree.geometry.primitive.curvesegments.ArcByCenterPoint;
import org.deegree.geometry.primitive.curvesegments.ArcString;
import org.deegree.geometry.primitive.curvesegments.ArcStringByBulge;
import org.deegree.geometry.primitive.curvesegments.BSpline;
import org.deegree.geometry.primitive.curvesegments.Bezier;
import org.deegree.geometry.primitive.curvesegments.Circle;
import org.deegree.geometry.primitive.curvesegments.CircleByCenterPoint;
import org.deegree.geometry.primitive.curvesegments.Clothoid;
import org.deegree.geometry.primitive.curvesegments.CubicSpline;
import org.deegree.geometry.primitive.curvesegments.CurveSegment;
import org.deegree.geometry.primitive.curvesegments.Geodesic;
import org.deegree.geometry.primitive.curvesegments.GeodesicString;
import org.deegree.geometry.primitive.curvesegments.Knot;
import org.deegree.geometry.primitive.curvesegments.LineStringSegment;
import org.deegree.geometry.primitive.curvesegments.OffsetCurve;
import org.deegree.geometry.primitive.surfacepatches.PolygonPatch;
import org.deegree.geometry.primitive.surfacepatches.Rectangle;
import org.deegree.geometry.primitive.surfacepatches.SurfacePatch;
import org.deegree.geometry.primitive.surfacepatches.Triangle;
import org.deegree.geometry.standard.aggregate.DefaultMultiCurve;
import org.deegree.geometry.standard.aggregate.DefaultMultiGeometry;
import org.deegree.geometry.standard.aggregate.DefaultMultiLineString;
import org.deegree.geometry.standard.aggregate.DefaultMultiPoint;
import org.deegree.geometry.standard.aggregate.DefaultMultiPolygon;
import org.deegree.geometry.standard.aggregate.DefaultMultiSolid;
import org.deegree.geometry.standard.aggregate.DefaultMultiSurface;
import org.deegree.geometry.standard.composite.DefaultCompositeCurve;
import org.deegree.geometry.standard.composite.DefaultCompositeGeometry;
import org.deegree.geometry.standard.composite.DefaultCompositeSolid;
import org.deegree.geometry.standard.composite.DefaultCompositeSurface;
import org.deegree.geometry.standard.curvesegments.AffinePlacement;
import org.deegree.geometry.standard.curvesegments.DefaultArc;
import org.deegree.geometry.standard.curvesegments.DefaultArcByBulge;
import org.deegree.geometry.standard.curvesegments.DefaultArcByCenterPoint;
import org.deegree.geometry.standard.curvesegments.DefaultArcString;
import org.deegree.geometry.standard.curvesegments.DefaultArcStringByBulge;
import org.deegree.geometry.standard.curvesegments.DefaultBSpline;
import org.deegree.geometry.standard.curvesegments.DefaultBezier;
import org.deegree.geometry.standard.curvesegments.DefaultCircle;
import org.deegree.geometry.standard.curvesegments.DefaultCircleByCenterPoint;
import org.deegree.geometry.standard.curvesegments.DefaultClothoid;
import org.deegree.geometry.standard.curvesegments.DefaultCubicSpline;
import org.deegree.geometry.standard.curvesegments.DefaultGeodesic;
import org.deegree.geometry.standard.curvesegments.DefaultGeodesicString;
import org.deegree.geometry.standard.curvesegments.DefaultLineStringSegment;
import org.deegree.geometry.standard.curvesegments.DefaultOffsetCurve;
import org.deegree.geometry.standard.primitive.DefaultCurve;
import org.deegree.geometry.standard.primitive.DefaultEnvelope;
import org.deegree.geometry.standard.primitive.DefaultLineString;
import org.deegree.geometry.standard.primitive.DefaultLinearRing;
import org.deegree.geometry.standard.primitive.DefaultOrientableCurve;
import org.deegree.geometry.standard.primitive.DefaultOrientableSurface;
import org.deegree.geometry.standard.primitive.DefaultPoint;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.deegree.geometry.standard.primitive.DefaultPolyhedralSurface;
import org.deegree.geometry.standard.primitive.DefaultRing;
import org.deegree.geometry.standard.primitive.DefaultSolid;
import org.deegree.geometry.standard.primitive.DefaultSurface;
import org.deegree.geometry.standard.primitive.DefaultTin;
import org.deegree.geometry.standard.primitive.DefaultTriangulatedSurface;
import org.deegree.geometry.standard.surfacepatches.DefaultPolygonPatch;
import org.deegree.geometry.standard.surfacepatches.DefaultRectangle;
import org.deegree.geometry.standard.surfacepatches.DefaultTriangle;

/**
 * 
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author:$
 * 
 * @version $Revision:$, $Date:$
 */
public class DefaultGeometryFactory extends AbstractGeometryFactory {

    @Override
    public ArcByCenterPoint createArcByCenterPoint( Point midPoint, Length radius, Angle startAngle, Angle endAngle ) {
        return new DefaultArcByCenterPoint( midPoint, radius, startAngle, endAngle );
    }

    @Override
    public ArcString createArcString( List<Point> points ) {
        return new DefaultArcString( points );
    }

    @Override
    public ArcStringByBulge createArcStringByBulge( List<Point> points, double[] bulges, List<Point> normals ) {
        return new DefaultArcStringByBulge( points, bulges, normals );
    }

    @Override
    public CompositeCurve createCompositeCurve( String id, CRS crs, List<Curve> memberCurves ) {
        return new DefaultCompositeCurve( id, crs, memberCurves );
    }

    @Override
    public CompositeSolid createCompositeSolid( String id, CRS crs, List<Solid> memberSolids ) {
        return new DefaultCompositeSolid( id, crs, memberSolids );
    }

    @Override
    public CompositeSurface createCompositeSurface( String id, CRS crs, List<Surface> memberSurfaces ) {
        return new DefaultCompositeSurface( id, crs, memberSurfaces );
    }

    @Override
    public CompositeGeometry<GeometricPrimitive> createCompositeGeometry( String id, CRS crs,
                                                                          List<GeometricPrimitive> memberPrimitives ) {
        return new DefaultCompositeGeometry( id, crs, memberPrimitives );
    }

    @Override
    public Curve createCurve( String id, Point[][] coordinates, CRS crs ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Curve createCurve( String id, CurveSegment[] segments, CRS crs ) {
        return new DefaultCurve( id, crs, Arrays.asList( segments ) );
    }

    @Override
    public Envelope createEnvelope( double[] min, double[] max, double precision, CRS crs ) {
        return new DefaultEnvelope( null, crs, new DefaultPoint( null, crs, min ), new DefaultPoint( null, crs, max ) );
    }

    @Override
    public Envelope createEnvelope( double[] min, double[] max, CRS crs ) {
        return new DefaultEnvelope( null, crs, new DefaultPoint( null, crs, min ), new DefaultPoint( null, crs, max ) );
    }

    @Override
    public Envelope createEnvelope( double minx, double miny, double maxx, double maxy, CRS crs ) {
        return createEnvelope( new double[] { minx, miny }, new double[] { maxx, maxy }, crs );
    }

    @Override
    public Envelope createEnvelope( List<Double> lowerCorner, List<Double> upperCorner, CRS crs ) {
        if ( lowerCorner.size() != upperCorner.size() ) {
            throw new IllegalArgumentException( "LowerCorner must be of same dimension as upperCorner." );
        }
        double[] lc = new double[lowerCorner.size()];
        double[] uc = new double[upperCorner.size()];
        for ( int i = 0; i < lc.length; ++i ) {
            lc[i] = lowerCorner.get( i );
            uc[i] = upperCorner.get( i );
        }
        return createEnvelope( lc, uc, crs );
    }

    @Override
    public Envelope createEnvelope( double minx, double miny, double maxx, double maxy, double precision,
                                    CRS crs ) {
        return createEnvelope( new double[] { minx, miny }, new double[] { maxx, maxy }, precision, crs );
    }

    @Override
    public Envelope createEnvelope( String id, SurfacePatch patch ) {
        throw new UnsupportedOperationException( "not yet supported" );
    }

    @Override
    public LineStringSegment createLineStringSegment( List<Point> points ) {
        return new DefaultLineStringSegment( points );
    }

    @Override
    public Point createPoint( String id, double x, double y, CRS crs ) {
        return new DefaultPoint( id, crs, new double[] { x, y } );
    }

    @Override
    public Point createPoint( String id, double x, double y, double z, CRS crs ) {
        return new DefaultPoint( id, crs, new double[] { x, y, z } );
    }

    @Override
    public Point createPoint( String id, double[] coordinates, double precision, CRS crs ) {
        return new DefaultPoint( id, crs, coordinates );
    }

    @Override
    public Point createPoint( String id, double[] coordinates, CRS crs ) {
        return new DefaultPoint( id, crs, coordinates );
    }

    @Override
    public Surface createSurface( String id, List<SurfacePatch> patches, CRS crs ) {
        return new DefaultSurface( id, crs, patches );
    }

    @Override
    public BSpline createBSpline( List<Point> points, int degree, List<Knot> knots, boolean isPolynomial ) {
        return new DefaultBSpline( points, degree, knots, isPolynomial );
    }

    @Override
    public Arc createArc( Point p1, Point p2, Point p3 ) {
        return new DefaultArc( p1, p2, p3 );
    }

    @Override
    public ArcByBulge createArcByBulge( Point p1, Point p2, double bulge, Point normal ) {
        return new DefaultArcByBulge( p1, p2, bulge, normal );
    }

    @Override
    public Bezier createBezier( List<Point> points, int degree, Knot knot1, Knot knot2 ) {
        return new DefaultBezier( points, degree, knot1, knot2 );
    }

    @Override
    public Circle createCircle( Point p1, Point p2, Point p3 ) {
        return new DefaultCircle( p1, p2, p3 );
    }

    @Override
    public CircleByCenterPoint createCircleByCenterPoint( Point midPoint, Length radius, Angle startAngle ) {
        return new DefaultCircleByCenterPoint( midPoint, radius, startAngle );
    }

    @Override
    public Clothoid createClothoid( AffinePlacement referenceLocation, double scaleFactor, double startParameter,
                                    double endParameter ) {
        return new DefaultClothoid( referenceLocation, scaleFactor, startParameter, endParameter );
    }

    @Override
    public CubicSpline createCubicSpline( List<Point> points, Point vectorAtStart, Point vectorAtEnd ) {
        return new DefaultCubicSpline( points, vectorAtStart, vectorAtEnd );
    }

    @Override
    public Geodesic createGeodesic( Point p1, Point p2 ) {
        return new DefaultGeodesic( p1, p2 );
    }

    @Override
    public GeodesicString createGeodesicString( List<Point> points ) {
        return new DefaultGeodesicString( points );
    }

    @Override
    public OffsetCurve createOffsetCurve( Curve baseCurve, Point direction, Length distance ) {
        return new DefaultOffsetCurve( baseCurve, direction, distance );
    }

    @Override
    public Ring createRing( String id, CRS crs, List<Curve> members ) {
        return new DefaultRing( id, crs, members );
    }

    @Override
    public LinearRing createLinearRing( String id, CRS crs, List<Point> points ) {
        return new DefaultLinearRing( id, crs, points );
    }

    @Override
    public LineString createLineString( String id, CRS crs, List<Point> points ) {
        return new DefaultLineString( id, crs, points );
    }

    @Override
    public OrientableCurve createOrientableCurve( String id, CRS crs, Curve baseCurve, boolean isReversed ) {
        return new DefaultOrientableCurve( id, crs, baseCurve, isReversed );
    }

    @Override
    public Polygon createPolygon( String id, CRS crs, Ring exteriorRing, List<Ring> interiorRings ) {
        return new DefaultPolygon( id, crs, exteriorRing, interiorRings );
    }

    @Override
    public PolygonPatch createPolygonPatch( Ring exteriorRing, List<Ring> interiorRings ) {
        return new DefaultPolygonPatch( exteriorRing, interiorRings );
    }

    @Override
    public Rectangle createRectangle( LinearRing exterior ) {
        return new DefaultRectangle( exterior );
    }

    @Override
    public Triangle createTriangle( LinearRing exterior ) {
        return new DefaultTriangle( exterior );
    }

    @Override
    public OrientableSurface createOrientableSurface( String id, CRS crs, Surface baseSurface,
                                                      boolean isReversed ) {
        return new DefaultOrientableSurface( id, crs, baseSurface, isReversed );
    }

    @Override
    public PolyhedralSurface createPolyhedralSurface( String id, CRS crs, List<PolygonPatch> memberPatches ) {
        return new DefaultPolyhedralSurface( id, crs, memberPatches );
    }

    @Override
    public TriangulatedSurface createTriangulatedSurface( String id, CRS crs, List<Triangle> memberPatches ) {
        return new DefaultTriangulatedSurface( id, crs, memberPatches );
    }

    @Override
    public Tin createTin( String id, CRS crs, List<List<LineStringSegment>> stopLines,
                          List<List<LineStringSegment>> breakLines, Length maxLength, List<Point> controlPoints ) {
        return new DefaultTin( id, crs, stopLines, breakLines, maxLength, controlPoints );
    }

    @Override
    public Solid createSolid( String id, CRS crs, Surface exteriorSurface, List<Surface> interiorSurfaces ) {
        return new DefaultSolid( id, crs, exteriorSurface, interiorSurfaces );
    }

    @Override
    public MultiPoint createMultiPoint( String id, CRS crs, List<Point> members ) {
        return new DefaultMultiPoint( id, crs, members );
    }

    @Override
    public MultiCurve createMultiCurve( String id, CRS crs, List<Curve> members ) {
        return new DefaultMultiCurve( id, crs, members );
    }

    @Override
    public MultiLineString createMultiLineString( String id, CRS crs, List<LineString> members ) {
        return new DefaultMultiLineString( id, crs, members );
    }

    @Override
    public MultiSurface createMultiSurface( String id, CRS crs, List<Surface> members ) {
        return new DefaultMultiSurface( id, crs, members );
    }

    @Override
    public MultiPolygon createMultiPolygon( String id, CRS crs, List<Polygon> members ) {
        return new DefaultMultiPolygon( id, crs, members );
    }

    @Override
    public MultiSolid createMultiSolid( String id, CRS crs, List<Solid> members ) {
        return new DefaultMultiSolid( id, crs, members );
    }

    @Override
    public MultiGeometry<Geometry> createMultiGeometry( String id, CRS crs, List<Geometry> members ) {
        return new DefaultMultiGeometry<Geometry>( id, crs, members );
    }

}
