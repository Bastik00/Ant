package de.hska.lat.robot.component.generic.heading.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterAngle extends RemoteParameter<RemoteParameterAngle>
{

	protected static final int BYTE_SIZE = 3;
	protected static final int FRACTION_FACTOR = 1000;
	protected float value;
	
	
	
public RemoteParameterAngle(String name, String description)
{
	super(name, description);
}


public void setValue(float value)
{
		this.value = value;
}


public float getValue()
{
	return(this.value);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterAngle.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	int dataValue;
	
	
	dataValue = (int) (this.value * RemoteParameterAngle.FRACTION_FACTOR); 
	

	data.put((byte)((dataValue>>>16)&0xff));

	data.putChar((char)(dataValue&0xffff));

}



@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	int dataValue;
	
	byte high;
	char low;
	
	
	high = data.get(index++);
	low = data.getChar(index);
	
	
	dataValue = high <<16;
	dataValue += low;
	
	if (high<0)
	{
		dataValue |=0xff000000;
	}
	
	this.value = ((float)dataValue ) / RemoteParameterAngle.FRACTION_FACTOR;
	
	return(RemoteParameterAngle.BYTE_SIZE);
}


}
