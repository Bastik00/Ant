package de.hska.lat.robot.component.generic.colorSensor;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface ColorSensorChangeNotifier extends ComponentChangeNotifier
{
	public void colorChanged(ColorSensor<?,?> sensor);
}
