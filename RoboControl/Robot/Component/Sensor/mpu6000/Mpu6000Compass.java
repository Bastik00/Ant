package de.hska.lat.robot.component.sensor.mpu6000;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.compass.Compass;
import de.hska.lat.robot.component.generic.compass.CompassProtocol;

public class Mpu6000Compass extends Compass<ComponentSettingsChangeNotifier,  CompassProtocol>
{

	public Mpu6000Compass(ComponentMetaData metaData,
			CompassProtocol protocol)
	{
		super(metaData, protocol);
		this.setComponentName(this.name+" compass");
	}

}
