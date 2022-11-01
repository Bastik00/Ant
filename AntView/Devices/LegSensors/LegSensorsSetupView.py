"""package de.hska.lat.ant.devices.legSensors;


import de.hska.lat.ant.metaData.AntDeviceId;
import de.hska.lat.robot.abstractRobot.AbstractRobot;




import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.component.sensor.vcnl4000.view.Vcnl4000SetupView;


import de.hska.lat.robot.device.viewer.DeviceView;



public class LegSensorsSetupView extends DeviceView
{



	
/**
	 * 
	 */
	private static final long serialVersionUID = 1840040237898819284L;


@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	LegSensors ls;
	
	ls=(LegSensors)robot.getDeviceOnName(AntDeviceId.LEG_SENSORS.getName());
	
			
	if (ls!=null)
	{
		this.makeDisplay(robot.getName(), ls);
		return(true);
	}
	else
	{
		this.makeErrorDisplay(LegSensors.class.getName());
		return(false);
	}
}



private void makeDisplay(String robotName, LegSensors hs) 
{

	this.setDevice(robotName, hs);
	


	for ( Vcnl4000 sensor : hs.getVcnl4000Set())
	{
		this.addComponent(Vcnl4000SetupView.createView(sensor));
	}
	
	
	


	
}









}
"""