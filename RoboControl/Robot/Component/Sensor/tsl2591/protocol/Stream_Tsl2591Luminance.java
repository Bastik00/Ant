package de.hska.lat.robot.component.sensor.tsl2591.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint24;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_Tsl2591Luminance extends RemoteStream
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -6996895532918120599L;
	protected static final String name = "templateData";
	protected static final String description = "template data";


public Stream_Tsl2591Luminance() 
{
}
	
	
public Stream_Tsl2591Luminance(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_Tsl2591Luminance.name);
}


@Override
public String getDescription() 
{
	return(Stream_Tsl2591Luminance.description);
}



public void setData(int... values)
{
	int enumerator;
	RemoteParameterUint16 parameter;
	
	enumerator = 0;
	
	for (int value : values)
	{
		parameter = new RemoteParameterUint16("data","template component data "+enumerator);
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
		parameter = new RemoteParameterUint16("data","template component data "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public int getBarometricPresure(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterUint24) this.get(index)).getValue());
	}
	
return(0);	
}




public static Stream_Tsl2591Luminance getCommand(int id)
{
	Stream_Tsl2591Luminance cmd;
	cmd = new Stream_Tsl2591Luminance(id);
	
	return(cmd);
}



public static Stream_Tsl2591Luminance getCommand(int command, int...values)
{
	Stream_Tsl2591Luminance cmd;
	cmd = Stream_Tsl2591Luminance.getCommand(command);
	cmd.setData(values);
	
	return(cmd);
}


}

