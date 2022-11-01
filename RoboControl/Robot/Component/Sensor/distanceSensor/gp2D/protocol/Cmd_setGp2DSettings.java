package de.hska.lat.robot.component.distanceSensor.gp2D.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt32;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setGp2DSettings extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setGp2dSettints";
	protected static final String description = "set settings for a GP2D Sensor";


	private static final int INDEX_SENSOR 		= 0;
	private static final int INDEX_GRADIENT		= 1;
	private static final int INDEX_OFFSET 		= 2;
	private static final int INDEX_MAX_DISTANCE	= 3;

public Cmd_setGp2DSettings() 
{
	this.add(new RemoteParameterUint8("index","GP2D sensor index"));
	this.add(new RemoteParameterInt32("gradient","GP2D sensor gradient"));
	this.add(new RemoteParameterInt32("offset","GP2D sensor ofset"));
	this.add(new RemoteParameterUint16("range","GP2D sensor range"));
}
	
	
public Cmd_setGp2DSettings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setGp2DSettings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setGp2DSettings.description);
}



public void setData(int index, int gradient, int offset,int range)
{
	(( RemoteParameterUint8) this.get(Cmd_setGp2DSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterInt32) this.get(Cmd_setGp2DSettings.INDEX_GRADIENT)).setValue(gradient);
	(( RemoteParameterInt32) this.get(Cmd_setGp2DSettings.INDEX_OFFSET)).setValue(offset);
	(( RemoteParameterUint16) this.get(Cmd_setGp2DSettings.INDEX_MAX_DISTANCE)).setValue(range);

}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setGp2DSettings.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public int getGradient()
{
	return((( RemoteParameterInt32) this.get(Cmd_setGp2DSettings.INDEX_GRADIENT)).getValue());
}


/**
 * get offset
 * @return offset 
 */
public int getOffset()
{
	return((( RemoteParameterInt32) this.get(Cmd_setGp2DSettings.INDEX_OFFSET)).getValue());
}


/**
 * get maximal distance 
 * @return maximal distance
 */
public int getMaxDistance()
{
	return((( RemoteParameterUint16) this.get(Cmd_setGp2DSettings.INDEX_MAX_DISTANCE)).getValue());
}




public static Cmd_setGp2DSettings getCommand(int id)
{
	Cmd_setGp2DSettings cmd;
	cmd = new Cmd_setGp2DSettings(id);
	
	return(cmd);
}



public static Cmd_setGp2DSettings getCommand(int command, int index,
		int gradient, int offset, int maxDistance)
{

	Cmd_setGp2DSettings cmd;
	cmd = Cmd_setGp2DSettings.getCommand(command);
	cmd.setData(index, gradient, offset, maxDistance);
	
	return(cmd);
}


}

