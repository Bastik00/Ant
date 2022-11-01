package de.hska.lat.robot.component.analogDetector;

import de.hska.lat.robot.component.detector.DetectorChangeNotifier;


public interface AnalogDetectorChangeNotifier extends DetectorChangeNotifier{

	

	/**
	 * notify when sensor value has changed 
	 * 
	 * @param globalId global id of corresponding sensor
	 * @param value new value of given sensor
	 */
	
	public void valueChanged(AnalogDetector<?,?,?> sensor);	
	

	public void statisticChanged(AnalogDetector<?,?,?> sensor);
}
	
	

