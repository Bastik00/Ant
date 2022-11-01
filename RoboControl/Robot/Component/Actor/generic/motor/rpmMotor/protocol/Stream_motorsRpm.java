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

public class Stream_motorsRpm extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorsRpm";
	protected static final String description = "motors revolutions per minute";


public Stream_motorsRpm() 
{
}
	
	
public Stream_motorsRpm(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_motorsRpm.name);
}


@Override
public String getDescription() 
{
	return(Stream_motorsRpm.description);
}



public void setData(float... rpm)
{
	int enumerator;
	RemoteParameterMotorRpm parameter;
	
	enumerator = 0;
	
	for (float value : rpm)
	{
		parameter = new RemoteParameterMotorRpm("motors revolutions per minute for motor "+enumerator);
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
		parameter = new RemoteParameterMotorRpm("motors revolutions per minute for motor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}

/**
 * get motor rpm value
 * @param index
 * @return
 */

public float getRpm(int index)
{
	float rpm = 0;
	
	if (index < this.size())
	{
	
		rpm = (( RemoteParameterMotorRpm) this.get(index)).getValue();

	}
	return(rpm);
}




public static Stream_motorsRpm getCommand(int id)
{
	Stream_motorsRpm cmd;
	cmd = new Stream_motorsRpm(id);
	
	return(cmd);
}



public static Stream_motorsRpm getCommand(int command, float...rpm)
{
	Stream_motorsRpm cmd;
	cmd = Stream_motorsRpm.getCommand(command);
	cmd.setData(rpm);
	
	return(cmd);
}




}

