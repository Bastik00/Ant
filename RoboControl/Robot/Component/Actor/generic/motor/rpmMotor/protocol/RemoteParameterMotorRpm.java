package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterMotorRpm extends RemoteParameter<RemoteParameterMotorRpm>
{



	protected static final int BYTE_SIZE = 4;
	protected static final int DISCRETIZATION_FACTOR = 1<<16;
							   
	protected float value;
	
	
	
public RemoteParameterMotorRpm(String description)
{
	super("rpm", description);
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
	return(RemoteParameterMotorRpm.BYTE_SIZE);
}


@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	

	dataValue = this.value;
	dataValue *= RemoteParameterMotorRpm.DISCRETIZATION_FACTOR;
	
	data.putInt((int)dataValue);
}




@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	this.value = data.getInt(index);
	this.value /= RemoteParameterMotorRpm.DISCRETIZATION_FACTOR;
	
	return(RemoteParameterMotorRpm.BYTE_SIZE);
}




}
