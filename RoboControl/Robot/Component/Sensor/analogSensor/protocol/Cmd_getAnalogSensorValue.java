package de.hska.lat.robot.component.analogSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getAnalogSensorValue extends RemoteCommand
{
	
	

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7668727909048952420L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAnalogSensorValue";
	protected static final String description = "get value from an analog sensor ";
	
	
public Cmd_getAnalogSensorValue() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getAnalogSensorValue(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getAnalogSensorValue.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getAnalogSensorValue.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getAnalogSensorValue.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getAnalogSensorValue.description);
}



public static Cmd_getAnalogSensorValue getCommand(int command)
{
	Cmd_getAnalogSensorValue cmd;
	cmd = new Cmd_getAnalogSensorValue(command);
	
	return(cmd);
}

public static Cmd_getAnalogSensorValue getCommand(int command,int index)
{
	Cmd_getAnalogSensorValue cmd;
	cmd = Cmd_getAnalogSensorValue.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
