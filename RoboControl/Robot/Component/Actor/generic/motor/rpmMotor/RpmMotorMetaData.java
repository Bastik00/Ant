package de.hska.lat.robot.component.actor.generic.motor.rpmMotor;



import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotorMetaData;


public class RpmMotorMetaData extends PwmMotorMetaData
{
	

	protected float rpmRange;
	

	public RpmMotorMetaData(PwmMotorMetaData metaData)
	{
		super(metaData, metaData.getDirection());
	}


	public float getRpmRange()
	{
		return (this.rpmRange);
	}


	public static RpmMotorMetaData getRpmMotorMetaData(ComponentMetaData metaData, boolean direction, float rpmRange)
	{
		RpmMotorMetaData rpmMotorMetaData = new RpmMotorMetaData(PwmMotorMetaData.getMetaData(metaData,  direction));
		rpmMotorMetaData.rpmRange = rpmRange;
		
		
		return(rpmMotorMetaData);
	}

	
	
}
