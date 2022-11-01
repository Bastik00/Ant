package de.hska.lat.robot.component.generic.accelerometer;



import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.generic.accelerometer.protocol.Msg_acceleration;
import de.hska.lat.robot.component.generic.accelerometer.protocol.Stream_accelerations;






/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class AccelerometerSet<T extends Accelerometer<?,?>,P extends AccelerometerProtocol>  extends ComponentSet<T,P>
	{


	


/**
	 * 
	 */
	private static final long serialVersionUID = -2319328805472528668L;




public void processAccelerations(Stream_accelerations accelerations) 
{
	T sensor;
	int index;
	

	for (index=0;index<accelerations.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			FloatVector3D accelerationVector = accelerations.getAccelerations(index);
			sensor.setAccelerations(accelerationVector);

		}

	}
}



public void processAcceleration(Msg_acceleration acceleration)
{
	T sensor;
	int index;
	
	index = acceleration.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		FloatVector3D accelerationVector = acceleration.getAccelerations();
		sensor.setAccelerations(accelerationVector);
	
	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_acceleration)
	{
		this.processAcceleration((Msg_acceleration) remoteData);
	}
	else
	{
		super.decodeMessage(remoteData);		
	}

	
	return false;
}



@Override
public boolean decodeStream(RemoteStream remoteData)
{
	if (remoteData instanceof Stream_accelerations)
	{
		this.processAccelerations((Stream_accelerations)remoteData);

	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return(true);	
}



}
