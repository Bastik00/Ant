package de.hska.lat.robot.component.generic.colorSensor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.color.HsvColor;
import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterHsvColor extends RemoteParameter<RemoteParameterHsvColor>
{

	protected static final int BYTE_SIZE = 6;

	protected HsvColor color;
	
	
public RemoteParameterHsvColor(String name, String description)
{
	super(name, description);
}


public void setColor(HsvColor color)
{
	this.color = color;
}


public HsvColor getColor()
{
	return(this.color);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterHsvColor.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	//data.putShort((short)(this.color.h * 16535 / (2 * Math.PI)));
	data.putShort((short)(this.color.h * 4096));
	data.putChar((char)(this.color.s * 65535));
	data.putChar((char)(this.color.v * 65535));
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	float h;
	float s;
	float v;
	

	h = (int) (data.getShort(index) & 0xffff);
	//h = (float) (h / 16535 * 2 * Math.PI); 
	h /= 4096;
	index+=2;
	
	s = (int) (data.getShort(index) & 0xffff);
	s /= 65535; 
	
	index+=2;
	
	v =(int) (data.getShort(index++) & 0xffff);
	v /= 65535;
	
	index+=2;

	
	this.color = new HsvColor(h, s, v);
	

	return(RemoteParameterHsvColor.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= this.name;

		returnString+= " h: "+this.color.h;
		returnString+= ", s: "+this.color.s;
		returnString+= ", v: "+this.color.v;
	}
	else
	{
		returnString+= this.color.h+", ";
		returnString+= this.color.s+", ";
		returnString+= this.color.v;	
	}

	
	return(returnString);
}

}
