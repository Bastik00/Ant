package de.hska.lat.robot.component.generic.accelerometer.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.FloatVector3D;


public class Stream_accelerations extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	


	
	protected static final String name = "accelometerRates";
	protected static final String description = "acceleration values froman acceleration sensor";
	
	
public Stream_accelerations()
{
}
	
	
public Stream_accelerations(int command)
{

	this.setId(command);
}



public String getName()
{
	return(Stream_accelerations.name);
}


public String getDescription()
{
	return(Stream_accelerations.description);
}



public void setData(FloatVector3D...rates)
{
	
	for (FloatVector3D rate : rates)
	{
		RemoteParameterAcceleration acceleration;
		
		acceleration = new RemoteParameterAcceleration("X","acceleration in x direction");
		acceleration.setValue(rate.x);
		this.add(acceleration);
		
		acceleration = new RemoteParameterAcceleration("Y","acceleration in x direction");
		acceleration.setValue(rate.y);
		this.add(acceleration);
		
		acceleration = new RemoteParameterAcceleration("Z","acceleration in x direction");
		acceleration.setValue(rate.z);
		this.add(acceleration);
	}
}




@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	
	ByteBuffer dataBuffer;
	RemoteParameterAcceleration acceleration;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();


	
	for (dataIndex = 0; dataIndex<(dataBuffer.capacity()-8);dataIndex++)
	{
		
		acceleration = new RemoteParameterAcceleration("X","acceleration in x direction");
		acceleration.parseFromBuffer(dataBuffer, dataIndex);
		this.add(acceleration);
		
		dataIndex+=RemoteParameterAcceleration.BYTE_SIZE;
		acceleration = new RemoteParameterAcceleration("Y","acceleration in x direction");
		acceleration.parseFromBuffer(dataBuffer, dataIndex);
		this.add(acceleration);
		
		dataIndex+=RemoteParameterAcceleration.BYTE_SIZE;
		acceleration = new RemoteParameterAcceleration("Z","acceleration in x direction");
		acceleration.parseFromBuffer(dataBuffer, dataIndex);
		this.add(acceleration);
	
	}

}





public FloatVector3D getAccelerations(int index)
{
	FloatVector3D acceleration;
	
	index *=3;
	
	
	acceleration = new FloatVector3D((( RemoteParameterAcceleration) this.get(index++)).getValue(),
							(( RemoteParameterAcceleration) this.get(index++)).getValue(),
							(( RemoteParameterAcceleration) this.get(index++)).getValue());
	
	return (acceleration);
}



public static Stream_accelerations getCommand(int command)
{
	Stream_accelerations cmd;
	cmd = new Stream_accelerations(command);
	
	return(cmd);
}

public static Stream_accelerations getCommand(int command, FloatVector3D...rates)
{
	Stream_accelerations cmd;
	cmd = Stream_accelerations.getCommand(command);
	cmd.setData(rates);
	
	return(cmd);
}



}
