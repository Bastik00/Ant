package de.hska.lat.robot.component.generic.heading;

import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface HeadingChangeNotifier  extends ComponentChangeNotifier
{
	public void headingChanged(AngularVector3D heading);
}
