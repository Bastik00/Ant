package de.hska.lat.robot.component.sensor.bmp085.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.bmp085.Bmp085Resolution;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_bmp085Settings extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 			= 0;
	private static final int INDEX_PARAMETERS		= 1;
	
	
	protected static final String name = "tmp102Settings";
	protected static final String description = "settings for a bmp085 barimetric presure sensor";
	
	

public Msg_bmp085Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp102 sensor index"));
	this.add(new RemoteParameterBmp085Parameters());

}





	
	
public Msg_bmp085Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_bmp085Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_bmp085Settings.description);
}




public void setData(int index, Bmp085Resolution resolution)
{
	(( RemoteParameterUint8) this.get(Msg_bmp085Settings.INDEX_SENSOR)).setValue(index);

	(( RemoteParameterBmp085Parameters) this.get(Msg_bmp085Settings.INDEX_PARAMETERS)).setResolution(resolution);
}




/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_bmp085Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */




public Bmp085Resolution  getResolution()
{
	return((( RemoteParameterBmp085Parameters) this.get(Msg_bmp085Settings.INDEX_PARAMETERS)).getResolution());
}



public static Msg_bmp085Settings getCommand(int id)
{
	Msg_bmp085Settings cmd;
	cmd = new Msg_bmp085Settings(id);
	
	return(cmd);
}







public static Msg_bmp085Settings getCommand(int command,int index,	Bmp085Resolution resolution)
{
	
	Msg_bmp085Settings cmd;
	cmd = Msg_bmp085Settings.getCommand(command);
	cmd.setData(index, resolution);
	
	return(cmd);
	
	
}




}

