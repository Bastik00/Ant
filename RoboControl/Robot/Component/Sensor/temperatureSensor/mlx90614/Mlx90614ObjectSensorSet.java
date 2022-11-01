package de.hska.lat.robot.component.temperatureSensor.mlx90614;


import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;

public class Mlx90614ObjectSensorSet   extends TemperatureSensorSet<Mlx90614ObjectSensor,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3606052712894068618L;

public Mlx90614ObjectSensorSet(Mlx90614Set mlx90614SensorSet)
{
	for (Mlx90614 sensor : mlx90614SensorSet)
	{
		this.add(sensor.getObjectSensor());
	}
}
		
	

}
