package de.hska.lat.robot.display.generics.map.navigationPath;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JComboBox;

import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;




public class NavigationPointSelector extends JComboBox<NavigationPoint> implements FocusListener{



	

/**
	 * 
	 */
	private static final long serialVersionUID = 4468062608225200813L;

	protected NavigationPath navigationPath = new NavigationPath();

public 	NavigationPointSelector()
{
	
	super();
	
	this.setRenderer(new NavigationPointSelectorCellRederer());
	buildList();
	this.addFocusListener(this);
}



private void buildList()
{

	//String selectetPort;
	
	
	//selectetPort=getSelectedPortName();
	this.removeAllItems();

	
	for (NavigationPoint point : this.navigationPath)
	{
		this.addItem(point);
	}
	
	/*
	java.util.Enumeration <CommPortIdentifier> portEnum =  CommPortIdentifier.getPortIdentifiers();
    
	
    while ( portEnum.hasMoreElements() ) 
    	{
        CommPortIdentifier portIdentifier = portEnum.nextElement();
     

        
        
        if (portIdentifier.getPortType()==CommPortIdentifier.PORT_SERIAL)
        	{
        	
        	       	this.addItem(portIdentifier);
        	       	
        	}
    	}
    
    setSelectedPort(selectetPort);
    
    */
}


public void setPath(NavigationPath navigationPath)
{
	this.navigationPath = navigationPath;
	this. buildList();
}



public NavigationPoint getSelected()
{
	return((NavigationPoint)this.getSelectedItem());
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
