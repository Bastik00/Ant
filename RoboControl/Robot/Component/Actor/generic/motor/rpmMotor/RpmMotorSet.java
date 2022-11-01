package de.hska.lat.robot.component.actor.generic.motor.rpmMotor;





import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotorSet;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Msg_motorRpm;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Stream_motorsRpm;




public class RpmMotorSet<m extends RpmMotor, p extends RpmMotorProtocol> extends PwmMotorSet<m, p>
{
	

/**
	 * 
	 */
	private static final long serialVersionUID = -5903240812480095408L;










	/**
	 * process motor rps data stream message.Its include rpm values   
	 * @param motorVelocitys 
	 */
	

public void processRpm(Msg_motorRpm motorRpm)
{
	RpmMotor actor;
	int index;
	

	for (index=0;index<motorRpm.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setPwm(motorRpm.getRpm());
		}

	}
	
}



/**
 * process motor rpm data stream message.Its include rpm values   
 * @param motorVelocitys 
 */

public void processRpm(Stream_motorsRpm motorsPwm)
{
	RpmMotor actor;
	int index;
	

	for (index=0;index<motorsPwm.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setRpm(motorsPwm.getRpm(index));
		}

	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_motorRpm)
	{
		this.processRpm((Msg_motorRpm) remoteMessage);
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
		this.processRpm((Stream_motorsRpm)remoteStream);
	}
	else
	{
		this.decodeStream(remoteStream);
	}
	
	return false;
}

}