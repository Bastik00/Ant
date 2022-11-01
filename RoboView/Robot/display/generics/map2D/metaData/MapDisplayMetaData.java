package de.hska.lat.robot.display.generics.map2D.metaData;

import java.util.ArrayList;

import de.hska.lat.navigation.Destination;
import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.generic.navigator.Navigator;


import de.hska.lat.robot.display.generics.map.navigationPath.NavigationPathDrawer;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.origin.MapOriginDrawer;
import de.hska.lat.robot.display.generics.map2D.destination.DestinationDrawer;
import de.hska.lat.robot.display.generics.map2D.fieldOfView.FieldOfViewDrawer;
import de.hska.lat.robot.display.generics.map2D.pose.PoseDrawer;



public class MapDisplayMetaData //extends DisplayMetaData
{

	
	
	protected ArrayList<FieldOfViewDrawer>  filedOfViewDrawers = new ArrayList<FieldOfViewDrawer>(); 
	
	protected DisplayData displayData = new DisplayData();
	protected Destination destination;
	protected DestinationDrawer destinationDrawer; 
	protected PoseDrawer poseDrawer;
	
	protected NavigationPathDrawer navigationPathDrawer;
	
	protected NavigationPath navigationPath = new NavigationPath(); 
	
	protected Navigator<?> navigator;
	
	protected MapOriginDrawer originDrawer;
	
	
	
	
public MapDisplayMetaData(AbstractRobot<?,?,?> robot ) //DisplayMetaData metaData) 
{
	//super(metaData.getId(),metaData.getName());
	
	this.originDrawer = new MapOriginDrawer(this.displayData);
	this.poseDrawer=new PoseDrawer(this.displayData, robot.getPose());
	this.navigationPathDrawer = new NavigationPathDrawer(this);
}




/**
 * add new FieldOfView Drawer, connect it with map display DisplayData
 * @param filedOfViewDrawer filedOfViewDrawer to by add to this MapView
 */

public void addFieldOfViewDrawer(FieldOfViewDrawer filedOfViewDrawer)
{
	filedOfViewDrawer.setDisplayData(displayData);
	this.filedOfViewDrawers.add(filedOfViewDrawer);
	
}



public ArrayList<FieldOfViewDrawer>  getFieldOfViewDrawers()
{
	return (this.filedOfViewDrawers);
}




public DisplayData getDisplayData() 
{
	return(this.displayData);
	
}


public void setPoseDrawer(PoseDrawer poseDrawer) 
{
	this.poseDrawer= poseDrawer;
}


public PoseDrawer getPoseDrawer() 
{
	return (this.poseDrawer);
}




public DestinationDrawer getDestinationDrawer() 
{
	return(this.destinationDrawer);
}



public void setDestinationDrawer(DestinationDrawer destinationDrawer)
{
	this.destinationDrawer = destinationDrawer;
}


public Destination getDestination() 
{
	return(this.destination);
}



public void setDestination(Destination destination)
{
	this.destination = destination;
}




public void setNavigator(Navigator<?> navigator) 
{
	this.navigator=navigator;
}



public Navigator<?> getNavigator() 
{
	return(this.navigator);
	
} 

public void setNavigationPath(NavigationPath navigationPath) 
{
	this.navigationPath = navigationPath;
}


public NavigationPath getNavigationPath() 
{
	return (this.navigationPath);
	
}






public void  setNavigationPathDrawer(NavigationPathDrawer navigationPathDrawer) 
{
	this.navigationPathDrawer=navigationPathDrawer;
}



public NavigationPathDrawer getNavigationPathDrawer() 
{
	return (this.navigationPathDrawer);
}





public MapOriginDrawer getOriginDrawer()
{
	return (this.originDrawer);
}




public void setOriginDrawer(MapOriginDrawer originDrawer)
{
	this.originDrawer=originDrawer;
}






}
