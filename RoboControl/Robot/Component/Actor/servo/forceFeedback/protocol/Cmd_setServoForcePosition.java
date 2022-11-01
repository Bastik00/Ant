package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setServoForcePosition extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 952904018157758492L;
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_SPEED = 1;
	

	protected static final String name = "setServoForcePosition";
	protected static final String description = "set servos force position";
	
	
public Cmd_setServoForcePosition() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterUint16("speed","new servo force position"));
}



public Cmd_setServoForcePosition(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int position)
{
	(( RemoteParameterUint8) this.get(Cmd_setServoForcePosition.INDEX_SERVO)).setValue(index);
	(( RemoteParameterUint16) this.get(Cmd_setServoForcePosition.INDEX_SPEED)).setValue(position);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoForcePosition.INDEX_SERVO)).getValue());
}

public int getSpeed()
{
	return((( RemoteParameterUint16) this.get(Cmd_setServoForcePosition.INDEX_SPEED)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setServoForcePosition.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setServoForcePosition.description);
}



public static Cmd_setServoForcePosition getCommand(int command)
{
	Cmd_setServoForcePosition cmd;
	cmd = new Cmd_setServoForcePosition(command);
	
	return(cmd);
}

public static Cmd_setServoForcePosition getCommand(int command,int index, int position)
{
	Cmd_setServoForcePosition cmd;
	cmd = Cmd_setServoForcePosition.getCommand(command);
	cmd.setData(index, position);
	
	return(cmd);
}


}
