package de.hska.lat.robot.component.distanceSensor.srf10;


import java.util.ArrayList;


import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.detector.DetectorMetaData;


import de.hska.lat.robot.component.distanceSensor.srf10.protocol.*;
import de.hska.lat.robot.component.generic.distance.DistanceSensorProtocol;
import de.hska.lat.robot.component.generic.distance.DistanceSensorSet;
import de.hska.lat.robot.component.sensor.SensorProtocol;


/**
 * Sensor set for the srf10 range finder sensors  
 * 
 * @author Oktavian Gniot
 *
 */
public class Srf10Set  extends DistanceSensorSet<Srf10, DistanceSensorProtocol>
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1985714891299637009L;

	

public Srf10Set(ArrayList<DetectorMetaData> detectors, SensorProtocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Srf10(detector, protocol));
	}
	
}


/**
 * pass actual distances in given message to sensor instances
 * 
 * @param sensorDistances message with actual distances
 * @return array of dirty sensors
 */
/*
public void processSensorDistances(Msg_DistanceSensorDistances sensorDistances) 
{
	Srf10 sensor;
	int index;
	

	for (index=0;index<sensorDistances.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			sensor.setDistance(sensorDistances.getParameterValue(index));
		}

	}
}
*/
/**
 * pass sensor distance value to sensor instance
 * @param sensorDistance 
 */
/*
public void processSensorDistance(Msg_DistanceSensorDistance sensorDistance)
{
	Srf10 sensor;
	int index;
	
	index=sensorDistance.getSensorIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setValue(sensorDistance.getSensorDistance());	
	}
	
}
*/

//distanceChanged

public void processSettings(Msg_srf10Settings sensorDefaults)
{
	Srf10 sensor;
	int index;
	
	index=sensorDefaults.getIndex();
	
	sensor=this.getComponentOnLocalId(index);
	
	if (sensor!=null)
	{
		sensor.setSetup(sensorDefaults.getRange(), sensorDefaults.getGain(), sensorDefaults.getAddress());
	}

}


@Override
public boolean decodeMessage(RemoteMessage message)
{
	/*
if (message instanceof Stream_analogSensorsValues)
	{
//		this.processSensorsValues((Stream_analogSensorsValues) streamData);
	}
*/
	return false;
}




}
