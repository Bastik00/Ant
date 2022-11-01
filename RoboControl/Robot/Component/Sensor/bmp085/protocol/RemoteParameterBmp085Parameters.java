package de.hska.lat.robot.component.sensor.bmp085.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.bmp085.Bmp085Resolution;


public class RemoteParameterBmp085Parameters extends RemoteParameter<RemoteParameterBmp085Parameters>
{

	protected static final int BYTE_SIZE = 1;
	
	
	private Bmp085Resolution 		resolution;

	
	

	
	private static final String name = "tmp102Parameters";
	private static final String description = "parameters for a tmp102 sensor";	
	
	
	public RemoteParameterBmp085Parameters()
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterBmp085Parameters.BYTE_SIZE);
	}

	public void setResolution(Bmp085Resolution 	resolution)
	{
		this.resolution = resolution;	
	}
	
	
	public Bmp085Resolution getResolution()
	{
		return(this.resolution);
	}
	
	
	
	
	
	@Override
	public void putData(ByteBuffer data)
	{
		byte parameters;
		
		parameters = 0;
		
		parameters = (byte)this.resolution.getNumber(); 
		
		data.put(parameters);
	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		
		byte parameters;
		
		
		parameters = data.get(index);
	
		this.resolution = Bmp085Resolution.get(parameters & 0x3) ;
		
		return(RemoteParameterBmp085Parameters.BYTE_SIZE);
	}



	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= RemoteParameterBmp085Parameters.name+" ";
			
			returnString+= "resolution="+ this.resolution;
		}
		else
		{
			returnString+= this.resolution;
		
		}
			
		return(returnString);
	}



	
}
