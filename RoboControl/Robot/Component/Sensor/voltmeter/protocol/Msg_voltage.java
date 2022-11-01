package de.hska.lat.robot.component.voltmeter.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Msg_voltage extends RemoteCommand
{
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3191990661565962352L;
	private static final int INDEX_VOLTMETER = 0;
	private static final int INDEX_VOLTAGE = 1;

	

	protected static final String name = "batteryStatistics";
	protected static final String description = " statistic data from battery ( min / max) ";
	
	
public Msg_voltage() 
{
	this.add(new RemoteParameterUint8("index","index of battery"));
	this.add(new RemoteParameterUint16("min","min voltage of battery"));
	this.add(new RemoteParameterUint16("max","max voltage of battery"));
}



public Msg_voltage(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index,float voltage)
{
	(( RemoteParameterUint8) this.get(Msg_voltage.INDEX_VOLTMETER)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_voltage.INDEX_VOLTAGE)).setValue(index);
}





public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_voltage.INDEX_VOLTMETER)).getValue());
}




public int getVoltage()
{
	return((( RemoteParameterUint16) this.get(Msg_voltage.INDEX_VOLTAGE)).getValue());
}





@Override
public String getName() 
{
	return(Msg_voltage.name);
}




@Override
public String getDescription() 
{
	return(Msg_voltage.description);
}




public static Msg_voltage getCommand(int command)
{
	Msg_voltage cmd;
	cmd = new Msg_voltage(command);
	return(cmd);
}




public static Msg_voltage getCommand(int command,int index,float voltage)
{
	Msg_voltage cmd;
	cmd = Msg_voltage.getCommand(command);
	cmd.setData(index, voltage);
	
	return(cmd);
}


}
