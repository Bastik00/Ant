package de.hska.lat.ant.behavior.motion.action;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;


public class Move extends ArbiterAction
{

	private static final String name = "move";
	
	protected MovementVelocity movementVelocity = new MovementVelocity();
	
	protected MovementStepSize movementStepSize = new MovementStepSize();
	
	protected MovementHeading movementHeading = new MovementHeading();
	protected MovementStepCount movementStepCount = new MovementStepCount();
	protected MovementStepCount movementStepCounter = new MovementStepCount();
	
public Move(int priority)
{
	super(priority);
	
}

public void setStepCount(float stepsToWalk){
	this.movementStepCount.setValue(stepsToWalk);
	this.movementStepCounter.setValue(0.0f);
}

public float getStepCount(){
	return this.movementStepCounter.getValue();
}

public void countStep(){
	
	this.movementStepCounter.setValue(this.movementStepCounter.getValue()+1.0f);
	
	if(this.movementStepCount.getValue() > 0.0f	&&
			this.movementStepCounter.getValue() >= this.movementStepCount.getValue() )
	{
		this.completed();
	}
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
 * set step size for all servos movement
 * @param stepSize step size 
 */
public void setStepSize(float stepSize)
{
	this.movementStepSize.setValue(stepSize);
}


/**
 * get step size of this movement
 * @return step size 
 */
public float getStepSize()
{
	return(this.movementStepSize.getValue());
}


private float getStepSizeinPercentage(float stepSize) {
	float newStepSize = 1.0f;
	
	newStepSize = (float) (Math.abs(this.movementHeading.getValue()) / (Math.PI / 100)); 
	newStepSize = (stepSize * ((100.0f - newStepSize))) / 100.0f;
	return newStepSize;
}

public float getLeftStepSize() {
	float stepSizeInPercant = 1.0f;
	
	if(movementHeading.getValue() < 0)
	{
		stepSizeInPercant = getStepSizeinPercentage(stepSizeInPercant);
	}
	
	return stepSizeInPercant;
}



public float getRightStepSize() {
	float stepSizeInPercant = 1.0f;
	
	if(movementHeading.getValue() > 0)
	{
		stepSizeInPercant = getStepSizeinPercentage(stepSizeInPercant);
	}
	
	return stepSizeInPercant;
}

public void setHeading(float heading) {
	this.movementHeading.setValue(heading);
}


@Override
public String getName()
{
	return(Move.name);
}



@Override
public String toString ()
{
	String returnString;
	
	returnString = this.getName();
	
	return(returnString);
}


}
