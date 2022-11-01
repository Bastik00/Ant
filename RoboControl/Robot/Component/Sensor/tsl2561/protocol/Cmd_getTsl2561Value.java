package de.hska.lat.robot.component.sensor.tsl2561.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getTsl2561Value extends RemoteCommand
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -563301386794674462L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getTsl2561Value";
	protected static final String description = "get measured light value from a TSL2561 sensor";
	
	
public Cmd_getTsl2561Value() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getTsl2561Value(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getTsl2561Value.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getTsl2561Value.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getTsl2561Value.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getTsl2561Value.description);
}



public static Cmd_getTsl2561Value getCommand(int command)
{
	Cmd_getTsl2561Value cmd;
	cmd = new Cmd_getTsl2561Value(command);
	
	return(cmd);
}

public static Cmd_getTsl2561Value getCommand(int command,int index)
{
	Cmd_getTsl2561Value cmd;
	cmd = Cmd_getTsl2561Value.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
