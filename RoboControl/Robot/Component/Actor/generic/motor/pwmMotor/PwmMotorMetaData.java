package de.hska.lat.robot.component.actor.generic.motor.pwmMotor;

import de.hska.lat.robot.component.ComponentMetaData;

public class PwmMotorMetaData extends ComponentMetaData
{

	public boolean direction;
	
public PwmMotorMetaData(ComponentMetaData metaData, boolean direction)
{
	super(metaData);

}


public boolean getDirection()
{
	// TODO Auto-generated method stub
	return this.direction;
}


public static PwmMotorMetaData getMetaData(ComponentMetaData metaData, boolean direction)
{

	return new PwmMotorMetaData(metaData,  direction);
}





}
