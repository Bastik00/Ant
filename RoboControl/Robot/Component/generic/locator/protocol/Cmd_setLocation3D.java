 package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt24;
import de.hska.lat.math.vector.FloatVector3D;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setLocation3D extends RemoteCommand
{
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 4043141982311053953L;


	private static final int INDEX_X = 0;
	private static final int INDEX_Y = 1;
	private static final int INDEX_Z = 2;
	

	protected static final String name = "setActualLocation";
	protected static final String description = "set robots new actual location";
	
	
public Cmd_setLocation3D() 
{
	this.add(new RemoteParameterInt24("x location","new location on x axis"));
	this.add(new RemoteParameterInt24("y location","new location on y axis"));
	this.add(new RemoteParameterInt24("z location","new location on z axis"));
}



public Cmd_setLocation3D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float xLocation, float yLocation, float zLocation)
{
	(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_X)).setValue((int)xLocation);
	(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Y)).setValue((int)yLocation);
	(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Z)).setValue((int)zLocation);
}



public FloatVector3D getLocation()
{
	FloatVector3D location;
	
	location = new FloatVector3D(
			(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_X)).getValue(),
			(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Y)).getValue(),
			(( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Z)).getValue()
			);

	return(location);
}


public float getX()
{
	return((( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_X)).getValue());
}


public float getY()
{
	return((( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Y)).getValue());
}


public float getZ()
{
	return((( RemoteParameterInt24) this.get(Cmd_setLocation3D.INDEX_Z)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_setLocation3D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setLocation3D.description);
}



public static Cmd_setLocation3D getCommand(int command)
{
	Cmd_setLocation3D cmd;
	cmd = new Cmd_setLocation3D(command);
	
	return(cmd);
}

public static Cmd_setLocation3D getCommand(int command,float xPosition,float yPosition,float zPosition)
{
	Cmd_setLocation3D cmd;
	cmd = Cmd_setLocation3D.getCommand(command);
	cmd.setData(xPosition,yPosition,zPosition);
	
	return(cmd);
}


}
