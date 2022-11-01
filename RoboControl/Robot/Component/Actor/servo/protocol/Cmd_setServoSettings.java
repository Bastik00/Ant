package de.hska.lat.robot.component.actor.servo.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setServoSettings extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setServoSettings";
	protected static final String description = "set settings for a servo";


	private static final int INDEX_SENSOR 				= 0;
	private static final int INDEX_MIN_RANGE			= 1;
	private static final int INDEX_MAX_RANGE 			= 2;
	private static final int INDEX_OFFSET				= 3;
	private static final int INDEX_SCALE				= 4;
	private static final int INDEX_FLAGS				= 5;
	

public Cmd_setServoSettings() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterServoPosition("min range","servo min range "));
	this.add(new RemoteParameterServoPosition("max range","servo max range"));
	this.add(new RemoteParameterUint16("offset","servo offset"));
	this.add(new RemoteParameterUint16("scale","servo scale"));
	this.add(new RemoteParameterServoFlags());
}
	
	
public Cmd_setServoSettings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setServoSettings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setServoSettings.description);
}



public void setData(int index, float minRange, float maxRange,int offset, int scale, boolean reverse)
{
	(( RemoteParameterUint8) this.get(Cmd_setServoSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterServoPosition) this.get(Cmd_setServoSettings.INDEX_MIN_RANGE)).setPosition(minRange);
	(( RemoteParameterServoPosition) this.get(Cmd_setServoSettings.INDEX_MAX_RANGE)).setPosition(maxRange);
	(( RemoteParameterUint16) this.get(Cmd_setServoSettings.INDEX_OFFSET)).setValue(offset);
	(( RemoteParameterUint16) this.get(Cmd_setServoSettings.INDEX_SCALE)).setValue(scale);
	(( RemoteParameterServoFlags) this.get(Cmd_setServoSettings.INDEX_FLAGS)).setReverse(reverse);

}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setServoSettings.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public float getMinRange()
{
	return((( RemoteParameterServoPosition) this.get(Cmd_setServoSettings.INDEX_MIN_RANGE)).getPosition());
}


public float getMaxRange()
{
	return((( RemoteParameterServoPosition) this.get(Cmd_setServoSettings.INDEX_MAX_RANGE)).getPosition());
}


/**
 * get offset
 * @return offset 
 */
public int getOffset()
{
	return((( RemoteParameterUint16) this.get(Cmd_setServoSettings.INDEX_OFFSET)).getValue());
}



public boolean isReverse()
{
	return((( RemoteParameterServoFlags) this.get(Cmd_setServoSettings.INDEX_FLAGS)).isReverse());
}


public static Cmd_setServoSettings getCommand(int id)
{
	Cmd_setServoSettings cmd;
	cmd = new Cmd_setServoSettings(id);
	
	return(cmd);
}



public static Cmd_setServoSettings getCommand(int command,int index, float minRange, float maxRange,int offset, int scale, boolean reverse)
{

	Cmd_setServoSettings cmd;
	cmd = Cmd_setServoSettings.getCommand(command);
	cmd.setData(index, minRange, maxRange, offset, scale, reverse);
	
	return(cmd);
}


}

