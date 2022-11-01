package de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getMotorVelocity extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int MOTOR_INDEX = 0;
	

	protected static final String name = "getMotorVelocity";
	protected static final String description = "get motors motors velocity in m/s";
	
	
public Cmd_getMotorVelocity() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
}



public Cmd_getMotorVelocity(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getMotorVelocity.MOTOR_INDEX)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getMotorVelocity.MOTOR_INDEX)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getMotorVelocity.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getMotorVelocity.description);
}



public static Cmd_getMotorVelocity getCommand(int command)
{
	Cmd_getMotorVelocity cmd;
	cmd = new Cmd_getMotorVelocity(command);
	
	return(cmd);
}

public static Cmd_getMotorVelocity getCommand(int command,int index)
{
	Cmd_getMotorVelocity cmd;
	cmd = Cmd_getMotorVelocity.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
