package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setServoForceThreshold extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8326110658538181166L;
	
	
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_SPEED = 1;
	

	protected static final String name = "setServoForceThreshold";
	protected static final String description = "set servos force threshold";
	
	
public Cmd_setServoForceThreshold() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterUint16("speed","new servo force threshold"));
}



public Cmd_setServoForceThreshold(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int position)
{
	(( RemoteParameterUint8) this.get(Cmd_setServoForceThreshold.INDEX_SERVO)).setValue(index);
	(( RemoteParameterUint16) this.get(Cmd_setServoForceThreshold.INDEX_SPEED)).setValue(position);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoForceThreshold.INDEX_SERVO)).getValue());
}

public int getSpeed()
{
	return((( RemoteParameterUint16) this.get(Cmd_setServoForceThreshold.INDEX_SPEED)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setServoForceThreshold.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setServoForceThreshold.description);
}



public static Cmd_setServoForceThreshold getCommand(int command)
{
	Cmd_setServoForceThreshold cmd;
	cmd = new Cmd_setServoForceThreshold(command);
	
	return(cmd);
}

public static Cmd_setServoForceThreshold getCommand(int command,int index, int position)
{
	Cmd_setServoForceThreshold cmd;
	cmd = Cmd_setServoForceThreshold.getCommand(command);
	cmd.setData(index, position);
	
	return(cmd);
}


}
