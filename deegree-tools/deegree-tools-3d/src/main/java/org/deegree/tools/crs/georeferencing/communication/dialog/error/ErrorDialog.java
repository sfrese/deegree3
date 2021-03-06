//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

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
package org.deegree.tools.crs.georeferencing.communication.dialog.error;

import java.awt.Component;
import java.awt.image.ImageObserver;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Custom class to provide the functionality to show errors. <br>
 * TODO implement an own ErrorDialog because this one hears just on the parentFrame but should hear on its near standing
 * parent which can be a Dialog as well
 * 
 * 
 * @author <a href="mailto:thomas@lat-lon.de">Steffen Thomas</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class ErrorDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of {@code Dialog} to show error messages.
     * 
     * @param com
     *            the parent component of this error message.
     * @param messageType
     *            one of the types specified by the Java API
     * @param messageText
     *            the customized message that should be shown.
     */
    public ErrorDialog( Component com, int messageType, String messageText ) {

        switch ( messageType ) {

        case ImageObserver.ERROR:
            JOptionPane.showMessageDialog( com, messageText, "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

}
