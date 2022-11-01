package de.hska.lat.robot.component.generic.spectralAnalyzer.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterSpectralAnalyserGain extends RemoteParameter<RemoteParameterSpectralAnalyserGain>
{

	protected static final int BYTE_SIZE = 2;
	protected float gain;
	
	private static final float CONVERSION_FRACTION = 10000.0f;
	
public RemoteParameterSpectralAnalyserGain(String name, String description)
{
	super(name, description);
	// TODO Auto-generated constructor stub
}


public void setGain(float gain)
{
	this.gain = gain;
}


public float getGain()
{
	return(this.gain);

}







@Override
public int getBufferSize()
{
	return(RemoteParameterSpectralAnalyserGain.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	int value;
	
	value = (int)(this.gain * RemoteParameterSpectralAnalyserGain.CONVERSION_FRACTION );
	data.putChar((char)value);

}


@Override
public int parseFromBuffer(ByteBuffer dataBuffer, int index)
{
	float value;

	

	value = dataBuffer.getChar(index);
	
	this.gain = value / RemoteParameterSpectralAnalyserGain.CONVERSION_FRACTION;

	return(RemoteParameterSpectralAnalyserGain.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"=";
		
		returnString+= this.gain+"";
	}
	else
	{
		returnString+= this.gain;
	}	
	
	return(returnString);
}
}
