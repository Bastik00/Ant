package de.hska.lat.robot.component.sensor.lm75.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.lm75.Lm75Address;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102Address;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_Lm75Shutdown extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_SHUTDOWN				= 1;
	private static final int INDEX_PARAMETERS				= 2;
	
	protected static final String name = "lm75Shutdown";
	protected static final String description = "switch(shutdown) a lm75 sensor on/off";
	
	

public Cmd_Lm75Shutdown() 
{
	this.add(new RemoteParameterUint8("index","lm75 sensor index"));
	this.add(new RemoteParameterLm75Parameters());
}





	
	
public Cmd_Lm75Shutdown(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_Lm75Shutdown.name);
}


@Override
public String getDescription() 
{
	return(Cmd_Lm75Shutdown.description);
}




public void setData(int index, Lm75Address address, boolean on, boolean extended)
{
	
	(( RemoteParameterUint8) this.get(Cmd_Lm75Shutdown.INDEX_SENSOR)).setValue(index);
	

//	(( RemoteParameterTmp102Parameters) this.get(Cmd_setLm75Settings.INDEX_PARAMETERS)).setAddress(address);
	(( RemoteParameterLm75Parameters) this.get(Cmd_Lm75Shutdown.INDEX_SHUTDOWN)).setOn(on);
	(( RemoteParameterLm75Parameters) this.get(Cmd_Lm75Shutdown.INDEX_SHUTDOWN)).setExtended(extended);
}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_Lm75Shutdown.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public Tmp102Address getAddress()
{
	return((( RemoteParameterLm75Parameters) this.get(Cmd_Lm75Shutdown.INDEX_PARAMETERS)).getAddress());
}


public boolean isOn()
{
	return((( RemoteParameterLm75Parameters) this.get(Cmd_Lm75Shutdown.INDEX_PARAMETERS)).isOn());
}

public boolean isExtended()
{
	return((( RemoteParameterLm75Parameters) this.get(Cmd_Lm75Shutdown.INDEX_PARAMETERS)).isExtended());
}





public static Cmd_Lm75Shutdown getCommand(int id)
{
	Cmd_Lm75Shutdown cmd;
	cmd = new Cmd_Lm75Shutdown(id);
	
	return(cmd);
}







public static Cmd_Lm75Shutdown getCommand(int command, int index,
		Lm75Address address, boolean on, boolean extended)
{
	
	Cmd_Lm75Shutdown cmd;
	cmd = Cmd_Lm75Shutdown.getCommand(command);
	cmd.setData(index, address, on, extended);
	
	return(cmd);
	
	
}




}


