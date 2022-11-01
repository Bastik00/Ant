package de.hska.lat.robot.component.generic.encoder.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt32;




/**
 * 
 * @author Oktavian Gniot
 *
 *meaadge containing two dimensional heading information
 */

public class Msg_encoderTicks extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int TICKS_INDEX 		= 0;
	
	
	protected static final String name = "encoderTicks";
	protected static final String description = "tick counted by an encoder";
	
	

public Msg_encoderTicks() 
{
	this.add(new RemoteParameterInt32("ticks ","tick counted by an encoder "));
}





	
	
public Msg_encoderTicks(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_encoderTicks.name);
}


@Override
public String getDescription() 
{
	return(Msg_encoderTicks.description);
}




public void setData(int ticks)
{
	(( RemoteParameterInt32) this.get(Msg_encoderTicks.TICKS_INDEX)).setValue(ticks);

}



public int getTicks()
{
	return((( RemoteParameterInt32) this.get(Msg_encoderTicks.TICKS_INDEX)).getValue());
}


public static Msg_encoderTicks getCommand(int id)
{
	Msg_encoderTicks cmd;
	cmd = new Msg_encoderTicks(id);
	
	return(cmd);
}







public static Msg_encoderTicks getCommand(int command, int ticks)
{
	
	Msg_encoderTicks cmd;
	cmd = Msg_encoderTicks.getCommand(command);
	cmd.setData(ticks);
	
	return(cmd);
	
	
}




}

