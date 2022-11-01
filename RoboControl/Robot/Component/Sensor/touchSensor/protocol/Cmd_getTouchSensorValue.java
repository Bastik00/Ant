package de.hska.lat.robot.component.touchSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getTouchSensorValue extends RemoteCommand
{
	
	

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7668727909048952420L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAnalogSensorValue";
	protected static final String description = "get value from an analog sensor ";
	
	
public Cmd_getTouchSensorValue() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getTouchSensorValue(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getTouchSensorValue.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getTouchSensorValue.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getTouchSensorValue.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getTouchSensorValue.description);
}



public static Cmd_getTouchSensorValue getCommand(int command)
{
	Cmd_getTouchSensorValue cmd;
	cmd = new Cmd_getTouchSensorValue(command);
	
	return(cmd);
}

public static Cmd_getTouchSensorValue getCommand(int command,int index)
{
	Cmd_getTouchSensorValue cmd;
	cmd = Cmd_getTouchSensorValue.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
