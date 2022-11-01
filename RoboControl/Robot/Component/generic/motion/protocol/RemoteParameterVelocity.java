package de.hska.lat.robot.component.generic.motion.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterVelocity extends RemoteParameter<RemoteParameterVelocity>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterVelocity(String name, String description)
{
	super(name, description);
}


public void setValue(float value)
{
	if (value > 1)
	{
		this.value = 1;
	}
	else if (this.value >1)
	{
		value = -1;
	}
	else
	{
		this.value = value;
	}
		
}


public float getValue()
{
	return(this.value);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterVelocity.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	
	
	dataValue = this.value;
	dataValue *=0x7fff;
	
	data.putShort((short)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	//TODO correct this to right values !!!!
	
	this.value = data.getShort(index);
	this.value /=0x7fff;
	
	return(RemoteParameterVelocity.BYTE_SIZE);
}


}
