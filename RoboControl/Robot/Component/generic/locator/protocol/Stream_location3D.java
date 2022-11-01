package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteStream;



import de.hska.lat.math.vector.FloatVector3D;





public class Stream_location3D extends RemoteStream
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7978404265887071494L;
	
	
	
	public static final int INDEX_X						= 0;
	public static final int INDEX_Y						= 1;
	public static final int INDEX_Z						= 2;

	
	protected static final String name = "location";
	protected static final String description = "location ";
	
	
	
	
	
	
public Stream_location3D()
{
	this.add(new RemoteParameterLocation("x location","actual location on x axis"));
	this.add(new RemoteParameterLocation("y location","actual location on y axis"));
	this.add(new RemoteParameterLocation("z location","actual location on z axis"));
}


public Stream_location3D(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_location3D.name);
}


@Override
public String getDescription() 
{
	return(Stream_location3D.description);
}



public void setData(FloatVector3D location3D)
{
	(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_X)).setValue((int)location3D.x);
	(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_Y)).setValue((int)location3D.y);
	(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_Z)).setValue((int)location3D.z);
	
}




public FloatVector3D getLocation()
{
	
	FloatVector3D location;
	
	location = new FloatVector3D(
				(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_X)).getValue(),
				(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_Y)).getValue(),
				(( RemoteParameterLocation) this.get(Stream_location3D.INDEX_Z)).getValue()
				);
	
	return (location);
}





public static Stream_location3D getCommand(int command)
{
	Stream_location3D cmd;
	cmd = new Stream_location3D(command);
	
	return(cmd);
}



public static Stream_location3D getCommand(int command, float x,float y, float z)
{
	Stream_location3D cmd;
	FloatVector3D location = new FloatVector3D(x,y ,z);
	
	
	
	cmd = Stream_location3D.getCommand(command);
	cmd.setData(location);
	
	return(cmd);
}



public static Stream_location3D getCommand(int command, FloatVector3D location)
{
	Stream_location3D cmd;
	cmd = Stream_location3D.getCommand(command);
	cmd.setData( location );
	
	return(cmd);
}

}
