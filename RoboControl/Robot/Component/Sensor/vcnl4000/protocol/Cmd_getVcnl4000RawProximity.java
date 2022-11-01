package de.hska.lat.robot.component.sensor.vcnl4000.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings for a MPU9150 IMU 
 */

public class Cmd_getVcnl4000RawProximity extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setMpu9150Settings";
	protected static final String description = "set settings for a mpu9150Sensor";


	private static final int INDEX_SENSOR 				= 0;
	
	

public Cmd_getVcnl4000RawProximity() 
{
	this.add(new RemoteParameterUint8("index","mpu9150 sensor index"));
}
	
	
public Cmd_getVcnl4000RawProximity(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getVcnl4000RawProximity.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getVcnl4000RawProximity.description);
}



public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getVcnl4000RawProximity.INDEX_SENSOR)).setValue(index);


	
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getVcnl4000RawProximity.INDEX_SENSOR)).getValue());
}



public static Cmd_getVcnl4000RawProximity getCommand(int id)
{
	Cmd_getVcnl4000RawProximity cmd;
	cmd = new Cmd_getVcnl4000RawProximity(id);
	
	return(cmd);
}



public static Cmd_getVcnl4000RawProximity getCommand(int command,int index)
{
	
	Cmd_getVcnl4000RawProximity cmd;
	cmd = Cmd_getVcnl4000RawProximity.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}

