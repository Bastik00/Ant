package de.hska.lat.ant.control.movementControl;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.robot.abstractRobot.AbstractRobot;

import de.hska.lat.robot.component.generic.motion.MotionController2D;
import de.hska.lat.robot.control.driveControl.driveControl2D.view.DriveControl2DView;

public class MovementControlView extends DriveControl2DView
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432651758485331945L;

	
	
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	if (robot instanceof Ant)
	{
		this.motionController = (MotionController2D) robot.getComponentOnGlobalId(AntComponents.MOTION_CONTROLLER.getGlobalId()); 
		return(true);
	}
	
	return(false);
}




}
