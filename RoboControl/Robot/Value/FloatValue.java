package de.hska.lat.robot.value;



public class FloatValue extends ComponentValue<FloatValue>
{

//	protected float minRange = Float.NEGATIVE_INFINITY;
//	protected float maxRange = Float.POSITIVE_INFINITY;
//	protected float maxRange = Float.MAX_VALUE;
//	protected float minRange = -Float.MAX_VALUE;

	
	
public FloatValue(String name)
{
	super(name);
}	


public FloatValue(String name,float minRange, float maxRange)
{
	super(name);
	this.minRange=minRange;
	this.maxRange=maxRange;
	
}
	



public float add(float value)
{
	return(setValue(this.value+value));
}


/**
 * set new value of this instance. If value out of range correct value to range.
 * @param value new value of this instance
 * @return actual value
 */

public float setValue(float value)
{
	

		
	if (value> this.maxRange)
	{
		value = this.maxRange;
	}
	else if (value < this.minRange)
	{
		value = this.minRange;
	}

	
	if( value == this.value)
	{
		return(value);
	}
	
	this.value= value;
	this.valueChanged();

	
	return(this.value);
}


/**
 * get actual minimum range of this numeric value
 * @return minimum range
 */
public float getMinRange()
{
	return(this.minRange);
}

/**
 * get actual maximum range of this numeric value
 * @return maximum range
 */

public float getMaxRange()
{
	return(this.maxRange);
}

/**
 * get actual value of this numeric value
 * @return actual value 
 */

public float getValue()
{
	return(this.value);
}

/**
 * notify all listeners that value of this instance has changed 
 */
private void valueChanged()
{
	int index;
	
	for (index=0; index < this.listeners.size(); index++)
	{
		listeners.get(index).valueChanged(this);
	}
}








}
