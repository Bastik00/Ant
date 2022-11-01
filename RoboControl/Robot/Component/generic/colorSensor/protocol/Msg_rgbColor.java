package de.hska.lat.robot.component.generic.colorSensor.protocol;

import de.hska.lat.color.RgbColor;
import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_rgbColor extends RemoteMessage
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -9220265994763161827L;
	
	protected static final String name = "rgbCcolor";
	protected static final String description = "actual color measured by a color sensor as RGB";


	private static final int INDEX_SENSOR 	= 0;
	private static final int INDEX_COLOR	 = 1;
	

public Msg_rgbColor() 
{
	this.add(new RemoteParameterUint8("index"," sensor index"));
	this.add(new RemoteParameterRgbColor("color","rgb color"));
}
	
	
public Msg_rgbColor(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_rgbColor.name);
}


@Override
public String getDescription() 
{
	return(Msg_rgbColor.description);
}



public void setData(int index, RgbColor color)
{
	(( RemoteParameterUint8) this.get(Msg_rgbColor.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterRgbColor) this.get(Msg_rgbColor.INDEX_COLOR)).setColor(color);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_rgbColor.INDEX_SENSOR)).getValue());
}


/**
 * get this message rgb color 
 * @return rgb color
 */
public RgbColor getColor()
{
	return((( RemoteParameterRgbColor) this.get(Msg_rgbColor.INDEX_COLOR)).getColor());
}





public static Msg_rgbColor getCommand(int id)
{
	Msg_rgbColor cmd;
	cmd = new Msg_rgbColor(id);
	
	return(cmd);
}



public static Msg_rgbColor getCommand(int command, int index,
		RgbColor color)
{

	Msg_rgbColor cmd;
	cmd = Msg_rgbColor.getCommand(command);
	cmd.setData(index, color);
	
	return(cmd);
}


}

