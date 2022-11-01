package de.hska.lat.robot.component.generic.colorSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getRgbColor extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getColor";
	protected static final String description = "get color measured from a temperature sensor";
	
	
public Cmd_getRgbColor() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getRgbColor(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getRgbColor.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getRgbColor.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getRgbColor.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getRgbColor.description);
}



public static Cmd_getRgbColor getCommand(int command)
{
	Cmd_getRgbColor cmd;
	cmd = new Cmd_getRgbColor(command);
	
	return(cmd);
}

public static Cmd_getRgbColor getCommand(int command,int index)
{
	Cmd_getRgbColor cmd;
	cmd = Cmd_getRgbColor.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
