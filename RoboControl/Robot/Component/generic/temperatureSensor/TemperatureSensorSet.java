package de.hska.lat.robot.component.generic.temperatureSensor;



import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Msg_temperature;
import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Stream_temperatures;




/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class TemperatureSensorSet<T extends TemperatureSensor<?,?>,P extends TemperatureSensorProtocol>  extends ComponentSet<T,P>
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1985714891299637009L;

	
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
 * pass actual distances in given message to sensor instances
 * 
 * @param sensorDistances message with actual distances
 * @return array of dirty sensors
 */

public void processSensorsTemperatures(Stream_temperatures sensorsTemperatures) 
{
	T sensor;
	int index;
	

	for (index=0;index<sensorsTemperatures.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			sensor.setTemperature(sensorsTemperatures.getTemperature(index));
		}

	}
}

/**
 * pass sensor distance value to sensor instance
 * @param sensorDistance 
 */

public void processSensorTemperature(Msg_temperature sensorTemperature)
{
	T sensor;
	int index;
	
	index=sensorTemperature.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setTemperature(sensorTemperature.getTemperature());	
	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_temperature)
	{
		processSensorTemperature((Msg_temperature)remoteData);
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
	if (remoteData instanceof Stream_temperatures)
	{
		processSensorsTemperatures((Stream_temperatures)remoteData);
	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return false;
}



}
