package de.hska.lat.robot.value.detector;

import de.hska.lat.robot.value.ComponentValue;



public class DetectorValue extends ComponentValue<DetectorValue >
{

	//protected v


	
public DetectorValue(String name)
{
	super(name);
	this.minRange = 0;
	this.maxRange = 1;
}





public boolean getStatus()
{
	if (value>0)
		return(true);
	
	return(false);
}


public void setStatus(boolean status)
{
	if (status)
	{
		this.setValue(1f);
	}
	else
	{
		this.setValue(0f);
	}


	
}


@Override
protected void setOverflowValue()
{
	this.value = 1;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}


@Override
protected void setUnderflowValue()
{
	this.value = 0;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}

}
