package de.hska.lat.robot.component.sensor.mpu6000;

import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;

public class Mpu6000TemperatureSensorSet extends TemperatureSensorSet<Mpu6000TemperatureSensor,TemperatureSensorProtocol>
{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 806068147761917828L;


public Mpu6000TemperatureSensorSet(Mpu6000Set Mpu6000SensorSet)
{
	for (Mpu6000 sensor : Mpu6000SensorSet)
	{
		this.add(sensor.getTemperatureSensor());
	}
}
	
	


}