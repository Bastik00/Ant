package de.hska.lat.robot.component.sensor.tmp006.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006Address;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006ConversionRate;


public class RemoteParameterTmp006Parameters extends RemoteParameter<RemoteParameterTmp006Parameters>
{

	protected static final int BYTE_SIZE = 1;
	
	
	private boolean 					on;
	private Tmp006ConversionRate 		conversionRate;
	private Tmp006Address 				address;
	
	private static final int CONVERSION_RATE_INDEX	= 0;
	private static final int I2C_ADDRESS_INDEX		= 3;
		
	private static final int ON_INDEX				= 5;

	
	private static final String name = "tmp006Parameters";
	private static final String description = "parameters for a tmp006 thermophyle sensor";	
	
	
	public RemoteParameterTmp006Parameters()
	{
		super(name, description);
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterTmp006Parameters.BYTE_SIZE);
	}

	public void setConversionRate(Tmp006ConversionRate 	conversionRate)
	{
		this.conversionRate = conversionRate;	
	}
	
	
	public Tmp006ConversionRate getConversionRate()
	{
		return(this.conversionRate);
	}
	
	
	
	
	public void setAddress(Tmp006Address address)
	{
		this.address = address;
		
	}


	public Tmp006Address getAddress()
	{
		// TODO Auto-generated method stub
		return (this.address);
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
		
		
		this.conversionRate = Tmp006ConversionRate.get(parameters & 0x3) ;
		
		this.address = Tmp006Address.get((parameters>> I2C_ADDRESS_INDEX ) & 0x3);
		
		//this.period = parameters &0x3;
		
		
		if ((parameters & (1<<ON_INDEX))>0)
		{
			this.on = true;
		}		
		
		
		return(RemoteParameterTmp006Parameters.BYTE_SIZE);
	}



	
	@Override
	public String getAsString(boolean description)
	{
		String returnString ="";
		
		if (description)
		{
			returnString+= RemoteParameterTmp006Parameters.name+" ";
			
			returnString+= "conversion rate="+ this.conversionRate;
			returnString+= ", I2C address="+ this.address;
		}
		else
		{
			returnString+= this.conversionRate+", ";
			returnString+= this.address+", ";
		}
			
		return(returnString);
	}



	
}
