package de.hska.lat.robot.component.generic.locator;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.component.sensor.SensorProtocol;

public abstract class Locator<C extends LocatorChangeNotifier, S extends ComponentSettingsChangeNotifier,P extends  SensorProtocol> extends Sensor <C,S,P>
{

	public Locator(ComponentMetaData metaData, P protocol)
	{
		super(metaData, protocol);
		// TODO Auto-generated constructor stub
	}

}
