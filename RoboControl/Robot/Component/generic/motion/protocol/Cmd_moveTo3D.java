package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * comand robots movement controller to move the given absolute distance
 * @author tavin
 *
 */
public class Cmd_moveTo3D extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;
	private static final int INDEX_X_DISTANCE = 0;
	private static final int INDEX_Y_DISTANCE = 1;
	private static final int INDEX_Z_DISTANCE = 2;
	private static final int INDEX_VELOCITY		 = 3;
	

	protected static final String name = "moveTo";
	protected static final String description = "move to an absolute position";
	
	
public Cmd_moveTo3D() 
{
	this.add(new RemoteParameterDistance("x","x distancer"));
	this.add(new RemoteParameterDistance("y","y distance"));
	this.add(new RemoteParameterDistance("z","z distance"));
	this.add(new RemoteParameterVelocity("velocity"," velocity"));
}



public Cmd_moveTo3D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float xDistance, float yDistance, float zDistance, float velocity)
{
	(( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_X_DISTANCE)).setValue(xDistance);
	(( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_Y_DISTANCE)).setValue(yDistance);
	(( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_Z_DISTANCE)).setValue(zDistance);
	(( RemoteParameterVelocity) this.get(Cmd_moveTo3D.INDEX_VELOCITY)).setValue(velocity);
}



public float getXDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_X_DISTANCE)).getValue());
}


public float getYDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_Y_DISTANCE)).getValue());
}


public float getZDistancex()
{
	return((( RemoteParameterDistance) this.get(Cmd_moveTo3D.INDEX_Z_DISTANCE)).getValue());
}

public float getVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_moveTo3D.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_moveTo3D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_moveTo3D.description);
}



public static Cmd_moveTo3D getCommand(int command)
{
	Cmd_moveTo3D cmd;
	cmd = new Cmd_moveTo3D(command);
	
	return(cmd);
}

public static Cmd_moveTo3D getCommand(int command, float xDistance, float yDistance, float zDistance, float velocity)
{
	Cmd_moveTo3D cmd;
	cmd = Cmd_moveTo3D.getCommand(command);
	cmd.setData( xDistance, yDistance, zDistance, velocity);
	
	return(cmd);
}


}
