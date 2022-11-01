package de.hska.lat.robot.component.actor.led.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.color.HsvColor;
import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.generic.colorSensor.protocol.RemoteParameterHsvColor;




/**
 * 
 * @author Oktavian Gniot
 *
 *data stream containing actual color information for leds
 */

public class Stream_ledColor extends RemoteStream
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "ledColor";
	protected static final String description = "actual colors of leds";


public Stream_ledColor() 
{
}
	
	
public Stream_ledColor(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_ledColor.name);
}


@Override
public String getDescription() 
{
	return(Stream_ledColor.description);
}



public void setData(HsvColor... colors)
{
	int enumerator;
	RemoteParameterHsvColor parameter;
	
	enumerator = 0;
	
	for (HsvColor color : colors)
	{
		parameter = new RemoteParameterHsvColor("color ","color for LED "+enumerator);
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
		parameter =  new RemoteParameterHsvColor("color ","color for LED "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}


public HsvColor getColor(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterHsvColor) this.get(index)).getColor());
	}
	
return(new HsvColor(0,0,0));	
}




public static Stream_ledColor getCommand(int id)
{
	Stream_ledColor cmd;
	cmd = new Stream_ledColor(id);
	
	return(cmd);
}



public static Stream_ledColor getCommand(int command, HsvColor...colors)
{
	Stream_ledColor cmd;
	cmd = Stream_ledColor.getCommand(command);
	cmd.setData(colors);
	
	return(cmd);
}


}

