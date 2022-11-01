package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_moveServoToAtSpeed extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6183833401213415133L;
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_POSITION = 1;
	private static final int INDEX_SPEED = 2;
	

	protected static final String name = "moveServoToAtSpeed";
	protected static final String description = "move servo to given position at given speed";
	
	
public Cmd_moveServoToAtSpeed() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterServoPosition("position","new servo position"));
	this.add(new RemoteParameterServoVelocity("velocity"," servo velocity"));
}



public Cmd_moveServoToAtSpeed(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float position, float velocity)
{
	(( RemoteParameterUint8) this.get(Cmd_moveServoToAtSpeed.INDEX_SERVO)).setValue(index);
	(( RemoteParameterServoPosition) this.get(Cmd_moveServoToAtSpeed.INDEX_POSITION)).setPosition(position);
	(( RemoteParameterServoVelocity) this.get(Cmd_moveServoToAtSpeed.INDEX_SPEED)).setVelocity(velocity);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_moveServoToAtSpeed.INDEX_SERVO)).getValue());
}

public float getPosition()
{
	return((( RemoteParameterServoPosition) this.get(Cmd_moveServoToAtSpeed.INDEX_POSITION)).getPosition());
}


public float getVelocity()
{
	return((( RemoteParameterServoVelocity) this.get(Cmd_moveServoToAtSpeed.INDEX_SPEED)).getVelocity());
}

@Override
public String getName() 
{
	return(Cmd_moveServoToAtSpeed.name);
}


@Override
public String getDescription() 
{
	return(Cmd_moveServoToAtSpeed.description);
}



public static Cmd_moveServoToAtSpeed getCommand(int command)
{
	Cmd_moveServoToAtSpeed cmd;
	cmd = new Cmd_moveServoToAtSpeed(command);
	
	return(cmd);
}

public static Cmd_moveServoToAtSpeed getCommand(int command,int index, float position, float velocity)
{
	Cmd_moveServoToAtSpeed cmd;
	cmd = Cmd_moveServoToAtSpeed.getCommand(command);
	cmd.setData(index, position, velocity);
	
	return(cmd);
}


}
