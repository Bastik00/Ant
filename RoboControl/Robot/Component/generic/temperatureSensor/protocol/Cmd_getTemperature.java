package de.hska.lat.robot.component.generic.temperatureSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getTemperature extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getTemperature";
	protected static final String description = "get measured temperature from a temperature sensor";
	
	
public Cmd_getTemperature() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getTemperature(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getTemperature.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getTemperature.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getTemperature.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getTemperature.description);
}



public static Cmd_getTemperature getCommand(int command)
{
	Cmd_getTemperature cmd;
	cmd = new Cmd_getTemperature(command);
	
	return(cmd);
}

public static Cmd_getTemperature getCommand(int command,int index)
{
	Cmd_getTemperature cmd;
	cmd = Cmd_getTemperature.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
