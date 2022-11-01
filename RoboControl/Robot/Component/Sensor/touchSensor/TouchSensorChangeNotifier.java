package de.hska.lat.robot.component.touchSensor;

import de.hska.lat.robot.component.analogDetector.AnalogDetectorChangeNotifier;


public interface TouchSensorChangeNotifier extends AnalogDetectorChangeNotifier{

	

	public void ProximityValueChanged(TouchSensor sensor);


}
	
	

