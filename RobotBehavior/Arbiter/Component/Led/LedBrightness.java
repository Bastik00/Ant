package de.hska.lat.behavior.arbiter.component.led;


import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class LedBrightness extends ArbiterAction
{

	
	protected float brightness;


	private static final String name = "change led brightness";
	
	
	public LedBrightness(int priority)
	{
		super(priority);
	}

	@Override
	public String getName()
	{
		return(LedBrightness.name);
	}


	@Override
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		
		return(returnString);
	}
	
	public float getBrightness()
	{
		return brightness;
	}

	public void setBrightness(float brightness)
	{
		this.brightness = brightness;
	}

}
