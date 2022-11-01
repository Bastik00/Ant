package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getMotorStatus extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getMotorStatus";
	protected static final String description = "get status of a motor";
	
	
public Cmd_getMotorStatus() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterUint8("status","status"));
}



public Cmd_getMotorStatus(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getMotorStatus.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getMotorStatus.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getMotorStatus.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getMotorStatus.description);
}



public static Cmd_getMotorStatus getCommand(int command)
{
	Cmd_getMotorStatus cmd;
	cmd = new Cmd_getMotorStatus(command);
	
	return(cmd);
}

public static Cmd_getMotorStatus getCommand(int command,int index)
{
	Cmd_getMotorStatus cmd;
	cmd = Cmd_getMotorStatus.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
