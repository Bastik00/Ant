package de.hska.lat.robot.component.voltmeter;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.value.voltage.VoltageValue;

public class Voltmeter extends RobotComponent<ComponentChangeNotifier, ComponentSettingsChangeNotifier,  VoltmeterProtocol>
{

	
	VoltageValue voltage;
	
	
public Voltmeter(ComponentMetaData metaData, VoltmeterProtocol protocol,float minRange, float maxRange)
{
	super(metaData, protocol);
	
	this.voltage = new VoltageValue(this.getComponentName(), minRange,maxRange );
}





}
