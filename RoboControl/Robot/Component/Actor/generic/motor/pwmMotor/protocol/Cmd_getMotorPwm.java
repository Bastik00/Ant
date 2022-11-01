package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getMotorPwm extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getMotorPwm";
	protected static final String description = "get pwm widh for a motor";
	
	
public Cmd_getMotorPwm() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_getMotorPwm(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getMotorPwm.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getMotorPwm.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getMotorPwm.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getMotorPwm.description);
}



public static Cmd_getMotorPwm getCommand(int command)
{
	Cmd_getMotorPwm cmd;
	cmd = new Cmd_getMotorPwm(command);
	
	return(cmd);
}

public static Cmd_getMotorPwm getCommand(int command,int index)
{
	Cmd_getMotorPwm cmd;
	cmd = Cmd_getMotorPwm.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
