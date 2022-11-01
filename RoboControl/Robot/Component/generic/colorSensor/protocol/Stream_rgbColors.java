package de.hska.lat.robot.component.generic.colorSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.color.RgbColor;
import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_rgbColors extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = -6790340933111783878L;
	protected static final String name = "rgbColors";
	protected static final String description = "actual color measured by a color sensors";


public Stream_rgbColors() 
{
}
	
	
public Stream_rgbColors(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_rgbColors.name);
}


@Override
public String getDescription() 
{
	return(Stream_rgbColors.description);
}



public void setData(RgbColor ... colors)
{
	int enumerator;
	RemoteParameterRgbColor parameter;
	
	enumerator = 0;
	
	for (RgbColor color : colors)
	{
		parameter = new RemoteParameterRgbColor("color ","color for sensor "+enumerator);
		parameter.setColor(color);
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
		parameter = new RemoteParameterRgbColor("color ","color for sensor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public RgbColor getColor(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterRgbColor) this.get(index)).getColor());
	}
	
return(null);	
}




public static Stream_rgbColors getCommand(int id)
{
	Stream_rgbColors cmd;
	cmd = new Stream_rgbColors(id);
	
	return(cmd);
}



public static Stream_rgbColors getCommand(int command, RgbColor ... colors)
{
	Stream_rgbColors cmd;
	cmd = Stream_rgbColors.getCommand(command);
	cmd.setData(colors);
	
	return(cmd);
}


}

