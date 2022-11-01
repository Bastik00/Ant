package de.hska.lat.robot.component.digitalDetector;


import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.detector.DetectorSet;







public class DigitalDetectorSet extends DetectorSet<DigitalDetector, ComponentProtocol>  
{

/**
	 * 
	 */
	private static final long serialVersionUID = -1873427197675680228L;

public DigitalDetectorSet(DetectorMetaData[] detectors, ComponentProtocol protocol)
{
	
	for (DetectorMetaData detector: detectors)
	{
		this.add(new DigitalDetector(detector, protocol));
	}

}	
	





}
