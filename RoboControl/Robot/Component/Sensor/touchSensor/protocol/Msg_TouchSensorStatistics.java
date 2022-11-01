package de.hska.lat.robot.component.touchSensor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Msg_TouchSensorStatistics extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	
	private static final int INDEX_SENSOR					= 0;
	private static final int INDEX_MIN_VALUE				= 1;
	private static final int INDEX_MAX_VALUE				= 2;
	
	protected static final String name = "analogSensorStatistics";
	protected static final String description = "statistoics for an analog sensor (min/max values)";
	
	
	
	
	
	
public Msg_TouchSensorStatistics()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterUint16("minValue","the minimal ever measured value for this sensor"));
	this.add(new RemoteParameterUint16("MaxValue","the maximal ever measured value for this sensor"));
}


public Msg_TouchSensorStatistics(int command)
{
	this();
	this.setId(command);
}


public void setData(int index ,int minValue,int maxValue)
{
	(( RemoteParameterUint8) this.get(Msg_TouchSensorStatistics.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_TouchSensorStatistics.INDEX_MIN_VALUE)).setValue(minValue);
	(( RemoteParameterUint16) this.get(Msg_TouchSensorStatistics.INDEX_MAX_VALUE)).setValue(maxValue);
}




public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_TouchSensorStatistics.INDEX_SENSOR)).getValue());
}

public int getMinValue()
{
	return ((( RemoteParameterUint16) this.get(Msg_TouchSensorStatistics.INDEX_MIN_VALUE)).getValue());
}

public int getMaxValue()
{
	return((( RemoteParameterUint16) this.get(Msg_TouchSensorStatistics.INDEX_MAX_VALUE)).getValue());
}






public static Msg_TouchSensorStatistics getCommand(int command)
{
	Msg_TouchSensorStatistics cmd;
	cmd = new Msg_TouchSensorStatistics(command);
	
	return(cmd);
}



public static Msg_TouchSensorStatistics getCommand(int command,int index ,int minValue,int maxValue)
{
	Msg_TouchSensorStatistics cmd;
	cmd = Msg_TouchSensorStatistics.getCommand(command);
	cmd.setData(index, minValue, maxValue);
	
	return(cmd);
}



}
