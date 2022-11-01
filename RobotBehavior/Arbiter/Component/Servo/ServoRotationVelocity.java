package de.hska.lat.behavior.arbiter.component.servo;

import de.hska.lat.behavior.arbiter.action.ArbiterActionParameter;


public class ServoRotationVelocity extends ArbiterActionParameter
{

	private static final String name = "rotation velocity";
	
	/**
	 * get name of this action parameter
	 * @return name of this action
	 */
	
	protected float velocity = 0.0f;
	
	
	public String getName()
	{
		return(ServoRotationVelocity.name);
	}

	
	/**
	 * get rotation velocity in radiant
	 * @return rotation velocity as radiant
	 */
	public float getVelocity()
	{
		return(this.velocity);
	}
	

	/**
	 * set rotation velocity in radiant
	 * @param velocity rotation velocity as radiant
	 */
	public void setVelocity(float velocity)
	{
		this.velocity = velocity;
	}
	

	
	
	
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		returnString += "="+this.velocity;
		
		return(returnString);
	}	
	
	
}
