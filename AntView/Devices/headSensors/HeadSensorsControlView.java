package de.hska.lat.ant.devices.headSensors;

import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;



import de.hska.lat.robot.device.generic.motionController.MotionControllerControlView;



public class HeadSensorsControlView extends MotionControllerControlView
{



/**
	 * 
	 */
	private static final long serialVersionUID = -5337372269809597680L;

@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	HeadSensors hs;
	
	hs=(HeadSensors)robot.getDeviceOnName(AntDeviceId.HEAD_SENSORS.getName());
	
			
	if (hs!=null)
	{
		this.makeDisplay(robot.getName(), hs);
		return(true);
	}
	else
	{
		makeErrorDisplay(HeadSensors.class.getName());
		return(false);
	}
}





public void makeDisplay(String robotName, HeadSensors hs)
{
	
	this.setDevice(robotName, hs);
	



}







}
