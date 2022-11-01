package de.hska.lat.robot.component.sensor.tmp102.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102Address;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102ConversionRate;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setTmp102Settings extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_PARAMETERS			= 1;
	
	protected static final String name = "setTmp102Settings";
	protected static final String description = "set settings for a tmp102 temperature sensor";
	
	

public Cmd_setTmp102Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp102 sensor index"));
	this.add(new RemoteParameterTmp102Parameters());
}





	
	
public Cmd_setTmp102Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setTmp102Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setTmp102Settings.description);
}




public void setData(int index, Tmp102Address address, boolean on, boolean extended, Tmp102ConversionRate conversionRate)
{
	
	(( RemoteParameterUint8) this.get(Cmd_setTmp102Settings.INDEX_SENSOR)).setValue(index);
	
	(( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).setConversionRate(conversionRate);
	(( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).setAddress(address);
	(( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).setOn(on);
	(( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).setExtended(extended);
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTmp102Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public Tmp102Address getAddress()
{
	return((( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).getAddress());
}


public boolean isOn()
{
	return((( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).isOn());
}

public boolean isExtended()
{
	return((( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).isExtended());
}


public Tmp102ConversionRate getPeriod()
{
	return((( RemoteParameterTmp102Parameters) this.get(Cmd_setTmp102Settings.INDEX_PARAMETERS)).getConversionRate());
}



public static Cmd_setTmp102Settings getCommand(int id)
{
	Cmd_setTmp102Settings cmd;
	cmd = new Cmd_setTmp102Settings(id);
	
	return(cmd);
}







public static Cmd_setTmp102Settings getCommand(int command, int index,
		Tmp102Address address, boolean on, boolean extended, Tmp102ConversionRate conversionRate)
{
	
	Cmd_setTmp102Settings cmd;
	cmd = Cmd_setTmp102Settings.getCommand(command);
	cmd.setData(index, address, on, extended, conversionRate);
	
	return(cmd);
	
	
}




}


