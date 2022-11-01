package de.hska.lat.robot.component.generic.compass;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface CompassChangeNotifier extends ComponentChangeNotifier
{
	public void rateChanged(Compass<?,?> compass);
	
}



