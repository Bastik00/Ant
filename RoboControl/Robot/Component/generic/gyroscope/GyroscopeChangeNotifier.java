package de.hska.lat.robot.component.generic.gyroscope;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface GyroscopeChangeNotifier extends ComponentChangeNotifier
{
	public void rateChanged(Gyroscope<?,?> gyroscope);
	
}
