package de.hska.lat.robot.component.battery.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Msg_batteryStatistics extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8615954259563545343L;


	private static final int INDEX_BATTERY = 0;
	private static final int INDEX_MIN_VALUE = 1;
	private static final int INDEX_MAX_VALUE = 2;
	

	protected static final String name = "batteryStatistics";
	protected static final String description = " statistic data from battery ( min / max) ";
	
	
public Msg_batteryStatistics() 
{
	this.add(new RemoteParameterUint8("index","index of battery"));
	this.add(new RemoteParameterUint16("min","min voltage of battery"));
	this.add(new RemoteParameterUint16("max","max voltage of battery"));
}



public Msg_batteryStatistics(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index,int minValue,  int maxValue)
{
	(( RemoteParameterUint8) this.get(Msg_batteryStatistics.INDEX_BATTERY)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_batteryStatistics.INDEX_MIN_VALUE)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_batteryStatistics.INDEX_MAX_VALUE)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_batteryStatistics.INDEX_BATTERY)).getValue());
}


public int getMinValue()
{
	return((( RemoteParameterUint16) this.get(Msg_batteryStatistics.INDEX_MIN_VALUE)).getValue());
}

public int getMaxValue()
{
	return((( RemoteParameterUint16) this.get(Msg_batteryStatistics.INDEX_MAX_VALUE)).getValue());
}



@Override
public String getName() 
{
	return(Msg_batteryStatistics.name);
}


@Override
public String getDescription() 
{
	return(Msg_batteryStatistics.description);
}



public static Msg_batteryStatistics getCommand(int command)
{
	Msg_batteryStatistics cmd;
	cmd = new Msg_batteryStatistics(command);
	
	return(cmd);
}

public static Msg_batteryStatistics getCommand(int command,int index, int minValue, int maxValue)
{
	Msg_batteryStatistics cmd;
	cmd = Msg_batteryStatistics.getCommand(command);
	cmd.setData(index, minValue, maxValue);
	
	return(cmd);
}


}
