//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2012 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -
 and
 - Occam Labs UG (haftungsbeschränkt) -

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

 Occam Labs UG (haftungsbeschränkt)
 Godesberger Allee 139, 53175 Bonn
 Germany

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.services.wms.controller.capabilities;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static org.deegree.commons.xml.CommonNamespaces.WMSNS;
import static org.deegree.commons.xml.CommonNamespaces.XLINK_PREFIX;
import static org.deegree.commons.xml.CommonNamespaces.XLNNS;
import static org.deegree.commons.xml.XMLAdapter.writeElement;
import static org.deegree.services.wms.controller.capabilities.Capabilities111XMLAdapter.writeDimensions;
import static org.deegree.services.wms.controller.capabilities.WmsCapabilities111SpatialMetadataWriter.writeSrsAndEnvelope;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.deegree.commons.ows.metadata.DatasetMetadata;
import org.deegree.commons.tom.ows.CodeType;
import org.deegree.commons.tom.ows.LanguageString;
import org.deegree.commons.utils.DoublePair;
import org.deegree.commons.utils.Pair;
import org.deegree.commons.utils.StringPair;
import org.deegree.commons.utils.StringUtils;
import org.deegree.geometry.metadata.SpatialMetadata;
import org.deegree.layer.metadata.LayerMetadata;
import org.deegree.rendering.r2d.legends.Legends;
import org.deegree.services.metadata.OWSMetadataProvider;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.services.wms.controller.capabilities.theme.LayerMetadataQueryable;
import org.deegree.style.se.unevaluated.Style;
import org.deegree.theme.Theme;
import org.deegree.theme.Themes;

/**
 * Responsible for writing out theme capabilities.
 * 
 * @author <a href="mailto:schmitz@occamlabs.de">Andreas Schmitz</a>
 * @author last edited by: $Author: stranger $
 * 
 * @version $Revision: $, $Date: $
 */
class WmsCapabilities111ThemeWriter {

    private WMSController controller;

    private String getUrl;

    private Capabilities111XMLAdapter capWriter;

    private OWSMetadataProvider metadata;

    WmsCapabilities111ThemeWriter( WMSController controller, String getUrl, Capabilities111XMLAdapter capWriter,
                                   OWSMetadataProvider metadata ) {
        this.controller = controller;
        this.getUrl = getUrl;
        this.capWriter = capWriter;
        this.metadata = metadata;
    }

    void writeTheme( XMLStreamWriter writer, Theme theme )
                            throws XMLStreamException {
        writer.writeStartElement( "Layer" );
        LayerMetadata md = theme.getMetadata();
        // TODO think about a push approach instead of a pull approach
        LayerMetadata lmd = null;
        int layerQueryable = 0;
        for ( org.deegree.layer.Layer l : Themes.getAllLayers( theme ) ) {
            layerQueryable |= LayerMetadataQueryable.analyseQueryable( l.getMetadata() );
            if ( lmd == null ) {
                lmd = l.getMetadata();
            } else {
                lmd.merge( l.getMetadata() );
            }
        }
        md.merge( lmd );
        LayerMetadataQueryable.applyQueryable( md, layerQueryable );

        if ( md.isQueryable() && md.getName() != null ) {
            writer.writeAttribute( "queryable", "1" );
        }
        if ( md.getCascaded() != 0 ) {
            writer.writeAttribute( "cascaded", md.getCascaded() + "" );
        }
        writeLayerMetadata( writer, md, lmd );

        SpatialMetadata smd = md.getSpatialMetadata();
        writeSrsAndEnvelope( writer, smd.getCoordinateSystems(), smd.getEnvelope() );
        writeDimensions( writer, md.getDimensions() );

        writeMetadataUrls( writer, theme );
        writeStyles( writer, md );
        writeScaleDenominators( writer, md, theme );

        for ( Theme t : theme.getThemes() ) {
            writeTheme( writer, t );
        }
        writer.writeEndElement();
    }

    private void writeLayerMetadata( XMLStreamWriter writer, LayerMetadata md, LayerMetadata lmd )
                            throws XMLStreamException {
        if ( md.getName() != null ) {
            writeElement( writer, "Name", md.getName() );
        }
        writeElement( writer, "Title", md.getDescription().getTitles().get( 0 ).getString() );
        List<LanguageString> abs = md.getDescription().getAbstracts();
        if ( lmd != null && ( abs == null || abs.isEmpty() ) ) {
            abs = lmd.getDescription().getAbstracts();
        }
        if ( abs != null && !abs.isEmpty() ) {
            writeElement( writer, "Abstract", abs.get( 0 ).getString() );
        }
        writeKeywords( writer, md, lmd );
    }

    private void writeKeywords( XMLStreamWriter writer, LayerMetadata md, LayerMetadata lmd )
                            throws XMLStreamException {
        List<Pair<List<LanguageString>, CodeType>> kws = md.getDescription().getKeywords();
        if ( lmd != null && ( kws == null || kws.isEmpty() || kws.get( 0 ).first.isEmpty() ) ) {
            kws = lmd.getDescription().getKeywords();
        }
        if ( kws != null && !kws.isEmpty() && !kws.get( 0 ).first.isEmpty() ) {
            writer.writeStartElement( "KeywordList" );
            for ( LanguageString ls : kws.get( 0 ).first ) {
                writeElement( writer, "Keyword", ls.getString() );
            }
            writer.writeEndElement();
        }
    }

    private void writeMetadataUrls( XMLStreamWriter writer, Theme theme )
                            throws XMLStreamException {
        String id = null, name = null;

        inner: for ( org.deegree.layer.Layer l : theme.getLayers() ) {
            if ( l.getMetadata().getName() != null ) {
                name = l.getMetadata().getName();
            }
            if ( l.getMetadata().getMetadataId() != null ) {
                id = l.getMetadata().getMetadataId();
                break inner;
            }
        }

        writeMetadataFromProvider( writer, name );

        if ( controller.getMetadataURLTemplate() != null ) {
            String mdurlTemplate = controller.getMetadataURLTemplate();
            if ( mdurlTemplate.isEmpty() ) {
                mdurlTemplate = getUrl;
                if ( !( mdurlTemplate.endsWith( "?" ) || mdurlTemplate.endsWith( "&" ) ) ) {
                    mdurlTemplate += "?";
                }
                mdurlTemplate += "service=CSW&request=GetRecordById&version=2.0.2&outputSchema=http%3A//www.isotc211.org/2005/gmd&elementSetName=full&id=${metadataSetId}";
            }
            if ( id != null ) {
                writeMetadataUrl( writer, StringUtils.replaceAll( mdurlTemplate, "${metadataSetId}", id ) );
            }
        }
    }

    // please note that this does NOT support writing of description metadata at the moment!
    private void writeMetadataFromProvider( XMLStreamWriter writer, String name )
                            throws XMLStreamException {
        if ( name == null || metadata == null ) {
            return;
        }

        Map<String, String> auths = metadata.getExternalMetadataAuthorities();
        DatasetMetadata md = metadata.getDatasetMetadata( new QName( name ) );

        if ( md != null ) {
            for ( StringPair ext : md.getExternalUrls() ) {
                String url = auths.get( ext.first );
                writer.writeStartElement( "AuthorityURL" );
                writer.writeAttribute( "name", ext.first );
                writer.writeStartElement( "OnlineResource" );
                writer.writeNamespace( XLINK_PREFIX, XLNNS );
                writer.writeAttribute( XLNNS, "type", "simple" );
                writer.writeAttribute( XLNNS, "href", url );
                writer.writeEndElement();
                writer.writeEndElement();
            }
            for ( StringPair ext : md.getExternalUrls() ) {
                writer.writeStartElement( "Identifier" );
                writer.writeAttribute( "authority", ext.first );
                writer.writeCharacters( ext.second );
                writer.writeEndElement();
            }

            List<DatasetMetadata> mds = metadata.getAllDatasetMetadata( new QName( name ) );
            for ( DatasetMetadata datasetMetadata : mds ) {
                String url = datasetMetadata.getUrl();
                writeMetadataUrl( writer, url );
            }
        }
    }

    private void writeMetadataUrl( XMLStreamWriter writer, String url )
                            throws XMLStreamException {
        writer.writeStartElement( "MetadataURL" );
        writer.writeAttribute( "type", "TC211" );  
        writeElement( writer, "Format", "text/xml" ); // no format explicit specified in OGC-WMS-1.1.1 but 'text/xml' given in OGC-WMS-1.3.0 example on page 82
        writer.writeStartElement( "OnlineResource" );
        writer.writeNamespace( XLINK_PREFIX, XLNNS );
        writer.writeAttribute( XLNNS, "type", "simple" );
        writer.writeAttribute( XLNNS, "href", url );
        writer.writeEndElement();
        writer.writeEndElement();
    }

    private void writeStyles( XMLStreamWriter writer, LayerMetadata md )
                            throws XMLStreamException {
        Map<String, Style> legends = md.getLegendStyles();
        for ( Entry<String, Style> e : md.getStyles().entrySet() ) {
            if ( e.getKey() == null || e.getKey().isEmpty() ) {
                continue;
            }
            Style ls = e.getValue();
            if ( legends.get( e.getKey() ) != null ) {
                ls = legends.get( e.getKey() );
            }
            Pair<Integer, Integer> p = new Legends().getLegendSize( ls );
            capWriter.writeStyle( writer, e.getKey(), e.getKey(), p, md.getName(), e.getValue() );
        }
    }

    private void writeScaleDenominators( XMLStreamWriter writer, LayerMetadata md, Theme theme )
                            throws XMLStreamException {
        DoublePair hint = md.getScaleDenominators();
        // use layers' settings only if not set for theme
        if ( hint.first.isInfinite() && hint.second.isInfinite() ) {
            hint = new DoublePair( hint.second, hint.first );
            for ( org.deegree.layer.Layer l : theme.getLayers() ) {
                hint.first = Math.min( l.getMetadata().getScaleDenominators().first, hint.first );
                hint.second = Math.max( l.getMetadata().getScaleDenominators().second, hint.second );
            }
        }
        if ( !hint.first.isInfinite() || !hint.second.isInfinite() ) {
            double fac = 0.00028;
            writer.writeStartElement( "ScaleHint" );
            writer.writeAttribute( "min", Double.toString( hint.first.isInfinite() ? MIN_VALUE : hint.first * fac ) );
            writer.writeAttribute( "max", Double.toString( hint.second.isInfinite() ? MAX_VALUE : hint.second * fac ) );
            writer.writeEndElement();
        }
    }

}
