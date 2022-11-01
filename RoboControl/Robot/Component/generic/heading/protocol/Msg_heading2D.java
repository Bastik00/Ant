package de.hska.lat.robot.component.generic.heading.protocol;

import de.hska.lat.comm.remote.RemoteMessage;




/**
 * 
 * @author Oktavian Gniot
 *
 *meaadge containing two dimensional heading information
 */

public class Msg_heading2D extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int HEADING_INDEX 		= 0;
	
	
	protected static final String name = "setHeading2D";
	protected static final String description = "set a new 2D Heading";
	
	

public Msg_heading2D() 
{
	this.add(new RemoteParameterAngle("heading ","heading "));
}





	
	
public Msg_heading2D(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_heading2D.name);
}


@Override
public String getDescription() 
{
	return(Msg_heading2D.description);
}




public void setData(float heading)
{
	(( RemoteParameterAngle) this.get(Msg_heading2D.HEADING_INDEX)).setValue(heading);

}



public float getHeading()
{
	return((( RemoteParameterAngle) this.get(Msg_heading2D.HEADING_INDEX)).getValue());
}


public static Msg_heading2D getCommand(int id)
{
	Msg_heading2D cmd;
	cmd = new Msg_heading2D(id);
	
	return(cmd);
}







public static Msg_heading2D getCommand(int command, float heading)
{
	
	Msg_heading2D cmd;
	cmd = Msg_heading2D.getCommand(command);
	cmd.setData(heading);
	
	return(cmd);
	
	
}




}

