package de.hska.lat.robot.component.template.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setTemplateSettings extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4186016534736822798L;

	private static final int INDEX_SENSOR 					= 0;
	
	private static final int INDEX_USER_DEFINED				= 1;

	
	protected static final String name = "setTemplateSettings";
	protected static final String description = "set settings for a Template component";
	
	

public Cmd_setTemplateSettings() 
{
	this.add(new RemoteParameterUint8("index","Template component index"));
	this.add(new RemoteParameterUint8("user defined","user defined settings value 1"));
	
}





	
	
public Cmd_setTemplateSettings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setTemplateSettings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setTemplateSettings.description);
}




public void setData(int index, int resolution)
{
	if (resolution>3)
		resolution=3;
	
	(( RemoteParameterUint8) this.get(Cmd_setTemplateSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setTemplateSettings.INDEX_USER_DEFINED)).setValue(resolution);
	
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTemplateSettings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public int getSettingsValue()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTemplateSettings.INDEX_USER_DEFINED)).getValue());
}



public static Cmd_setTemplateSettings getCommand(int id)
{
	Cmd_setTemplateSettings cmd;
	cmd = new Cmd_setTemplateSettings(id);
	
	return(cmd);
}







public static Cmd_setTemplateSettings getCommand(int command, int index,  int userDefined)
{
	
	Cmd_setTemplateSettings cmd;
	cmd = Cmd_setTemplateSettings.getCommand(command);
	cmd.setData(index, userDefined);
	
	return(cmd);
	
	
}




}


