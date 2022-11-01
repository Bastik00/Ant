package de.hska.lat.robot.component.sensor.tmp102.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102Address;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102ConversionRate;

public class RemoteParameterTmp102Parameters extends RemoteParameter<RemoteParameterTmp102Parameters>
{

	protected static final int BYTE_SIZE = 1;
	
	
	private boolean 					extended;
	private boolean 					on;
	private Tmp102ConversionRate 		conversionRate;
	private Tmp102Address 				address;
	
	private static final int CONVERSION_RATE_INDEX	= 0;
	private static final int I2C_ADDRESS_INDEX		= 2;
	private static final int EXTENDED_INDEX			= 4;
	
	private static final int ON_INDEX				= 5;

	
	private static final String name = "tmp102Parameters";
	private static final String description = "parameters for a tmp102 sensor";	
	
	
	public RemoteParameterTmp102Parameters()
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterTmp102Parameters.BYTE_SIZE);
	}

	public void setConversionRate(Tmp102ConversionRate 	conversionRate)
	{
		this.conversionRate = conversionRate;	
	}
	
	
	public Tmp102ConversionRate getConversionRate()
	{
		return(this.conversionRate);
	}
	
	
	
	
	public void setAddress(Tmp102Address address)
	{
		this.address = address;
		
	}


	public Tmp102Address getAddress()
	{
		// TODO Auto-generated method stub
		return (this.address);
	}


	
	
	
	
	public void setExtended(boolean status)
	{
		this.extended = status;
	}

	public boolean isExtended()
	{
		return(this.extended);
	}


	
	public void setOn(boolean status)
	{
		this.on = status;	
	}
	
	
	public boolean isOn()
	{
		return(this.on);
	}
	
	
	
	
	
	@Override
	public void putData(ByteBuffer data)
	{
		byte parameters;
		
		parameters = 0;
		
		parameters |= (this.conversionRate.getNumber()<<CONVERSION_RATE_INDEX); 
		
		parameters |= (this.address.getNumber()<<I2C_ADDRESS_INDEX);
		
		if (this.extended)
		{
			parameters |= (1<<EXTENDED_INDEX);	
		}
		  
		if (this.on)
		{
			parameters |= (1<<ON_INDEX);	
		}		
		
		data.put(parameters);
	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		
		byte parameters;
		
		
		parameters = data.get(index);
		
		
		this.conversionRate = Tmp102ConversionRate.get(parameters & 0x3) ;
		
		this.address = Tmp102Address.get((parameters>> I2C_ADDRESS_INDEX ) & 0x3);
		
		//this.period = parameters &0x3;
		
		if ((parameters & (1<<EXTENDED_INDEX))>0)
		{
			this.extended = true;
		}
		
		if ((parameters & (1<<ON_INDEX))>0)
		{
			this.on = true;
		}		
		
		
		return(RemoteParameterTmp102Parameters.BYTE_SIZE);
	}



	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= RemoteParameterTmp102Parameters.name+" ";
			
			returnString+= "conversion rate="+ this.conversionRate;
			returnString+= ", I2C address="+ this.address;
			returnString+= ", extended mode="+ this.extended;
		}
		else
		{
			returnString+= this.conversionRate+", ";
			returnString+= this.address+", ";
			returnString+= this.extended;
		}
			
		return(returnString);
	}



	
}
