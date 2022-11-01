package de.hska.lat.robot.component.voltmeter.protocol;

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

public class Stream_voltages extends RemoteStream
{
	



	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1771607796508968304L;
	protected static final String name = "measuredVoltages";
	protected static final String description = "actual voltages measured by voltmeters";


public Stream_voltages() 
{
}
	
	
public Stream_voltages(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_voltages.name);
}


@Override
public String getDescription() 
{
	return(Stream_voltages.description);
}



public void setData(float... voltages)
{
	int enumerator;
	RemoteParameterVoltage parameter;
	
	enumerator = 0;
	
	for (float value : voltages)
	{
		parameter = new RemoteParameterVoltage("voltage ","voltage for voltmeter"+enumerator);
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
		parameter = new RemoteParameterVoltage("voltage ","voltage for voltmeter"+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public float getTemperature(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterVoltage) this.get(index)).getValue());
	}
	
return(0);	
}




public static Stream_voltages getCommand(int id)
{
	Stream_voltages cmd;
	cmd = new Stream_voltages(id);
	
	return(cmd);
}



public static Stream_voltages getCommand(int command, float...voltages)
{
	Stream_voltages cmd;
	cmd = Stream_voltages.getCommand(command);
	cmd.setData(voltages);
	
	return(cmd);
}


}

