package de.hska.lat.robot.component.generic.imu;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface ImuChangeNotifier  extends ComponentChangeNotifier
{
	public void imuChanged(Imu imu);
}
