package de.hska.lat.robot.device.generic.motionController;




import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.currentSensor.CurrentSensorSet;
import de.hska.lat.robot.device.generic.motionController.mark1.MotionController;
import de.hska.lat.robot.device.viewer.DeviceView;
import de.hska.lat.robot.value.view.current.CurrentSensorDataView;
import de.hska.lat.robot.value.view.servo.ServoValueView;





public class MotionControllerDataView extends DeviceView
{






/**
	 * 
	 */
	private static final long serialVersionUID = 9087438736398362143L;




public void makeDisplay(String robotName,MotionController motionController)
{
	
	this.setDevice(robotName, motionController);
	
	

	this.addServos(motionController.getServos());
	this.addCurrentSensors(motionController.getCurrentSensors());
	
	
}





protected void addServos(ServoSet servos)
{

	for (Servo servo : servos)
	{
		this.addValue(new ServoValueView(servo.getServoValue(), servo.getDestinationValue(), false));
	}
}




protected void addCurrentSensors(CurrentSensorSet currentSensors)
{

	for (CurrentSensor sensor : currentSensors)
	{
		//this.addValue(new CurrentValueView(sensor.getValue(), false, "" ));
		this.addComponent(CurrentSensorDataView.createView(sensor));
	}
	
}





}
