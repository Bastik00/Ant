package de.hska.lat.robot.component.actor.generic.motor.velocityMotor;





import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.RpmMotorSet;

import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Stream_motorsRpm;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Stream_motorsVelocity;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.Msg_motorVelocity;






public class VelocityMotorSet<m extends VelocityMotor, p extends VelocityMotorProtocol> extends RpmMotorSet<m, p>
{
	

/**
	 * 
	 */
	private static final long serialVersionUID = -5903240812480095408L;





public void processVelocity(Msg_motorVelocity motorVelocity)
{
	VelocityMotor actor;
	int index;
	

	for (index=0;index<motorVelocity.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setVelocity(motorVelocity.getVelocity());
		}

	}
	
}



public void processVelocity(Stream_motorsVelocity motorsVelocity)
{
	VelocityMotor actor;
	int index;
	

	for (index=0;index<motorsVelocity.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setVelocity(motorsVelocity.getVelocity(index));
		}

	}
	
}



@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_motorVelocity)
	{
		this.processVelocity((Msg_motorVelocity) remoteMessage);
	}
	else
	{
		super.decodeMessage(remoteMessage);
	}
	return false;
}


	
@Override
public boolean decodeStream(RemoteStream remoteStream)
{
	if (remoteStream instanceof Stream_motorsRpm)
	{
		this.processVelocity((Stream_motorsVelocity)remoteStream);
	}
	else
	{
		this.decodeStream(remoteStream);
	}
	
	return false;
}

}