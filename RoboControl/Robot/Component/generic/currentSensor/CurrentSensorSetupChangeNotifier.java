package de.hska.lat.robot.component.currentSensor;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

public interface CurrentSensorSetupChangeNotifier extends ComponentSettingsChangeNotifier
{
		public void currentThresholdChanged(CurrentSensor current);
		public void currentWindowSizeChanged(CurrentSensor current);
}
