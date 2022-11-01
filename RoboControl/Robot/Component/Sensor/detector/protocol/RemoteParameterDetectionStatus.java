package de.hska.lat.robot.component.detector.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterDetectionStatus extends RemoteParameter<RemoteParameterDetectionStatus>
{


	protected boolean [] statusArray = new boolean[0];
	
public RemoteParameterDetectionStatus()
{
	super("contacts", "contacts");
}


public void setValues(boolean [] status)
{
	int index;
	
	statusArray = new boolean[status.length];
	
	for (index =0;index<statusArray.length;index++)
	{
		statusArray[index] = status[index];
	}
}






public boolean getValue(int index)
{
	if (index <statusArray.length)
	{
		return (this.statusArray[index]);
	}

	return(false);
}



@Override
public int getBufferSize()
{
	int size;
	
	size = ((statusArray.length+7)>>3);
	
	return(size);
}

@Override
public void putData(ByteBuffer data)
{
	int bitIndex=0;
	int index=0;
	byte dataByte=0;
	
	while (index<statusArray.length)
	{
		if (statusArray[index]==true)
		{
			dataByte |=(byte) (1<<bitIndex);
		}
		
		bitIndex++;
		if (bitIndex>7)
		{
			data.put(dataByte);
			bitIndex=0;
			dataByte=0;
		}
		
		index ++;
	}
	if (bitIndex!=0)
	{
		data.put(dataByte);
	}
	
	

}



@Override
public int parseFromBuffer(ByteBuffer data, int index)
{
	
	int byteSize;
	int byteIndex;
	int bitIndex;
	int statusIndex = 0;
	int contactData;
	
	byteSize = data.capacity()-index;
	
	this.statusArray = new boolean [byteSize*8];
	
	
	for(byteIndex=0;byteIndex<byteSize; byteIndex++)
	{
		contactData = data.get(index+byteIndex);
		
		for(bitIndex=0;bitIndex<8;bitIndex++)
		{

			if ((contactData & (1<<bitIndex))>0)
			{
				this.statusArray[statusIndex]=true;
			}
			else
			{
				this.statusArray[statusIndex]=false;
			}
			
			statusIndex++;
		}
	}
	

	return(this.getBufferSize());
}


}
