package de.hska.lat.robot.component.generic.colorSensor;

import de.hska.lat.robot.value.ComponentValue;



public class ValueValue extends ComponentValue<ValueValue>
{


	
	private static final String TYPE_NAME = "value (HSV)"; 

	
public ValueValue(String name)
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
	return(ValueValue.TYPE_NAME);
}




}
