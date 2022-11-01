package de.hska.lat.robot.component.touchSensor;
 


import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.analogDetector.AnalogDetector;
import de.hska.lat.robot.component.detector.DetectorMetaData;





public class TouchSensor extends  AnalogDetector<TouchSensorChangeNotifier,ComponentSettingsChangeNotifier, ComponentProtocol > 
{

	
	protected int detectionFactor;
	

	
public int getDetectionFactor() {
		return detectionFactor;
	}




	public void setDetectionFactor(int detectionFactor) {
		this.detectionFactor = detectionFactor;
	}




public TouchSensor(DetectorMetaData metaData,int range,ComponentProtocol protocol) 
{
		super(metaData, range, protocol);



}

/*


@Override
public boolean saveToutchSensorDefaults(int index) 
{
	
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.saveToutchSensorDefaults(this.localId));

}




@Override
public boolean loadToutchSensorDefaults(int index) 
{
	
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.loadToutchSensorDefaults(this.localId));
}
	
	
	
	
@Override
public boolean getToutchSensorSettings(int index) 
{
	
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.getToutchSensorSettings(this.localId));
}

*/

/*
@Override
public boolean setToutchSensorSettings(int index, int treshold, boolean inverse, int detectionFactor) 
{
	
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.setToutchSensorSettings(this.localId, treshold, inverse, detectionFactor));
}

@Override
public boolean getToutchSensorValue(int index)
{
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.getToutchSensorValue(this.localId));
}

@Override
public boolean getToutchSensorMax(int index)
{
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.getToutchSensorMax(this.localId));
}

@Override
public boolean getToutchSensorMin(int index)
{
	if (this.controllerListener==null)
		return(false);
	
	return(this.controllerListener.getToutchSensorMin(this.localId));
}



public void setSettings(int treshold, boolean inverse, int detectFactor)
{
	this.setSettings(treshold, inverse);
	this.setDetectionFactor(detectFactor);
}

public void setInverse(boolean inverse)
{
	this.inverse=inverse;
	
}



public boolean getStatus()
{
	return this.status;
}


*/





}


