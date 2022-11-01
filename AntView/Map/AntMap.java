package de.hska.lat.ant.map;

import de.hska.lat.ant.metaData.AntFieldOfViews;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;

import de.hska.lat.robot.component.generic.navigator.Navigator;
import de.hska.lat.robot.display.generics.map2D.MapDisplay;

import de.hska.lat.robot.display.generics.map2D.fieldOfView.LuxFieldOfViewDrawer;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;


public class AntMap extends MapDisplay
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 9002877366185290660L;

	
	
	
public AntMap()
{
	super("name");
}


@Override	
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	
	
	MapDisplayMetaData metaData = new MapDisplayMetaData(robot);

	
	LuxFieldOfViewDrawer luxDrawer = new LuxFieldOfViewDrawer(((Robot)robot).getFieldOfViewOnId(AntFieldOfViews.LUX.getId()));
	
	metaData.addFieldOfViewDrawer(luxDrawer);
	
	this.robot = robot;
	
	this.setMetaData(metaData);
	
	return(true);
}


@Override
protected Navigator<?> getNavigator()
{
	// TODO Auto-generated method stub
	return null;
}





}
