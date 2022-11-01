package de.hska.lat.ant.arbiter.motion;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.robot.component.actor.servo.Servo;


public class CenterServoArbiter extends ServoArbiter
{

public CenterServoArbiter(Ant ant)
{
	super(AntBehavoirPriority.SERVO_ARBITER_CENTER.getPriority(), (Servo) ant.getComponentOnGlobalId(AntComponents.CENTER_SERVO.getGlobalId()));
	this.name = "center servo";
}




	
}
