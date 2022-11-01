package de.hska.lat.ant.devices.tailBoard;

import de.hska.lat.ant.devices.tailBoard.TailBoard;
import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;



import de.hska.lat.robot.component.sensor.mpu6000.view.Mpu6000SetupView;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.component.sensor.vcnl4000.view.Vcnl4000SetupView;

import de.hska.lat.robot.device.viewer.DeviceView;


public class TailBoardSetupView extends DeviceView
{




/**
	 * 
	 */
	private static final long serialVersionUID = -4594401705703074044L;




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
	
	this.addComponent(Mpu6000SetupView.createView(tailBoard.getMpu()));
	
	
	for ( Vcnl4000 sensor : tailBoard.getVcnl4000Set())
	{
		this.addComponent(Vcnl4000SetupView.createView(sensor));
	}
	
}


}