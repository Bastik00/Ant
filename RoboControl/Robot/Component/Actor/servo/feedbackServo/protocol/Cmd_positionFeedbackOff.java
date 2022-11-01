package de.hska.lat.robot.component.actor.servo.feedbackServo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_positionFeedbackOff extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1938395425194931535L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "positionFeedbackOff";
	protected static final String description = "switch forceFeedback off";
	
	
public Cmd_positionFeedbackOff() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_positionFeedbackOff(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_positionFeedbackOff.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_positionFeedbackOff.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_positionFeedbackOff.name);
}


@Override
public String getDescription() 
{
	return(Cmd_positionFeedbackOff.description);
}



public static Cmd_positionFeedbackOff getCommand(int command)
{
	Cmd_positionFeedbackOff cmd;
	cmd = new Cmd_positionFeedbackOff(command);
	
	return(cmd);
}

public static Cmd_positionFeedbackOff getCommand(int command,int index)
{
	Cmd_positionFeedbackOff cmd;
	cmd = Cmd_positionFeedbackOff.getCommand(command);
	cmd.setData(1<<index);
	
	return(cmd);
}


}
