package de.hska.lat.ant.devices.headSensors;


import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;



import de.hska.lat.robot.component.temperatureSensor.mlx90614.Mlx90614;
import de.hska.lat.robot.component.sensor.mlx90614.view.Mlx90614SetupView;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.component.sensor.vcnl4000.view.Vcnl4000SetupView;


import de.hska.lat.robot.device.viewer.DeviceView;



public class HeadSensorsSetupView extends DeviceView
{



	
/**
	 * 
	 */
	private static final long serialVersionUID = 1840040237898819284L;


@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	HeadSensors hs;
	
	hs=(HeadSensors)robot.getDeviceOnName(AntDeviceId.HEAD_SENSORS.getName());
	
			
	if (hs!=null)
	{
		this.makeDisplay(robot.getName(), hs);
		return(true);
	}
	else
	{
		this.makeErrorDisplay(HeadSensors.class.getName());
		return(false);
	}
}



private void makeDisplay(String robotName, HeadSensors hs) 
{

	this.setDevice(robotName, hs);
	
	for ( Mlx90614 sensor : hs.getMlx90614Set())
	{
		this.addComponent(Mlx90614SetupView.createView(sensor));
	}


	for ( Vcnl4000 sensor : hs.getVcnl4000Set())
	{
		this.addComponent(Vcnl4000SetupView.createView(sensor));
	}
	
	/*
	for ( Bmp085 sensor : hs.getBmp085Set())
	{
		this.addComponent(Bmp085SetupView.createView(sensor));
	}
	*/


	
}









}
