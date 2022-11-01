package de.hska.lat.robot.component.actor.servo.feedbackServo.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.robot.component.actor.servo.protocol.RemoteParameterServoPosition;

public class Stream_servoRawAnalogPosition  extends RemoteStream
{


	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8684877476581295124L;
	protected static final String name = "servoAnalogRawPositions";
	protected static final String description = "actual analog positions ";
	
	
	
	
	
	
public Stream_servoRawAnalogPosition()
{
}


public Stream_servoRawAnalogPosition(int command)
{
	this();
	this.setId(command);
}


public String getName() 
{
	return (Stream_servoRawAnalogPosition.name);
}



public String getDescription() 
{
	return(Stream_servoRawAnalogPosition.description);
}


public void setData(int... positions)
{
	int enumerator;
	RemoteParameterUint16 parameter;
	
	enumerator = 0;
	
	for (int position : positions)
	{
		parameter = new RemoteParameterUint16("position "+enumerator,"position for servo "+enumerator);
		parameter.setValue(position);
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
		parameter = new RemoteParameterUint16("position "+enumerator,"position for servo "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
}



public int getPositionsCount()
{
	return(this.size());	
}



public float getPosition(int index)
{
	if (index < this.size())
	{
		return((( RemoteParameterServoPosition) this.get(index)).getPosition());
	}
	
	
	
return(0);	
}





public static Stream_servoRawAnalogPosition getCommand(int command)
{
	Stream_servoRawAnalogPosition cmd;
	cmd = new Stream_servoRawAnalogPosition(command);
	
	return(cmd);
}





public static Stream_servoRawAnalogPosition getCommand(int command, int...positions)
{
	Stream_servoRawAnalogPosition cmd;
	cmd = Stream_servoRawAnalogPosition.getCommand(command);
	cmd.setData(positions);
	
	return(cmd);
}



}