package de.hska.lat.robot.value;

public class NumericValue extends ComponentValue <NumericValue>
{

public NumericValue(String name)
{
	super(name);
}

	protected int minValue = 0x80000000;
	protected int maxValue = 0x7fffffff;
	protected int value =0;
	

/**
 * set minimum und maximum range of this numeric value
 * 	
 * @param minValue new minimum range
 * @param maxValue new maximum range
 */
	
public void setRange(int minValue, int maxValue)
{
	this.minValue= minValue;
	this.maxValue=maxValue;
}
	


/**
 * set new value of this instance. If value out of range correct value to range.
 * @param value new value of this instance
 * @return actual value
 */

public int setValue(int value)
{
	

		
	if (value> this.maxValue)
	{
		value = this.maxValue;
	}
	else if (value < this.minValue)
	{
		value = this.minValue;
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
/*
public int getMinRange()
{
	return(this.minValue);
}
*/
/**
 * get actual maximum range of this numeric value
 * @return maximum range
 */
/*
public int getMaxRange()
{
	return(this.maxValue);
}
*/
/**
 * get actual value of this numeric value
 * @return actual value 
 */

public int getNumericValue()
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
