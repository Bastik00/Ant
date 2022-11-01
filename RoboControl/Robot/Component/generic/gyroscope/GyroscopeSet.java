package de.hska.lat.robot.component.generic.gyroscope;



import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.generic.gyroscope.protocol.Msg_angularRates;
import de.hska.lat.robot.component.generic.gyroscope.protocol.Stream_angularRates;






/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class GyroscopeSet<T extends Gyroscope<?,?>,P extends GyroscopeProtocol>  extends ComponentSet<T,P>
	{


	
/*
public DistanceSensorSet(ArrayList<DetectorMetaData> detectors)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new T(detector));
	}
	
}
*/

/**
	 * 
	 */
	private static final long serialVersionUID = -2319328805472528668L;




public void processAngularRates(Stream_angularRates angularRates) 
{
	T sensor;
	int index;
	

	for (index=0;index<angularRates.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			sensor.setAngularRates(angularRates.getAngularRates(index));
		}

	}
}



public void processAngularRates(Msg_angularRates angularRates)
{
	T sensor;
	int index;
	
	index = angularRates.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setAngularRates(angularRates.getAngularRates());
	
	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_angularRates)
	{
		processAngularRates((Msg_angularRates) remoteData);
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
	if (remoteData instanceof Stream_angularRates)
	{
		this.processAngularRates((Stream_angularRates)remoteData);

	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return(true);	
}



}
