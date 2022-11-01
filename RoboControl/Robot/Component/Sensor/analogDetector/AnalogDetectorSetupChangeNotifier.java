package de.hska.lat.robot.component.analogDetector;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;



public interface AnalogDetectorSetupChangeNotifier extends ComponentSettingsChangeNotifier{


/**
 * notify when detection status of a sensor changed
 * @param globalId global id of corresponding sensor
 * @param detected detection status true if object detected, false if object undetected
 */

public void maxValueChanged(AnalogDetector<?,?,?> sensor);

/**
 * notify when sensor minimum value has changed 
 * 
 * @param globalId global id of corresponding sensor
 * @param value new minimum value of given sensor
 */
public void minValueChanged(AnalogDetector<?,?,?> sensor);

/**
 * notify when threshold of a sensor changed
 * @param globalId global id of corresponding sensor
 * @param treshold new threshold value for sensor detection
 */
//public void thresholdChanged(int globalId,int treshold);



public void setupChanged(AnalogDetector<?,?,?> sensor);
}
