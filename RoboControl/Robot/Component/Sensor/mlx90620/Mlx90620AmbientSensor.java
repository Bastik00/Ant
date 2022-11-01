package de.hska.lat.robot.component.sensor.mlx90620;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;


public class Mlx90620AmbientSensor extends TemperatureSensor<ComponentSettingsChangeNotifier, TemperatureSensorProtocol>
{

	public Mlx90620AmbientSensor(ComponentMetaData metaData,
			TemperatureSensorProtocol protocol)
	{
		super(metaData, protocol);
		this.name = this.name+" Ambient";
		this.temperature = new TemperatureValue(this.getComponentName(),213.15f, 358.15f);
	}


}
