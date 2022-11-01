package de.hska.lat.robot.component.distanceSensor.srf10.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setSrf10Settings extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 		= 0;
	private static final int INDEX_RANGE		= 1;
	private static final int INDEX_GAIN 		= 2;
	private static final int INDEX_ADDRESS		= 3;
	
	
	protected static final String name = "setSfr10Settings";
	protected static final String description = "set settings for a sfr10 ultrasonic sensor";
	
	

public Cmd_setSrf10Settings() 
{
	this.add(new RemoteParameterUint8("index","sfr 10 sensor index"));
	this.add(new RemoteParameterUint8("range","sfr 10 sensor range"));
	this.add(new RemoteParameterUint8("gain","sfr 10 sensor gain"));
	this.add(new RemoteParameterUint8("addres","sfr 10 sensor addres"));
}





	
	
public Cmd_setSrf10Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setSrf10Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setSrf10Settings.description);
}




public void setData(int index, int range, int gain,int address)
{
	(( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_RANGE)).setValue(range);
	(( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_GAIN)).setValue(gain);
	(( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_ADDRESS)).setValue(address);

}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_SENSOR)).getValue());
}

public int getRange()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_RANGE)).getValue());
}


public int getGain()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_GAIN)).getValue());
}


public int getAddress()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSrf10Settings.INDEX_ADDRESS)).getValue());
}



public static Cmd_setSrf10Settings getCommand(int id)
{
	Cmd_setSrf10Settings cmd;
	cmd = new Cmd_setSrf10Settings(id);
	
	return(cmd);
}







public static Cmd_setSrf10Settings getCommand(int command, int index,
		int distanceRange, int gain, int address)
{
	
	Cmd_setSrf10Settings cmd;
	cmd = Cmd_setSrf10Settings.getCommand(command);
	cmd.setData(index, distanceRange, gain, address);
	
	return(cmd);
	
	
}




}

