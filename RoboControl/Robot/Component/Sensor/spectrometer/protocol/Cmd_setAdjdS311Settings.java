package de.hska.lat.robot.component.sensor.spectrometer.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311Setings;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setAdjdS311Settings extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_RED					= 1;
	private static final int INDEX_GREEN				= 2;
	private static final int INDEX_BLUE					= 3;
	private static final int INDEX_CLEAR				= 4;
	
	protected static final String name = "setSdjdS311Settings";
	protected static final String description = "set settings for a ADJD-S311 color sensor";
	
	

public Cmd_setAdjdS311Settings() 
{
	this.add(new RemoteParameterUint8("index","adjd s311 sensor index"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("red offset","offset for red channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("green offset","offset for green channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("blue offset","offset for blue channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("clear offset","offset for clear channel"));
//	this.add(new RemoteParameterBmp085Parameters());
}





	
	
public Cmd_setAdjdS311Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setAdjdS311Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setAdjdS311Settings.description);
}




public void setData(int index, AdjdS311Setings settings)
{
	//settings.redChannel.getCapacitators();
	(( RemoteParameterUint8) this.get(Cmd_setAdjdS311Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Cmd_setAdjdS311Settings.INDEX_RED)).setSettings(settings.redChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Cmd_setAdjdS311Settings.INDEX_GREEN)).setSettings(settings.greenChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Cmd_setAdjdS311Settings.INDEX_BLUE)).setSettings(settings.blueChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Cmd_setAdjdS311Settings.INDEX_CLEAR)).setSettings(settings.clearChannel);

}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setAdjdS311Settings.INDEX_SENSOR)).getValue());
}







public static Cmd_setAdjdS311Settings getCommand(int id)
{
	Cmd_setAdjdS311Settings cmd;
	cmd = new Cmd_setAdjdS311Settings(id);
	
	return(cmd);
}







public static Cmd_setAdjdS311Settings getCommand(int command, int index, AdjdS311Setings settings)
{
	
	Cmd_setAdjdS311Settings cmd;
	cmd = Cmd_setAdjdS311Settings.getCommand(command);
	cmd.setData(index, settings);
	
	return(cmd);
	
	
}




}


