package de.hska.lat.ant.behavior.motion.action;


public class MovementHeading
{

	private static final String name = "rotation velocity";
	
	/**
	 * get name of this action parameter
	 * @return name of this action
	 */
	
	protected float velocity = 0.0f;
	
	
	public String getName()
	{
		return(MovementHeading.name);
	}

	
	/**
	 * get rotation velocity in radiant
	 * @return rotation velocity as radiant
	 */
	public float getValue()
	{
		return(this.velocity);
	}
	

	/**
	 * set rotation velocity in radiant
	 * @param velocity rotation velocity as radiant
	 */
	public void setValue(float velocity)
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
