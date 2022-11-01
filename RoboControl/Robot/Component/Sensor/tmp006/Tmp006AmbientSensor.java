package de.hska.lat.robot.component.sensor.tmp006;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;

public class Tmp006AmbientSensor extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol> 
{

	
	
	
public Tmp006AmbientSensor(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData,  protocol);
	this.name = this.name+" Ambient";
	this.temperature = new TemperatureValue(this.getComponentName(), 233.15f, 398.15f);
}


}
