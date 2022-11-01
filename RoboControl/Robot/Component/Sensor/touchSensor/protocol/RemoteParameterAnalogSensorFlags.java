package de.hska.lat.robot.component.touchSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterAnalogSensorFlags extends RemoteParameter<RemoteParameterAnalogSensorFlags>
{

	protected static final int BYTE_SIZE = 1;
	protected int value;
	
	private boolean inverse;
	
	private static final int FLAG_INVERS	= 0;
	
	public RemoteParameterAnalogSensorFlags(String name, String description)
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterAnalogSensorFlags.BYTE_SIZE);
	}

	
	public void setInverse(boolean status)
	{
		this.inverse = status;	
	}
	
	
	public boolean isInverse()
	{
		return(this.inverse);
	}
	
	
	@Override
	public void putData(ByteBuffer data)
	{
		byte flags;
		
		flags = 0;
		
		if (this.inverse)
		{
			flags |= (1<<FLAG_INVERS);	
		}
		  
		
		
		data.put(flags);
	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		
		byte flags;
		
		
		flags = data.get(index);
		
		if ((flags & (1<<FLAG_INVERS))>0)
		{
			this.inverse = true;
		}
		
		
		return(RemoteParameterAnalogSensorFlags.BYTE_SIZE);
	}
	
	
}
