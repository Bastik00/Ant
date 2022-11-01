package de.hska.lat.ant.devices.tailBoard;

import de.hska.lat.ant.devices.tailBoard.TailBoard;
import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;


import de.hska.lat.robot.component.actor.led.Led;
import de.hska.lat.robot.component.actor.led.view.LedControlView;


import de.hska.lat.robot.device.viewer.DeviceView;





public class TailBoardControlView extends DeviceView
{



/**
	 * 
	 */
	private static final long serialVersionUID = -5337372269809597680L;

@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	TailBoard imuBoard;
	
	imuBoard=(TailBoard)robot.getDeviceOnId(AntDeviceId.TAIL_BOARD.getId());
	
			
	if (imuBoard!=null)
	{
		this.makeDisplay(robot.getName(), imuBoard);
		return(true);
	}
	else
	{
		makeErrorDisplay(TailBoard.class.getName());
		return(true);
	}
}
	

	

private void makeDisplay(String robotName, TailBoard tailBoard) 
{

	this.setDevice(robotName, tailBoard);
	
	


	for (Led led : tailBoard.getLedSet())
	{
		this.addComponent(LedControlView.createView(led));
	}
	
}


}