package de.hska.lat.robot.component.voltmeter.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getVoltage extends RemoteCommand
{
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 6796645598022842317L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getVoltage";
	protected static final String description = "get actual voltage value from an voltmeter ";
	
	
public Cmd_getVoltage() 
{
	this.add(new RemoteParameterUint8("index","index of voltmeter"));
}



public Cmd_getVoltage(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getVoltage.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getVoltage.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getVoltage.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getVoltage.description);
}



public static Cmd_getVoltage getCommand(int command)
{
	Cmd_getVoltage cmd;
	cmd = new Cmd_getVoltage(command);
	
	return(cmd);
}

public static Cmd_getVoltage getCommand(int command,int index)
{
	Cmd_getVoltage cmd;
	cmd = Cmd_getVoltage.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
