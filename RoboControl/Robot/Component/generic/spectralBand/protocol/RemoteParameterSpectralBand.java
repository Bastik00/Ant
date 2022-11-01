package de.hska.lat.robot.component.generic.spectralBand.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.generic.spectralBand.protocol.RemoteParameterSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.RemoteParameterSpectralBand;

public class RemoteParameterSpectralBand extends RemoteParameter<RemoteParameterSpectralBand>
{

	protected static final int BYTE_SIZE = 1;
	protected float value;
	
	
	
public RemoteParameterSpectralBand(String name, String description)
{
	super(name, description);
}


public void setSpectralband(float value)
{
		this.value = value;
}


public float getSpectralband()
{
	return(this.value);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterSpectralBand.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	float dataValue;
	
	
	dataValue = this.value;
	dataValue *=255;
	
	data.put((byte)dataValue);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{

	int a = data.get(index);
	a&=0xff;
	
	System.out.println(a);
	this.value = a;
	this.value /=255;
	return(RemoteParameterSpectralBand.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"="+(((float)this.value));
	}
	else
	{
		returnString+= this.value;	
	}
	
		
	return(returnString);
}




}
