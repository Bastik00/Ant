package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteStream;


import de.hska.lat.math.vector.FloatVector2D;





public class Stream_location2D extends RemoteStream
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7978404265887071494L;
	
	
	
	public static final int INDEX_X						= 0;
	public static final int INDEX_Y						= 1;


	
	protected static final String name = "location2D";
	protected static final String description = "location in a two dimensional space";
	
	
	
	
	
	
public Stream_location2D()
{
	this.add(new RemoteParameterLocation("x location","actual location on x axis"));
	this.add(new RemoteParameterLocation("y location","actual location on y axis"));
}


public Stream_location2D(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_location2D.name);
}


@Override
public String getDescription() 
{
	return(Stream_location2D.description);
}



public void setData(FloatVector2D location)
{
	(( RemoteParameterLocation) this.get(Stream_location2D.INDEX_X)).setValue((int)location.x);
	(( RemoteParameterLocation) this.get(Stream_location2D.INDEX_Y)).setValue((int)location.y);
}




public FloatVector2D getLocation()
{
	FloatVector2D location;
	
	location = new FloatVector2D(
			(( RemoteParameterLocation) this.get(Stream_location2D.INDEX_X)).getValue(),
			(( RemoteParameterLocation) this.get(Stream_location2D.INDEX_Y)).getValue()
			);
	
	return (location);
}





public static Stream_location2D getCommand(int command)
{
	Stream_location2D cmd;
	cmd = new Stream_location2D(command);
	
	return(cmd);
}



public static Stream_location2D getCommand(int command, FloatVector2D location)
{
	Stream_location2D cmd;
	cmd = Stream_location2D.getCommand(command);
	cmd.setData(location);
	
	return(cmd);
}

}
