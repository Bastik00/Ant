package de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.RemoteParameterMotorVelocity;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setMotorVelocity extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int MOTOR_INDEX 	= 0;
	private static final int VELOCITY_INDEX	 = 1;
	

	protected static final String name = "setMotorVelocity";
	protected static final String description = "set motor motors velocity in m/s";
	
	
public Cmd_setMotorVelocity() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterMotorVelocity("motors velocity in m/s"));
}



public Cmd_setMotorVelocity(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float velocity)
{
	(( RemoteParameterUint8) this.get(Cmd_setMotorVelocity.MOTOR_INDEX)).setValue(index);
	(( RemoteParameterMotorVelocity) this.get(Cmd_setMotorVelocity.VELOCITY_INDEX)).setValue(velocity);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMotorVelocity.MOTOR_INDEX)).getValue());
}

public float getRpm()
{
	return((( RemoteParameterMotorVelocity) this.get(Cmd_setMotorVelocity.VELOCITY_INDEX)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setMotorVelocity.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMotorVelocity.description);
}



public static Cmd_setMotorVelocity getCommand(int command)
{
	Cmd_setMotorVelocity cmd;
	cmd = new Cmd_setMotorVelocity(command);
	
	return(cmd);
}

public static Cmd_setMotorVelocity getCommand(int command,int index, float velocity)
{
	Cmd_setMotorVelocity cmd;
	cmd = Cmd_setMotorVelocity.getCommand(command);
	cmd.setData(index, velocity);
	
	return(cmd);
}


}
