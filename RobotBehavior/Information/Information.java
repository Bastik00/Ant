package de.hska.lat.behavior.information;

import de.hska.lat.behavior.information.filter.InformationFilter;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;

public class Information implements ComponentValueChangeListener
{

	protected ComponentValue<?>  value;
	protected float scale;
	protected InformationFilter filter;
	protected int id;
	protected boolean changed;
	
public Information(ComponentValue<?>  value, InformationFilter filter, float scale, int id)
{
	this.value = value;
	this.filter = filter;
	this.scale = scale;
	this.value.addListener(this);
	this.id = id;
}


public float getValue()
{
	return(this.filter.get());
}


/**
 * check if a given value is in given range
 * @param value
 * @param value2
 * @param range  
 * @return
 */

public boolean inRange(float value, float value2, float range)
{
	if ((value < (value2 + range)) && 
			(value > (value2 - range))) 
	{
		return (true);
	}
	return(false);
}

public boolean inRange(float value2, float range)
{
	float value = this.value.getValue();
	if ((value < (value2 + range)) && 
			(value > (value2 - range))) 
	{
		return (true);
	}
	return(false);
}


/**
 * 
 */

public void capture()
{
	float value;
	
	value = this.value.getValue();
	this.changed = false;
	value*= scale;
	
	this.filter.put(value);
	
}


/**
 * 
 * @return
 */
public boolean hasChanged()
{
	return (changed);
}



@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.changed = true;
}


public int getId()
{
	return(this.id);	
}




}
