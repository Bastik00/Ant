package de.hska.lat.robot.component.sensor.tsl2591.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setTsl2591Settings extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4186016534736822798L;

	private static final int INDEX_SENSOR 					= 0;
	
	private static final int INDEX_USER_DEFINED				= 1;

	
	protected static final String name = "setTemplateSettings";
	protected static final String description = "set settings for a Template component";
	
	

public Cmd_setTsl2591Settings() 
{
	this.add(new RemoteParameterUint8("index","Template component index"));
	this.add(new RemoteParameterUint8("user defined","user defined settings value 1"));
	
}





	
	
public Cmd_setTsl2591Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setTsl2591Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setTsl2591Settings.description);
}




public void setData(int index, int resolution)
{
	if (resolution>3)
		resolution=3;
	
	(( RemoteParameterUint8) this.get(Cmd_setTsl2591Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setTsl2591Settings.INDEX_USER_DEFINED)).setValue(resolution);
	
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTsl2591Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public int getSettingsValue()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTsl2591Settings.INDEX_USER_DEFINED)).getValue());
}



public static Cmd_setTsl2591Settings getCommand(int id)
{
	Cmd_setTsl2591Settings cmd;
	cmd = new Cmd_setTsl2591Settings(id);
	
	return(cmd);
}







public static Cmd_setTsl2591Settings getCommand(int command, int index,  int userDefined)
{
	
	Cmd_setTsl2591Settings cmd;
	cmd = Cmd_setTsl2591Settings.getCommand(command);
	cmd.setData(index, userDefined);
	
	return(cmd);
	
	
}




}


