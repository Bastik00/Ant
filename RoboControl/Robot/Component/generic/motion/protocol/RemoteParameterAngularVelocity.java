package de.hska.lat.robot.component.generic.motion.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterAngularVelocity extends RemoteParameter<RemoteParameterAngularVelocity>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterAngularVelocity(String name, String description)
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
	return(RemoteParameterAngularVelocity.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	
	
	
	//TODO correct this to right values !!!!
	dataValue = this.value;
	dataValue *=1000;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	//TODO correct this to right values !!!!
	
	this.value = data.getChar(index);
	this.value /=1000;
	
	return(RemoteParameterAngularVelocity.BYTE_SIZE);
}


}
