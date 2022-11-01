package de.hska.lat.ant.devices.tailBoard;

import de.hska.lat.ant.devices.tailBoard.TailBoard;
import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;


import de.hska.lat.robot.component.actor.led.Led;
import de.hska.lat.robot.component.actor.led.view.LedDataView;
import de.hska.lat.robot.component.generic.accelerometer.view.AccelerometerDataView;
import de.hska.lat.robot.component.generic.compass.view.CompassDataView;
import de.hska.lat.robot.component.generic.distance.view.DistanceSensorDataView;
import de.hska.lat.robot.component.generic.imu.view.ImuDataView;
import de.hska.lat.robot.component.generic.lux.view.LuxSensorDataView;
import de.hska.lat.robot.component.generic.temperature.view.TemperatureSensorDataView;
import de.hska.lat.robot.component.generic.gyroscope.view.GyroscopeDataView;
import de.hska.lat.robot.component.heading.Heading2DDataView;
import de.hska.lat.robot.component.locator.view.Locator2DDataView;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;


import de.hska.lat.robot.device.viewer.DeviceView;





public class TailBoardDataView extends DeviceView
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
	
	this.addComponent(AccelerometerDataView.createView(tailBoard.getAccelerometer()));
	this.addComponent(GyroscopeDataView.createView(tailBoard.getGyroscope()));
	this.addComponent(ImuDataView.createView(tailBoard.getImu()));
	this.addComponent(CompassDataView.createView(tailBoard.getCompass()));
	this.addComponent(TemperatureSensorDataView.createView(tailBoard.getTemperatureSensor()));

	this.addComponent(Heading2DDataView.createView(tailBoard.getHeading()));
	this.addComponent(Locator2DDataView.createView(tailBoard.getLocator()));
	
	
	for (Vcnl4000 sensor : tailBoard.getVcnl4000Set())
	{
		this.addComponent(LuxSensorDataView.createView(sensor.getLuxSensor()));
		this.addComponent(DistanceSensorDataView.createView(sensor.getDistanceSensor()));
	}
	
	for (Tmp006 sensor : tailBoard.getTmp006Set())
	{
		this.addComponent(TemperatureSensorDataView.createView(sensor.getAmbientSensor()));
		this.addComponent(TemperatureSensorDataView.createView(sensor.getObjectSensor()));
	}

	for (Led led : tailBoard.getLedSet())
	{
		this.addComponent(LedDataView.createView(led));
	}
	
}


}