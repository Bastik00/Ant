package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setMotorSettings extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_TICKS = 1;
	

	protected static final String name = "setMotorSettings";
	protected static final String description = "set settings of a motor";
	
	
public Cmd_setMotorSettings() 
{
	this.add(new RemoteParameterUint8("index","index of motor in set"));
	this.add(new RemoteParameterUint16("tick per revolution","motors tick prt revolution"));
}



public Cmd_setMotorSettings(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int tickPerRevolution)
{
	(( RemoteParameterUint8) this.get(Cmd_setMotorSettings.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Cmd_setMotorSettings.INDEX_TICKS)).setValue(tickPerRevolution);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMotorSettings.INDEX_MOTOR)).getValue());
}

public int getTickPerRevolution()
{
	return((( RemoteParameterUint16) this.get(Cmd_setMotorSettings.INDEX_TICKS)).getValue());
}


@Override
public String getName() 
{
	return(Cmd_setMotorSettings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMotorSettings.description);
}



public static Cmd_setMotorSettings getCommand(int command)
{
	Cmd_setMotorSettings cmd;
	cmd = new Cmd_setMotorSettings(command);
	
	return(cmd);
}

public static Cmd_setMotorSettings getCommand(int command,int index, int tickPerRevolution)
{
	Cmd_setMotorSettings cmd;
	cmd = Cmd_setMotorSettings.getCommand(command);
	cmd.setData(index, tickPerRevolution);
	
	return(cmd);
}


}
