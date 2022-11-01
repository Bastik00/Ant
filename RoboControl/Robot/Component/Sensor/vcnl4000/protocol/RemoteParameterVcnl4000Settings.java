package de.hska.lat.robot.component.sensor.vcnl4000.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000AveragingModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000FrequencyModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000IrCurrent;


public class RemoteParameterVcnl4000Settings extends RemoteParameter<RemoteParameterVcnl4000Settings>
{

	protected static final int BYTE_SIZE = 2;
	
	
	protected Vcnl4000IrCurrent			irCurrent;
	protected Vcnl4000AveragingModes	averagingMode;
	protected Vcnl4000FrequencyModes 	proximityFrequency;
	
	
	protected boolean 					autoConversion;
	protected boolean 					autoCompensation;
	
	
	
	private static final int 	IC_CURRENT_INDEX		= 0;
	private static final int	IC_CURRENT_MASK			= 0x3f;
	
	private static final int 	FREQUENCY_MODE_INDEX	= 6;
	private static final int 	FREQUENCY_MODE_MASK		= 0x2;
	
	private static final int 	AVERAGING_MODE_INDEX	= 8;
	private static final int 	AVERAGING_MODE_MASK		= 0x7;
	
	private static final int 	CONVERSION_MODE_INDEX	= 11;
	private static final int 	AUTO_OFFSET_INDEX		= 12; 
	
	
	
	
	
	private static final String name = "Vcnl4000 parameters";
	private static final String description = "Vcnl4000 parameters";	
	
public RemoteParameterVcnl4000Settings()
{
	super(RemoteParameterVcnl4000Settings.name, RemoteParameterVcnl4000Settings.description);
}



public Vcnl4000IrCurrent getIrCurrent()
{
	return(this.irCurrent);
}


public void setIrCurrent(Vcnl4000IrCurrent irCurrent)
{
	this.irCurrent = irCurrent;
}




public Vcnl4000AveragingModes getAveragingMode()
{
	return(this.averagingMode);
}


public void setAveragingMode(Vcnl4000AveragingModes averagingMode)
{
	this.averagingMode = averagingMode;
}





public Vcnl4000FrequencyModes getProximityFrequency()
{
	return(this.proximityFrequency);
}



public void setProximityFrequency(Vcnl4000FrequencyModes proximityFrequency)
{
	this.proximityFrequency = proximityFrequency;
}




public boolean getAutoConversion()
{
	return(this.autoConversion);
}





public void setAutoConversion(boolean autoConversion)
{
	this.autoConversion = autoConversion;
}



public boolean setAutoCompensation()
{
	return(this.autoCompensation);
}





public void setAutoCompensation(boolean autoCompensation)
{
	this.autoCompensation = autoCompensation;
}






@Override
public int getBufferSize()
{
	return(RemoteParameterVcnl4000Settings.BYTE_SIZE);
}


@Override
public void putData(ByteBuffer data)
{
	int dataValue = 0;
	
	dataValue=(this.irCurrent.getNumber()<<RemoteParameterVcnl4000Settings.IC_CURRENT_INDEX);
	dataValue|=this.proximityFrequency.getNumber()<<RemoteParameterVcnl4000Settings.FREQUENCY_MODE_INDEX;
	dataValue|=(this.averagingMode.getNumber()<<RemoteParameterVcnl4000Settings.AVERAGING_MODE_INDEX);
	if (this.autoConversion)
	{
		dataValue|=(1<<RemoteParameterVcnl4000Settings.CONVERSION_MODE_INDEX);
	}
	if (this.autoCompensation)
	{
		dataValue|=(1<<RemoteParameterVcnl4000Settings.AUTO_OFFSET_INDEX);
	}

	data.putChar((char)(dataValue&0xffff));

}



@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	int dataValue;

	dataValue = data.getChar(index);

	
	this.irCurrent = Vcnl4000IrCurrent.get((dataValue >>RemoteParameterVcnl4000Settings.IC_CURRENT_INDEX) & 
					RemoteParameterVcnl4000Settings.IC_CURRENT_MASK);
	
	this.proximityFrequency = Vcnl4000FrequencyModes.get((dataValue >>RemoteParameterVcnl4000Settings.FREQUENCY_MODE_INDEX) & 
			RemoteParameterVcnl4000Settings.FREQUENCY_MODE_MASK);
	
	
	this.averagingMode = Vcnl4000AveragingModes.get((dataValue >>RemoteParameterVcnl4000Settings.AVERAGING_MODE_INDEX) & 
			RemoteParameterVcnl4000Settings.AVERAGING_MODE_MASK);
	
	
	
	if ((dataValue & (1<<RemoteParameterVcnl4000Settings.CONVERSION_MODE_INDEX))>0)
	{
		this.autoConversion=true;
	}
	else
	{
		this.autoConversion= false;
	}
	
	
	if ((dataValue & (1<<RemoteParameterVcnl4000Settings.AUTO_OFFSET_INDEX))>0)
	{
		this.autoCompensation=true;
	}
	else
	{
		this.autoCompensation= false;
	}
	


	
	
//	this.dlpf = Mpu9150Dlpf.get((dataValue>>RemoteParameterVcnl4000Settings.DLPF_INDEX) & 0x7);
	
	
	
	
	return(RemoteParameterVcnl4000Settings.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= RemoteParameterVcnl4000Settings.name+" ";
		
		returnString+= "IR LED current="+ this.irCurrent;
		returnString+= ", frequency mode="+ this.proximityFrequency;
		returnString+= ", averaging mode="+ this.averagingMode;
		returnString+= ", conversion mode="+ this.autoConversion;
		returnString+= ", auto compensation="+ this.autoCompensation;
		

		
	}
	else
	{
		returnString+= this.irCurrent+", ";
		returnString+= this.proximityFrequency+", ";
		returnString+= this.averagingMode+", ";
		returnString+= this.autoConversion+", ";
		returnString+= this.autoCompensation+", ";
		
	}
		
	return(returnString);
}



public boolean getAutoCopensation()
{
	return (this.autoCompensation);
}






}
