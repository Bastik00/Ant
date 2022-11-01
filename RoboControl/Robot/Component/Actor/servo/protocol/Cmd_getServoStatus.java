package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getServoStatus extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 86468074988499624L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getServoStatus";
	protected static final String description = "get status of an servo";
	
	
public Cmd_getServoStatus() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_getServoStatus(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getServoStatus.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getServoStatus.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getServoStatus.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getServoStatus.description);
}



public static Cmd_getServoStatus getCommand(int command)
{
	Cmd_getServoStatus cmd;
	cmd = new Cmd_getServoStatus(command);
	
	return(cmd);
}

public static Cmd_getServoStatus getCommand(int command,int index)
{
	Cmd_getServoStatus cmd;
	cmd = Cmd_getServoStatus.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
