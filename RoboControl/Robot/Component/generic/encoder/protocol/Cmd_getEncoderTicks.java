package de.hska.lat.robot.component.generic.encoder.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_getEncoderTicks extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 		= 0;

	
	
	protected static final String name = "getEncoderTicks";
	protected static final String description = "get ticks from an encoder";
	
	

public Cmd_getEncoderTicks() 
{
	this.add(new RemoteParameterUint8("index","encoder index"));
}





	
	
public Cmd_getEncoderTicks(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getEncoderTicks.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getEncoderTicks.description);
}




public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getEncoderTicks.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getEncoderTicks.INDEX_SENSOR)).getValue());
}




public static Cmd_getEncoderTicks getCommand(int id)
{
	Cmd_getEncoderTicks cmd;
	cmd = new Cmd_getEncoderTicks(id);
	
	return(cmd);
}







public static Cmd_getEncoderTicks getCommand(int command, int index)
{
	
	Cmd_getEncoderTicks cmd;
	cmd = Cmd_getEncoderTicks.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
	
	
}




}

