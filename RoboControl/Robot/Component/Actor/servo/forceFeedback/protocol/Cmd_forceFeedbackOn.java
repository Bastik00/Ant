package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_forceFeedbackOn extends RemoteCommand
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8595200599490205211L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "forceFeedbackOn";
	protected static final String description = "switch forceFeedback on";
	
	
public Cmd_forceFeedbackOn() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_forceFeedbackOn(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_forceFeedbackOn.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_forceFeedbackOn.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_forceFeedbackOn.name);
}


@Override
public String getDescription() 
{
	return(Cmd_forceFeedbackOn.description);
}



public static Cmd_forceFeedbackOn getCommand(int command)
{
	Cmd_forceFeedbackOn cmd;
	cmd = new Cmd_forceFeedbackOn(command);
	
	return(cmd);
}

public static Cmd_forceFeedbackOn getCommand(int command,int index)
{
	Cmd_forceFeedbackOn cmd;
	cmd = Cmd_forceFeedbackOn.getCommand(command);
	cmd.setData(1<<index);
	
	return(cmd);
}


}
