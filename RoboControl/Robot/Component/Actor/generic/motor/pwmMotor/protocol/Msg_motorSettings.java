package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Msg_motorSettings extends RemoteMessage
{
	
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228417555208084978L;
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_SPEED = 1;
	

	protected static final String name = "setMotorSpeed";
	protected static final String description = "set speed of a motor";
	
	
public Msg_motorSettings() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterInt16("speed","motor speed"));
}



public Msg_motorSettings(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int speed)
{
	(( RemoteParameterUint8) this.get(Msg_motorSettings.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterInt16) this.get(Msg_motorSettings.INDEX_SPEED)).setValue(speed);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_motorSettings.INDEX_MOTOR)).getValue());
}

public int getSpeed()
{
	return((( RemoteParameterInt16) this.get(Msg_motorSettings.INDEX_SPEED)).getValue());
}


@Override
public String getName() 
{
	return(Msg_motorSettings.name);
}


@Override
public String getDescription() 
{
	return(Msg_motorSettings.description);
}



public static Msg_motorSettings getCommand(int command)
{
	Msg_motorSettings cmd;
	cmd = new Msg_motorSettings(command);
	
	return(cmd);
}

public static Msg_motorSettings getCommand(int command,int index, int speed)
{
	Msg_motorSettings cmd;
	cmd = Msg_motorSettings.getCommand(command);
	cmd.setData(index, speed);
	
	return(cmd);
}


}
