package de.hska.lat.robot.component.digitalDetector;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;

import de.hska.lat.robot.component.detector.Detector;



public class DigitalDetector extends Detector<DetectorChangeNotifier,ComponentSettingsChangeNotifier,ComponentProtocol> 
{

	
	
public DigitalDetector(DetectorMetaData metaData, ComponentProtocol protocol) 
{
		super(metaData, protocol);
}





}
