package de.hska.lat.robot.component.generic.accelerometer.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getAcceleration extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAcceleration";
	protected static final String description = "get acceleration measured by a accelerometer sensor";
	
	
public Cmd_getAcceleration() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getAcceleration(int command) 
{
	this();
	this.setId(command);
}





public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getAcceleration.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getAcceleration.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getAcceleration.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getAcceleration.description);
}



public static Cmd_getAcceleration getCommand(int command)
{
	Cmd_getAcceleration cmd;
	cmd = new Cmd_getAcceleration(command);
	
	return(cmd);
}

public static Cmd_getAcceleration getCommand(int command,int index)
{
	Cmd_getAcceleration cmd;
	cmd = Cmd_getAcceleration.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
