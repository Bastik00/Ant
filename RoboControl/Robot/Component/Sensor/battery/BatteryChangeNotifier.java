package de.hska.lat.robot.component.battery;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface BatteryChangeNotifier extends ComponentChangeNotifier
{
	public void presenceChanged(int globalId, boolean present);
	public void activityChanged(int globalId, boolean activity);
	public void criticalChanged(int globalId, boolean critical);
	
	public void voltageChanged(int globalId, int voltage);
	public void maxVoltageChanged(int globalId, int maxVoltage);
	public void minVoltageChanged(int globalId, int minVoltage);
}
