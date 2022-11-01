package de.hska.lat.robot.component.generic.locator.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterLocation extends RemoteParameter<RemoteParameterLocation>
{

	protected static final int BYTE_SIZE = 2;
	protected float value;
	
	
	
public RemoteParameterLocation(String name, String description)
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
	return(RemoteParameterLocation.BYTE_SIZE);
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
	
	return(RemoteParameterLocation.BYTE_SIZE);
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
	else
	{
		returnString+= this.value;
	}	
	
	return(returnString);
}


}
