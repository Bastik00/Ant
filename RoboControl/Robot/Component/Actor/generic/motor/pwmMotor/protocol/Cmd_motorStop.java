package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_motorStop extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3438577023951381528L;


	private static final int INDEX_MOTOR = 0;
	

	protected static final String name = "motorBrake";
	protected static final String description = "brake motor";
	
	
public Cmd_motorStop() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
}



public Cmd_motorStop(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_motorStop.INDEX_MOTOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_motorStop.INDEX_MOTOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_motorStop.name);
}


@Override
public String getDescription() 
{
	return(Cmd_motorStop.description);
}



public static Cmd_motorStop getCommand(int command)
{
	Cmd_motorStop cmd;
	cmd = new Cmd_motorStop(command);
	
	return(cmd);
}

public static Cmd_motorStop getCommand(int command,int index)
{
	Cmd_motorStop cmd;
	cmd = Cmd_motorStop.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
