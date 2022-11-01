package de.hska.lat.robot.component.generic.encoder;




import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;

import de.hska.lat.robot.component.generic.encoder.protocol.Msg_encoderTicks;
import de.hska.lat.robot.component.generic.encoder.protocol.Stream_encoderTicks;



public class EncoderSet extends ComponentSet<Encoder, EncoderProtocol>
{
	

/**
	 * 
	 */
	private static final long serialVersionUID = -5903240812480095408L;

/*
public EncoderSet(ArrayList<ComponentMetaData> encoders, EncoderProtocol protocol)
{

	for (ComponentMetaData encoder: encoders)
	{
		this.add(new Encoder(encoder, protocol));
	}
}
*/


	/**
	 * process motor velocity stream message.  
	 * @param motorVelocitys 
	 */
	public void processEncoderTicks(Msg_encoderTicks encoderValues)
	{
		Encoder actor;
		int index;
		

		for (index=0;index<encoderValues.getParameterCount(); index++)
		{
			actor=this.getComponentOnLocalId(index);

			if (actor!=null)
			{
				actor.setVelocity(encoderValues.getTicks());
			}

		}
		
	}

	
	
/**
 * process motor velocity stream message.  
 * @param motorVelocitys 
 */
public void processEncoderTicks(Stream_encoderTicks encoderValues)
{
	Encoder actor;
	int index;
	

	for (index=0;index<encoderValues.getParameterCount(); index++)
	{
		actor=this.getComponentOnLocalId(index);

		if (actor!=null)
		{
			actor.setVelocity(encoderValues.getTicks(index));
		}

	}
	
}


@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_encoderTicks)
	{
		processEncoderTicks((Msg_encoderTicks)remoteData);
	}
	
	
	return false;
}
@Override
public boolean decodeStream(RemoteStream remoteData)
{
	if (remoteData instanceof Stream_encoderTicks)
	{
		processEncoderTicks((Stream_encoderTicks)remoteData);
	}
	
	
	return false;
}

}