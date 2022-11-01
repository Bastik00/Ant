package de.hska.lat.robot.component.sensor.mpu6000;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.accelerometer.Accelerometer;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerProtocol;

public class Mpu6000Accelerometer extends Accelerometer<ComponentSettingsChangeNotifier,  AccelerometerProtocol>
{

	public Mpu6000Accelerometer(ComponentMetaData metaData,
			AccelerometerProtocol protocol)
	{
		super(metaData, protocol);
		this.setComponentName(this.name+" acc");
	}

}
