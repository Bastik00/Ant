package de.hska.lat.robot.value.servo;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.morphology.model.Rotator;
import de.hska.lat.robot.value.ComponentValue;







public class ServoAngleValue extends ComponentValue<ServoAngleValue> implements Rotator
{

//	protected FloatValue maxServoRange;
//	protected FloatValue minServoRange;
	/*
	protected float minRange;
	protected float maxRange;
*/
	private static final String TYPE_NAME = "servo value"; 
	
	protected boolean isAtMax;
	protected boolean isAtMin;
	
	protected boolean isActive;
	protected boolean isOn;
	
	protected boolean isInverse;
	protected boolean isStalling;
	
public ServoAngleValue(String name)
{
	super(name);

	
	
	this.minRange=0;
	this.maxRange=0;
	
//	this.minRange = this.minServoRange.getValue();
//	this.maxRange = this.maxServoRange.getValue(); 
	
//	this.minServoRange.addListener(this);
//	this.maxServoRange.addListener(this);
	
}
	
	



public float getPositionAsRadiant()
{
	return(this.value);
}


public float getPositionAsDegree()
{
	return(Radiant.convertRadiantToDegree(this.value));
}





public boolean isAtMin()
{
	return (this.isAtMin);
}

/**
 * set this value servo at minimum flag
 * @param status
 */
public void setAtMin(boolean status)
{

	if (this.isAtMin != status)
	{
		this.isAtMin= status;
	}
	
}




public boolean isAtMax()
{
	return (this.isAtMax);
}

/**
 * set this value servo at maximum flag
 * @param status
 */

public void setAtMax(boolean status)
{
	if (this.isAtMax != status)
	{
		this.isAtMax = status;
	}
}


/*
@Override
public void valueChanged(ComponentValue<FloatValue, ?> source)
{
	if (source==minServoRange)
	{
		this.minRange=source.getValue();
	}
	else if (source==maxServoRange)
	{
		this.maxRange=source.getValue();
	}
	
	this.setValue(source.getValue());
}

*/

/**
 * @return the isActive
 */
public boolean isActive()
{
	return isActive;
}





/**
 * @param isActive the isActive to set
 */
public void setActive(boolean isActive)
{
	this.isActive = isActive;
}





/**
 * @return the isOn
 */
public boolean isOn()
{
	return isOn;
}





/**
 * @param isOn the isOn to set
 */
public void setOn(boolean isOn)
{
	this.isOn = isOn;
}





/**
 * @return the isInverse
 */
public boolean isInverse()
{
	return isInverse;
}





/**
 * @param isInverse the isInverse to set
 */
public void setInverse(boolean isInverse)
{
	this.isInverse = isInverse;
}





/**
 * @return the isStalling
 */
public boolean isStalling()
{
	return isStalling;
}





/**
 * @param isStalling the isStalling to set
 */
public void setStalling(boolean isStalling)
{
	this.isStalling = isStalling;
}





@Override
public String getTypeName()
{
	return(ServoAngleValue.TYPE_NAME);

}





@Override
public float getRotation()
{
	// TODO Auto-generated method stub
	return (this.value);
}


}
