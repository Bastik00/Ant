package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setServoSpeed extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4573900446841356299L;
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_SPEED = 1;
	

	protected static final String name = "setServoSpeed";
	protected static final String description = "set servos actual speed";
	
	
public Cmd_setServoSpeed() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterUint8("speed","new servo speed"));
}



public Cmd_setServoSpeed(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int position)
{
	(( RemoteParameterUint8) this.get(Cmd_setServoSpeed.INDEX_SERVO)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setServoSpeed.INDEX_SPEED)).setValue(position);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoSpeed.INDEX_SERVO)).getValue());
}

public int getSpeed()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoSpeed.INDEX_SPEED)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setServoSpeed.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setServoSpeed.description);
}



public static Cmd_setServoSpeed getCommand(int command)
{
	Cmd_setServoSpeed cmd;
	cmd = new Cmd_setServoSpeed(command);
	
	return(cmd);
}

public static Cmd_setServoSpeed getCommand(int command,int index, int position)
{
	Cmd_setServoSpeed cmd;
	cmd = Cmd_setServoSpeed.getCommand(command);
	cmd.setData(index, position);
	
	return(cmd);
}


}
