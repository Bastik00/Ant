package de.hska.lat.robot.component.actor.servo.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;



public class Stream_servosStatus extends RemoteStream
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4452549041406810297L;
	
	
	protected static final String name = "servosStatus";
	protected static final String description = "status from servos";
	
	
	
	
	
	
public Stream_servosStatus()
{
}


public Stream_servosStatus(int command)
{
	this();
	this.setId(command);
}


public String getName() 
{
	return (Stream_servosStatus.name);
}



public String getDescription() 
{
	return(Stream_servosStatus.description);
}



public void setData(boolean on,
		boolean active,
		boolean reverse,
		boolean atMin,
		boolean atMax,
		boolean stalling)
{
	int enumerator;
	RemoteParameterServoStatus parameter;
	
	enumerator = 0;
	
	//for (int value : parameter)
	{
		parameter = new RemoteParameterServoStatus(enumerator);
		parameter.setOn(on);
		parameter.setActive(active);
		parameter.setAtMin(atMin);
		parameter.setAtMax(atMax);
		parameter.setStalling(stalling);

	//	parameter.setValue(value);
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
		parameter = new RemoteParameterServoStatus(enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}







public boolean isOn(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isOn());
	}
	return false;
}



public boolean isActive(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isActive());
	}
	return false;
}



public boolean isReverse(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isReverse());
	}
	return false;
}



public boolean isAtMax(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isAtMax());
	}
	return false;
}



public boolean isAtMin(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isAtMin());
	}
	return false;
}

public boolean isStalling(int index)
{
	if (index < this.getParameterCount())
	{
		return(((RemoteParameterServoStatus) this.get(index)).isStalling());
	}
	return false;
}






public static Stream_servosStatus getCommand(int command)
{
	Stream_servosStatus cmd;
	cmd = new Stream_servosStatus(command);
	
	return(cmd);
}


public static Stream_servosStatus getCommand(int command, 
		boolean on,
		boolean active,
		boolean reverse,
		boolean atMin,
		boolean atMax,
		boolean stalling
		)
{
	Stream_servosStatus cmd;
	cmd = Stream_servosStatus.getCommand(command);
	cmd.setData( on, active, reverse, atMin, atMax, stalling);
	
	return(cmd);
}




}
