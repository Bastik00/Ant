package de.hska.lat.robot.component.sensor.bmp085;



import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;




public class Bmp085TemperatureSensor extends TemperatureSensor<ComponentSettingsChangeNotifier ,TemperatureSensorProtocol>  
{

		
	
public Bmp085TemperatureSensor(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData, protocol);
	this.name = this.name+" Ambient";
	this.temperature = new TemperatureValue(this.getComponentName(),203.15f, 653.15f);
	
}



}
