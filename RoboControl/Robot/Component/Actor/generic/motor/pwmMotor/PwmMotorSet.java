package de.hska.lat.robot.component.actor.generic.motor.pwmMotor;






import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.actor.ActorSet;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Msg_motorPwm;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Stream_motorPwm;







public class PwmMotorSet <M extends PwmMotor, P extends PwmMotorProtocol> extends ActorSet<M, P>
{
	

/**
	 * 
	 */
	private static final long serialVersionUID = -5903240812480095408L;









	/**
	 * process motor pwm data stream message.Its include pwm values   
	 * @param motorVelocitys 
	 */
	public void processPwm(Msg_motorPwm motorPwm)
	{
		PwmMotor actor;
		int index;
		

		for (index=0;index<motorPwm.getParameterCount(); index++)
		{
			actor=this.getComponentOnLocalId(index);

			if (actor!=null)
			{
				actor.setPwm(motorPwm.getPwm());
			}

		}
		
	}


/**
 * process motor pwm data stream message.Its include pwm values   
 * @param motorVelocitys 
 */
public void processPwm(Stream_motorPwm motorsPwm)
{
	PwmMotor actor;
	int index;
	

	for (index=0;index<motorsPwm.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setPwm(motorsPwm.getPwm(index));
		}

	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_motorPwm)
	{
		processPwm((Msg_motorPwm)remoteMessage);
	}
	
	
	return false;
}


@Override
public boolean decodeStream(RemoteStream remoteStream)
{
	if (remoteStream instanceof Stream_motorPwm)
	{
		processPwm((Stream_motorPwm)remoteStream);
	}
	
	
	return false;
}




}