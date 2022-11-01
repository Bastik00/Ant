package de.hska.lat.robot.component.generic.colorSensor;

import de.hska.lat.robot.value.ComponentValue;



public class SaturationValue extends ComponentValue<SaturationValue>
{


	private static final String TYPE_NAME = "Saturation (HSV)"; 

	
public SaturationValue(String name)
{
	super(name);

	this.minRange = 0;
	this.maxRange = 1;
	this.notifyAllways = true;
}	






protected void setOverflowValue()
{
	this.value = 1;
	this.valid = true;
	this.overflow = true;
	this.underflow = false;
}


protected void setUnderflowValue()
{
	this.value = 0;
	this.valid = true;
	this.overflow = false;
	this.underflow = true;
}






@Override
public String getTypeName()
{
	return(SaturationValue.TYPE_NAME);
}




}
