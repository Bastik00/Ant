package de.hska.lat.robot.component.generic.heading.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.math.vector.AngularVector3D;




/**
 * 
 * @author Oktavian Gniot
 *
 *meaadge containing three dimensional heading information
 */

public class Msg_heading3D extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int HEADING_X_INDEX 		= 0;
	private static final int HEADING_Y_INDEX 		= 1;
	private static final int HEADING_Z_INDEX 		= 2;
	
	protected static final String name = "setHeading2D";
	protected static final String description = "set a new 2D Heading";
	
	

public Msg_heading3D() 
{
	this.add(new RemoteParameterAngle("heading x","heading on x axis"));
	this.add(new RemoteParameterAngle("heading y","heading on y axis"));
	this.add(new RemoteParameterAngle("heading z","heading on z axis"));
}





	
	
public Msg_heading3D(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_heading3D.name);
}


@Override
public String getDescription() 
{
	return(Msg_heading3D.description);
}




public void setData(AngularVector3D heading)
{
	(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_X_INDEX)).setValue(heading.x);
	(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_Y_INDEX)).setValue(heading.y);
	(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_Z_INDEX)).setValue(heading.z);

}



/**
 * get This message Heading data
 * @return Heading data as three dimensional float vector
 */

public AngularVector3D getHeading()
{
	AngularVector3D heading;
	
	heading = new AngularVector3D(
			(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_X_INDEX)).getValue(),
			(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_Y_INDEX)).getValue(),
			(( RemoteParameterAngle) this.get(Msg_heading3D.HEADING_Z_INDEX)).getValue()
			);
	
	return(heading);
}




public static Msg_heading3D getCommand(int id)
{
	Msg_heading3D cmd;
	cmd = new Msg_heading3D(id);
	
	return(cmd);
}







public static Msg_heading3D getCommand(int command, AngularVector3D heading)
{
	
	Msg_heading3D cmd;
	cmd = Msg_heading3D.getCommand(command);
	cmd.setData(heading);
	
	return(cmd);
	
	
}




}

