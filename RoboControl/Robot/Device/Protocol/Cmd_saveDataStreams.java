package de.hska.lat.robot.device.protocol;

import de.hska.lat.comm.remote.RemoteCommand;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_saveDataStreams extends RemoteCommand
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -660798077689811174L;
	protected static final String name = "saveDataStreams";
	protected static final String description = "save device actuals data Streams to non volatile memory";
	
	
public Cmd_saveDataStreams() 
{
}



public Cmd_saveDataStreams(int command) 
{
	this();
	this.setId(command);
}



@Override
public String getName() 
{
	return(Cmd_saveDataStreams.name);
}


@Override
public String getDescription() 
{
	return(Cmd_saveDataStreams.description);
}



public static Cmd_saveDataStreams getCommand(int id)
{
	Cmd_saveDataStreams cmd;
	cmd = new Cmd_saveDataStreams(id);
	
	return(cmd);
}


}
