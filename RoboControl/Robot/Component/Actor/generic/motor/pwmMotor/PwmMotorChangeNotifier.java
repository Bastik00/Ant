package de.hska.lat.robot.component.actor.generic.motor.pwmMotor;

import de.hska.lat.robot.component.ComponentChangeNotifier;


public interface PwmMotorChangeNotifier extends ComponentChangeNotifier
{

	public void speedChanged(int globalId, int speed);
}
