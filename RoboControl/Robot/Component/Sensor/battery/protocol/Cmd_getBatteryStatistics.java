package de.hska.lat.robot.component.battery.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getBatteryStatistics extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8615954259563545343L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getBatteryStatistics";
	protected static final String description = "get statistic from battery ( min / max) ";
	
	
public Cmd_getBatteryStatistics() 
{
	this.add(new RemoteParameterUint8("index","index of battery"));
}



public Cmd_getBatteryStatistics(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getBatteryStatistics.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getBatteryStatistics.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getBatteryStatistics.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getBatteryStatistics.description);
}



public static Cmd_getBatteryStatistics getCommand(int command)
{
	Cmd_getBatteryStatistics cmd;
	cmd = new Cmd_getBatteryStatistics(command);
	
	return(cmd);
}

public static Cmd_getBatteryStatistics getCommand(int command,int index)
{
	Cmd_getBatteryStatistics cmd;
	cmd = Cmd_getBatteryStatistics.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
