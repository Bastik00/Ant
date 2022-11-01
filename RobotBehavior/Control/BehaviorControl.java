package de.hska.lat.behavior.control;

public class BehaviorControl
{


	protected float value;
	protected boolean changed;
	
	protected static final String NAME = "generic control";
	

	
	public void setValue(float value)
	{
		this.changed = true;
		this.value = value;
		
	}
	
	public float getValue()
	{
		this.changed = false;
		return (this.value);
		
	}	
	
public boolean hasChanged()
{
	return(this.changed);
}
	
	
public String getName()
{
	return(BehaviorControl.NAME);
}
	
	
}
