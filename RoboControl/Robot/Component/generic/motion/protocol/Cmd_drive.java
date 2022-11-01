package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * comand robots movement controller to move the given distance, relative to its actual position
 * @author tavin
 *
 */
public class Cmd_drive extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4513146807668811969L;
	private static final int INDEX_X_VELOCITY = 0;
	private static final int INDEX_Y_VELOCITY = 1;
	private static final int INDEX_Z_VELOCITY = 2;
	

	protected static final String name = "drive";
	protected static final String description = "drive robot";
	
	
public Cmd_drive() 
{
	this.add(new RemoteParameterVelocity("velocity x","velocity on x Axis"));
	this.add(new RemoteParameterVelocity("velocity y","velocity on y Axis"));
	this.add(new RemoteParameterVelocity("velocity z","velocity on x Axis"));
}



public Cmd_drive(int command) 
{
	this();
	this.setId(command);
}


public void setData( float xVelocity,float yVelocity, float zVelocity)
{
	(( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_X_VELOCITY)).setValue(xVelocity);
	(( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_Y_VELOCITY)).setValue(yVelocity);
	(( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_Z_VELOCITY)).setValue(zVelocity);
}



public float getXVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_X_VELOCITY)).getValue());
}

public float getYVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_Y_VELOCITY)).getValue());
}

public float getZVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_drive.INDEX_Z_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_drive.name);
}


@Override
public String getDescription() 
{
	return(Cmd_drive.description);
}



public static Cmd_drive getCommand(int command)
{
	Cmd_drive cmd;
	cmd = new Cmd_drive(command);
	
	return(cmd);
}

public static Cmd_drive getCommand(int command, float xVelocity,float yVelocity, float zVelocity)
{
	Cmd_drive cmd;
	cmd = Cmd_drive.getCommand(command);
	cmd.setData( xVelocity, yVelocity, zVelocity );
	
	return(cmd);
}


}
