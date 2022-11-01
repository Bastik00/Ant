package de.hska.lat.robot.component.sensor.tmp006.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006Address;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006ConversionRate;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setTmp006Settings extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_PARAMETERS			= 1;
	
	protected static final String name = "setTmp006Settings";
	protected static final String description = "set settings for a tmp006 thermophyle sensor";
	
	

public Cmd_setTmp006Settings() 
{
	this.add(new RemoteParameterUint8("index","tmp102 sensor index"));
	this.add(new RemoteParameterTmp006Parameters());
}





	
	
public Cmd_setTmp006Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setTmp006Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setTmp006Settings.description);
}




public void setData(int index, Tmp006Address address, boolean on, Tmp006ConversionRate conversionRate)
{
	
	(( RemoteParameterUint8) this.get(Cmd_setTmp006Settings.INDEX_SENSOR)).setValue(index);
	
	(( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).setConversionRate(conversionRate);
	(( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).setAddress(address);
	(( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).setOn(on);
	}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTmp006Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public Tmp006Address getAddress()
{
	return((( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).getAddress());
}


public boolean isOn()
{
	return((( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).isOn());
}



public Tmp006ConversionRate getPeriod()
{
	return((( RemoteParameterTmp006Parameters) this.get(Cmd_setTmp006Settings.INDEX_PARAMETERS)).getConversionRate());
}



public static Cmd_setTmp006Settings getCommand(int id)
{
	Cmd_setTmp006Settings cmd;
	cmd = new Cmd_setTmp006Settings(id);
	
	return(cmd);
}







public static Cmd_setTmp006Settings getCommand(int command, int index,
		Tmp006Address address, boolean on, Tmp006ConversionRate conversionRate)
{
	
	Cmd_setTmp006Settings cmd;
	cmd = Cmd_setTmp006Settings.getCommand(command);
	cmd.setData(index, address, on, conversionRate);
	
	return(cmd);
	
	
}




}


