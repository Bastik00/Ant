package de.hska.lat.robot.device.generic.motionController;




import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.currentSensor.CurrentSensorSet;
import de.hska.lat.robot.component.currentSensor.CurrentSetupView;
import de.hska.lat.robot.component.servo.view.ServoSetupView;

import de.hska.lat.robot.device.generic.motionController.mark1.MotionController;
import de.hska.lat.robot.device.viewer.DeviceView;



public class MotionControllerSetupView extends DeviceView
{


/**
	 * 
	 */
	private static final long serialVersionUID = -3573452697056411482L;


	



public void makeDisplay(String robotName, MotionController motionController)
{
	
	this.setDevice(robotName, motionController);
	
	

	this.addServos(motionController.getServos());
	this.addCurrentSensors(motionController.getCurrentSensors());
	
	
}





protected void addServos(ServoSet servos)
{

	for (Servo servo : servos)
	{
		this.addComponent(ServoSetupView.createView(servo));
	}
}




protected void addCurrentSensors(CurrentSensorSet currentSensors)
{

	for (CurrentSensor sensor : currentSensors)
	{
		this.addComponent(CurrentSetupView.createView(sensor));
	}
}





}
