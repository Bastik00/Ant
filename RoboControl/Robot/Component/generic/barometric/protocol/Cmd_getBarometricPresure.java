package de.hska.lat.robot.component.generic.barometric.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getBarometricPresure extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getPresure";
	protected static final String description = "get measured presure from a barometric presure sensor";
	
	
public Cmd_getBarometricPresure() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getBarometricPresure(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getBarometricPresure.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getBarometricPresure.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getBarometricPresure.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getBarometricPresure.description);
}



public static Cmd_getBarometricPresure getCommand(int command)
{
	Cmd_getBarometricPresure cmd;
	cmd = new Cmd_getBarometricPresure(command);
	
	return(cmd);
}

public static Cmd_getBarometricPresure getCommand(int command,int index)
{
	Cmd_getBarometricPresure cmd;
	cmd = Cmd_getBarometricPresure.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
