package de.hska.lat.robot.component.distanceSensor.srf10.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_srf10Settings extends RemoteMessage
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 		= 0;
	private static final int INDEX_RANGE		= 1;
	private static final int INDEX_GAIN 		= 2;
	private static final int INDEX_ADDRESS		= 3;
	
	
	protected static final String name = "sfr10Settings";
	protected static final String description = "settings for a sfr10 ultrasonic sensor";
	
	

public Msg_srf10Settings() 
{
	this.add(new RemoteParameterUint8("index","sfr 10 sensor index"));
	this.add(new RemoteParameterUint8("range","sfr 10 sensor range"));
	this.add(new RemoteParameterUint8("gain","sfr 10 sensor gain"));
	this.add(new RemoteParameterUint8("addres","sfr 10 sensor addres"));
}





	
	
public Msg_srf10Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_srf10Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_srf10Settings.description);
}




public void setData(int index, int range, int gain,int address)
{
	(( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_RANGE)).setValue(range);
	(( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_GAIN)).setValue(gain);
	(( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_ADDRESS)).setValue(address);

}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_SENSOR)).getValue());
}

public int getRange()
{
	return((( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_RANGE)).getValue());
}


public int getGain()
{
	return((( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_GAIN)).getValue());
}


public int getAddress()
{
	return((( RemoteParameterUint8) this.get(Msg_srf10Settings.INDEX_ADDRESS)).getValue());
}



public static Msg_srf10Settings getCommand(int id)
{
	Msg_srf10Settings cmd;
	cmd = new Msg_srf10Settings(id);
	
	return(cmd);
}







public static Msg_srf10Settings getCommand(int command, int index,
		int distanceRange, int gain, int address)
{
	
	Msg_srf10Settings cmd;
	cmd = Msg_srf10Settings.getCommand(command);
	cmd.setData(index, distanceRange, gain, address);
	
	return(cmd);
	
	
}




}

