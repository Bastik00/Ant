package de.hska.lat.ant.behavior.motion.action;

import de.hska.lat.behavior.arbiter.action.ArbiterActionParameter;
import de.hska.lat.robot.value.FloatValue;


public class MovementVelocity extends ArbiterActionParameter
{

	private static final String name = "movement velocity";
	
	/**
	 * get name of this action parameter
	 * @return name of this action
	 */
	

	
	
	public String getName()
	{
		return(MovementVelocity.name);
	}

	
	

	/**
	 * set movement velocity. The range of this velocity lays between -1...+1 if given value
	 * exceed this limit. value will by internally corrected by floor or cell function.   
	 * @param velocity movement velocity
	 */
	@Override
	public void setValue(float value)
	{
		if (value>1)
		{
			value=1;
		}
		else if (value<-1)
		{
			value=-1;
		}
		
		this.value = value;
	}
	

	
	
	
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		returnString += "="+FloatValue.toFormatedFractionString(this.value*100,1)+"%";
		
		return(returnString);
	}	
	
	
}
