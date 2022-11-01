package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * comand robots movement controller to move rotate the given angular value 
 * @author tavin
 *
 */
public class Cmd_rotate3D extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;
	private static final int INDEX_X_ANGLE 			= 0;
	private static final int INDEX_Y_ANGLE 			= 1;
	private static final int INDEX_Z_ANGLE			 = 2;
	private static final int INDEX_VELOCITY			 = 3;
	

	protected static final String name = "rotate";
	protected static final String description = "rotate robot by given angular value";
	
	
public Cmd_rotate3D() 
{
	this.add(new RemoteParameterAngularVelocity("x","x angle"));
	this.add(new RemoteParameterAngularVelocity("y","y angle"));
	this.add(new RemoteParameterAngularVelocity("z","z angle"));
	this.add(new RemoteParameterAngularVelocity("velocity","angular velocity"));
	
}



public Cmd_rotate3D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float xDistance, float yDistance, float zDistance, float velocity)
{
	(( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_X_ANGLE)).setValue(xDistance);
	(( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_Y_ANGLE)).setValue(yDistance);
	(( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_Z_ANGLE)).setValue(zDistance);
	(( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_VELOCITY)).setValue(velocity);
}



public float getXAngle()
{
	return((( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_X_ANGLE)).getValue());
}


public float getYAngle()
{
	return((( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_Y_ANGLE)).getValue());
}


public float getZAngle()
{
	return((( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_Z_ANGLE)).getValue());
}

public float getVelocity()
{
	return((( RemoteParameterAngularVelocity) this.get(Cmd_rotate3D.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_rotate3D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_rotate3D.description);
}



public static Cmd_rotate3D getCommand(int command)
{
	Cmd_rotate3D cmd;
	cmd = new Cmd_rotate3D(command);
	
	return(cmd);
}

public static Cmd_rotate3D getCommand(int command, float xDistance, float yDistance, float zDistance, float velocity)
{
	Cmd_rotate3D cmd;
	cmd = Cmd_rotate3D.getCommand(command);
	cmd.setData( xDistance, yDistance, zDistance, velocity);
	
	return(cmd);
}


}
