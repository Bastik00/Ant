package de.hska.lat.robot.component.sensor.bmp085;




import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;

public class Bmp085TemperatureSensorSet  extends TemperatureSensorSet<Bmp085TemperatureSensor,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050320605836085539L;


	
public Bmp085TemperatureSensorSet(Bmp085Set bmp085SensorSet)
{
	for (Bmp085 sensor : bmp085SensorSet)
	{
		this.add(sensor.getTemperatureSensor());
	}
}
	
	


}
