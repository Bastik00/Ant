package de.hska.lat.robot.component.generic.barometric;



import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.generic.barometric.protocol.Msg_barometricPresure;
import de.hska.lat.robot.component.generic.barometric.protocol.Stream_barometricPresures;





/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class BarometricSensorSet<T extends BarometricSensor<?,?>,P extends BarometricSensorProtocol>  extends ComponentSet<T,P>
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



/**
 * pass actual distances in given message to sensor instances
 * 
 * @param sensorDistances message with actual distances
 * @return array of dirty sensors
 */

public void processSensorsPresures(Stream_barometricPresures sensorPresures) 
{
	T sensor;
	int index;
	

	for (index=0;index<sensorPresures.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			sensor.setPresure(sensorPresures.getBarometricPresure(index));
		}

	}
}



public void processSensorPresure(Msg_barometricPresure sensorPresure)
{
	T sensor;
	int index;
	
	index=sensorPresure.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setPresure(sensorPresure.getBarometricPresure());	
	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_barometricPresure)
	{
		processSensorPresure((Msg_barometricPresure)remoteData);
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
	if (remoteData instanceof Stream_barometricPresures)
	{
		processSensorsPresures((Stream_barometricPresures)remoteData);

	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return(true);	
}



}
