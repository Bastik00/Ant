package de.hska.lat.robot.component.detector;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface DetectorChangeNotifier  extends ComponentChangeNotifier 
{

	public void statusChanged(Detector<?,?,?> sensor);
	
}
