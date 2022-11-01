package de.hska.lat.robot.component.sensor.mpu6000;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Stream_temperatures;


public class Mpu6000TemperatureSensor extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol>  
{
	public Mpu6000TemperatureSensor(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
	{
		super( metaData,  protocol);
		this.name = this.name+" Ambient";
		this.temperature = new TemperatureValue(this.getComponentName(),203.15f, 653.15f);
		
	}
	
	/**
	 * pass actual distances in given message to sensor instances
	 * 
	 * @param sensorDistances message with actual distances
	 * @return array of dirty sensors
	 */

	public void processSensorsTemperatures(Stream_temperatures sensorsTemperatures) 
	{
	
		

		this.setTemperature(sensorsTemperatures.getTemperature(0));

	}

	
	@Override
	public boolean decodeStream(RemoteStream remoteData)
	{
		if (remoteData instanceof Stream_temperatures)
		{
			processSensorsTemperatures((Stream_temperatures)remoteData);
		}
		
		
		return false;
	}


	
}
