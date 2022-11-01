package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * commands robots movement controller to move the given distance, relative to its actual position & heading
 * @author tavin
 *
 */
public class Cmd_move extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;
	private static final int INDEX_X_DISTANCE = 0;
	private static final int INDEX_Y_DISTANCE = 1;
	private static final int INDEX_Z_DISTANCE = 2;
	private static final int INDEX_VELOCITY		 = 3;
	

	protected static final String name = "move";
	protected static final String description = "move a distance";
	
	
public Cmd_move() 
{
	this.add(new RemoteParameterDistance("x","x distance"));
	this.add(new RemoteParameterDistance("y","y distance"));
	this.add(new RemoteParameterDistance("z","z distance"));
	this.add(new RemoteParameterVelocity("velocity"," velocity"));
}



public Cmd_move(int command) 
{
	this();
	this.setId(command);
}


public void setData(float xDistance, float yDistance, float zDistance, float velocity)
{
	(( RemoteParameterDistance) this.get(Cmd_move.INDEX_X_DISTANCE)).setValue(xDistance);
	(( RemoteParameterDistance) this.get(Cmd_move.INDEX_Y_DISTANCE)).setValue(yDistance);
	(( RemoteParameterDistance) this.get(Cmd_move.INDEX_Z_DISTANCE)).setValue(zDistance);
	(( RemoteParameterVelocity) this.get(Cmd_move.INDEX_VELOCITY)).setValue(velocity);
}



public float getXDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_move.INDEX_X_DISTANCE)).getValue());
}


public float getYDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_move.INDEX_Y_DISTANCE)).getValue());
}


public float getZDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_move.INDEX_Z_DISTANCE)).getValue());
}

public float getVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_move.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_move.name);
}


@Override
public String getDescription() 
{
	return(Cmd_move.description);
}



public static Cmd_move getCommand(int command)
{
	Cmd_move cmd;
	cmd = new Cmd_move(command);
	
	return(cmd);
}

public static Cmd_move getCommand(int command, float xDistance, float yDistance, float zDistance, float velocity)
{
	Cmd_move cmd;
	cmd = Cmd_move.getCommand(command);
	cmd.setData( xDistance, yDistance, zDistance, velocity);
	
	return(cmd);
}


}
