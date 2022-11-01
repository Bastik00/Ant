package de.hska.lat.robot.component.lightSensor;


import de.hska.lat.robot.component.analogDetector.AnalogDetectorChangeNotifier;



public interface LightSensorChangeNotifier extends AnalogDetectorChangeNotifier
{

	public void luxChanged(LightSensor<?, ?,?> sensor);

	
}
