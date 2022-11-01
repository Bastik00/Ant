package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * comand robots movement controller to move rotate the given angular value 
 * @author tavin
 *
 */
public class Cmd_rotate2D extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;
	private static final int INDEX_ANGLE 				= 0;
	private static final int INDEX_VELOCITY			 	= 1;
	

	protected static final String name = "rotate";
	protected static final String description = "rotate robot by given angular value";
	
	
public Cmd_rotate2D() 
{
	this.add(new RemoteParameterAngle("heading","heading"));
	this.add(new RemoteParameterAngularVelocity("velocity","angular velocity"));
	
}



public Cmd_rotate2D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float heading, float velocity)
{
	(( RemoteParameterAngle) this.get(Cmd_rotate2D.INDEX_ANGLE)).setValue(heading);
	(( RemoteParameterAngularVelocity) this.get(Cmd_rotate2D.INDEX_VELOCITY)).setValue(velocity);
}



public float getAngle()
{
	return((( RemoteParameterAngle) this.get(Cmd_rotate2D.INDEX_ANGLE)).getValue());
}


public float getVelocity()
{
	return((( RemoteParameterAngularVelocity) this.get(Cmd_rotate2D.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_rotate2D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_rotate2D.description);
}



public static Cmd_rotate2D getCommand(int command)
{
	Cmd_rotate2D cmd;
	cmd = new Cmd_rotate2D(command);
	
	return(cmd);
}

public static Cmd_rotate2D getCommand(int command, float heading, float velocity)
{
	Cmd_rotate2D cmd;
	cmd = Cmd_rotate2D.getCommand(command);
	cmd.setData( heading, velocity);
	
	return(cmd);
}


}
