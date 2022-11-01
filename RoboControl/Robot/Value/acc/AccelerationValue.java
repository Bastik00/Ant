package de.hska.lat.robot.value.acc;

import de.hska.lat.robot.value.ComponentValue;


public class AccelerationValue extends ComponentValue<AccelerationValue>
{

		
public AccelerationValue(String name, int adcRange, float gRange)
{
	super(name);
	this.minRange = -gRange;
	this.maxRange = +gRange;
	this.notifyAllways = true;
}	
	
public AccelerationValue(String name)
{
	super(name);
	this.minRange = -4;
	this.maxRange = +4;
}






/**
 * set the actual g range of corresponding sensor 
 * @param gRange
 */
public void setAccelerationRange(float gRange)
{
	this.minRange = -gRange;
	this.maxRange = +gRange;
}






}
