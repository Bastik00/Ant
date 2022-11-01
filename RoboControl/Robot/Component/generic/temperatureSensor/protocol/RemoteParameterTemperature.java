package de.hska.lat.robot.component.generic.temperatureSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterTemperature extends RemoteParameter<RemoteParameterTemperature>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterTemperature(String name, String description)
{
	super(name, description);
}


public void setTemperature(float value)
{
		this.value = value;
}


public float getTemperature()
{
	return(this.value);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterTemperature.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	
	
	dataValue = this.value;
	dataValue *=100;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	this.value = data.getChar(index);
	this.value /=100;
	return(RemoteParameterTemperature.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"="+this.value+"K";
	}
	else
	{
		returnString+= this.value;	
	}
	
		
	return(returnString);
}




}
