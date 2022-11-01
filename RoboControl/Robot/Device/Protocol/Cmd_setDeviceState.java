package de.hska.lat.robot.device.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * 
 * @author oktavian Gniot
 *
 */
public class Cmd_setDeviceState extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_TYPE = 0;
	

	protected static final String name = "stopStreamData";
	protected static final String description = "stop streaming data";
	
	
public Cmd_setDeviceState() 
{
	this.add(new RemoteParameterUint8("type","type of data (device dependent)"));
}



public Cmd_setDeviceState(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_setDeviceState.INDEX_TYPE)).setValue(index);
}



public int getType()
{
	return((( RemoteParameterUint8) this.get(Cmd_setDeviceState.INDEX_TYPE)).getValue());
}




@Override
public String getName() 
{
	return(Cmd_setDeviceState.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setDeviceState.description);
}



public static Cmd_setDeviceState getCommand(int command)
{
	Cmd_setDeviceState cmd;
	
	cmd = new Cmd_setDeviceState(command);
	
	return(cmd);
}


public static Cmd_setDeviceState getCommand(int command, int index)
{
	Cmd_setDeviceState cmd;
	
	cmd = new Cmd_setDeviceState(command);
	cmd.setData(index);
	
	return(cmd);
}


}
