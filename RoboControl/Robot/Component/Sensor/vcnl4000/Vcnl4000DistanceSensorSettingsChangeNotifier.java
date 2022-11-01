package de.hska.lat.robot.component.sensor.vcnl4000;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

public interface Vcnl4000DistanceSensorSettingsChangeNotifier extends ComponentSettingsChangeNotifier
{

	public void distanceTableChanged(Vcnl4000DistanceSensor sensor);
	
	
}
