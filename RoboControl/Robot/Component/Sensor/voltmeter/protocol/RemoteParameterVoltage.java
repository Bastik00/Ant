package de.hska.lat.robot.component.voltmeter.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;


public class RemoteParameterVoltage extends RemoteParameter<RemoteParameterVoltage>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterVoltage(String name, String description)
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
	return(RemoteParameterVoltage.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	
	//TODO correct this to right values !!!!
	dataValue = this.value;
	dataValue *=100;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	this.value = data.getChar(index);
	this.value /=100;
	return(RemoteParameterVoltage.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"=";
		
		returnString+= this.value+"V";
	}
	else
	{
		returnString+= this.value;
	}	
	
	return(returnString);
}

}
