package de.hska.lat.robot.component.sensor.tmp006.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006Address;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006ConversionRate;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_tmp006Settings extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 			= 0;
	private static final int INDEX_PARAMETERS		= 1;
	
	
	protected static final String name = "tmp006Settings";
	protected static final String description = "settings for a tmp006 thermophyle presure sensor";
	
	

public Msg_tmp006Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp006 sensor index"));
	this.add(new RemoteParameterTmp006Parameters());

}





	
	
public Msg_tmp006Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_tmp006Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_tmp006Settings.description);
}




public void setData(int index, int address, int period, boolean on, Tmp006ConversionRate conversionRate)
{
	(( RemoteParameterUint8) this.get(Msg_tmp006Settings.INDEX_SENSOR)).setValue(index);

	(( RemoteParameterTmp006Parameters) this.get(Msg_tmp006Settings.INDEX_PARAMETERS)).setConversionRate(conversionRate);
	(( RemoteParameterTmp006Parameters) this.get(Msg_tmp006Settings.INDEX_PARAMETERS)).setOn(on);
}




/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_tmp006Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */


public boolean isOn()
{
	return((( RemoteParameterTmp006Parameters) this.get(Msg_tmp006Settings.INDEX_PARAMETERS)).isOn());
}



public Tmp006ConversionRate  getPeriod()
{
	return((( RemoteParameterTmp006Parameters) this.get(Msg_tmp006Settings.INDEX_PARAMETERS)).getConversionRate());
}


public Tmp006Address  getI2CAddres()
{
	return((( RemoteParameterTmp006Parameters) this.get(Msg_tmp006Settings.INDEX_PARAMETERS)).getAddress());
}


public static Msg_tmp006Settings getCommand(int id)
{
	Msg_tmp006Settings cmd;
	cmd = new Msg_tmp006Settings(id);
	
	return(cmd);
}







public static Msg_tmp006Settings getCommand(int command,int index, int address, int period, boolean on, 
		Tmp006ConversionRate conversionRate)
{
	
	Msg_tmp006Settings cmd;
	cmd = Msg_tmp006Settings.getCommand(command);
	cmd.setData(index, address, period, on, conversionRate);
	
	return(cmd);
	
	
}




}

