package de.hska.lat.robot.component.sensor.mpu6000;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;


public class Mpu6000Set extends ComponentSet<Mpu6000, ComponentProtocol>
{	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5983259135449904758L;

	
	protected Mpu6000TemperatureSensorSet temperatureSensorSet;
	protected Mpu6000AccelerometerSet accelerometerSet;
	protected Mpu6000GyroscopeSet	gyroscopeSet;
	protected Mpu6000CompassSet		compassSet;

	
	
	
public Mpu6000Set(ArrayList<ComponentMetaData> sensors,Mpu6000Protocol protocol)
{
	
	for (ComponentMetaData sensor: sensors)
	{
		this.add(new Mpu6000(sensor,protocol));
	}
	
	this.init();
}


public Mpu6000Set(ComponentMetaData metaData, Mpu6000Protocol protocol)
{
	
	this.add(new Mpu6000(metaData,protocol));
	this.init();
	

}

protected void init()
{
	this.temperatureSensorSet = new Mpu6000TemperatureSensorSet(this);
	this.accelerometerSet = new Mpu6000AccelerometerSet(this);
	this.gyroscopeSet = new Mpu6000GyroscopeSet (this);
	this.compassSet = new Mpu6000CompassSet(this);

}



/**
 * get this MPU 9150 temperature sensor sub set
 * @return temperature sensor sub set
 */
public Mpu6000TemperatureSensorSet getTemperatureSensorSet()
{
	return (this.temperatureSensorSet);
}	

/**
 * get this MPU 9150 Accelerometer sub set
 * @return accelerometer sub set
 */
public Mpu6000AccelerometerSet getAccelerometerSet()
{
	return (this.accelerometerSet);
}	


/**
 * get this MPU 9150 gyroscope sub set
 * @return gyroscope sub set
 */
public Mpu6000GyroscopeSet getGyroscopeSet()
{
	return (this.gyroscopeSet);
}	


/**
 * get this MPU 9150 compass sub set
 * @return compass sub set
 */
public Mpu6000CompassSet getCompassSet()
{
	return (this.compassSet);
}




};