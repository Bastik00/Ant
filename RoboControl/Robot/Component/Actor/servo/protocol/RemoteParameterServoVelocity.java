package de.hska.lat.robot.component.actor.servo.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.math.Radiant;

public class RemoteParameterServoVelocity extends RemoteParameter<RemoteParameterServoVelocity>
{

	protected static final int BYTE_SIZE = 2;
	protected float velocity;
	
	
	
public RemoteParameterServoVelocity(String name, String description)
{
	super(name, description);
}


public void setVelocity(float velocity)
{
	this.velocity = velocity;
}


public float getVelocity()
{
	return(this.velocity);

}




@Override
public int getBufferSize()
{
	return(RemoteParameterServoVelocity.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	short value;
	
	value = (short) (this.velocity*314.16f); 
	
	data.putShort(value);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	float position;
	
	position = data.getShort(index);
	position/=314.16f;

	this.velocity = position;
	return(RemoteParameterServoVelocity.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"=";
		
		returnString+= Radiant.convertRadiantToDegree(this.velocity)+"°";
	}
	else
	{
		returnString+= this.velocity;
	}	
	
	return(returnString);
}


}
