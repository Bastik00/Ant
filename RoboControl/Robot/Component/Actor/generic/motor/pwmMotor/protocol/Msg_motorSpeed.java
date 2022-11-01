package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Msg_motorSpeed extends RemoteMessage
{
	
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228417555208084978L;
	private static final int INDEX_MOTOR = 0;
	private static final int INDEX_SPEED = 1;
	

	protected static final String name = "setMotorSpeed";
	protected static final String description = "set speed of a motor";
	
	
public Msg_motorSpeed() 
{
	this.add(new RemoteParameterUint8("index","index of motor"));
	this.add(new RemoteParameterInt16("speed","motor speed"));
}



public Msg_motorSpeed(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, int speed)
{
	(( RemoteParameterUint8) this.get(Msg_motorSpeed.INDEX_MOTOR)).setValue(index);
	(( RemoteParameterInt16) this.get(Msg_motorSpeed.INDEX_SPEED)).setValue(speed);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_motorSpeed.INDEX_MOTOR)).getValue());
}

public int getSpeed()
{
	return((( RemoteParameterInt16) this.get(Msg_motorSpeed.INDEX_SPEED)).getValue());
}


@Override
public String getName() 
{
	return(Msg_motorSpeed.name);
}


@Override
public String getDescription() 
{
	return(Msg_motorSpeed.description);
}



public static Msg_motorSpeed getCommand(int command)
{
	Msg_motorSpeed cmd;
	cmd = new Msg_motorSpeed(command);
	
	return(cmd);
}

public static Msg_motorSpeed getCommand(int command,int index, int speed)
{
	Msg_motorSpeed cmd;
	cmd = Msg_motorSpeed.getCommand(command);
	cmd.setData(index, speed);
	
	return(cmd);
}


}
