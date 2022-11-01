package de.hska.lat.robot.component.generic.luxSensor;

import de.hska.lat.robot.component.analogDetector.AnalogDetectorChangeNotifier;



public interface LuxChangeNotifier extends AnalogDetectorChangeNotifier{

	

	public void luxValueChanged(LuxSensor<?,?> sensor);		

	
}
