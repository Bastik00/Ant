package de.hska.lat.robot.component.generic.colorSensor;

import de.hska.lat.robot.value.ComponentValue;





public class HueValue extends ComponentValue<HueValue>
{

		
	private static final String TYPE_NAME = "hue (HSV)"; 

	
public HueValue(String name)
{
	super(name);

	this.minRange = 0;
	this.maxRange = (float) (2* Math.PI);
	this.notifyAllways = true;
}	






protected void setOverflowValue()
{
	this.value = (float) (2* Math.PI);
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
	return(HueValue.TYPE_NAME);
}




}
