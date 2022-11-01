package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getServoSpeed extends RemoteCommand
{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 3564313371574431201L;

	
	
	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getServoSpeed";
	protected static final String description = "get steed of aservo";
	
	
public Cmd_getServoSpeed() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}






public Cmd_getServoSpeed(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getServoSpeed.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getServoSpeed.description);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getServoSpeed.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getServoSpeed.INDEX_SERVO)).getValue());
}







public static Cmd_getServoSpeed getCommand(int command)
{
	Cmd_getServoSpeed cmd;
	cmd = new Cmd_getServoSpeed(command);
	
	return(cmd);
}

public static Cmd_getServoSpeed getCommand(int command,int index)
{
	Cmd_getServoSpeed cmd;
	cmd = Cmd_getServoSpeed.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
