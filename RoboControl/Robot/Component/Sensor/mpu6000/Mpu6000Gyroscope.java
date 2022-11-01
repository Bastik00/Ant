package de.hska.lat.robot.component.sensor.mpu6000;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.gyroscope.Gyroscope;
import de.hska.lat.robot.component.generic.gyroscope.GyroscopeProtocol;

public class Mpu6000Gyroscope extends Gyroscope<ComponentSettingsChangeNotifier,  GyroscopeProtocol>
{

	public Mpu6000Gyroscope(ComponentMetaData metaData,
			GyroscopeProtocol protocol)
	{
		super(metaData, protocol);
		this.setComponentName(this.name+" gyroscope");
	}

}
