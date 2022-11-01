package de.hska.lat.comm.remote.parameter;

import java.nio.ByteBuffer;

public class RemoteParameterFloat extends RemoteParameter<RemoteParameterFloat>
{

	protected static final int BYTE_SIZE = 4;
	protected float value;

	public RemoteParameterFloat(String name, String description)
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void setValue(float value)
	{
		this.value = value;
	}

	@Override
	public int getBufferSize()
	{
		return (RemoteParameterFloat.BYTE_SIZE);
	}

	@Override
	public void putData(ByteBuffer data)
	{
		data.putFloat(this.value);
	}

	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		this.value = data.getFloat(index);
		return (RemoteParameterFloat.BYTE_SIZE);
	}

	public float getValue()
	{
		return this.value;
	}

	
	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= this.name+"=";
		}
		returnString+= this.value;
			
		return(returnString);
	}

	
}
