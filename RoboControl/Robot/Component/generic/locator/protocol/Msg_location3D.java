package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.math.vector.FloatVector3D;




/**
 * 
 * @author Oktavian Gniot
 *
 *Message containing three dimensional location information
 */

public class Msg_location3D extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int X_AXIS_INDEX 		= 0;
	private static final int Y_AXIS_INDEX 		= 1;
	private static final int Z_AXIS_INDEX 		= 1;
	
	
	protected static final String name = "location2D";
	protected static final String description = "3d location information";
	
	

public Msg_location3D() 
{
	this.add(new RemoteParameterLocation("x ","location along x axis "));
	this.add(new RemoteParameterLocation("y ","location along y axis "));
	this.add(new RemoteParameterLocation("z ","location along y axis "));
}





	
	
public Msg_location3D(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_location3D.name);
}


@Override
public String getDescription() 
{
	return(Msg_location3D.description);
}




public void setData(FloatVector3D location)
{
	(( RemoteParameterLocation) this.get(Msg_location3D.X_AXIS_INDEX)).setValue(location.x);
	(( RemoteParameterLocation) this.get(Msg_location3D.Y_AXIS_INDEX)).setValue(location.y);
	(( RemoteParameterLocation) this.get(Msg_location3D.Z_AXIS_INDEX)).setValue(location.z);

}



public FloatVector3D getHeading()
{
	FloatVector3D location;
	
	location = new FloatVector3D(
			(( RemoteParameterLocation) this.get(Msg_location3D.X_AXIS_INDEX)).getValue(),
			(( RemoteParameterLocation) this.get(Msg_location3D.Y_AXIS_INDEX)).getValue(),
			(( RemoteParameterLocation) this.get(Msg_location3D.Z_AXIS_INDEX)).getValue()
			);
			
	return(location);
}


public static Msg_location3D getCommand(int id)
{
	Msg_location3D cmd;
	cmd = new Msg_location3D(id);
	
	return(cmd);
}







public static Msg_location3D getCommand(int command, FloatVector3D location)
{
	
	Msg_location3D cmd;
	cmd = Msg_location3D.getCommand(command);
	cmd.setData(location);
	
	return(cmd);
	
	
}




}

