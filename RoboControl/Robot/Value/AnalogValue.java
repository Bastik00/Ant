package de.hska.lat.robot.value;



public abstract class AnalogValue extends ComponentValue<AnalogValue>
{


	protected int rawValue;
	protected int rawRange;

protected AnalogValue(String name,float minRange, float maxRange,int rawRange)
{
	super(name);
	this.minRange=minRange;
	this.maxRange=maxRange;
	this.rawRange=rawRange;
}
	


/**
 * set value 
 * @param rawValue
 */
public void setRawValue(int rawValue)
{
	float value;
	
	this.rawValue = rawValue;
	
	value = ((float)rawValue) / rawRange;
	
	this.setValue(value);
}



/**
 * return this values measured ADC value
 * @return ADCs raw value
 */
public int getRawValue()
{
	return (this.rawValue);
}

public int getRawRange()
{
	return (rawRange);
}





}
