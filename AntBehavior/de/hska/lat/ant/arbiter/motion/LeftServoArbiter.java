package de.hska.lat.ant.arbiter.motion;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.robot.component.actor.servo.Servo;


public class LeftServoArbiter extends ServoArbiter
{

public LeftServoArbiter(Ant ant)
{
	super(AntBehavoirPriority.SERVO_ARBITER_LEFT.getPriority(), (Servo) ant.getComponentOnGlobalId(AntComponents.LEFT_SERVO.getGlobalId()));
	this.name = "left servo";
}




	
}
