package de.hska.lat.robot.component.generic.colorSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getHsvColor extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getColor";
	protected static final String description = "get color measured from a temperature sensor";
	
	
public Cmd_getHsvColor() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getHsvColor(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getHsvColor.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getHsvColor.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getHsvColor.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getHsvColor.description);
}



public static Cmd_getHsvColor getCommand(int command)
{
	Cmd_getHsvColor cmd;
	cmd = new Cmd_getHsvColor(command);
	
	return(cmd);
}

public static Cmd_getHsvColor getCommand(int command,int index)
{
	Cmd_getHsvColor cmd;
	cmd = Cmd_getHsvColor.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
