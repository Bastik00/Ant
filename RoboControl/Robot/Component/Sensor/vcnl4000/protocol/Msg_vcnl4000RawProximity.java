package de.hska.lat.robot.component.sensor.vcnl4000.protocol;

import de.hska.lat.comm.remote.RemoteMessage;

import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_vcnl4000RawProximity extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "proximity";
	protected static final String description = "VCNL 4000 raw proximity value";


	private static final int INDEX_SENSOR 					= 0;
	private static final int INDEX_PROXIMITY_VALUE			= 1;

	

public Msg_vcnl4000RawProximity() 
{
	this.add(new RemoteParameterUint8("index","VCNL4000 sensor index"));
	this.add(new RemoteParameterUint16("provimity", "proximity value"));
}
	
	
public Msg_vcnl4000RawProximity(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_vcnl4000RawProximity.name);
}


@Override
public String getDescription() 
{
	return(Msg_vcnl4000RawProximity.description);
}



public void setData(int index, int value)
{
	(( RemoteParameterUint8) this.get(Msg_vcnl4000RawProximity.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_vcnl4000RawProximity.INDEX_PROXIMITY_VALUE)).setValue(value);

}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_vcnl4000RawProximity.INDEX_SENSOR)).getValue());
}

public int getProximityValue()
{
	return((( RemoteParameterUint16) this.get(Msg_vcnl4000RawProximity.INDEX_PROXIMITY_VALUE)).getValue());
}



public static Msg_vcnl4000RawProximity getCommand(int id)
{
	Msg_vcnl4000RawProximity cmd;
	cmd = new Msg_vcnl4000RawProximity(id);
	
	return(cmd);
}



public static Msg_vcnl4000RawProximity getCommand(int command,int index, int value) 

{
	
	Msg_vcnl4000RawProximity cmd;
	cmd = Msg_vcnl4000RawProximity.getCommand(command);
	cmd.setData(index, value);
	
	return(cmd);
}


}

