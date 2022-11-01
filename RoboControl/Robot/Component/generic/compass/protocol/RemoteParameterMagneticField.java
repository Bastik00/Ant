package de.hska.lat.robot.component.generic.compass.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterMagneticField extends RemoteParameter<RemoteParameterMagneticField>
{

	
	protected static final int BYTE_SIZE = 2;
	protected static final float FRACTION_FACTOR = 0.075f;
	protected float value;
	
	public RemoteParameterMagneticField(String name, String description)
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
		return(RemoteParameterMagneticField.BYTE_SIZE);
	}



	@Override
	public void putData(ByteBuffer data)
	{
		int dataValue;
		
		
		dataValue = (int) (this.value / RemoteParameterMagneticField.FRACTION_FACTOR); 
		

		data.putShort((short)dataValue);

	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		

		
		short value;
		
		
		value = data.getShort(index);
		
		System.out.println("v : "+value);
		
		this.value = ((float)value ) * RemoteParameterMagneticField.FRACTION_FACTOR;
		
		System.out.println("ut : "+this.value);
		
		return(RemoteParameterMagneticField.BYTE_SIZE);
	}

	
	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= this.name+" = "+this.value+" µT";
		}
		else
		{
			returnString+= +this.value;
		}	
		return(returnString);
	}

	
}
