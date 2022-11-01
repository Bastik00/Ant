package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_forceFeedbackOff extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5944014470805193706L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "forceFeedbackOff";
	protected static final String description = "switch forceFeedback off";
	
	
public Cmd_forceFeedbackOff() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_forceFeedbackOff(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_forceFeedbackOff.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_forceFeedbackOff.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_forceFeedbackOff.name);
}


@Override
public String getDescription() 
{
	return(Cmd_forceFeedbackOff.description);
}



public static Cmd_forceFeedbackOff getCommand(int command)
{
	Cmd_forceFeedbackOff cmd;
	cmd = new Cmd_forceFeedbackOff(command);
	
	return(cmd);
}

public static Cmd_forceFeedbackOff getCommand(int command,int index)
{
	Cmd_forceFeedbackOff cmd;
	cmd = Cmd_forceFeedbackOff.getCommand(command);
	cmd.setData(1<<index);
	
	return(cmd);
}


}
