package de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;

public class Cmd_rc5SendCommand extends RemoteCommand {
	
	private static final long serialVersionUID = -7446077035148723843L;
	private static final int INDEX_COMMAND = 0;
	
	protected static final String name = "Send-Data";
	protected static final String description = "Sends a Adress code and a command";
	
	public Cmd_rc5SendCommand() {
		this.add(new RemoteParameterUint16("command","rc5 command "));
	}

	public Cmd_rc5SendCommand(int command) {
		this();
		this.setId(command);
	}


	public void setData(int index) {
		(( RemoteParameterUint16) this.get(INDEX_COMMAND)).setValue(index);
	}


	public int getCommand() {
		return((( RemoteParameterUint16) this.get(INDEX_COMMAND)).getValue());
	}





@Override
public String getName() 
{
	return(Cmd_rc5SendCommand.name);
}


@Override
public String getDescription() 
{
	return(Cmd_rc5SendCommand.description);
}



public static Cmd_rc5SendCommand getCommand(int command)
{
	Cmd_rc5SendCommand cmd;
	cmd = new Cmd_rc5SendCommand(command);
	
	return(cmd);
}


public static Cmd_rc5SendCommand getCommand(int command,int rc5Command)
{
	Cmd_rc5SendCommand cmd;
	cmd = Cmd_rc5SendCommand.getCommand(command);
	cmd.setData(rc5Command);
	
	return(cmd);
}


}