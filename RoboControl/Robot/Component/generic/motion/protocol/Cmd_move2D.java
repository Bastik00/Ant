package de.hska.lat.robot.component.generic.motion.protocol;

import de.hska.lat.comm.remote.RemoteCommand;





/**
 * commands robots movement controller to move the given distance, relative to its actual position & heading
 * @author tavin
 *
 */
public class Cmd_move2D extends RemoteCommand
{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263135698657091560L;

	private static final int INDEX_VELOCITY		 = 0;
	private static final int INDEX_HEADING		 = 1;
	

	protected static final String name = "move";
	protected static final String description = "move a distance";
	
	
public Cmd_move2D() 
{
	this.add(new RemoteParameterVelocity("velocity"," velocity"));
	this.add(new RemoteParameterAngle("angle"," angle"));
}



public Cmd_move2D(int command) 
{
	this();
	this.setId(command);
}


public void setData(float velocity, float heading)
{
	(( RemoteParameterVelocity) this.get(Cmd_move2D.INDEX_VELOCITY)).setValue(velocity);
	(( RemoteParameterAngle) this.get(Cmd_move2D.INDEX_HEADING)).setValue(heading);
}



public float getHeading()
{
	return((( RemoteParameterAngle) this.get(Cmd_move2D.INDEX_HEADING)).getValue());
}


public float getVelocity()
{
	return((( RemoteParameterVelocity) this.get(Cmd_move2D.INDEX_VELOCITY)).getValue());
}

@Override
public String getName() 
{
	return(Cmd_move2D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_move2D.description);
}



public static Cmd_move2D getCommand(int command)
{
	Cmd_move2D cmd;
	cmd = new Cmd_move2D(command);
	
	return(cmd);
}

public static Cmd_move2D getCommand(int command, float velocity, float heading)
{
	Cmd_move2D cmd;
	cmd = Cmd_move2D.getCommand(command);
	cmd.setData( velocity, heading);
	
	return(cmd);
}


}
