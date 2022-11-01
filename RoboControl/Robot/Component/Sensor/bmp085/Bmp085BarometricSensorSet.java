package de.hska.lat.robot.component.sensor.bmp085;




import de.hska.lat.robot.component.generic.barometric.BarometricSensorProtocol;
import de.hska.lat.robot.component.generic.barometric.BarometricSensorSet;

public class Bmp085BarometricSensorSet  extends BarometricSensorSet<Bmp085BarometricSensor,BarometricSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050320605836085539L;


	
public Bmp085BarometricSensorSet(Bmp085Set bmp085SensorSet)
{
	for (Bmp085 sensor : bmp085SensorSet)
	{
		this.add(sensor.getBarometricSensor());
	}
}
	
	


}
