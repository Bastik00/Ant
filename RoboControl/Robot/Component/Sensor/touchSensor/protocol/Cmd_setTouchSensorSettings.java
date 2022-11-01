package de.hska.lat.robot.component.touchSensor.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Cmd_setTouchSensorSettings extends RemoteCommand
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	
	private static final int INDEX_SENSOR					= 0;
	private static final int INDEX_THRESHOLD				= 1;
	private static final int INDEX_FLAGS					= 2;
	
	protected static final String name = "setAnalogSensorSettings";
	protected static final String description = "set settings of an analog sensor";
	
	
	
	
	
	
public Cmd_setTouchSensorSettings()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterUint8("factor","detection factor"));
	this.add(new RemoteParameterAnalogSensorFlags("flags","flags "));
	this.add(new RemoteParameterUint8("threshold","sensor value thereshold for detector"));
}


public Cmd_setTouchSensorSettings(int command)
{
	this();
	this.setId(command);
}


public void setData(int index ,int threshold,boolean inverse)
{
	(( RemoteParameterUint8) this.get(Cmd_setTouchSensorSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Cmd_setTouchSensorSettings.INDEX_THRESHOLD)).setValue(threshold);
	(( RemoteParameterAnalogSensorFlags) this.get(Cmd_setTouchSensorSettings.INDEX_FLAGS)).setInverse(inverse);
}




public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setTouchSensorSettings.INDEX_SENSOR)).getValue());
}

public int getThreshold()
{
	return ((( RemoteParameterUint16) this.get(Cmd_setTouchSensorSettings.INDEX_THRESHOLD)).getValue());
}

public boolean isInverse()
{
	return((( RemoteParameterAnalogSensorFlags) this.get(Cmd_setTouchSensorSettings.INDEX_FLAGS)).isInverse());
}






public static Cmd_setTouchSensorSettings getCommand(int command)
{
	Cmd_setTouchSensorSettings cmd;
	cmd = new Cmd_setTouchSensorSettings(command);
	
	return(cmd);
}



public static Cmd_setTouchSensorSettings getCommand(int command,int index ,int threshold,boolean inverse)
{
	Cmd_setTouchSensorSettings cmd;
	cmd = Cmd_setTouchSensorSettings.getCommand(command);
	cmd.setData(index, threshold, inverse);
	
	return(cmd);
}



}
