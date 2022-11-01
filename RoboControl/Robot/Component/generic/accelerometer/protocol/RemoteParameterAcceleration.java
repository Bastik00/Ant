package de.hska.lat.robot.component.generic.accelerometer.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterAcceleration extends RemoteParameter<RemoteParameterAcceleration>
{

	protected static final int BYTE_SIZE = 3;
	protected static final int FRACTION_FACTOR = 1<<17;
	protected float value;
	
	
	
public RemoteParameterAcceleration(String name, String description)
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
	return(RemoteParameterAcceleration.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	int dataValue;
	
	
	dataValue = (int) (this.value * RemoteParameterAcceleration.FRACTION_FACTOR); 
	

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
	dataValue |= low;
	
	if (high<0)
	{
		dataValue |=0xff000000;
	}
	
	
	
	this.value = ((float) dataValue);
	this.value  /= RemoteParameterAcceleration.FRACTION_FACTOR;
	
	return(RemoteParameterAcceleration.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"="+this.value+"g";
	}
	else
	{
		returnString+= +this.value;		
	}

		
	return(returnString);
}

}
