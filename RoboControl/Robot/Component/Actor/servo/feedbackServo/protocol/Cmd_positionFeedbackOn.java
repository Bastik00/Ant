package de.hska.lat.robot.component.actor.servo.feedbackServo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_positionFeedbackOn extends RemoteCommand
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7250469492327294277L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "positionFeedbackOn";
	protected static final String description = "switch forceFeedback on";
	
	
public Cmd_positionFeedbackOn() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_positionFeedbackOn(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_positionFeedbackOn.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_positionFeedbackOn.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_positionFeedbackOn.name);
}


@Override
public String getDescription() 
{
	return(Cmd_positionFeedbackOn.description);
}



public static Cmd_positionFeedbackOn getCommand(int command)
{
	Cmd_positionFeedbackOn cmd;
	cmd = new Cmd_positionFeedbackOn(command);
	
	return(cmd);
}

public static Cmd_positionFeedbackOn getCommand(int command,int index)
{
	Cmd_positionFeedbackOn cmd;
	cmd = Cmd_positionFeedbackOn.getCommand(command);
	cmd.setData(1<<index);
	
	return(cmd);
}


}
