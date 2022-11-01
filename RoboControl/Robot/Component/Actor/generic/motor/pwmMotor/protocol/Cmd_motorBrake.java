package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_motorBrake extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3438577023951381528L;


	private static final int INDEX_MOTOR = 0;
	

	protected static final String name = "motorBrake";
	protected static final String description = "brake motor";
	
	
public Cmd_motorBrake() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
}



public Cmd_motorBrake(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_motorBrake.INDEX_MOTOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_motorBrake.INDEX_MOTOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_motorBrake.name);
}


@Override
public String getDescription() 
{
	return(Cmd_motorBrake.description);
}



public static Cmd_motorBrake getCommand(int command)
{
	Cmd_motorBrake cmd;
	cmd = new Cmd_motorBrake(command);
	
	return(cmd);
}

public static Cmd_motorBrake getCommand(int command,int index)
{
	Cmd_motorBrake cmd;
	cmd = Cmd_motorBrake.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
