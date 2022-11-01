package de.hska.lat.robot.component.lightSensor;


import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.analogDetector.AnalogDetectorChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.analogDetector.AnalogDetector;
import de.hska.lat.robot.value.lux.LuxValue;

public class LightSensor  <T extends AnalogDetectorChangeNotifier, S extends ComponentSettingsChangeNotifier, P extends ComponentProtocol> extends  AnalogDetector<T,S,P>  
{

	protected LuxValue luxValue = new LuxValue("lux",1000000);
	
public LightSensor(DetectorMetaData metaData, P protocol)
{
	super( metaData, 65535, protocol);
//	values.add(luxValue);
}



public float getLux()
{
	return(this.luxValue.getValue());
}



public LuxValue getLuxValue()
{
	return(this.luxValue);
}







}
