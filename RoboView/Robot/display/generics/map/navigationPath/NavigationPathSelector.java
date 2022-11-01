package de.hska.lat.robot.display.generics.map.navigationPath;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;


import javax.swing.JComboBox;

import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;




public class NavigationPathSelector extends JComboBox<NavigationPath> implements FocusListener{



	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5661204609066017221L;
	
	


	protected ArrayList<NavigationPath> pathList;
	
public 	NavigationPathSelector()
{
	
	super();
	
	this.setRenderer(new NavigationPathSelectorCellRederer());
	buildList();
	this.addFocusListener(this);
}



private void buildList()
{

	//String selectetPort;
	
	
	//selectetPort=getSelectedPortName();
	this.removeAllItems();

	if (this.pathList!=null)
	{
		for (NavigationPath path : this.pathList)
		{
			this.addItem(path);
		}
	}

}


public void setPathList(ArrayList<NavigationPath> pathList)
{
	this.pathList = pathList;
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
