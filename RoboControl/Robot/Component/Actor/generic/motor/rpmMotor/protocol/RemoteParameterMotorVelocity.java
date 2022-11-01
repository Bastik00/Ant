package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterMotorVelocity extends RemoteParameter<RemoteParameterMotorVelocity>
{



	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterMotorVelocity(String description)
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
	return(RemoteParameterMotorVelocity.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	

	dataValue = this.value;
	dataValue *=256;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	this.value = data.getShort(index);
	System.out.println("rpm : "+this.value );
	this.value /=256;
	
	return(RemoteParameterMotorVelocity.BYTE_SIZE);
}


}
