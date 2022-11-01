package de.hska.lat.ant.behavior.motion.action;

import de.hska.lat.behavior.arbiter.action.ArbiterActionParameter;
import de.hska.lat.robot.value.FloatValue;


public class MovementStepCount extends ArbiterActionParameter
{

	private static final String name = "step size";
	
	/**
	 * get name of this action parameter
	 * @return name of this action
	 */
	public String getName()
	{
		return(MovementStepCount.name);
	}
	

	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		returnString += "="+FloatValue.toFormatedFractionString(this.getValue()*100,1)+"%";
		
		return(returnString);
	}	
	
	
}
