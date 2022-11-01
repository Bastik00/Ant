package de.hska.lat.robot.value.pwm;


import de.hska.lat.robot.value.ComponentValue;


public class PwmValue extends ComponentValue<PwmValue> //ComponentValueChangeListener<BrightnessValue>>
{

	private static final String TYPE_NAME = "PWM";
	private static final String TYPE_DESCRIPTION = "PWM value";
	
public PwmValue(String name)
{
	super(name);
	this.minRange = -1;
	this.maxRange = 1;
}






@Override
public String getTypeName()
{
	return(PwmValue.TYPE_NAME);
}



@Override
public String getTypeDescription()
{
	return(PwmValue.TYPE_DESCRIPTION);
}


}
