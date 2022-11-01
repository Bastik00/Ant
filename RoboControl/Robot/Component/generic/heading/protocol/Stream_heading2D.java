package de.hska.lat.robot.component.generic.heading.protocol;

import de.hska.lat.comm.remote.RemoteStream;


public class Stream_heading2D extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;



	
	protected static final String name = "heading2DData";
	protected static final String description = "heading data (angle) for robot";
	
	
	public static final int INDEX_HEADING					= 0;
	
	
	
public Stream_heading2D()
{
	this.add(new RemoteParameterAngle("heading","heading"));
}


public Stream_heading2D(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_heading2D.name);
}


@Override
public String getDescription() 
{
	return(Stream_heading2D.description);
}



public void setData(float heading)
{
	(( RemoteParameterAngle) this.get(Stream_heading2D.INDEX_HEADING)).setValue(heading);

	
}


public float getHeading()
{

	return( (( RemoteParameterAngle) this.get(Stream_heading2D.INDEX_HEADING)).getValue());

}


public static Stream_heading2D getCommand(int command)
{
	Stream_heading2D cmd;
	cmd = new Stream_heading2D(command);
	
	return(cmd);
}





public static Stream_heading2D getCommand(int command, float  heading)
{
	Stream_heading2D cmd;
	cmd = Stream_heading2D.getCommand(command);
	cmd.setData( heading);
	
	return(cmd);
}

}
