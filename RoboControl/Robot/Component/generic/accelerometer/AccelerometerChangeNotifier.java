package de.hska.lat.robot.component.generic.accelerometer;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface AccelerometerChangeNotifier extends ComponentChangeNotifier
{

	public void accValueChanged(Accelerometer<?,?> accelometer);

	
}
