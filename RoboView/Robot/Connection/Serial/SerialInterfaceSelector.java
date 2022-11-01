package de.hska.lat.robot.connection.serial.view;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;

import javax.swing.JComboBox;



public class SerialInterfaceSelector extends JComboBox<CommPortIdentifier> implements FocusListener{



	

/**
	 * 
	 */
	private static final long serialVersionUID = 4468062608225200813L;



public 	SerialInterfaceSelector()
{
	
	super();
	
	this.setRenderer(new SerialInterfaceSelectorCellRederer());
	buildList();
	this.addFocusListener(this);
}



private void buildList()
{

	String selectetPort;
	
	
	selectetPort=getSelectedPortName();
	this.removeAllItems();

	
	Enumeration <CommPortIdentifier> portEnum =  CommPortIdentifier.getPortIdentifiers();
    

	
    while ( portEnum.hasMoreElements() ) 
    	{
        CommPortIdentifier portIdentifier = portEnum.nextElement();
     

        
        
        if (portIdentifier.getPortType()==CommPortIdentifier.PORT_SERIAL)
        	{
        	
        	       	this.addItem(portIdentifier);
        	       	
        	}
    	}
    
    setSelectedPort(selectetPort);
}



public boolean setSelectedPort(String portToSelect )
{
	
	
	int enumerator;
	CommPortIdentifier portIdentifier;
	
	
	for (enumerator=0;enumerator<this.getItemCount();enumerator++)
		{
		portIdentifier=this.getItemAt(enumerator);
		if (portIdentifier.getName().equals(portToSelect))
			{
			this.setSelectedIndex(enumerator);
			return(true);
			}
		}
	
	return(false);
}





public String getSelectedPortName()
{
	CommPortIdentifier portIdentifier;
	
	
	portIdentifier=this.getItemAt(this.getSelectedIndex());
	if (portIdentifier!=null)
		{
		return(portIdentifier.getName());
		}
	
	return("");
}





@Override
public void focusGained(FocusEvent focusEvent) 
{
	buildList();
}



@Override
public void focusLost(FocusEvent focusEvent)
{
// unused
}

}
