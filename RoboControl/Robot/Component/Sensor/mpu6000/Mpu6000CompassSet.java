package de.hska.lat.robot.component.sensor.mpu6000;


import de.hska.lat.robot.component.generic.compass.CompassProtocol;
import de.hska.lat.robot.component.generic.compass.CompassSet;



public class Mpu6000CompassSet   extends CompassSet<Mpu6000Compass, CompassProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3606052712894068618L;

public Mpu6000CompassSet(Mpu6000Set mpu6000Set)
{
	for (Mpu6000 sensor : mpu6000Set)
	{
		this.add(sensor.getCompass());
	}
}
		
	

}
