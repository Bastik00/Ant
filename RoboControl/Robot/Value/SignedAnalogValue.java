package de.hska.lat.robot.value;

public class SignedAnalogValue extends AnalogValue
{

public SignedAnalogValue(String name,int rawRange)
{
	super(name,-1 ,1, rawRange);

}

}
