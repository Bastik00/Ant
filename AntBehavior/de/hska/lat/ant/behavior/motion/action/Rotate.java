package de.hska.lat.ant.behavior.motion.action;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;


public class Rotate extends ArbiterAction
{

	private static final String name = "rotate";
	
	protected MovementVelocity movementVelocity = new MovementVelocity();
	protected MovementHeading movementRotation = new MovementHeading();
	
public Rotate(int priority)
{
	super(priority);
}


/**
 * set velocity for this movement
 * @param velocity movement velocity
 */
public void setVelocity(float velocity)
{
	this.movementVelocity.setValue(velocity);
}


/**
 * get Velocity of this movement
 * @return movement velocity
 */
public float getVelocity()
{
	return(this.movementVelocity.getValue());
}

/**
 * @param heading
 */
public void setRotation(float rotation) {
	this.movementRotation.setValue(rotation);
}

/**
 * @return rotation as radiant
 */
public float getRotation(){
	return this.movementRotation.getValue();
}


@Override
public String getName()
{
	return(Rotate.name);
}



@Override
public String toString ()
{
	String returnString;
	
	returnString = this.getName();
	
	return(returnString);
}


}
