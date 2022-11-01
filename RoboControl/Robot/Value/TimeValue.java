package de.hska.lat.robot.value;


public class TimeValue extends ComponentValue <TimeValue>
{


public TimeValue(String name)
{
	super(name);
	// TODO Auto-generated constructor stub
}





	protected long value =0;
	
	



/**
 * set new value of this instance. If value out of range correct value to range.
 * @param value new value of this instance
 * @return actual value
 */

public long setValue(long value)
{

	this.value= value;
	this.valueChanged();


	return(this.value);
}


/**
 * get actual value of this numeric value
 * @return actual value 
 */

public long getTimeValue()
{
	return(this.value);
}

public int getSeconds()
{
	return(0);
}

public int getMinutes()
{
	return(0);
}

public int getHours()
{
	return(0);
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
