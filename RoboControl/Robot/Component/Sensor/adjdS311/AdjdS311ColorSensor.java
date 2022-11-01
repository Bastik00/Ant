package de.hska.lat.robot.component.sensor.adjdS311;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensor;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensorProtocol;


public class AdjdS311ColorSensor extends ColorSensor<ComponentSettingsChangeNotifier, ColorSensorProtocol>
{

public	AdjdS311ColorSensor(ComponentMetaData metaData, ColorSensorProtocol protocol)
{
	super( metaData, protocol);
	this.name = this.name+" Ambient";
	
	
}
	
}
