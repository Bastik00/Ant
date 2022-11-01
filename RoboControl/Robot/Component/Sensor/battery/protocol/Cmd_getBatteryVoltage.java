package de.hska.lat.robot.component.battery.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getBatteryVoltage extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8615954259563545343L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getBatteryStatistics";
	protected static final String description = "get statistic from battery ( min / max) ";
	
	
public Cmd_getBatteryVoltage() 
{
	this.add(new RemoteParameterUint8("index","index of battery"));
}



public Cmd_getBatteryVoltage(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getBatteryVoltage.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getBatteryVoltage.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getBatteryVoltage.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getBatteryVoltage.description);
}



public static Cmd_getBatteryVoltage getCommand(int command)
{
	Cmd_getBatteryVoltage cmd;
	cmd = new Cmd_getBatteryVoltage(command);
	
	return(cmd);
}

public static Cmd_getBatteryVoltage getCommand(int command,int index)
{
	Cmd_getBatteryVoltage cmd;
	cmd = Cmd_getBatteryVoltage.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
