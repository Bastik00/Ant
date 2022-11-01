package de.hska.lat.robot.component.generic.encoder.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt32;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_encoderTicks extends RemoteStream
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5103359306711895136L;
	protected static final String name = "encoderTicks";
	protected static final String description = "ticks counted by an encoder";


public Stream_encoderTicks() 
{
}
	
	
public Stream_encoderTicks(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_encoderTicks.name);
}


@Override
public String getDescription() 
{
	return(Stream_encoderTicks.description);
}



public void setData(int ... ticks)
{
	int enumerator;
	RemoteParameterInt32 parameter;
	
	enumerator = 0;
	
	for (int value : ticks)
	{
		parameter = new RemoteParameterInt32("tick ","ticks counted by an encoder "+enumerator);
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
		parameter = new RemoteParameterInt32("tick ","ticks counted by an encoder "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public int getTicks(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterInt32) this.get(index)).getValue());
	}
	
return(0);	
}




public static Stream_encoderTicks getCommand(int id)
{
	Stream_encoderTicks cmd;
	cmd = new Stream_encoderTicks(id);
	
	return(cmd);
}



public static Stream_encoderTicks getCommand(int command, int...ticks)
{
	Stream_encoderTicks cmd;
	cmd = Stream_encoderTicks.getCommand(command);
	cmd.setData(ticks);
	
	return(cmd);
}


}

