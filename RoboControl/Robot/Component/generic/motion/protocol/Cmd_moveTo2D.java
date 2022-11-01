package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * comand robots movement controller to move the given absolute distance
 * @author tavin
 *
 */
public class Cmd_moveTo2D extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;
	private static final int INDEX_X_LOCATION = 0;
	private static final int INDEX_Y_LOCATION = 1;
	private static final int INDEX_VELOCITY	 = 2;
	

	protected static final String name = "moveTo";
	protected static final String description = "move to an absolute position";
	
	
public Cmd_moveTo2D() 
{
	this.add(new RemoteParameterDistance("x","x location"));
	this.add(new RemoteParameterDistance("y","y location"));
	this.add(new RemoteParameterVelocity("velocity"," velocity"));
}



public Cmd_moveTo2D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float xLocation, float yLocation, float velocity)
{
	(( RemoteParameterDistance) this.get(Cmd_moveTo2D.INDEX_X_LOCATION)).setValue(xLocation);
	(( RemoteParameterDistance) this.get(Cmd_moveTo2D.INDEX_Y_LOCATION)).setValue(yLocation);
	(( RemoteParameterVelocity) this.get(Cmd_moveTo2D.INDEX_VELOCITY)).setValue(velocity);
}



public float getXLocation()
{
	return((( RemoteParameterDistance) this.get(Cmd_moveTo2D.INDEX_X_LOCATION)).getValue());
}


public float getYLocation()
{
	return((( RemoteParameterDistance) this.get(Cmd_moveTo2D.INDEX_Y_LOCATION)).getValue());
}


public float getVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_moveTo2D.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_moveTo2D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_moveTo2D.description);
}



public static Cmd_moveTo2D getCommand(int command)
{
	Cmd_moveTo2D cmd;
	cmd = new Cmd_moveTo2D(command);
	
	return(cmd);
}

public static Cmd_moveTo2D getCommand(int command, float xLocation, float yLocation,  float velocity)
{
	Cmd_moveTo2D cmd;
	cmd = Cmd_moveTo2D.getCommand(command);
	cmd.setData( xLocation, yLocation,  velocity);
	
	return(cmd);
}


}
