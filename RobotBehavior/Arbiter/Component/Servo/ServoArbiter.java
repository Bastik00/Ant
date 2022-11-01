package de.hska.lat.behavior.arbiter.component.servo;



import java.util.ArrayList;

import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.ComponentArbiter;
import de.hska.lat.behavior.arbiter.action.ActionWait;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoDestinationValue;
import de.hska.lat.robot.component.actor.servo.ServoVelocityValue;
import de.hska.lat.robot.value.servo.ServoAngleValue;



public class ServoArbiter extends ComponentArbiter
{

	

	
	protected ServoVelocityValue velocityControl;
	protected ServoDestinationValue destinationControl;
	protected ServoAngleValue servoPosition;
	
	protected static final int TIME_BASE 	= 100; 
	protected static final int MOTION_DELAY = 10;
	protected float lastPosition;
	protected int motionDelay; 
	
	protected Servo servo;
	
public ServoArbiter(int id, Servo servo)
{
	super(id, ServoArbiter.TIME_BASE);

	this.servo = servo;
	
	this.velocityControl = servo.getVelocityControlValue();
	this.destinationControl = servo.getDestinationControlValue();
	
	this.servoPosition =  servo.getAngleValue();
	
	this.start();
}





/**
 * execute given action
 * @param action action to be executed
 */
protected void executeAction(ArbiterAction action)
{

	
	this.activeAction = action;
	
	if (action.isInitializing())
	{
		this.motionDelay = ServoArbiter.MOTION_DELAY;
		action.activate();
		this.setActive();
	}
	
	if (action.isActive())
	{
		if (action instanceof ServoRotate)
		{
			this.doRotate((ServoRotate) action);
		}
		else if (action instanceof ServoRotateTo)
		{
			this.doRotateTo((ServoRotateTo) action);
		}
		else if (action instanceof ServoHold)
		{
			this.doHold();
		}
		else if (action instanceof ActionWait)
		{
			this.doWait();
		}
	}
}



protected boolean checkFalure()
{

	
	if (this.lastPosition == this.servoPosition.getRotation())
	{
		this.motionDelay--;
	}
	
	this.lastPosition = this.servoPosition.getRotation();
	
	if (this.motionDelay==0)
	{
		this.activeAction.failed();
		this.setError();
		return(true);
	}
	
	
	return(false);
}


	
protected void doRotate(ServoRotate action)
{
	if (!servo.isOn())
	{
		this.servo.remote_servoOn();
	}
	
	
	if ((!this.servoPosition.isAtMax()) && (action.getVelocity()>0))
	{
		this.velocityControl.setValue(action.getVelocity());
	}
	else if ((!this.servoPosition.isAtMin()) && (action.getVelocity()<0))
	{
		this.velocityControl.setValue(action.getVelocity());
	}
	
	checkFalure();
}



protected void doRotateTo(ServoRotateTo action)
{

	if (!servo.isOn())
	{
		this.servo.remote_servoOn();
	}
	
	if (this.servoPosition.getRotation() != action.getDestination())
	{
		this.destinationControl.setVelocity(action.getVelocity());
		this.destinationControl.setDestination(action.getDestination());

		checkFalure();
	}


}



protected void doHold()
{
	
	if (!servo.isOn())
	{
		this.servo.remote_servoOn();
	}
	
	this.velocityControl.setValue(0);
	this.servo.remote_servoOn();
}


protected void doWait()
{
	if (servo.isOn())
	{
		this.servo.remote_servoOff();
	}
	this.velocityControl.setValue(0);
	this.setReady();

}






public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	
	return(list);
}







	
}
