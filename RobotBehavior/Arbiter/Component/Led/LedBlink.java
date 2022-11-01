package de.hska.lat.behavior.arbiter.component.led;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class LedBlink extends ArbiterAction
{

	
	protected float frequency;


	private static final String name = "blink led";
	
	
	public LedBlink(int priority)
	{
		super(priority);
	}

	@Override
	public String getName()
	{
		return(LedBlink.name);
	}


	@Override
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		
		return(returnString);
	}
	
	
	public float getFrequency()
	{
		return frequency;
	}

	public void setFrequency(float frequency)
	{
		this.frequency = frequency;
	}

}



