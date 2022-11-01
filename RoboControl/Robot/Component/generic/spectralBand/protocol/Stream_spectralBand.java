package de.hska.lat.robot.component.generic.spectralBand.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.generic.spectralBand.protocol.RemoteParameterSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Stream_spectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Stream_spectralBand;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_spectralBand extends RemoteStream
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886697886026233519L;
	protected static final String name = "spectralbands";
	protected static final String description = "actual spectralbands measured by an microphone as a fourier";


public Stream_spectralBand() 
{
}
	
	
public Stream_spectralBand(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_spectralBand.name);
}


@Override
public String getDescription() 
{
	return(Stream_spectralBand.description);
}



public void setData(float... spectralbands)
{
	int enumerator;
	RemoteParameterSpectralBand parameter;
	
	enumerator = 0;
	
	for (float value : spectralbands)
	{
		parameter = new RemoteParameterSpectralBand("spectralband ","spectralband"+enumerator);
		parameter.setSpectralband(value);
		this.add(parameter);
	}
}





@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	int enumerator;
	ByteBuffer dataBuffer;
	RemoteParameterSpectralBand parameter;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();
	enumerator = 0;
	

	
	for (dataIndex = 0; dataIndex<dataBuffer.capacity()-1;enumerator++)
	{
		parameter = new RemoteParameterSpectralBand("spectralband ","spectralband"+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public float getSpectralband(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterSpectralBand) this.get(index)).getSpectralband());
	}
	
return(0);	
}




public static Stream_spectralBand getCommand(int id)
{
	Stream_spectralBand cmd;
	cmd = new Stream_spectralBand(id);
	
	return(cmd);
}



public static Stream_spectralBand getCommand(int command, float...temperatures)
{
	Stream_spectralBand cmd;
	cmd = Stream_spectralBand.getCommand(command);
	cmd.setData(temperatures);
	
	return(cmd);
}


}

