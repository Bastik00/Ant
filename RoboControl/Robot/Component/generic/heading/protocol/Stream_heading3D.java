package de.hska.lat.robot.component.generic.heading.protocol;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;



public class Stream_heading3D extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	public static final int INDEX_X					= 0;
	public static final int INDEX_Y					= 1;
	public static final int INDEX_Z					= 2;

	
	protected static final String name = "headingData";
	protected static final String description = "heading data (angles) for robot";
	
	
	
	
	
	
public Stream_heading3D()
{
	this.add(new RemoteParameterAngle("X","rotation on x axis"));
	this.add(new RemoteParameterAngle("Y","rotation on y axis"));
	this.add(new RemoteParameterAngle("Z","rotation on z axis"));
}


public Stream_heading3D(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_heading3D.name);
}


@Override
public String getDescription() 
{
	return(Stream_heading3D.description);
}


/**
 * set actual heading
 * @param heading heading as 3D vector
 */

public void setHeading(AngularVector3D heading)
{
	(( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_X)).setValue(heading.x);
	(( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_Y)).setValue(heading.y);
	(( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_Z)).setValue(heading.z);
	
}

/**
 * get actual heading as 3D vector
 * @return heading as 3D vector
 */
public AngularVector3D getHeading()
{
	
	AngularVector3D angles;
	
	angles = new AngularVector3D((( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_X)).getValue(),
					(( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_Y)).getValue(),
					(( RemoteParameterAngle) this.get(Stream_heading3D.INDEX_Z)).getValue());
	
	return (angles);
}


public static Stream_heading3D getCommand(int command)
{
	Stream_heading3D cmd;
	cmd = new Stream_heading3D(command);
	
	return(cmd);
}




public static Stream_heading3D getCommand(int command, AngularVector3D heading)
{
	Stream_heading3D cmd;
	cmd = Stream_heading3D.getCommand(command);
	cmd.setHeading( heading );
	
	return(cmd);
}

}
