package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getMotorRpm extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_MOTOR = 0;
	

	protected static final String name = "getMotorRpm";
	protected static final String description = "get motors revolutions per minute";
	
	
public Cmd_getMotorRpm() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
}



public Cmd_getMotorRpm(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getMotorRpm.INDEX_MOTOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getMotorRpm.INDEX_MOTOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getMotorRpm.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getMotorRpm.description);
}



public static Cmd_getMotorRpm getCommand(int command)
{
	Cmd_getMotorRpm cmd;
	cmd = new Cmd_getMotorRpm(command);
	
	return(cmd);
}

public static Cmd_getMotorRpm getCommand(int command,int index)
{
	Cmd_getMotorRpm cmd;
	cmd = Cmd_getMotorRpm.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
