package de.hska.lat.robot.component.battery.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_batteryVoltage extends RemoteStream
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "batteryLevel";
	protected static final String description = "actual distance measured by an distance sensors";


public Stream_batteryVoltage() 
{
}
	
	
public Stream_batteryVoltage(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_batteryVoltage.name);
}


@Override
public String getDescription() 
{
	return(Stream_batteryVoltage.description);
}



public void setData(int... values)
{
	int enumerator;
	RemoteParameterUint16 parameter;
	
	enumerator = 0;
	
	for (int value : values)
	{
		parameter = new RemoteParameterUint16("distance ","distance for sensor "+enumerator);
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
		parameter = new RemoteParameterUint16("distance ","distance for sensor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public int getDistance(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterUint16) this.get(index)).getValue());
	}
	
return(0);	
}




public static Stream_batteryVoltage getCommand(int id)
{
	Stream_batteryVoltage cmd;
	cmd = new Stream_batteryVoltage(id);
	
	return(cmd);
}



public static Stream_batteryVoltage getCommand(int command, int...distances)
{
	Stream_batteryVoltage cmd;
	cmd = Stream_batteryVoltage.getCommand(command);
	cmd.setData(distances);
	
	return(cmd);
}


}

