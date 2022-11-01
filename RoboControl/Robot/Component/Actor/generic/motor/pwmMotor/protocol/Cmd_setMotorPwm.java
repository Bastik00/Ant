package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.RemoteParameterMotorVelocity;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setMotorPwm extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_VELOCITY = 1;
	

	protected static final String name = "setMotorSpeed";
	protected static final String description = "set speed of a motor";
	
	
public Cmd_setMotorPwm() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterMotorVelocity("velocity","motor velocity"));
}



public Cmd_setMotorPwm(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float pwm)
{
	(( RemoteParameterUint8) this.get(Cmd_setMotorPwm.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterMotorVelocity) this.get(Cmd_setMotorPwm.INDEX_VELOCITY)).setValue(pwm);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMotorPwm.INDEX_MOTOR)).getValue());
}

public float getSpeed()
{
	return((( RemoteParameterMotorVelocity) this.get(Cmd_setMotorPwm.INDEX_VELOCITY)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setMotorPwm.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMotorPwm.description);
}



public static Cmd_setMotorPwm getCommand(int command)
{
	Cmd_setMotorPwm cmd;
	cmd = new Cmd_setMotorPwm(command);
	
	return(cmd);
}

public static Cmd_setMotorPwm getCommand(int command,int index, float pwm)
{
	Cmd_setMotorPwm cmd;
	cmd = Cmd_setMotorPwm.getCommand(command);
	cmd.setData(index, pwm);
	
	return(cmd);
}


}
