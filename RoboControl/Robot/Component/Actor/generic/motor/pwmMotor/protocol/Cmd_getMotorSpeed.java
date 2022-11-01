package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getMotorSpeed extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getMotorSpeed";
	protected static final String description = "get speed of a motor";
	
	
public Cmd_getMotorSpeed() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_getMotorSpeed(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getMotorSpeed.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getMotorSpeed.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getMotorSpeed.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getMotorSpeed.description);
}



public static Cmd_getMotorSpeed getCommand(int command)
{
	Cmd_getMotorSpeed cmd;
	cmd = new Cmd_getMotorSpeed(command);
	
	return(cmd);
}

public static Cmd_getMotorSpeed getCommand(int command,int index)
{
	Cmd_getMotorSpeed cmd;
	cmd = Cmd_getMotorSpeed.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
