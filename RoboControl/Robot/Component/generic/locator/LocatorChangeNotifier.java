package de.hska.lat.robot.component.generic.locator;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface LocatorChangeNotifier extends ComponentChangeNotifier
{
	public void locationChanged(FloatVector3D location);
}
