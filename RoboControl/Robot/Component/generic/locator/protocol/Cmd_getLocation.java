package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteCommand;




/**
 * 
 * @author Oktavian Gniot
 *
 * 
 */

public class Cmd_getLocation extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4164251095565646107L;
	
	
	protected static final String name = "getLocation";
	protected static final String description = "get Locationfrom robot";
	
	

public Cmd_getLocation() 
{
}





	
	
public Cmd_getLocation(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getLocation.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getLocation.description);
}





public static Cmd_getLocation getCommand(int id)
{
	Cmd_getLocation cmd;
	cmd = new Cmd_getLocation(id);
	
	return(cmd);
}









}

