package de.hska.lat.robot.component.actor.servo.analogServo;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.actor.servo.ServoSet;
import de.hska.lat.robot.component.actor.servo.protocol.ServoProtocol;

public class AnalogServoServoSet extends ServoSet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3959214926862805842L;

	public AnalogServoServoSet(ArrayList<ComponentMetaData> servos, ServoProtocol protocoll)
	{
		for (ComponentMetaData servo: servos)
		{
			this.add(new AnalogServo(servo, protocoll));
		}
		
	}
	
}