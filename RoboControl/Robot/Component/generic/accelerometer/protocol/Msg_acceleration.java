package de.hska.lat.robot.component.generic.accelerometer.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.math.vector.FloatVector3D;

public class Msg_acceleration extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	
	private static final int SENSOR_INDEX					= 0;
	private static final int INDEX_AXIS_X					= 1;
	private static final int INDEX_AXIS_Y					= 2;
	private static final int INDEX_AXIS_Z					= 3;

	
	protected static final String name = "msgAcceleration";
	protected static final String description = "acceleration measured by an acceleration sensor";
	
	
	
	
	
	
public Msg_acceleration()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterAcceleration("offset X","offset in x direction"));
	this.add(new RemoteParameterAcceleration("offset Y","offset in y direction"));
	this.add(new RemoteParameterAcceleration("offset Z","offset in z direction"));
}


public Msg_acceleration(int command)
{
	this();
	this.setId(command);
}


public String getName()
{
	return(Msg_acceleration.name);
}


public String getDescription()
{
	return(Msg_acceleration.description);
}



public void setData(int index, FloatVector3D acceleration )
{
	(( RemoteParameterUint8) this.get(Msg_acceleration.SENSOR_INDEX)).setValue(index);
	(( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_X)).setValue(acceleration.x);
	(( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_Y)).setValue(acceleration.y);
	(( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_Z)).setValue(acceleration.z);
	
}




public FloatVector3D getAccelerations()
{
	FloatVector3D acceleration;
	
	acceleration = new FloatVector3D((( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_X)).getValue(),
							(( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_Y)).getValue(),
							(( RemoteParameterAcceleration) this.get(Msg_acceleration.INDEX_AXIS_Z)).getValue());
	
	return (acceleration);
}


public int getIndex( )
{
	return((( RemoteParameterUint8) this.get(Msg_acceleration.SENSOR_INDEX)).getValue());
	
}

public static Msg_acceleration getCommand(int command)
{
	Msg_acceleration cmd;
	cmd = new Msg_acceleration(command);
	
	return(cmd);
}

public static Msg_acceleration getCommand(int command, int index, FloatVector3D acceleration)
{
	Msg_acceleration cmd;
	cmd = Msg_acceleration.getCommand(command);
	cmd.setData(index, acceleration );
	
	return(cmd);
}





}
