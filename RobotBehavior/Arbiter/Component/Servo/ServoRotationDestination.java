package de.hska.lat.behavior.arbiter.component.servo;

import de.hska.lat.behavior.arbiter.action.ArbiterActionParameter;


public class ServoRotationDestination extends ArbiterActionParameter
{

	private static final String name = "destination";
	
	/**
	 * get name of this action parameter
	 * @return name of this action
	 */
	
	protected float destination = 0.0f;
	
	
	public String getName()
	{
		return(ServoRotationDestination.name);
	}

	
	/**
	 * get  rotation destination in radiant
	 * @return rotation destination in radiant/second
	 */
	public float getDestination()
	{
		return(this.destination);
	}
	

	/**
	 * set head rotation destination in radiant
	 * @param head rotation destination in radiant/second 
	 */
	public void setDestination(float destination)
	{
		this.destination = destination;
	}
	

	
	
	
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		returnString += "="+this.destination;
		
		return(returnString);
	}	
	
	
}
