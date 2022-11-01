package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getServoForceThreshold extends RemoteCommand
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 9067742003929538167L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "getServoForceThreshold";
	protected static final String description = "get force theshold of a servo";
	
	
public Cmd_getServoForceThreshold() 
{
	this.add(new RemoteParameterUint16("index","index of servo"));
}






public Cmd_getServoForceThreshold(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getServoForceThreshold.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getServoForceThreshold.description);
}


public void setData(int index)
{
	(( RemoteParameterUint16) this.get(Cmd_getServoForceThreshold.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint16) this.get(Cmd_getServoForceThreshold.INDEX_SERVO)).getValue());
}







public static Cmd_getServoForceThreshold getCommand(int command)
{
	Cmd_getServoForceThreshold cmd;
	cmd = new Cmd_getServoForceThreshold(command);
	
	return(cmd);
}

public static Cmd_getServoForceThreshold getCommand(int command,int index)
{
	Cmd_getServoForceThreshold cmd;
	cmd = Cmd_getServoForceThreshold.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
