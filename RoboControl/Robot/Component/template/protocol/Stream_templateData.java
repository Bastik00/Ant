package de.hska.lat.robot.component.template.protocol;

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

public class Stream_templateData extends RemoteStream
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -6996895532918120599L;
	protected static final String name = "templateData";
	protected static final String description = "template data";


public Stream_templateData() 
{
}
	
	
public Stream_templateData(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_templateData.name);
}


@Override
public String getDescription() 
{
	return(Stream_templateData.description);
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




public static Stream_templateData getCommand(int id)
{
	Stream_templateData cmd;
	cmd = new Stream_templateData(id);
	
	return(cmd);
}



public static Stream_templateData getCommand(int command, int...values)
{
	Stream_templateData cmd;
	cmd = Stream_templateData.getCommand(command);
	cmd.setData(values);
	
	return(cmd);
}


}

