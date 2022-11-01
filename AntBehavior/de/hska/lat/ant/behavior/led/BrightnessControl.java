package de.hska.lat.ant.behavior.led;

import de.hska.lat.behavior.control.BehaviorControl;

public class BrightnessControl extends BehaviorControl
{

	
	protected static final String NAME = "velocity control";
	
	
	
	
	
	
	
	public String getName()
	{
		return(BrightnessControl.NAME);
	}







	public void setValue(float value)
	{
		if (value>1.0f)
		{
			value = 1.0f;
		}
		else if (value<0.0f)
		{
			value = 0.0f;
		} 
		
		super.setValue(value);
		
	}
	

	
}
