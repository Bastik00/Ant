package de.hska.lat.ant.behavior.led;


import de.hska.lat.behavior.control.BehaviorControl;
import de.hska.lat.behavior.control.BehaviorControlSet;

public class LeftLedBlinkControlControlls extends BehaviorControlSet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6520758735222826234L;


public LeftLedBlinkControlControlls()
{
	
	this.add( new FrequencyControl());
	this.add( new BrightnessControl());
	
}






	

	public FrequencyControl getFrequencyControl()
	{
		for (BehaviorControl control : this)
		{
			if (control instanceof FrequencyControl)
				return((FrequencyControl)control);
		}
		
		return (null);
	}


	public BrightnessControl getBrightnessControl()
	{
		for (BehaviorControl control : this)
		{
			if (control instanceof BrightnessControl)
				return((BrightnessControl)control);
		}
		
		return (null);
	}
}
