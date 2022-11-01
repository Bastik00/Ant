package de.hska.lat.robot.component.temperatureSensor.mlx90614;



import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;





public class Mlx90614ObjectSensor extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol>  
{

	
	protected int emisivity;
	
public Mlx90614ObjectSensor(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData,  protocol);
	
	this.name = this.name+" object";
	this.temperature = new TemperatureValue(this.getComponentName(),233.15f,398.15f); 
	
}




}
