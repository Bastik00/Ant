package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setServoPosition extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -647503691256279155L;
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_POSITION = 1;
	

	protected static final String name = "setServoPosition";
	protected static final String description = "set servo position, if received, servo try to reach this position at full speed";
	
	
public Cmd_setServoPosition() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterServoPosition("position","new servo position"));
}



public Cmd_setServoPosition(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float position)
{
	(( RemoteParameterUint8) this.get(Cmd_setServoPosition.INDEX_SERVO)).setValue(index);
	(( RemoteParameterServoPosition) this.get(Cmd_setServoPosition.INDEX_POSITION)).setPosition(position);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoPosition.INDEX_SERVO)).getValue());
}

public float getPosition()
{
	return((( RemoteParameterServoPosition) this.get(Cmd_setServoPosition.INDEX_POSITION)).getPosition());
}


@Override
public String getName() 
{
	return(Cmd_setServoPosition.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setServoPosition.description);
}



public static Cmd_setServoPosition getCommand(int command)
{
	Cmd_setServoPosition cmd;
	cmd = new Cmd_setServoPosition(command);
	
	return(cmd);
}

public static Cmd_setServoPosition getCommand(int command,int index, float position)
{
	Cmd_setServoPosition cmd;
	cmd = Cmd_setServoPosition.getCommand(command);
	cmd.setData(index, position);
	
	return(cmd);
}


}
