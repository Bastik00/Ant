package de.hska.lat.robot.component.distanceSensor.gp2D;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.distanceSensor.gp2D.protocol.*;
import de.hska.lat.robot.component.generic.distance.DistanceSensorSet;


/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public class Gp2DSet  extends DistanceSensorSet<Gp2D, Gp2DProtocol>
{



/**
	 * 
	 */
	private static final long serialVersionUID = 2425608203206175979L;


	
	
public Gp2DSet(ArrayList<DetectorMetaData> detectors,int range, Gp2DProtocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Gp2D(detector,range, protocol));
	}
	
}

/**
 * pass actual analog values in given message to sensor instances
 *  
 * @param sensorValues message with actual analog sensor values
 * @return array of dirty sensors
 */
/*
public void  processRawVales(Msg_gp2DRawValues sensorValues) 
{
	Gp2D sensor;
	int index;


	for (index=0;index<sensorValues.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);
		if (sensor!=null)
		{
			sensor.setValue(sensorValues.getParameterValue(index));			
		}
	}


}
*/
/**
 * pass sensor value to sensor instance
 * @param sensorDistance 
 */



/**
 * pass actual distances in given message to sensor instances
 * 
 * @param sensorDistances message with actual distances
 * @return array of dirty sensors
 */
/*
public void processSensorDistances(Msg_DistanceSensorDistances sensorDistances) 
{
	SharpGp2 sensor;
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
	SharpGp2 sensor;
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

public void processSettings(Msg_gp2DSettings sensorDefaults)
{
	Gp2D sensor;
	int index;
	
	index=sensorDefaults.getIndex();
	
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setSetup(sensorDefaults.getGradient(), sensorDefaults.getOffset(), sensorDefaults.getMaxDistance());
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
