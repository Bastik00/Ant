package de.hska.lat.robot.component.sensor.mpu6000.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000AccRange;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000ClockSource;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000Dlpf;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000GyroscopeRange;



public class RemoteParameterMpu6000Settings extends RemoteParameter<RemoteParameterMpu6000Settings>
{

	protected static final int BYTE_SIZE = 2;
	
	
	protected Mpu6000ClockSource 		clockSource;
	protected Mpu6000Dlpf 				dlpf;
	protected Mpu6000AccRange	 		accRange;
	protected Mpu6000GyroscopeRange		gyroscopeRange;
	
	
	
	protected static final int 		CLOCK_SOURCE_INDEX			= 0;
	protected static final int 		DLPF_INDEX					= 3;
	protected static final int 		ACC_RANGE_INDEX				= 6;
	protected static final int 		GYROSCOPE_RANGE_INDEX		= 8;
	
	
	private static final String name = "mpu9150 parameters";
	private static final String description = "mpu9150 parameters";	
	
public RemoteParameterMpu6000Settings()
{
	super(RemoteParameterMpu6000Settings.name, RemoteParameterMpu6000Settings.description);
}



public Mpu6000ClockSource getClockSource()
{
	return(this.clockSource);
}


public void setClockSource(Mpu6000ClockSource clockSource)
{
	this.clockSource = clockSource;
}




public Mpu6000Dlpf getDlpfFrequency()
{
	return(this.dlpf);
}


public void setDlpfFrequency(Mpu6000Dlpf dlpf)
{
	this.dlpf = dlpf;
}





public Mpu6000AccRange getAccRange()
{
	return(this.accRange);
}



public void setAccRange(Mpu6000AccRange accRange)
{
	this.accRange = accRange;
}




public Mpu6000GyroscopeRange getGyroscopeRange()
{
	return(this.gyroscopeRange);
}





public void setGyroscopeRange(Mpu6000GyroscopeRange gyroscopeRange)
{
	this.gyroscopeRange = gyroscopeRange;
}






@Override
public int getBufferSize()
{
	return(RemoteParameterMpu6000Settings.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	int dataValue = 0;
	

	dataValue=(this.clockSource.getNumber()<<RemoteParameterMpu6000Settings.CLOCK_SOURCE_INDEX);
	dataValue|=this.accRange.getNumber()<<RemoteParameterMpu6000Settings.ACC_RANGE_INDEX;
	dataValue|=(this.gyroscopeRange.getNumber()<<RemoteParameterMpu6000Settings.GYROSCOPE_RANGE_INDEX);
	dataValue|=(this.dlpf.getNumber()<<RemoteParameterMpu6000Settings.DLPF_INDEX);
	
/*	
	dataValue = (int) (this.value * RemoteParameterAngle.FRACTION_FACTOR); 
	

	data.put((byte)((dataValue>>>16)&0xff));
*/
	data.putChar((char)(dataValue&0xffff));

}



@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	int dataValue;

	dataValue = data.getChar(index);

	
	this.dlpf = Mpu6000Dlpf.get((dataValue>>RemoteParameterMpu6000Settings.DLPF_INDEX) & 0x7);
	
	
	
	
	return(RemoteParameterMpu6000Settings.BYTE_SIZE);
}



@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= RemoteParameterMpu6000Settings.name+" ";
		
		returnString+= "clock source="+ this.clockSource;
		returnString+= ", dlpf="+ this.dlpf;
		returnString+= ", acc range="+ this.accRange;
		returnString+= ", gyroscope range="+ this.gyroscopeRange;
		

		
	}
	else
	{
		returnString+= this.clockSource+", ";
		returnString+= this.dlpf+", ";
		returnString+= this.accRange+", ";
		returnString+= this.gyroscopeRange+", ";
		
	}
		
	return(returnString);
}






}
