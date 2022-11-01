package de.hska.lat.robot.component.touchSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;


public class Stream_touchSensorsValues extends RemoteStream
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452549041406810297L;
	
	
	protected static final String name = "analogValues";
	protected static final String description = "analog values from device size/ values count is device dependent";
	
	
	
	
	
	
public Stream_touchSensorsValues()
{
}


public Stream_touchSensorsValues(int command)
{
	this();
	this.setId(command);
}




@Override
public String getName() 
{
	return(Stream_touchSensorsValues.name);
}


@Override
public String getDescription() 
{
	return(Stream_touchSensorsValues.description);
}


public void setData(int... values)
{
	int enumerator;
	RemoteParameterUint16 parameter;
	
	enumerator = 0;
	
	for (int value : values)
	{
		parameter = new RemoteParameterUint16("value","value for sensor "+enumerator);
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
	enumerator =0;
	

	
	for (dataIndex = 0; dataIndex<dataBuffer.capacity();enumerator++)
	{
		parameter = new RemoteParameterUint16("value","value for sensor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}



public int getValuesCount()
{
	return(this.size());	
}



public int getValue(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterUint16) this.get(index)).getValue());
	}
	
return(0);	
}





public static Stream_touchSensorsValues getCommand(int command)
{
	Stream_touchSensorsValues cmd;
	cmd = new Stream_touchSensorsValues(command);
	
	return(cmd);
}





public static Stream_touchSensorsValues getCommand(int command, int...values)
{
	Stream_touchSensorsValues cmd;
	cmd = Stream_touchSensorsValues.getCommand(command);
	cmd.setData(values);
	
	return(cmd);
}



}
