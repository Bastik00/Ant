package de.hska.lat.robot.component.text.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_getText extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4186016534736822798L;

	private static final int INDEX_TEXT 					= 0;

	
	protected static final String name = "getText";
	protected static final String description = "get a text";
	
	

public Cmd_getText() 
{
	this.add(new RemoteParameterUint8("index","index of text"));
	
}





	
	
public Cmd_getText(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getText.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getText.description);
}




public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getText.INDEX_TEXT)).setValue(index);
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getText.INDEX_TEXT)).getValue());
}





public static Cmd_getText getCommand(int id)
{
	Cmd_getText cmd;
	cmd = new Cmd_getText(id);
	
	return(cmd);
}







public static Cmd_getText getCommand(int command, int index)
{
	
	Cmd_getText cmd;
	cmd = Cmd_getText.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
	
	
}




}


