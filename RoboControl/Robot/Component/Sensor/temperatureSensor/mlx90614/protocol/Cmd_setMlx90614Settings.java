package de.hska.lat.robot.component.temperatureSensor.mlx90614.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setMlx90614Settings extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 963289354654598629L;

	private static final int INDEX_SENSOR 				= 0;
	
	private static final int INDEX_ADDRESS				= 1;
	private static final int INDEX_EMISSIVITY			= 2;
	
	protected static final String name = "setMlx90614Settings";
	protected static final String description = "set settings for a mlx90614 thermophyle sensor";
	
	

public Cmd_setMlx90614Settings() 
{
	this.add(new RemoteParameterUint8("index","mlx90614 sensor index"));
	this.add(new RemoteParameterUint8("address","sensors i2C address "));
	this.add(new RemoteParameterUint16("emissivity","emissivity "));

}





	
	
public Cmd_setMlx90614Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setMlx90614Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMlx90614Settings.description);
}




public void setData(int index, int address, int emissivity)
{
	
	(( RemoteParameterUint8) this.get(Cmd_setMlx90614Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setMlx90614Settings.INDEX_ADDRESS)).setValue(address);
	(( RemoteParameterUint16) this.get(Cmd_setMlx90614Settings.INDEX_EMISSIVITY)).setValue(emissivity);
	

}



/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMlx90614Settings.INDEX_SENSOR)).getValue());
}


/**
 * 
 * @return
 */
public int getAddress()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMlx90614Settings.INDEX_ADDRESS)).getValue());
}

public int getEmissivity()
{
	return((( RemoteParameterUint16) this.get(Cmd_setMlx90614Settings.INDEX_EMISSIVITY)).getValue());
}




public static Cmd_setMlx90614Settings getCommand(int id)
{
	Cmd_setMlx90614Settings cmd;
	cmd = new Cmd_setMlx90614Settings(id);
	
	return(cmd);
}







public static Cmd_setMlx90614Settings getCommand(int command, int index,
		int address, int emisivity)
{
	
	Cmd_setMlx90614Settings cmd;
	cmd = Cmd_setMlx90614Settings.getCommand(command);
	cmd.setData(index, address, emisivity);
	
	return(cmd);
	
	
}




}


