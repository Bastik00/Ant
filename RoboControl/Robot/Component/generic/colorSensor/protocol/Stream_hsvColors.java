package de.hska.lat.robot.component.generic.colorSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.color.HsvColor;
import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_hsvColors extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = -6790340933111783878L;
	protected static final String name = "hsvColors";
	protected static final String description = "actual color measured by a color sensors";


public Stream_hsvColors() 
{
}
	
	
public Stream_hsvColors(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_hsvColors.name);
}


@Override
public String getDescription() 
{
	return(Stream_hsvColors.description);
}



public void setData(HsvColor ... colors)
{
	int enumerator;
	RemoteParameterHsvColor parameter;
	
	enumerator = 0;
	
	for (HsvColor color : colors)
	{
		parameter = new RemoteParameterHsvColor("color ","color for sensor "+enumerator);
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
		parameter = new RemoteParameterHsvColor("color ","color for sensor "+enumerator);
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
	
return(null);	
}




public static Stream_hsvColors getCommand(int id)
{
	Stream_hsvColors cmd;
	cmd = new Stream_hsvColors(id);
	
	return(cmd);
}



public static Stream_hsvColors getCommand(int command, HsvColor ... colors)
{
	Stream_hsvColors cmd;
	cmd = Stream_hsvColors.getCommand(command);
	cmd.setData(colors);
	
	return(cmd);
}


public float getHue()
{
	// TODO Auto-generated method stub
	return 0;
}


public float getSaturation()
{
	// TODO Auto-generated method stub
	return 0;
}


}

