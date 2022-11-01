package de.hska.lat.robot.component.generic.accelerometer;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

public interface AccelerometerSetupChangeNotifier extends ComponentSettingsChangeNotifier
{
		public void offsetChanged(Accelerometer<?,?> accelometer);
}
