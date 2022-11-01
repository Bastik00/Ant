package de.hska.lat.robot.component.distanceSensor.gp2D.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt32;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_gp2DSettings extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "gp2dSettints";
	protected static final String description = "settings from a GP2D Sensor";


	private static final int INDEX_SENSOR 		= 0;
	private static final int INDEX_GRADIENT		= 1;
	private static final int INDEX_OFFSET 		= 2;
	private static final int INDEX_MAX_DISTANCE	= 3;

public Msg_gp2DSettings() 
{
	this.add(new RemoteParameterUint8("index","GP2D sensor index"));
	this.add(new RemoteParameterInt32("gradient","GP2D sensor gradient"));
	this.add(new RemoteParameterInt32("offset","GP2D sensor ofset"));
	this.add(new RemoteParameterUint16("range","GP2D sensor range"));
}
	
	
public Msg_gp2DSettings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_gp2DSettings.name);
}


@Override
public String getDescription() 
{
	return(Msg_gp2DSettings.description);
}



public void setData(int index, int gradient, int offset,int range)
{
	(( RemoteParameterUint8) this.get(Msg_gp2DSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterInt32) this.get(Msg_gp2DSettings.INDEX_GRADIENT)).setValue(gradient);
	(( RemoteParameterInt32) this.get(Msg_gp2DSettings.INDEX_OFFSET)).setValue(offset);
	(( RemoteParameterUint16) this.get(Msg_gp2DSettings.INDEX_MAX_DISTANCE)).setValue(range);

}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_gp2DSettings.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public int getGradient()
{
	return((( RemoteParameterInt32) this.get(Msg_gp2DSettings.INDEX_GRADIENT)).getValue());
}


/**
 * get offset
 * @return offset 
 */
public int getOffset()
{
	return((( RemoteParameterInt32) this.get(Msg_gp2DSettings.INDEX_OFFSET)).getValue());
}


/**
 * get maximal distance 
 * @return maximal distance
 */
public int getMaxDistance()
{
	return((( RemoteParameterUint16) this.get(Msg_gp2DSettings.INDEX_MAX_DISTANCE)).getValue());
}




public static Msg_gp2DSettings getCommand(int id)
{
	Msg_gp2DSettings cmd;
	cmd = new Msg_gp2DSettings(id);
	
	return(cmd);
}



public static Msg_gp2DSettings getCommand(int command, int index,
		int gradient, int offset, int maxDistance)
{

	Msg_gp2DSettings cmd;
	cmd = Msg_gp2DSettings.getCommand(command);
	cmd.setData(index, gradient, offset, maxDistance);
	
	return(cmd);
}


}

