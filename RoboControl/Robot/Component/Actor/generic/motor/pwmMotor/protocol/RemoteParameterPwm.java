package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterPwm extends RemoteParameter<RemoteParameterPwm>
{



	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterPwm(String description)
{
	super("pwm", description);
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
	return(RemoteParameterPwm.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	

	dataValue = this.value;
	dataValue *=32768.0f;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	this.value = data.getShort(index);
	this.value /=32768.0f;
	
	return(RemoteParameterPwm.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+" = "+this.value+" pwm";
	}
	else
	{
		returnString+= +this.value;
	}	
	return(returnString);
}

}
