package de.hska.lat.robot.component.generic.temperatureSensor;



import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.ComponentValue;




public abstract class TemperatureSensor <S extends ComponentSettingsChangeNotifier , P extends TemperatureSensorProtocol> 
	extends  Sensor <TemperatureChangeNotifier,S,P>

{


	protected TemperatureValue temperature; 
	

	
public TemperatureSensor(ComponentMetaData metaData, P protocol)
{
	super( metaData,  protocol);

	
}

/**
 * get this sensor instance of TemperatureValue class. This sensor use thr returned Instance of TemperatureValue for data storage 
 * @return this sensors instance of TemperatureValue 
 */
public TemperatureValue getTemperatureValue()
{
	return(this.temperature);
}

/**
 * set new temperature 
 * @param temperature temperature in kelvin
 */
public void setTemperature(float temperature)
{
	this.temperature.setValue(temperature);
}



@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
	values.add(this.getTemperatureValue());

	
	return (values);
}


}
