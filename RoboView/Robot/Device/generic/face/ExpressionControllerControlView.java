package de.hska.lat.robot.device.generic.face;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.muscle.Muscle;
import de.hska.lat.robot.component.muscle.MuscleControlView;
import de.hska.lat.robot.component.muscle.MuscleSet;

import de.hska.lat.robot.device.generic.expressionController.ExpressionController;
import de.hska.lat.robot.device.viewer.DeviceView;

public class ExpressionControllerControlView extends DeviceView
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8380412949856100831L;

	@Override
	public boolean setRobot(AbstractRobot<?, ?, ?> robot)
	{

		ExpressionController expressionController = (ExpressionController) robot
				.getDeviceOnName(ExpressionController.DEVICE_NAME);

		if (expressionController != null)
		{
			makeDisplay(robot.getName(), expressionController);
		} else
		{
			makeErrorDisplay(ExpressionController.class.getName());
		}

		return (true);
	}

	public void makeDisplay(String robotName, ExpressionController expressionController)
	{
		this.setDevice( robotName, expressionController);

		MuscleSet muscles = expressionController.getMuscles();

		for (Muscle muscle : muscles.getComponents())
		{
			this.addComponent(MuscleControlView.createView(muscle));
		}

		this.autoResize();

	}

}