package de.hska.lat.robot.component.generic.colorSensor.protocol;

import de.hska.lat.color.HsvColor;
import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_hsvColor extends RemoteMessage
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -9220265994763161827L;
	
	protected static final String name = "hsvColor";
	protected static final String description = "actual color measured by a color sensor";


	private static final int INDEX_SENSOR 	= 0;
	private static final int INDEX_COLOR	 = 1;
	

public Msg_hsvColor() 
{
	this.add(new RemoteParameterUint8("index"," sensor index"));
	this.add(new RemoteParameterHsvColor("color","hsv color"));
}
	
	
public Msg_hsvColor(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_hsvColor.name);
}


@Override
public String getDescription() 
{
	return(Msg_hsvColor.description);
}



public void setData(int index, int value)
{
	(( RemoteParameterUint8) this.get(Msg_hsvColor.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_hsvColor.INDEX_COLOR)).setValue(value);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_hsvColor.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public HsvColor getColor()
{
	return((( RemoteParameterHsvColor) this.get(Msg_hsvColor.INDEX_COLOR)).getColor());
}





public static Msg_hsvColor getCommand(int id)
{
	Msg_hsvColor cmd;
	cmd = new Msg_hsvColor(id);
	
	return(cmd);
}



public static Msg_hsvColor getCommand(int command, int index,
		int distance)
{

	Msg_hsvColor cmd;
	cmd = Msg_hsvColor.getCommand(command);
	cmd.setData(index, distance);
	
	return(cmd);
}


}

