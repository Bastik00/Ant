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

public class Msg_servoPosition extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "servoPosition";
	protected static final String description = "actual servo position";


	private static final int INDEX_SERVO = 0;
	private static final int INDEX_POSITION = 1;
	

public Msg_servoPosition() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterInt16("position","servo position"));
}
	
	
public Msg_servoPosition(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_servoPosition.name);
}


@Override
public String getDescription() 
{
	return(Msg_servoPosition.description);
}



public void setData(int index, float position)
{
	int servoPosition = (int) (position * 16);
	(( RemoteParameterUint8) this.get(Msg_servoPosition.INDEX_SERVO)).setValue(index);
	(( RemoteParameterInt16) this.get(Msg_servoPosition.INDEX_POSITION)).setValue(servoPosition);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_servoPosition.INDEX_SERVO)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public float getPosition()
{
	float position;
	
	position = (( RemoteParameterInt16) this.get(Msg_servoPosition.INDEX_POSITION)).getValue();
	position/=16;
	
	return(position);
}





public static Msg_servoPosition getCommand(int id)
{
	Msg_servoPosition cmd;
	cmd = new Msg_servoPosition(id);
	
	return(cmd);
}



public static Msg_servoPosition getCommand(int command, int index,
		float position)
{

	Msg_servoPosition cmd;
	cmd = Msg_servoPosition.getCommand(command);
	cmd.setData(index, position);
	
	return(cmd);
}


}

