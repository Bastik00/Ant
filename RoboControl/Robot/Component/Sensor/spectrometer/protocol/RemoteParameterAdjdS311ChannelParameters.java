package de.hska.lat.robot.component.sensor.spectrometer.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311ChannelSetings;



public class RemoteParameterAdjdS311ChannelParameters extends RemoteParameter<RemoteParameterAdjdS311ChannelParameters>
{

	protected static final int BYTE_SIZE = 3;
	
	
	protected AdjdS311ChannelSetings settings = new AdjdS311ChannelSetings();

	

	public RemoteParameterAdjdS311ChannelParameters(String name, String  description)
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterAdjdS311ChannelParameters.BYTE_SIZE);
	}

	public void setSettings(AdjdS311ChannelSetings 	settings)
	{
		this.settings = settings;	
	}
	
	
	public AdjdS311ChannelSetings getSettings()
	{
		return(this.settings);
	}
	
	
	
	
	
	@Override
	public void putData(ByteBuffer data)
	{
				
		data.putChar((char)	(this.settings.getCapacitators() | this.settings.getIntegrationTime()<<4));
		data.put((byte) this.settings.getOffset());
	
	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		
		int parameters;
		this.settings.setOffset(data.get());
		
		parameters = data.getChar(index);
	
		this.settings.setCapacitators(parameters & 0xf);
		this.settings.setIntegrationTime(parameters>>4);
		this.settings.setOffset(data.get());
		
		
		return(RemoteParameterAdjdS311ChannelParameters.BYTE_SIZE);
	}



	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= this.name+" :";
			returnString+= " capacitators = "+ this.settings.getCapacitators();
			returnString+= ", integration time = "+ this.settings.getIntegrationTime();
			returnString+= ", offset = "+ this.settings.getOffset();
		}
		else
		{
			returnString+= ",  "+ this.settings.getCapacitators();
			returnString+= ", "+ this.settings.getIntegrationTime();
			returnString+= ", "+ this.settings.getOffset();
		
		}
			
		return(returnString);
	}



	
}
