package de.hska.lat.robot.value.voltage;

import de.hska.lat.robot.value.ComponentValue;




public class VoltageValue extends ComponentValue<VoltageValue>
{

	
	private static final String TYPE_NAME = "voltage";
	

public VoltageValue(String name, float minRange, float maxRange)
{
	super(name);

	this.minRange = minRange;
	this.maxRange = maxRange;
	this.notifyAllways = true;
}	






protected void setOverflowValue()
{
	this.value = 0;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}


protected void setUnderflowValue()
{
	this.value = 0;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}



@Override
public String getTypeName()
{
	return(VoltageValue.TYPE_NAME);
}








}
