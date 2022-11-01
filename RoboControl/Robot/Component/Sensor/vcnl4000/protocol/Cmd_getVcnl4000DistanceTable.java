package de.hska.lat.robot.component.sensor.vcnl4000.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings for a MPU9150 IMU 
 */

public class Cmd_getVcnl4000DistanceTable extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "getVcnl4000DistanceTable";
	protected static final String description = "get distance table for VCNL4000 sensor";


	private static final int INDEX_SENSOR 				= 0;
	
	

public Cmd_getVcnl4000DistanceTable() 
{
	this.add(new RemoteParameterUint8("index","VCNL4000 sensor index"));
}
	
	
public Cmd_getVcnl4000DistanceTable(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_getVcnl4000DistanceTable.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getVcnl4000DistanceTable.description);
}



public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getVcnl4000DistanceTable.INDEX_SENSOR)).setValue(index);


	
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getVcnl4000DistanceTable.INDEX_SENSOR)).getValue());
}



public static Cmd_getVcnl4000DistanceTable getCommand(int id)
{
	Cmd_getVcnl4000DistanceTable cmd;
	cmd = new Cmd_getVcnl4000DistanceTable(id);
	
	return(cmd);
}



public static Cmd_getVcnl4000DistanceTable getCommand(int command,int index)
{
	
	Cmd_getVcnl4000DistanceTable cmd;
	cmd = Cmd_getVcnl4000DistanceTable.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}

