package de.hska.lat.robot.component.temperatureSensor.mlx90614;




import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;

public class Mlx90614AmbientSensorSet  extends TemperatureSensorSet<Mlx90614AmbientSensor,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050320605836085539L;


	
public Mlx90614AmbientSensorSet(Mlx90614Set mlx90614SensorSet)
{
	for (Mlx90614 sensor : mlx90614SensorSet)
	{
		this.add(sensor.getAmbientSensor());
	}
}
	
	


}
