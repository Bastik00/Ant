package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;






/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_motorsVelocity extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorsVelocity";
	protected static final String description = "motors velocity in m/s";


public Stream_motorsVelocity() 
{
}
	
	
public Stream_motorsVelocity(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_motorsVelocity.name);
}


@Override
public String getDescription() 
{
	return(Stream_motorsVelocity.description);
}



public void setData(float... velocity)
{
	int enumerator;
	RemoteParameterMotorVelocity parameter;
	
	enumerator = 0;
	
	for (float value : velocity)
	{
		parameter = new RemoteParameterMotorVelocity("motors velocity in m/s for motor "+enumerator);
		parameter.setValue(value);
		this.add(parameter);
	}
}





@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	int enumerator;
	ByteBuffer dataBuffer;
	RemoteParameter<?> parameter;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();
	enumerator = 0;
	

	
	for (dataIndex = 0; dataIndex<dataBuffer.capacity()-1;enumerator++)
	{
		parameter = new RemoteParameterMotorVelocity("motors velocity in m/s for motor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}

/**
 * get motor rpm value
 * @param index
 * @return
 */

public float getVelocity(int index)
{
	float rpm = 0;
	
	if (index < this.size())
	{
	
		rpm = (( RemoteParameterMotorVelocity) this.get(index)).getValue();

	}
	return(rpm);
}




public static Stream_motorsVelocity getCommand(int id)
{
	Stream_motorsVelocity cmd;
	cmd = new Stream_motorsVelocity(id);
	
	return(cmd);
}



public static Stream_motorsVelocity getCommand(int command, float...velocity)
{
	Stream_motorsVelocity cmd;
	cmd = Stream_motorsVelocity.getCommand(command);
	cmd.setData(velocity);
	
	return(cmd);
}




}

