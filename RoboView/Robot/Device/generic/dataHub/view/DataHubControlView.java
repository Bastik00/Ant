package de.hska.lat.robot.device.generic.dataHub.view;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.device.generic.dataHub.DataHub;
import de.hska.lat.robot.device.viewer.DeviceView;

public class DataHubControlView extends DeviceView
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1057936252156327605L;


/**
	 * 
	 */




@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	DataHub dataHub;

	dataHub = (DataHub)robot.getDeviceOnId(DataHub.ID);
			
	if (dataHub!=null)
	{
		makeDisplay(robot.getName(), dataHub);
		return(true);
	}
	else
	{
		makeErrorDisplay(DataHub.class.getName());
		return(false);
	}
	
}


public void makeDisplay(String robotName, DataHub dataHub)
{
	setDevice(robotName, dataHub);
	
	this.autoResize();
}	

}
