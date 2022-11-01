package de.hska.lat.robot.component.temperatureSensor.mlx90614;



import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;




public class Mlx90614AmbientSensor extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol>  
{

		
	
public Mlx90614AmbientSensor(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData,  protocol);
	this.name = this.name+" Ambient";
	this.temperature = new TemperatureValue(this.getComponentName(),203.15f, 653.15f);
	
}



}
