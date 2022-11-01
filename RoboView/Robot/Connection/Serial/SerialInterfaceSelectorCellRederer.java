package de.hska.lat.robot.connection.serial.view;

import gnu.io.CommPortIdentifier;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;



public class SerialInterfaceSelectorCellRederer extends JLabel implements ListCellRenderer<Object>{

	

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


public SerialInterfaceSelectorCellRederer()
{
	setOpaque(true);
}
	
	
public Component getListCellRendererComponent(JList<? extends Object> list, Object object,
			int index, boolean isSelected, boolean arg4) 
{

		Color background;
        Color foreground;
        
        String cellText;
        CommPortIdentifier portIdentifier;
        
        portIdentifier=(CommPortIdentifier)object;
    
        if (portIdentifier != null)
        {
	        cellText= portIdentifier.getName();
	
	        if ( portIdentifier.isCurrentlyOwned()==true )
	    	{
	        	cellText+=" busy";
	    	}
        }
        else
        {
        	cellText = "NO PORTS";
        }
        
		setText(cellText);

		
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
            background = Color.RED;
            foreground = Color.WHITE;

        // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        };

        setBackground(background);
        setForeground(foreground);


		

		return this;

}



	
}
