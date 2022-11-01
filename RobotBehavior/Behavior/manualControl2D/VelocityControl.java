package de.hska.lat.behavior.behavior.manualControl2D;

import de.hska.lat.behavior.control.BehaviorControl;

public class VelocityControl extends BehaviorControl
{

	
	protected static final String NAME = "velocity control";
	
	
	
	
	
	
	
	public String getName()
	{
		return(VelocityControl.NAME);
	}







	public void setValue(float value)
	{
		if (value>1.0f)
		{
			value = 1.0f;
		}
		else if (value<-1.0f)
		{
			value = -1.0f;
		} 
		
		super.setValue(value);
		
	}
	
	public float getVelocity()
	{
		this.changed = false;
		return (this.value);
		
	}
	
	
}
