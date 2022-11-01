package de.hska.lat.robot.component.generic.barometric.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint24;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_barometricPresures extends RemoteStream
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -6996895532918120599L;
	protected static final String name = "measuredPresuress";
	protected static final String description = "actual barometric presure measured by presure sensors";


public Stream_barometricPresures() 
{
}
	
	
public Stream_barometricPresures(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_barometricPresures.name);
}


@Override
public String getDescription() 
{
	return(Stream_barometricPresures.description);
}



public void setData(int... values)
{
	int enumerator;
	RemoteParameterUint24 parameter;
	
	enumerator = 0;
	
	for (int value : values)
	{
		parameter = new RemoteParameterUint24("presure","barometric presure for sensor "+enumerator);
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
		parameter = new RemoteParameterUint24("presure ","presure for sensor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public float getBarometricPresure(int index)
{
	if (index < this.size())
	{
		float value;
		 value =((RemoteParameterUint24) this.get(index)).getValue();
		 value /= 100;
		 
		return(value);
	}
	
return(0);	
}




public static Stream_barometricPresures getCommand(int id)
{
	Stream_barometricPresures cmd;
	cmd = new Stream_barometricPresures(id);
	
	return(cmd);
}



public static Stream_barometricPresures getCommand(int command, int...distances)
{
	Stream_barometricPresures cmd;
	cmd = Stream_barometricPresures.getCommand(command);
	cmd.setData(distances);
	
	return(cmd);
}


}

