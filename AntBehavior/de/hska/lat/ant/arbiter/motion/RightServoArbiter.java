package de.hska.lat.ant.arbiter.motion;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.robot.component.actor.servo.Servo;


public class RightServoArbiter extends ServoArbiter
{

public RightServoArbiter(Ant ant)
{
	super(AntBehavoirPriority.SERVO_ARBITER_RIGHT.getPriority(), (Servo) ant.getComponentOnGlobalId(AntComponents.RIGHT_SERVO.getGlobalId()));
	this.name = "right servo";
}




	
}
