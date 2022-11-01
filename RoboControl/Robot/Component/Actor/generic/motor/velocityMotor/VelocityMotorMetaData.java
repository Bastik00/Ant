package de.hska.lat.robot.component.actor.generic.motor.velocityMotor;



import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.RpmMotorMetaData;


public class VelocityMotorMetaData extends RpmMotorMetaData
{
	



	
	
	public VelocityMotorMetaData(RpmMotorMetaData metaData)
	{
		super(metaData);
	}


	public float getRpmRange()
	{
		return (this.rpmRange);
	}

	
	

	public static VelocityMotorMetaData getVelocityMotorMetaData(ComponentMetaData metaData, boolean direction, float rpmRange)
	{
		VelocityMotorMetaData velocityMotorMetaData = new VelocityMotorMetaData(RpmMotorMetaData.getRpmMotorMetaData(metaData, direction,  rpmRange));
		
			return(velocityMotorMetaData);
	}	
	
	
}
