package de.hska.lat.robot.value.angular;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.value.ComponentValue;


public class AngularValue extends ComponentValue<AngularValue>
{
	protected boolean normalized;
		
public AngularValue(String name, boolean normalized)
{
	super(name);
	this.minRange = (float) -Math.PI;
	this.maxRange = (float) Math.PI;
	this.normalized = normalized;
	this.notifyAllways = true;
}	
	


public AngularValue(String name)
{
	super(name);
	this.minRange = (float) -Math.PI;
	this.maxRange = (float) Math.PI;
	this.normalized = true;
	this.notifyAllways = true;
}	

protected boolean isNormalized()
{
	return(this.normalized);
}


protected void setOverflowValue()
{
	if (this.normalized)
	{
		value = (float) (value % (Math.PI*2));
	}
	

	this.valid = true;
}


protected void setUnderflowValue()
{
	if (this.normalized)
	{
		value = (float) (value % (Math.PI*2));
	}
	

	this.valid = true;
}



/**
 * returns actual angle value as degree
 * @return
 */
public float getAsDegree()
{
	return(Radiant.convertRadiantToDegree(this.value));
}



}
