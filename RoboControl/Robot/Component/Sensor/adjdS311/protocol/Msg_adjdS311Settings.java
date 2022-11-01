package de.hska.lat.robot.component.sensor.adjdS311.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311Setings;






/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_adjdS311Settings extends RemoteMessage
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
	
	
	
	protected static final String name = "adjdS311ettings";
	protected static final String description = "settings for a ADJD-S311 color sensor";
	
	

public Msg_adjdS311Settings() 
{
	this.add(new RemoteParameterUint8("index","ADJD-S311 sensor index"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("red offset","offset for red channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("green offset","offset for green channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("blue offset","offset for blue channel"));
	this.add(new RemoteParameterAdjdS311ChannelParameters("clear offset","offset for clear channel"));

}





	
	
public Msg_adjdS311Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_adjdS311Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_adjdS311Settings.description);
}




public void setData(int index, AdjdS311Setings settings)
{
	(( RemoteParameterUint8) this.get(Msg_adjdS311Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Msg_adjdS311Settings.INDEX_RED)).setSettings(settings.redChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Msg_adjdS311Settings.INDEX_GREEN)).setSettings(settings.greenChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Msg_adjdS311Settings.INDEX_BLUE)).setSettings(settings.blueChannel);
	(( RemoteParameterAdjdS311ChannelParameters) this.get(Msg_adjdS311Settings.INDEX_CLEAR)).setSettings(settings.clearChannel);


}




/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_adjdS311Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */


/*

public Bmp085Resolution  getResolution()
{
//	return((( RemoteParameterBmp085Parameters) this.get(Msg_adjdS311Settings.INDEX_PARAMETERS)).getResolution());
}
*/


public static Msg_adjdS311Settings getCommand(int id)
{
	Msg_adjdS311Settings cmd;
	cmd = new Msg_adjdS311Settings(id);
	
	return(cmd);
}







public static Msg_adjdS311Settings getCommand(int command,int index,	 AdjdS311Setings settings)
{
	
	Msg_adjdS311Settings cmd;
	cmd = Msg_adjdS311Settings.getCommand(command);
	cmd.setData(index, settings);
	
	return(cmd);
	
	
}




}

