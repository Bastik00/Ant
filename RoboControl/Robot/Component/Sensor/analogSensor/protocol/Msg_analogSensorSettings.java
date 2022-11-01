package de.hska.lat.robot.component.analogSensor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Msg_analogSensorSettings extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	
	private static final int INDEX_SENSOR					= 0;
	private static final int INDEX_THRESHOLD				= 1;
	private static final int INDEX_FLAGS					= 2;
	
	protected static final String name = "analogSensorSettings";
	protected static final String description = "settings of an analog sensor";
	
	
	
	
	
	
public Msg_analogSensorSettings()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterUint16("threshold","sensor value thereshold for detector"));
	this.add(new RemoteParameterAnalogSensorFlags("flags","flags "));
}


public Msg_analogSensorSettings(int command)
{
	this();
	this.setId(command);
}


public void setData(int index ,int threshold,boolean inverse)
{
	(( RemoteParameterUint8) this.get(Msg_analogSensorSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_analogSensorSettings.INDEX_THRESHOLD)).setValue(threshold);
	(( RemoteParameterAnalogSensorFlags) this.get(Msg_analogSensorSettings.INDEX_FLAGS)).setInverse(inverse);
}




public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_analogSensorSettings.INDEX_SENSOR)).getValue());
}

public int getThreshold()
{
	return ((( RemoteParameterUint16) this.get(Msg_analogSensorSettings.INDEX_THRESHOLD)).getValue());
}

public boolean isInverse()
{
	return((( RemoteParameterAnalogSensorFlags) this.get(Msg_analogSensorSettings.INDEX_FLAGS)).isInverse());
}






public static Msg_analogSensorSettings getCommand(int command)
{
	Msg_analogSensorSettings cmd;
	cmd = new Msg_analogSensorSettings(command);
	
	return(cmd);
}



public static Msg_analogSensorSettings getCommand(int command,int index ,int threshold,boolean inverse)
{
	Msg_analogSensorSettings cmd;
	cmd = Msg_analogSensorSettings.getCommand(command);
	cmd.setData(index, threshold, inverse);
	
	return(cmd);
}



}
