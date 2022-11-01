package de.hska.lat.robot.component.generic.collision.protocol;

import de.hska.lat.comm.remote.RemoteException;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.generic.locator.protocol.RemoteParameterLocation;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Exception_collision extends RemoteException
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int X				= 0;
	private static final int Y				= 1;
	private static final int Z		 		= 2;
	
	
	
	protected static final String name = "exceptionCollision";
	protected static final String description = "robot has detected a colision";
	
	

public Exception_collision() 
{
	this.add(new RemoteParameterLocation("x","x"));
	this.add(new RemoteParameterLocation("y","y"));
	this.add(new RemoteParameterLocation("z","z"));

}





	
	
public Exception_collision(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Exception_collision.name);
}


@Override
public String getDescription() 
{
	return(Exception_collision.description);
}




public void setData(FloatVector3D vector)
{
	(( RemoteParameterLocation) this.get(Exception_collision.X)).setValue((int)vector.x);
	(( RemoteParameterLocation) this.get(Exception_collision.Y)).setValue((int)vector.y);
	(( RemoteParameterLocation) this.get(Exception_collision.Z)).setValue((int)vector.z);
	

}

public FloatVector3D getVector()
{
	FloatVector3D vector;
	
	vector = new FloatVector3D((( RemoteParameterLocation) this.get(Exception_collision.X)).getValue(),
	(( RemoteParameterLocation) this.get(Exception_collision.Y)).getValue(),
	(( RemoteParameterLocation) this.get(Exception_collision.Z)).getValue()
	);
	
	return(vector);
}


public static Exception_collision getCommand(int id)
{
	Exception_collision cmd;
	cmd = new Exception_collision(id);
	
	return(cmd);
}







public static Exception_collision getCommand(int command, FloatVector3D vector)
{
	
	Exception_collision cmd;
	cmd = Exception_collision.getCommand(command);
	cmd.setData(vector);
	
	return(cmd);
	
	
}




}

