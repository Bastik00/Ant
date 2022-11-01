package de.hska.lat.robot.component.sensor.bmp085.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.bmp085.Bmp085Resolution;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setBmp085Settings extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_PARAMETERS			= 1;
	
	protected static final String name = "setTmp102Settings";
	protected static final String description = "set settings for a tmp102 temperature sensor";
	
	

public Cmd_setBmp085Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp102 sensor index"));
	this.add(new RemoteParameterBmp085Parameters());
}





	
	
public Cmd_setBmp085Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setBmp085Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setBmp085Settings.description);
}




public void setData(int index, Bmp085Resolution resolution)
{
	
	(( RemoteParameterUint8) this.get(Cmd_setBmp085Settings.INDEX_SENSOR)).setValue(index);
	
	(( RemoteParameterBmp085Parameters) this.get(Cmd_setBmp085Settings.INDEX_PARAMETERS)).setResolution(resolution);
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setBmp085Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public Bmp085Resolution getResolution()
{
	return((( RemoteParameterBmp085Parameters) this.get(Cmd_setBmp085Settings.INDEX_PARAMETERS)).getResolution());
}






public static Cmd_setBmp085Settings getCommand(int id)
{
	Cmd_setBmp085Settings cmd;
	cmd = new Cmd_setBmp085Settings(id);
	
	return(cmd);
}







public static Cmd_setBmp085Settings getCommand(int command, int index, Bmp085Resolution resolution)
{
	
	Cmd_setBmp085Settings cmd;
	cmd = Cmd_setBmp085Settings.getCommand(command);
	cmd.setData(index, resolution);
	
	return(cmd);
	
	
}




}


