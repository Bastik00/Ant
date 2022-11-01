package de.hska.lat.robot.component.analogSensor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Msg_analogSensorStatistics extends RemoteMessage
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
	
	
	
	
	
	
public Msg_analogSensorStatistics()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterUint16("minValue","the minimal ever measured value for this sensor"));
	this.add(new RemoteParameterUint16("MaxValue","the maximal ever measured value for this sensor"));
}


public Msg_analogSensorStatistics(int command)
{
	this();
	this.setId(command);
}


public void setData(int index ,int minValue,int maxValue)
{
	(( RemoteParameterUint8) this.get(Msg_analogSensorStatistics.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_analogSensorStatistics.INDEX_MIN_VALUE)).setValue(minValue);
	(( RemoteParameterUint16) this.get(Msg_analogSensorStatistics.INDEX_MAX_VALUE)).setValue(maxValue);
}




public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_analogSensorStatistics.INDEX_SENSOR)).getValue());
}

public int getMinValue()
{
	return ((( RemoteParameterUint16) this.get(Msg_analogSensorStatistics.INDEX_MIN_VALUE)).getValue());
}

public int getMaxValue()
{
	return((( RemoteParameterUint16) this.get(Msg_analogSensorStatistics.INDEX_MAX_VALUE)).getValue());
}






public static Msg_analogSensorStatistics getCommand(int command)
{
	Msg_analogSensorStatistics cmd;
	cmd = new Msg_analogSensorStatistics(command);
	
	return(cmd);
}



public static Msg_analogSensorStatistics getCommand(int command,int index ,int minValue,int maxValue)
{
	Msg_analogSensorStatistics cmd;
	cmd = Msg_analogSensorStatistics.getCommand(command);
	cmd.setData(index, minValue, maxValue);
	
	return(cmd);
}



}
