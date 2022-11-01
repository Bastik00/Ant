package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterMotorRps extends RemoteParameter<RemoteParameterMotorRps>
{



	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterMotorRps(String name, String description)
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
	return(RemoteParameterMotorRps.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	

	dataValue = this.value;
	dataValue *=360;
	
	data.putChar((char)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	this.value = data.getShort(index);
	System.out.println("rpm : "+this.value );
	this.value /=360;
	
	return(RemoteParameterMotorRps.BYTE_SIZE);
}


}
