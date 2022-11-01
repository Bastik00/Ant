package de.hska.lat.robot.component.sensor.mpu6000;


import de.hska.lat.robot.component.generic.gyroscope.GyroscopeProtocol;
import de.hska.lat.robot.component.generic.gyroscope.GyroscopeSet;



public class Mpu6000GyroscopeSet   extends GyroscopeSet<Mpu6000Gyroscope, GyroscopeProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3606052712894068618L;

public Mpu6000GyroscopeSet(Mpu6000Set Mpu6000Set)
{
	for (Mpu6000 sensor : Mpu6000Set)
	{
		this.add(sensor.getGyroscope());
	}
}
		
	

}
