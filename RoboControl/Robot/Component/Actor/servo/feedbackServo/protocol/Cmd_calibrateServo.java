package de.hska.lat.robot.component.actor.servo.feedbackServo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_calibrateServo extends RemoteCommand
{
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final int INDEX_SERVO = 0;
	

	protected static final String name = "calibrateServo";
	protected static final String description = "calibrate analog position measure for this servo";
	
	
public Cmd_calibrateServo() 
{
	this.add(new RemoteParameterUint8("index","index of servo"));
}



public Cmd_calibrateServo(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_calibrateServo.INDEX_SERVO)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_calibrateServo.INDEX_SERVO)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_calibrateServo.name);
}


@Override
public String getDescription() 
{
	return(Cmd_calibrateServo.description);
}



public static Cmd_calibrateServo getCommand(int command)
{
	Cmd_calibrateServo cmd;
	cmd = new Cmd_calibrateServo(command);
	
	return(cmd);
}

public static Cmd_calibrateServo getCommand(int command,int index)
{
	Cmd_calibrateServo cmd;
	cmd = Cmd_calibrateServo.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
