package de.hska.lat.robot.device.generic.headcontroller.mk1;


import de.hska.lat.robot.device.generic.headcontroller.mk1.HeadController;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.servo.view.ServoControlView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.device.viewer.DeviceView;


public class HeadControllerControlView extends DeviceView
{



/**
	 * 
	 */
	private static final long serialVersionUID = -769307551701950545L;



@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	HeadController headController;

	headController = (HeadController)robot.getDeviceOnName(HeadControllerConfiguration.getDeviceName());
			
	if (headController!=null)
	{
		makeDisplay(robot.getName(), headController);
		return(true);
	}
	else
	{
		makeErrorDisplay(HeadController.class.getName());
		return(false);
	}
	
}



public void makeDisplay(String robotName,HeadController headController)
{
	this.setDevice(robotName, headController);

	ServoSet servos;
	
	servos = headController.getServos();
	
	ComponentView atlasServo = ServoControlView.createView(servos.get(0));
	ComponentView axisServo = ServoControlView.createView(servos.get(1));

	
	addComponent(atlasServo,5,5);
	addComponentAtRight(atlasServo,axisServo,5,0);

	this.autoResize();	

}





}