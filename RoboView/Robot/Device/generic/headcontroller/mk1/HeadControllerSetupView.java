package de.hska.lat.robot.device.generic.headcontroller.mk1;




import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.currentSensor.CurrentSetupView;
import de.hska.lat.robot.component.distanceSensor.gp2D.Gp2DSetupView;
import de.hska.lat.robot.component.distanceSensor.gp2D.Gp2D;
import de.hska.lat.robot.component.servo.view.ServoSetupView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.device.viewer.DeviceView;

public class HeadControllerSetupView extends DeviceView
{




/**
	 * 
	 */
	private static final long serialVersionUID = 6127918988715757482L;




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
	
	
	this.setDevice( robotName, headController);
	
	//this.addValue(this.addServo(HeadControllerConfiguration.HEAD_ATLAS_SERVO.getName()));
	this.addComponent(this.addServo(HeadControllerConfiguration.HEAD_ATLAS_SERVO.getName()));
	this.addComponent(this.addServo(HeadControllerConfiguration.HEAD_AXIS_SERVO.getName()));

	/*
	ComponentView atlasCurrent = this.addCurrent(HeadControllerConfiguration.HEAD_ATLAS_CURRENT.getName());
	ComponentView axisCurrent = this.addCurrent(HeadControllerConfiguration.HEAD_AXIS_CURRENT.getName());

	
	ComponentView gp2Left_90  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_90.getName());
	ComponentView gp2Left_45  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_45.getName());
	ComponentView gp2Left_00  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_LEFT_00.getName());
	ComponentView gp2Right_00  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_00.getName());
	ComponentView gp2Right_45  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_45.getName());
	ComponentView gp2Right_90  = this.addGp2Sensor(HeadControllerConfiguration.HEAD_DISTANCE_SENSOR_RIGHT_90.getName());
	
*/
/*
	addComponent(atlasServo,5,5);
	addComponentAtRight(atlasServo,axisServo,5,0);

	addComponentAtBottom(atlasServo,atlasCurrent,5,5);
	addComponentAtRight(atlasCurrent,axisCurrent,5,0);
	
	

	addComponentAtBottom(atlasCurrent,gp2Left_00,0,5);
	addComponentAtBottom(gp2Left_00,gp2Left_45,0,5);
	addComponentAtBottom(gp2Left_45,gp2Left_90,0,5);

	
	addComponentAtBottom(axisCurrent,gp2Right_00,0,5);
	addComponentAtBottom(gp2Right_00,gp2Right_45,0,5);
	addComponentAtBottom(gp2Right_45,gp2Right_90,0,5);


	
	this.autoResize();	*/

}



public ComponentView addServo(String servoName )
{
	
	Servo servo = (Servo) this.device.findComponentOnName(servoName);
	
	if (servo!=null)
	{
		return(ServoSetupView.createView(servo));
	}
	else
	{
		return(new MissingComponentView(Servo.class.getName()));
	}
}




public ComponentView addCurrent(String currentName )
{
	
	CurrentSensor current = (CurrentSensor) this.device.findComponentOnName(currentName);
	
	return(CurrentSetupView.createView(current));

}




public ComponentView addGp2Sensor(String sensorName)
{
	
	Gp2D sensor = (Gp2D) this.device.findComponentOnName(sensorName);
	

	if (sensor!=null)
	{
		return( new Gp2DSetupView(sensor));
	}
	
	else
	{
		return(new MissingComponentView(Servo.class.getName()));
	}
	
}

/*
public ComponentView addTsl2561Sensor(String sensorName)
{
	
	
	Tsl2561 sensor = (Tsl2561) this.device.findComponentOnName(sensorName);
	
	

	if (sensor!=null)
	{
		Gp2SetupView gp2View = new Gp2SetupView(sensor.getComponentName(),sensor);
		
		sensor.addSensorListener(gp2View);
		sensor.addSetupListener(gp2View);
		return(gp2View);
	}
	
	else
	{
		return(new MissingComponentView(sensorName,Servo.class.getName()));
	}
	
}*/



}