package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getServoForcePosition extends RemoteCommand
{



	/**
	 * 
	 */
	private static final long serialVersionUID = -1036027675449465129L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getServoForcePosition";
	protected static final String description = "get force position of a servo";
	
	
public Cmd_getServoForcePosition() 
{
	this.add(new RemoteParameterUint16("index","index of servo"));
}






public Cmd_getServoForcePosition(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getServoForcePosition.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getServoForcePosition.description);
}


public void setData(int index)
{
	(( RemoteParameterUint16) this.get(Cmd_getServoForcePosition.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint16) this.get(Cmd_getServoForcePosition.INDEX_SERVO)).getValue());
}







public static Cmd_getServoForcePosition getCommand(int command)
{
	Cmd_getServoForcePosition cmd;
	cmd = new Cmd_getServoForcePosition(command);
	
	return(cmd);
}

public static Cmd_getServoForcePosition getCommand(int command,int index)
{
	Cmd_getServoForcePosition cmd;
	cmd = Cmd_getServoForcePosition.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
