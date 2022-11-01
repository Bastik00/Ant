package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_motorOn extends RemoteCommand
{
	
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 131223324090087545L;
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_STATUS = 1;
	

	protected static final String name = "motorOn";
	protected static final String description = "turn motor on/off";
	
	
public Cmd_motorOn() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterUint8("status","motor status"));
}



public Cmd_motorOn(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, boolean status)
{
	int value=0;
	
	if (status)
	{
		value =1;
	}
	
	(( RemoteParameterUint8) this.get(Cmd_motorOn.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_motorOn.INDEX_STATUS)).setValue(value);
}


/**
 * get motors index
 * @return motors index
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_motorOn.INDEX_MOTOR)).getValue());
}


/**
 * get new status for motor
 * true - motor is to be turn on
 * false - motor should be shut off
 * @return
 */
public boolean getStatus()
{
	int value;
	
	value = (( RemoteParameterUint8) this.get(Cmd_motorOn.INDEX_STATUS)).getValue();
	
	if (value==0)
		return(false);
	
	return(true);
}


@Override
public String getName() 
{
	return(Cmd_motorOn.name);
}


@Override
public String getDescription() 
{
	return(Cmd_motorOn.description);
}



public static Cmd_motorOn getCommand(int command)
{
	Cmd_motorOn cmd;
	cmd = new Cmd_motorOn(command);
	
	return(cmd);
}

public static Cmd_motorOn getCommand(int command,int index, boolean status)
{
	Cmd_motorOn cmd;
	cmd = Cmd_motorOn.getCommand(command);
	cmd.setData(index,status);
	
	return(cmd);
}


}
