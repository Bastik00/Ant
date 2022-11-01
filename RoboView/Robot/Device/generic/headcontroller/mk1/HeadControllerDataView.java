package de.hska.lat.robot.device.generic.headcontroller.mk1;



import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.distanceSensor.gp2D.Gp2D;
import de.hska.lat.robot.component.distanceSensor.srf10.Srf10;
import de.hska.lat.robot.component.generic.distance.view.DistanceValueView;
import de.hska.lat.robot.component.generic.lux.view.LuxSensorDataView;
import de.hska.lat.robot.component.luxSensor.tsl2561.Tsl2561;


import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.device.viewer.DeviceView;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.servo.ServoValueView;


public class HeadControllerDataView extends DeviceView
{


/**
	 * 
	 */
	private static final long serialVersionUID = 8695418769174241942L;




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



public void makeDisplay(String robotName, HeadController headController)
{
	
	
	setDevice( robotName, headController);
	/*
	
	ComponentView atlasServo = this.addServo(HeadControllerConfiguration.HEAD_ATLAS_SERVO.getName());
	ComponentView axisServo = this.addServo(HeadControllerConfiguration.HEAD_AXIS_SERVO.getName());

	*/




//	this.add(atlasServo);
//	this.add(axisServo);



	this.addValue(this.addServo(HeadControllerConfiguration.HEAD_ATLAS_SERVO.getName()));
	this.addValue(this.addServo(HeadControllerConfiguration.HEAD_AXIS_SERVO.getName()));
	
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_90.getName()));
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_45.getName()));
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_00.getName()));

	this.addComponent(this.addTsl2561Sensor(HeadControllerConfiguration.HEAD_LIGHT_SENSOR_LEFT_45.getName()));
	this.addComponent(this.addTsl2561Sensor(HeadControllerConfiguration.HEAD_LIGHT_SENSOR_LEFT_00.getName()));
	
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_00.getName()));
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_45.getName()));
	this.addValue(this.addGp2DSensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_90.getName()));


	this.addComponent(this.addTsl2561Sensor(HeadControllerConfiguration.HEAD_LIGHT_SENSOR_RIGHT_00.getName()));
	this.addComponent(this.addTsl2561Sensor(HeadControllerConfiguration.HEAD_LIGHT_SENSOR_RIGHT_45.getName()));
	

	this.addValue(this.adSrf10Sensor(HeadControllerConfiguration.HEAD_ULTRASONIC_SENSOR.getName()));


}





public ValueView<?> addServo(String servoName )
{
	
	Servo servo = (Servo) this.device.findComponentOnName(servoName);
	
	return(new ServoValueView(servo.getServoValue(), servo.getDestinationValue(), false));

}






public ValueView<?> addGp2DSensor(String sensorName)
{
	
	
	Gp2D sensor = (Gp2D) this.device.findComponentOnName(sensorName);
	
	return( DistanceValueView.createView(sensor.getDistanceValue(),false));


	
}




public ValueView<?> adSrf10Sensor(String sensorName)
{

	Srf10 sensor =  (Srf10) this.device.findComponentOnName(sensorName);

	return( DistanceValueView.createView(sensor.getDistanceValue(),false));



	
}




public ComponentView addTsl2561Sensor(String sensorName)
{
	
	
	Tsl2561 sensor = (Tsl2561) this.device.findComponentOnName(sensorName);
	
	
	return(LuxSensorDataView.createView(sensor));


	
}







}
