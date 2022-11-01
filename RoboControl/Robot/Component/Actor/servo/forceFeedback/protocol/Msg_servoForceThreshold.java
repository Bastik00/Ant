package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_servoForceThreshold extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "servoForceThreshold";
	protected static final String description = "actual servo force threshold";


	private static final int INDEX_SERVO = 0;
	private static final int INDEX_SPEED = 1;
	

public Msg_servoForceThreshold() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterUint16("speed","servo force threshold"));
}
	
	
public Msg_servoForceThreshold(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_servoForceThreshold.name);
}


@Override
public String getDescription() 
{
	return(Msg_servoForceThreshold.description);
}



public void setData(int index, int position)
{
	(( RemoteParameterUint8) this.get(Msg_servoForceThreshold.INDEX_SERVO)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_servoForceThreshold.INDEX_SPEED)).setValue(position);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_servoForceThreshold.INDEX_SERVO)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public int getForceThreshold()
{
	return((( RemoteParameterUint16) this.get(Msg_servoForceThreshold.INDEX_SPEED)).getValue());
}





public static Msg_servoForceThreshold getCommand(int id)
{
	Msg_servoForceThreshold cmd;
	cmd = new Msg_servoForceThreshold(id);
	
	return(cmd);
}



public static Msg_servoForceThreshold getCommand(int command, int index,
		int speed)
{

	Msg_servoForceThreshold cmd;
	cmd = Msg_servoForceThreshold.getCommand(command);
	cmd.setData(index, speed);
	
	return(cmd);
}


}

