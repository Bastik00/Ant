package de.hska.lat.robot.component.generic.colorSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.color.RgbColor;
import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterRgbColor extends RemoteParameter<RemoteParameterRgbColor>
{

	protected static final int BYTE_SIZE = 6;

	protected RgbColor color;
	
	
public RemoteParameterRgbColor(String name, String description)
{
	super(name, description);
}


public void setColor(RgbColor color)
{
	this.color = color;
}


public RgbColor getColor()
{
	return(this.color);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterRgbColor.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	data.getChar((short)(this.color.red * 65535));
	data.getChar((byte)(this.color.green * 65535));
	data.getChar((byte)(this.color.blue * 65535));
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	float red;
	float green;
	float blue;
	
	red = (int) ((data.getChar(index) & 0xffff));
	red /= 65535; 

	index+=2;
	
	green = (int) ((data.getChar(index) & 0xffff));
	green /= 65535; 
	
	index+=2;
	
	blue = (int) ((data.getChar(index) & 0xffff));
	blue /= 65535; 
	

	
	this.color = new RgbColor(red, green, blue);
	

	return(RemoteParameterRgbColor.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name;

		returnString+= " red: "+this.color.red;
		returnString+= ", green: "+this.color.green;
		returnString+= ", blue: "+this.color.blue;
	}
	else
	{
		returnString+= this.color.red+", ";
		returnString+= this.color.green+", ";
		returnString+= this.color.blue;	
	}

	
	return(returnString);
}

}
