package de.hska.lat.robot.component.generic.barometric;



import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.barometric.BarometricValue;






public abstract class BarometricSensor<S extends ComponentSettingsChangeNotifier,  P extends SensorProtocol> 
	extends  Sensor <BarometricSensorChangeNotifier,S,P>
{

	
	protected BarometricValue presure;
	
public BarometricSensor(DetectorMetaData metaData, P protocol)
{
	super(metaData, protocol);
	// TODO Auto-generated constructor stub
}



public BarometricValue getPresureValue()
{
	return(this.presure);
}


public void setPresure(float presure)
{
	this.presure.setValue(presure);
}



@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
	values.add(this.presure);	
			
	return (values);
}



}
