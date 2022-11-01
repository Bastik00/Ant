package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * commands robots movement controller to move the given distance, relative to its actual position & heading
 * @author tavin
 *
 */
public class Cmd_stop extends RemoteCommand
{
	
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3718538554900400981L;
	protected static final String name = "stop";
	protected static final String description = "stop motion";
	
	
public Cmd_stop() 
{
}



public Cmd_stop(int command) 
{
	this();
	this.setId(command);
}


public void setData()
{

}



@Override
public String getName() 
{
	return(Cmd_stop.name);
}


@Override
public String getDescription() 
{
	return(Cmd_stop.description);
}



public static Cmd_stop getCommand(int command)
{
	Cmd_stop cmd;
	cmd = new Cmd_stop(command);
	
	return(cmd);
}




}
