package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;

import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_servoMove extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_SERVO = 0;
	private static final int INDEX_VELOCITY = 1;
	

	protected static final String name = "moveServo";
	protected static final String description = "move servo at given velocity";
	
	
public Cmd_servoMove() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
	this.add(new RemoteParameterServoVelocity("velocity","velocity of this movement"));
}



public Cmd_servoMove(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float velocity)
{
	(( RemoteParameterUint8) this.get(Cmd_servoMove.INDEX_SERVO)).setValue(index);
	(( RemoteParameterServoVelocity) this.get(Cmd_servoMove.INDEX_VELOCITY)).setVelocity(velocity);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_servoMove.INDEX_SERVO)).getValue());
}

public float getVelocity()
{
	return((( RemoteParameterServoVelocity) this.get(Cmd_servoMove.INDEX_VELOCITY)).getVelocity());
}


@Override
public String getName() 
{
	return(Cmd_servoMove.name);
}


@Override
public String getDescription() 
{
	return(Cmd_servoMove.description);
}



public static Cmd_servoMove getCommand(int command)
{
	Cmd_servoMove cmd;
	cmd = new Cmd_servoMove(command);
	
	return(cmd);
}

public static Cmd_servoMove getCommand(int command,int index, float velocity)
{
	Cmd_servoMove cmd;
	cmd = Cmd_servoMove.getCommand(command);
	cmd.setData(index, velocity);
	
	return(cmd);
}


}
