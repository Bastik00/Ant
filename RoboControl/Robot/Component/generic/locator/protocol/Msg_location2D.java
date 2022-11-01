package de.hska.lat.robot.component.generic.locator.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.math.vector.FloatVector2D;




/**
 * 
 * @author Oktavian Gniot
 *
 *Message containing two dimensional location information
 */

public class Msg_location2D extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int X_AXIS_INDEX 		= 0;
	private static final int Y_AXIS_INDEX 		= 1;
	
	
	protected static final String name = "location2D";
	protected static final String description = "2D location information";
	
	

public Msg_location2D() 
{
	this.add(new RemoteParameterLocation("x ","location along x axis "));
	this.add(new RemoteParameterLocation("y ","location along y axis "));
}





	
	
public Msg_location2D(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_location2D.name);
}


@Override
public String getDescription() 
{
	return(Msg_location2D.description);
}




public void setData(FloatVector2D location)
{
	(( RemoteParameterLocation) this.get(Msg_location2D.X_AXIS_INDEX)).setValue(location.x);
	(( RemoteParameterLocation) this.get(Msg_location2D.Y_AXIS_INDEX)).setValue(location.y);

}



public FloatVector2D getHeading()
{
	FloatVector2D location;
	
	location = new FloatVector2D(
			(( RemoteParameterLocation) this.get(Msg_location2D.X_AXIS_INDEX)).getValue(),
			(( RemoteParameterLocation) this.get(Msg_location2D.Y_AXIS_INDEX)).getValue()
			);
			
	return(location);
}


public static Msg_location2D getCommand(int id)
{
	Msg_location2D cmd;
	cmd = new Msg_location2D(id);
	
	return(cmd);
}







public static Msg_location2D getCommand(int command, FloatVector2D location)
{
	
	Msg_location2D cmd;
	cmd = Msg_location2D.getCommand(command);
	cmd.setData(location);
	
	return(cmd);
	
	
}




}

