package de.hska.lat.robot.component.generic.gyroscope.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;



public class Stream_angularRates extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	


	
	protected static final String name = "angularRates";
	protected static final String description = "angular rates from a gyroscope sensor";
	
	
	
	
	
	
public Stream_angularRates()
{
}


public Stream_angularRates(int command)
{
	this();
	this.setId(command);
}


public String getName()
{
	return(Stream_angularRates.name);
}


public String getDescription()
{
	return(Stream_angularRates.description);
}



public void setData(AngularVector3D...rates)
{
	
	for (AngularVector3D rate : rates)
	{
		RemoteParameterAngularRate angularRate;
		
		angularRate = new RemoteParameterAngularRate("X","acceleration in x direction");
		angularRate.setValue(rate.x);
		this.add(angularRate);
		
		angularRate = new RemoteParameterAngularRate("Y","acceleration in x direction");
		angularRate.setValue(rate.y);
		this.add(angularRate);
		
		angularRate = new RemoteParameterAngularRate("Z","acceleration in x direction");
		angularRate.setValue(rate.z);
		this.add(angularRate);
	}
}



@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	
	ByteBuffer dataBuffer;
	RemoteParameterAngularRate angularRate;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();


	
	for (dataIndex = 0; dataIndex<(dataBuffer.capacity()-8);dataIndex++)
	{
		
		angularRate = new RemoteParameterAngularRate("X","acceleration in x direction");
		angularRate.parseFromBuffer(dataBuffer, dataIndex);
		this.add(angularRate);
		
		dataIndex+=RemoteParameterAngularRate.BYTE_SIZE;
		angularRate = new RemoteParameterAngularRate("Y","acceleration in x direction");
		angularRate.parseFromBuffer(dataBuffer, dataIndex);
		this.add(angularRate);
		
		dataIndex+=RemoteParameterAngularRate.BYTE_SIZE;
		angularRate = new RemoteParameterAngularRate("Z","acceleration in x direction");
		angularRate.parseFromBuffer(dataBuffer, dataIndex);
		this.add(angularRate);
	
	}

}


public AngularVector3D getAngularRates(int index)
{
	AngularVector3D angularRates;
	
	index *=3;
	
	
	angularRates = new AngularVector3D((( RemoteParameterAngularRate) this.get(index)).getValue(),
							(( RemoteParameterAngularRate) this.get(index+1)).getValue(),
							(( RemoteParameterAngularRate) this.get(index+2)).getValue());
	
	return (angularRates);
}




public static Stream_angularRates getCommand(int command)
{
	Stream_angularRates cmd;
	cmd = new Stream_angularRates(command);
	
	return(cmd);
}



public static Stream_angularRates getCommand(int command,AngularVector3D...angularRates)
{
	Stream_angularRates cmd;
	cmd = Stream_angularRates.getCommand(command);
	cmd.setData(angularRates);
	
	return(cmd);
}



}
