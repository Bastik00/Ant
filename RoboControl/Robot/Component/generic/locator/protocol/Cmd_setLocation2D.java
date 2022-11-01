 package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt24;
import de.hska.lat.math.vector.FloatVector2D;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setLocation2D extends RemoteCommand
{
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 4043141982311053953L;


	private static final int INDEX_X = 0;
	private static final int INDEX_Y = 1;
	

	protected static final String name = "setActualLocation2D";
	protected static final String description = "set robots new actual location";
	
	
public Cmd_setLocation2D() 
{
	this.add(new RemoteParameterInt24("x location","new location on x axis"));
	this.add(new RemoteParameterInt24("y location","new location on y axis"));
}



public Cmd_setLocation2D(int command) 
{
	this();
	this.setId(command);
}


public void setData(FloatVector2D location)
{
	(( RemoteParameterInt24) this.get(Cmd_setLocation2D.INDEX_X)).setValue((int)location.x);
	(( RemoteParameterInt24) this.get(Cmd_setLocation2D.INDEX_Y)).setValue((int)location.y);
}




public FloatVector2D getLocation()
{
	return( new FloatVector2D(
			(( RemoteParameterInt24) this.get(Cmd_setLocation2D.INDEX_X)).getValue(),
			(( RemoteParameterInt24) this.get(Cmd_setLocation2D.INDEX_Y)).getValue()
			));
}

/*
public float getX()
{
	return((( RemoteParameterInt24) this.get(Cmd_setActualLocation2D.INDEX_X)).getValue());
}


public float getY()
{
	return((( RemoteParameterInt24) this.get(Cmd_setActualLocation2D.INDEX_Y)).getValue());
}

*/


@Override
public String getName() 
{
	return(Cmd_setLocation2D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setLocation2D.description);
}



public static Cmd_setLocation2D getCommand(int command)
{
	Cmd_setLocation2D cmd;
	cmd = new Cmd_setLocation2D(command);
	
	return(cmd);
}

public static Cmd_setLocation2D getCommand(int command,FloatVector2D location)
{
	Cmd_setLocation2D cmd;
	cmd = Cmd_setLocation2D.getCommand(command);
	cmd.setData(location);
	
	return(cmd);
}


}
