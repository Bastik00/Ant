package de.hska.lat.robot.component.sensor.mpu6000;


import de.hska.lat.robot.component.generic.accelerometer.AccelerometerProtocol;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerSet;



public class Mpu6000AccelerometerSet   extends AccelerometerSet<Mpu6000Accelerometer, AccelerometerProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3606052712894068618L;

public Mpu6000AccelerometerSet(Mpu6000Set Mpu6000Set)
{
	for (Mpu6000 sensor : Mpu6000Set)
	{
		this.add(sensor.getAccelerometer());
	}
}
		
	

}
