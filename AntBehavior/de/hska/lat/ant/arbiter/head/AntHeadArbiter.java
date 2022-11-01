package de.hska.lat.ant.arbiter.head;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.robot.component.actor.servo.Servo;


public class AntHeadArbiter extends ServoArbiter
{

public AntHeadArbiter(Ant ant)
{
	super(AntBehavoirPriority.SERVO_ARBITER_HEAD.getPriority(), (Servo) ant.getComponentOnGlobalId(AntComponents.HEAD_SERVO.getGlobalId()));
	this.name = "Head Servo";
}




	
}
