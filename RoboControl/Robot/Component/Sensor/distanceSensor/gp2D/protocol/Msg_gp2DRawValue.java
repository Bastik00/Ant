package de.hska.lat.robot.component.distanceSensor.gp2D.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_gp2DRawValue extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "gp2dRawValue";
	protected static final String description = "unprocessed raw value of actual measurment from a GP2D Sensor";


	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_VALUE = 1;
	

public Msg_gp2DRawValue() 
{
	this.add(new RemoteParameterUint8("index","GP2D sensor index"));
	this.add(new RemoteParameterUint16("raw value","GP2D sensor raw value"));
}
	
	
public Msg_gp2DRawValue(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_gp2DRawValue.name);
}


@Override
public String getDescription() 
{
	return(Msg_gp2DRawValue.description);
}



public void setData(int index, int value)
{
	(( RemoteParameterUint8) this.get(Msg_gp2DRawValue.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_gp2DRawValue.INDEX_VALUE)).setValue(value);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_gp2DRawValue.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public int getRaWValue()
{
	return((( RemoteParameterUint16) this.get(Msg_gp2DRawValue.INDEX_VALUE)).getValue());
}





public static Msg_gp2DRawValue getCommand(int id)
{
	Msg_gp2DRawValue cmd;
	cmd = new Msg_gp2DRawValue(id);
	
	return(cmd);
}



public static Msg_gp2DRawValue getCommand(int command, int index,
		int rawValue)
{

	Msg_gp2DRawValue cmd;
	cmd = Msg_gp2DRawValue.getCommand(command);
	cmd.setData(index, rawValue);
	
	return(cmd);
}


}

