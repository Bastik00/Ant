package de.hska.lat.robot.component.detector;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.protocol.Stream_detectionStatus;


public class DetectorSet<T extends Detector<? extends DetectorChangeNotifier,? extends ComponentSettingsChangeNotifier, ? extends ComponentProtocol>,P extends ComponentProtocol> extends ComponentSet<T , P> 
{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 7256265236450697916L;

	
/*	
public DetectorSet(T[] detectors, ComponentProtocol protocol)
{
	
	for (T detector: detectors)
	{
		this.add(detector);
	}

}		
	
	*/
	





public void  processContacts(Stream_detectionStatus sensorValues)
{
	int index;
	Detector<?, ?, ?> detector;
	
	for (index=0;index<this.size(); index++)
	{
		detector=this.getComponentOnLocalId(index);
		
		if (detector!=null)
		{
			detector.setStatus(sensorValues.getStatus(index));
		}
	}

}


	

@Override
public boolean decodeStream(RemoteStream streamData)
{
	
if (streamData instanceof Stream_detectionStatus)
	{
		this.processContacts((Stream_detectionStatus) streamData);
	}

	return false;
}


}
