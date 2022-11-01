package de.hska.lat.robot.component.sensor.tsl2561;



import de.hska.lat.robot.component.lightSensor.LightSensorChangeNotifier;



public interface Tsl2561ChangeNotifier extends LightSensorChangeNotifier
{

	/**
	 * Notify when the measured amount of infrared light has changed 
	 * @param sensor sensor 
	 */

	public void irChanged(Tsl2561 sensor);
	
}
