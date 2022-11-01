package de.hska.lat.robot.component.touchSensor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Msg_touchSensorValue extends RemoteMessage
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7292538832933305191L;
	private static final int INDEX_SENSOR					= 0;
	private static final int INDEX_VALUE					= 1;
		
	protected static final String name = "analogSensorValue";
	protected static final String description = " actual analog sensor value";
	
	
	
	
	
	
public Msg_touchSensorValue()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterUint16("value","actual analog sensor value"));
}


public Msg_touchSensorValue(int command)
{
	this();
	this.setId(command);
}


public void setData(int index ,int value)
{
	(( RemoteParameterUint8) this.get(Msg_touchSensorValue.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_touchSensorValue.INDEX_VALUE)).setValue(value);
}




public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_touchSensorValue.INDEX_SENSOR)).getValue());
}

public int getValue()
{
	return ((( RemoteParameterUint16) this.get(Msg_touchSensorValue.INDEX_VALUE)).getValue());
}





public static Msg_touchSensorValue getCommand(int command)
{
	Msg_touchSensorValue cmd;
	cmd = new Msg_touchSensorValue(command);
	
	return(cmd);
}



public static Msg_touchSensorValue getCommand(int command,int index ,int value )
{
	Msg_touchSensorValue cmd;
	cmd = Msg_touchSensorValue.getCommand(command);
	cmd.setData(index, value );
	
	return(cmd);
}



}
