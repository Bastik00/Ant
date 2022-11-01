package de.hska.lat.robot.value.rpm;


import de.hska.lat.robot.value.ComponentValue;


public class RpmValue extends ComponentValue<RpmValue> 
{

	private static final String TYPE_NAME = "rpm";
	private static final String TYPE_DESCRIPTION = "Revolving per minute";
	
public RpmValue(String name, float maxRange)
{
	super(name);
	this.minRange = -maxRange;
	this.maxRange = maxRange;
}






@Override
public String getTypeName()
{
	return(RpmValue.TYPE_NAME);
}



@Override
public String getTypeDescription()
{
	return(RpmValue.TYPE_DESCRIPTION);
}


}
