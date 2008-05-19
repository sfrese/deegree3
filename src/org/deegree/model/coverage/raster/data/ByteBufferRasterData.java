//$HeadURL$
/*----------------    FILE HEADER  ------------------------------------------
 This file is part of deegree.
 Copyright (C) 2001-2007 by:
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
package org.deegree.model.coverage.raster.data;

import java.nio.ByteBuffer;

/**
 * This abstract class implements the RasterData interface for ByteBuffer based raster.
 * 
 * <p>
 * It is based on java.nio.ByteBuffer and implements common get- and set-operations on the data. The different
 * InterleaveTypes are implemented by additional subclasses.
 * 
 * <p>
 * get- and set-operations are implemented naive and access all data sample-wise. For efficiency subclasses should
 * overwrite methods that access more than one sample and leverage the knowledge of the internal storage format
 * (interleaving).
 * 
 * <p>
 * TODO: Only implements access to byte and float data at the moment. Copy float methods for other data types and change
 * 'Float/float' to short, int, long or double. These types are supported by ByteBuffer and the according methods only
 * differ in the name of the type (eg. getFloat, getInt, getDouble,...). Opposed to the methods for bytes, which lack
 * the type in the method names (eg. only get()).
 * 
 * <p>
 * Also this implementation is able to store a sub-view on another {@link ByteBufferRasterData}, resp. ByteBuffer. With
 * this feature you are able to create subsets without copying the data. Though the current deegree SimpleRaster
 * implementation makes no use of it.
 * 
 * @author <a href="mailto:tonnhofer@lat-lon.de">Oliver Tonnhofer</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 * 
 */
public abstract class ByteBufferRasterData implements RasterData {

    /**
     * the width of the raster
     */
    protected int width;

    /**
     * the height of the raster
     */
    protected int height;

    /**
     * The number of bands in this raster.
     */
    protected int bands;

    /**
     * The {@link DataType} of this raster.
     */
    protected DataType dataType;

    /**
     * The NODATA value.
     */
    protected byte[] nodata;

    /**
     * The raster data itself.
     */
    protected ByteBuffer data;

    /**
     * Creates a new single-band ByteBufferRasterData instance.
     * 
     * @param dataType
     *            sample data type
     * @param width
     *            size of the raster
     * @param height
     *            size of the raster
     */
    protected ByteBufferRasterData( int width, int height, DataType dataType ) {
        this( width, height, 1, dataType );
    }

    /**
     * Creates a new ByteBufferRasterData instance.
     * 
     * @param dataType
     *            sample data type
     * @param width
     *            size of the raster
     * @param height
     *            size of the raster
     * @param bands
     *            number of bands
     */
    protected ByteBufferRasterData( int width, int height, int bands, DataType dataType ) {
        this.width = width;
        this.height = height;

        this.bands = bands;
        this.dataType = dataType;

        this.nodata = new byte[dataType.getSize() * bands];
        this.data = createByteBuffer();
    }

    private ByteBuffer createByteBuffer() {
        return ByteBuffer.allocate( getBufferSize() );
    }

    public abstract ByteBufferRasterData createCompatibleRasterData( int width, int height, int bands );

    public ByteBufferRasterData createCompatibleRasterData( int bands ) {
        return createCompatibleRasterData( width, height, bands );
    }

    public ByteBufferRasterData createCompatibleRasterData() {
        return createCompatibleRasterData( width, height, bands );
    }

    public final int getBands() {
        return bands;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final DataType getDataType() {
        return dataType;
    }

    /**
     * Returns the size of the ByteBuffer in bytes.
     * 
     * @return size of the buffer
     */
    public final int getBufferSize() {
        return width * height * bands * dataType.getSize();
    }

    public abstract InterleaveType getInterleaveType();

    /**
     * Retruns the byte offset to the next pixel.
     * 
     * @return byte offset to next pixel
     */
    public abstract int getPixelStride();

    /**
     * Returns the byte offset to the next sample of the same pixel.
     * 
     * @return byte offset to sample in the next band (same pixel)
     */
    public abstract int getBandStride();

    /**
     * Returns the byte offset to the next row (same column, same sample)
     * 
     * @return byte offset to next row (same column, same sample)
     */
    public abstract int getLineStride();

    /**
     * @return The internal ByteBuffer.
     */
    public ByteBuffer getByteBuffer() {
        return data;
    }

    /**
     * Checks whether a given rect is inside the raster, throws an exception if outside.
     * 
     * @param x
     *            The x position of the rect.
     * @param y
     *            The y position of the rect.
     * @param width
     *            The width of the rect.
     * @param height
     *            The height of the rect.
     * @throws IndexOutOfBoundsException
     *             if the given rect is outside the raster
     */
    protected final void checkBoundsEx( int x, int y, int width, int height )
                            throws IndexOutOfBoundsException {
        if ( !checkBounds( x, y, width, height ) ) {
            throw new IndexOutOfBoundsException( "request out of bounds" );
        }
    }

    /**
     * Checks whether a given rect is inside the raster.
     * 
     * @param x
     *            The x position of the rect.
     * @param y
     *            The y position of the rect.
     * @param width
     *            The width of the rect.
     * @param height
     *            The height of the rect.
     * @return <code>true</code> if the given rect is inside the raster, else <code>false</code>
     */
    protected final boolean checkBounds( int x, int y, int width, int height ) {
        if ( ( x < width ) || ( y < height ) || ( x < 0 ) || ( y < 0 ) ) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the position of a pixel in the ByteBuffer.
     * 
     * This method calculates the position of a pixel and returns the offset to this pixel in bytes. Use this method for
     * direct access to ByteBuffers.
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return byte offset to the pixel with the specified coordinate
     */
    public final int calculatePos( int x, int y ) {
        return y * getLineStride() + x * getPixelStride();
    }

    /**
     * Calculates the position of a sample in the ByteBuffer.
     * 
     * This method calculates the position of a pixel and returns the offset to this pixel in bytes. Use this method for
     * direct access to ByteBuffers.
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param band
     *            band index of the sample
     * @return byte offset to the sample with the specified coordinate
     */
    public final int calculatePos( int x, int y, int band ) {
        return y * getLineStride() + x * getPixelStride() + band * getBandStride();
    }

    /**
     * Calculates the position of a pixel in a view (FloatBuffer, etc.) of the ByteBuffer.
     * 
     * This method considers different sample sizes (eg. byte, float) and returns the position in sample stides (not
     * byte strides). Use this method to get proper positions for ByteBuffer views like FloatBuffer, ShortBuffer, etc..
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return offset to the pixel with the specified coordinates
     */
    public final int calculateViewPos( int x, int y ) {
        return calculatePos( x, y ) / dataType.getSize();// TODO
    }

    /**
     * Calculates the position of a sample in a view (FloatBuffer, etc.) of the ByteBuffer.
     * 
     * This method considers different sample sizes (eg. byte, float) and returns the position in sample stides (not
     * byte strides). Use this method to get proper positions for ByteBuffer-views like FloatBuffer, ShortBuffer, etc..
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param band
     *            band index of the sample
     * @return offset to the sample with the specified coordinates
     */
    public final int calculateViewPos( int x, int y, int band ) {
        return calculatePos( x, y, band ) / dataType.getSize();// TODO
    }

    public byte[] getNullPixel( byte[] result ) {
        if ( result == null ) {
            result = new byte[nodata.length];
        }
        System.arraycopy( nodata, 0, result, 0, result.length );
        return result;
    }

    public byte[] getSample( int x, int y, int band, byte[] result ) {
        if ( result == null ) {
            result = new byte[dataType.getSize()];
        }
        int pos = calculatePos( x, y, band );

        data.position( pos );
        data.get( result );
        return result;
    }

    public void setSample( int x, int y, int band, byte[] value ) {
        data.position( calculatePos( x, y, band ) );
        data.put( value );
    }



    public byte getByteSample( int x, int y, int band ) {
        return data.get( calculatePos( x, y, band ) );
    }

    public short getShortSample( int x, int y, int band ) {
        return data.getShort( calculatePos( x, y, band ) );
    }

    public float getFloatSample( int x, int y, int band ) {
        return data.getFloat( calculatePos( x, y, band ) );
    }

    public void setByteSample( int x, int y, int band, byte value ) {
        data.put( calculatePos( x, y, band ), value );
    }

    public void setShortSample( int x, int y, int band, short value ) {
        data.putShort( calculatePos( x, y, band ), value );
    }

    public void setFloatSample( int x, int y, int band, float value ) {
        data.putFloat( calculatePos( x, y, band ), value );
    }

    public void setPixel( int x, int y, byte[] result ) {
        int sampleSize = getDataType().getSize();
        for ( int b = 0; b < getBands(); b++ ) {
            data.position( calculatePos( x, y, b ) );
            data.put( result, b * sampleSize, sampleSize );
        }
    }

    public byte[] getPixel( int x, int y, byte[] result ) {
        int numBands = getBands();
        int sampleSize = getDataType().getSize();
        if ( result == null ) {
            result = new byte[numBands * sampleSize];
        }
        if ( 0 > x || x >= width || 0 > y || y >= width ) {
            System.arraycopy( nodata, 0, result, 0, result.length );
            return result;
        }
        for ( int b = 0; b < numBands; b++ ) {
            data.position( calculatePos( x, y, b ) );
            data.get( result, b * sampleSize, sampleSize );
        }

        return result;
    }
    
    public byte[] getBytePixel( int x, int y, byte[] result ) {
        if ( result == null ) {
            result = new byte[getBands()];
        }
        for ( int band = 0; band < getBands(); band++ ) {
            result[band] = getByteSample( x, y, band );
        }
        return result;
    }

    public short[] getShortPixel( int x, int y, short[] result ) {
        if ( result == null ) {
            result = new short[getBands()];
        }
        for ( int band = 0; band < getBands(); band++ ) {
            result[band] = getShortSample( x, y, band );
        }
        return result;
    }

    public float[] getFloatPixel( int x, int y, float[] result ) {
        if ( result == null ) {
            result = new float[getBands()];
        }
        for ( int band = 0; band < getBands(); band++ ) {
            result[band] = getFloatSample( x, y, band );
        }
        return result;
    }
   
    public void setBytePixel( int x, int y, byte[] pixel ) {
        for ( int band = 0; band < bands; band++ ) {
            data.put( calculatePos( x, y, band ), pixel[band] );
        }
    }

    public void setShortPixel( int x, int y, short[] pixel ) {
        for ( int band = 0; band < bands; band++ ) {
            data.putShort( calculatePos( x, y, band ), pixel[band] );
        }
    }

    public void setFloatPixel( int x, int y, float[] pixel ) {
        for ( int band = 0; band < bands; band++ ) {
            data.putFloat( calculatePos( x, y, band ), pixel[band] );
        }
    }

    @Override
    public byte[] getBytes( int x, int y, int width, int height, int band, byte[] result ) {
        if ( result == null ) {
            result = new byte[width * height];
        }
        if ( !checkBounds( x, y, width, height ) ) {
            System.arraycopy( nodata, band * dataType.getSize(), result, 0, result.length );
            return result;
        }
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                result[( 2 * i ) + j] = data.get( calculatePos( x + j, y + i, band ) );
            }
        }
        return result;
    }

    @Override
    public short[] getShorts( int x, int y, int width, int height, int band, short[] result ) {
        if ( result == null ) {
            result = new short[width * height];
        }
        if ( !checkBounds( x, y, width, height ) ) {
            System.arraycopy( nodata, band * dataType.getSize(), result, 0, result.length );
            return result;
        }
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                result[( 2 * i ) + j] = data.getShort( calculatePos( x + j, y + i, band ) );
            }
        }
        return result;
    }

    @Override
    public float[] getFloats( int x, int y, int width, int height, int band, float[] result ) {
        if ( result == null ) {
            result = new float[width * height];
        }
        if ( !checkBounds( x, y, width, height ) ) {
            System.arraycopy( nodata, band * dataType.getSize(), result, 0, result.length );
            return result;
        }
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                result[( 2 * i ) + j] = data.getFloat( calculatePos( x + j, y + i, band ) );
            }
        }
        return result;
    }

    public void setBytes( int x, int y, int width, int height, int band, byte[] values ) {
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                data.put( calculatePos( x + j, y + i, band ), values[( 2 * i ) + j] );
            }
        }
    }

    public void setShorts( int x, int y, int width, int height, int band, short[] values ) {
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                data.putShort( calculatePos( x + j, y + i, band ), values[( 2 * i ) + j] );
            }
        }
    }

    public void setFloats( int x, int y, int width, int height, int band, float[] values ) {
        for ( int i = 0; i < height; i++ ) {
            for ( int j = 0; j < width; j++ ) {
                data.putFloat( calculatePos( x + j, y + i, band ), values[( 2 * i ) + j] );
            }
        }
    }
    
    public ByteBufferRasterData getSubset( int x0, int y0, int width, int height ) {
        ByteBufferRasterData result = createCompatibleRasterData( width, height, bands );
        result.setSubset( 0, 0, width, height, this, x0, y0 );
        return result;
    }

    public ByteBufferRasterData getSubset( RasterRect env ) {
        return this.getSubset( env.x, env.y, env.width, env.height );
    }

    public ByteBufferRasterData getSubset( int x0, int y0, int width, int height, int band ) {
        ByteBufferRasterData result = createCompatibleRasterData( width, height, 1 );
        result.setSubset( 0, 0, width, height, 0, this, band, x0, y0 );
        return result;
    }

    public void setSubset( int x0, int y0, int width, int height, RasterData sourceRaster ) {
        setSubset( x0, y0, width, height, sourceRaster, 0, 0 );
    }

    public void setSubset( int x0, int y0, int width, int height, int dstBand, RasterData sourceRaster, int srcBand ) {
        setSubset( x0, y0, width, height, dstBand, sourceRaster, srcBand, 0, 0 );
    }

    public void setSubset( int x0, int y0, int width, int height, RasterData sourceRaster, int xOffset, int yOffset ) {
        byte[] tmp = new byte[getDataType().getSize()];
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                for ( int band = 0; band < this.bands; band++ ) {
                    tmp = sourceRaster.getSample( x  + xOffset, y+yOffset, band, tmp );
                    setSample( x0 + x, y0 + y, band, tmp );
                }
            }
        }
    }

    public void setSubset( int x0, int y0, int width, int height, int dstBand, RasterData sourceRaster, int srcBand,
                           int xOffset, int yOffset ) {
        byte[] tmp = new byte[getDataType().getSize()];
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                tmp = sourceRaster.getSample( x + xOffset, y + yOffset, srcBand, tmp );
                setSample( x0 + x, y0 + y, dstBand, tmp );
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append( "RasterData: type " + dataType + ", " );
        result.append( "size " + width + "x" + height + "(" + width + "x" + height + ")" );
        result.append( ", interleaving " + getInterleaveType() );

        return result.toString();
    }
}
