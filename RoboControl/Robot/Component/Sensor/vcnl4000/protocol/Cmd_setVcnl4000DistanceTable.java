package de.hska.lat.robot.component.sensor.vcnl4000.protocol;


import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.vcnl4000.DistanceTable;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000DistanceSensor;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings for a MPU9150 IMU 
 */

public class Cmd_setVcnl4000DistanceTable extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setVcnl4000DistanceTable";
	protected static final String description = "entrys for a VCNL4000 Sensor distance table";


	private static final int INDEX_SENSOR 				= 0;
	
	

public Cmd_setVcnl4000DistanceTable() 
{
	int index;
	
	this.add(new RemoteParameterUint8("index","VCNL4000 sensor index"));
	
	for (index=0; index <Vcnl4000DistanceSensor.DISTANCE_DATA_DEEPTH ;index++)
	{
		this.add(new RemoteParameterUint8("distance ("+index+")","distance point"));
		this.add(new RemoteParameterUint16("value("+index+")","proximity value"));
	}
}
	
	
public Cmd_setVcnl4000DistanceTable(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setVcnl4000DistanceTable.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setVcnl4000DistanceTable.description);
}



public void setData(int index, DistanceTable distances)
{
	(( RemoteParameterUint8) this.get(Cmd_setVcnl4000DistanceTable.INDEX_SENSOR)).setValue(index);
	

	for (index=0; index <Vcnl4000DistanceSensor.DISTANCE_DATA_DEEPTH ;index++)
	{
		(( RemoteParameterUint8) this.get((index*2)+1)).setValue(distances.getDistance(index));
		(( RemoteParameterUint16) this.get((index*2)+2)).setValue(distances.getProximityValue(index));
		
	}
	
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setVcnl4000DistanceTable.INDEX_SENSOR)).getValue());
}



public static Cmd_setVcnl4000DistanceTable getCommand(int id)
{
	Cmd_setVcnl4000DistanceTable cmd;
	cmd = new Cmd_setVcnl4000DistanceTable(id);
	
	return(cmd);
}



public static Cmd_setVcnl4000DistanceTable getCommand(int command,int index, DistanceTable distances)
{
	
	Cmd_setVcnl4000DistanceTable cmd;
	cmd = Cmd_setVcnl4000DistanceTable.getCommand(command);
	cmd.setData(index, distances);
	
	return(cmd);
}


}

