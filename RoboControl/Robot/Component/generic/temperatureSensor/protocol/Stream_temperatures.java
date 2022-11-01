package de.hska.lat.robot.component.generic.temperatureSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_temperatures extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = -6790340933111783878L;
	protected static final String name = "temperatures";
	protected static final String description = "actual temperatures measured by an temperature sensors";


public Stream_temperatures() 
{
}
	
	
public Stream_temperatures(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_temperatures.name);
}


@Override
public String getDescription() 
{
	return(Stream_temperatures.description);
}



public void setData(float... temperatures)
{
	int enumerator;
	RemoteParameterTemperature parameter;
	
	enumerator = 0;
	
	for (float value : temperatures)
	{
		parameter = new RemoteParameterTemperature("temperature ","temperature for sensor "+enumerator);
		parameter.setTemperature(value);
		this.add(parameter);
	}
}





@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	int enumerator;
	ByteBuffer dataBuffer;
	RemoteParameterTemperature parameter;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();
	enumerator = 0;
	

	
	for (dataIndex = 0; dataIndex<dataBuffer.capacity()-1;enumerator++)
	{
		parameter = new RemoteParameterTemperature("temperature ","temperature for sensor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public float getTemperature(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterTemperature) this.get(index)).getTemperature());
	}
	
return(0);	
}




public static Stream_temperatures getCommand(int id)
{
	Stream_temperatures cmd;
	cmd = new Stream_temperatures(id);
	
	return(cmd);
}



public static Stream_temperatures getCommand(int command, float...temperatures)
{
	Stream_temperatures cmd;
	cmd = Stream_temperatures.getCommand(command);
	cmd.setData(temperatures);
	
	return(cmd);
}


}

