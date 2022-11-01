package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setMotorRpm extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_RPM = 1;
	

	protected static final String name = "setMotorRpm";
	protected static final String description = "set motor revoltuions per minute";
	
	
public Cmd_setMotorRpm() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterMotorRpm("revoltuions per minute"));
}



public Cmd_setMotorRpm(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float rpm)
{
	(( RemoteParameterUint8) this.get(Cmd_setMotorRpm.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterMotorRpm) this.get(Cmd_setMotorRpm.INDEX_RPM)).setValue(rpm);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMotorRpm.INDEX_MOTOR)).getValue());
}

public float getRpm()
{
	return((( RemoteParameterMotorRpm) this.get(Cmd_setMotorRpm.INDEX_RPM)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setMotorRpm.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMotorRpm.description);
}



public static Cmd_setMotorRpm getCommand(int command)
{
	Cmd_setMotorRpm cmd;
	cmd = new Cmd_setMotorRpm(command);
	
	return(cmd);
}


public static Cmd_setMotorRpm getCommand(int command,int index, float rpm)
{
	Cmd_setMotorRpm cmd;
	cmd = Cmd_setMotorRpm.getCommand(command);
	cmd.setData(index, rpm);
	
	return(cmd);
}


}
