package de.hska.lat.comm.remote.parameter;

import java.nio.ByteBuffer;

public class RemoteParameterBooelan extends RemoteParameter<RemoteParameterBooelan>
{

	protected static final int BYTE_SIZE = 1;
	protected boolean value;
	
	
	
public RemoteParameterBooelan(String name, String description)
{
	super(name, description);
}


public void setValue(boolean value)
{

	this.value = value;


}

public boolean getValue()
{
	return(this.value);

}

@Override
public String toString()
{
	return(this.name+": "+this.value);
}



@Override
public int getBufferSize()
{
	return(RemoteParameterBooelan.BYTE_SIZE);
}

@Override
public void putData(ByteBuffer data)
{
	if (this.value==false)
	{
		data.put((byte)0);
	}
	else
	{
		data.put((byte)0xff);
	}
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	int value;
	value = data.get(index);

	if (value==0)
	{
		this.value = false;	
	}
	else
	{
		this.value = true;
	}
	
	return(RemoteParameterBooelan.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name+"=";
	}
	returnString+= this.value;
		
	return(returnString);
}


}
