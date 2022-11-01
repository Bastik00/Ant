package de.hska.lat.robot.component.generic.motion.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterDistance extends RemoteParameter<RemoteParameterDistance>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterDistance(String name, String description)
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
	return(RemoteParameterDistance.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	data.putShort((short)this.value);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	this.value = data.getShort(index);
	
	return(RemoteParameterDistance.BYTE_SIZE);
}


@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"=";
		returnString+= this.value+"mm";
	}
	returnString+= +this.value;
		
	return(returnString);
}



}
