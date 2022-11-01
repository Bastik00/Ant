package de.hska.lat.robot.component.sensor.tmp102.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102Address;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102ConversionRate;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_tmp102Settings extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 			= 0;
	private static final int INDEX_PARAMETERS		= 1;
	
	
	protected static final String name = "tmp102Settings";
	protected static final String description = "settings for a bmp085 barimetric presure sensor";
	
	

public Msg_tmp102Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp102 sensor index"));
	this.add(new RemoteParameterTmp102Parameters());

}





	
	
public Msg_tmp102Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_tmp102Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_tmp102Settings.description);
}




public void setData(int index, int address, int period, boolean on, boolean extended, Tmp102ConversionRate conversionRate)
{
	(( RemoteParameterUint8) this.get(Msg_tmp102Settings.INDEX_SENSOR)).setValue(index);

	(( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).setConversionRate(conversionRate);
	(( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).setOn(on);
	(( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).setExtended(extended);
}




/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_tmp102Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */


public boolean isOn()
{
	return((( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).isOn());
}

public boolean isExtended()
{
	return((( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).isExtended());
}


public Tmp102ConversionRate  getPeriod()
{
	return((( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).getConversionRate());
}


public Tmp102Address  getI2CAddres()
{
	return((( RemoteParameterTmp102Parameters) this.get(Msg_tmp102Settings.INDEX_PARAMETERS)).getAddress());
}


public static Msg_tmp102Settings getCommand(int id)
{
	Msg_tmp102Settings cmd;
	cmd = new Msg_tmp102Settings(id);
	
	return(cmd);
}







public static Msg_tmp102Settings getCommand(int command,int index, int address, int period, boolean on, boolean extended,
		Tmp102ConversionRate conversionRate)
{
	
	Msg_tmp102Settings cmd;
	cmd = Msg_tmp102Settings.getCommand(command);
	cmd.setData(index, address, period, on, extended, conversionRate);
	
	return(cmd);
	
	
}




}

