package de.hska.lat.robot.component.touchSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getTouchSensorStatistic extends RemoteCommand
{
	
	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4349207964986087110L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAnalogsensorStatistic";
	protected static final String description = "get statistic min & max values from an analog sensor ";
	
	
public Cmd_getTouchSensorStatistic() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_getTouchSensorStatistic(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getTouchSensorStatistic.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getTouchSensorStatistic.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getTouchSensorStatistic.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getTouchSensorStatistic.description);
}



public static Cmd_getTouchSensorStatistic getCommand(int command)
{
	Cmd_getTouchSensorStatistic cmd;
	cmd = new Cmd_getTouchSensorStatistic(command);
	
	return(cmd);
}

public static Cmd_getTouchSensorStatistic getCommand(int command,int index)
{
	Cmd_getTouchSensorStatistic cmd;
	cmd = Cmd_getTouchSensorStatistic.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
