package de.hska.lat.robot.component.actor.servo.analogServo;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.actor.servo.protocol.ServoProtocol;
import de.hska.lat.robot.component.currentSensor.CurrentSensor;



public class AnalogServo extends de.hska.lat.robot.component.actor.servo.Servo {

	protected CurrentSensor currentMeter;

public AnalogServo(ComponentMetaData metaData, ServoProtocol protocol)
{
	super(metaData, protocol);
	
//	position = new ServoPosition(-90,90);
	
}




}
