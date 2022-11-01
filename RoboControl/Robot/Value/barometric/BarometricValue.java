package de.hska.lat.robot.value.barometric;


import de.hska.lat.robot.value.ComponentValue;


public class BarometricValue extends ComponentValue<BarometricValue>
{

public BarometricValue(String name, float minRange, float maxRange)
{
	super(name);
	
	this.minRange = minRange;
	this.maxRange = maxRange;
	this.notifyAllways = true;
}



}
