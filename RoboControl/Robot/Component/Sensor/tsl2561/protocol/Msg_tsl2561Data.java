package de.hska.lat.robot.component.sensor.tsl2561.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_tsl2561Data extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "tsl2561Data";
	protected static final String description = "data from a tsl2561 sensor";


	private static final int INDEX_SENSOR		= 0;
	private static final int INDEX_VISIBLE	 	= 1;
	private static final int INDEX_IR 			= 2;
	

public Msg_tsl2561Data() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterInt16("visible","measured visible light"));
	this.add(new RemoteParameterInt16("ir","measured ir light"));
}
	
	
public Msg_tsl2561Data(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_tsl2561Data.name);
}


@Override
public String getDescription() 
{
	return(Msg_tsl2561Data.description);
}



public void setData(int index, int visible, int ir)
{

	(( RemoteParameterUint8) this.get(Msg_tsl2561Data.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterInt16) this.get(Msg_tsl2561Data.INDEX_VISIBLE)).setValue(visible);
	(( RemoteParameterInt16) this.get(Msg_tsl2561Data.INDEX_IR)).setValue(ir);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_tsl2561Data.INDEX_SENSOR)).getValue());
}





public int getVisible()
{
	return((( RemoteParameterInt16) this.get(Msg_tsl2561Data.INDEX_VISIBLE)).getValue());
}





public int getIr()
{
	return((( RemoteParameterInt16) this.get(Msg_tsl2561Data.INDEX_IR)).getValue());
}



public static Msg_tsl2561Data getCommand(int id)
{
	Msg_tsl2561Data cmd;
	cmd = new Msg_tsl2561Data(id);
	
	return(cmd);
}



public static Msg_tsl2561Data getCommand(int command, int index,
		int visible, int ir)
{

	Msg_tsl2561Data cmd;
	cmd = Msg_tsl2561Data.getCommand(command);
	cmd.setData(index, visible, ir);
	
	return(cmd);
}


}

