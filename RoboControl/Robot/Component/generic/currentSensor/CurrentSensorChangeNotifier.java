package de.hska.lat.robot.component.currentSensor;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface CurrentSensorChangeNotifier extends ComponentChangeNotifier
{

	public void currentValueChanged(CurrentSensor current);


}
