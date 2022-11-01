package de.hska.lat.robot.component.analogDetector;


import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.detector.Detector;
import de.hska.lat.robot.value.UnsignedAnalogValue;


public class AnalogDetector <T extends AnalogDetectorChangeNotifier, S extends ComponentSettingsChangeNotifier, P extends ComponentProtocol> 
extends  Detector<T,S,P> 
{
	
	protected UnsignedAnalogValue value;
	
	protected int treshold;
	
	protected boolean status;
	protected boolean inverse;
	
	protected int statisticalMax;
	protected int statisticalMin;
	
	
	
public AnalogDetector(DetectorMetaData metaData,int rawRange, P protocol) 
{
	super(metaData, protocol);
	this.value = new UnsignedAnalogValue(this.getComponentName(),rawRange);
	
	this.statisticalMin=rawRange;
	this.statisticalMax=0;
	
}
	



public int getTreshold()
{
	return(this.treshold);
}


public void setSettings(int treshold, boolean inverse)
{
	boolean changed = false;
//	int index;
	
	if (this.inverse!=inverse)
	{
		changed=true;
		this.inverse=inverse;
	}

	if (this.treshold!=treshold)
	{
		changed=true;
		this.treshold=treshold;
	}
	
	if (changed)
	{	
		this.notifySetupChanged();
	}
	
}

public boolean isInverse()
{
	return(this.inverse);
}


/*
public int getValue()
{
	return(this.value.getValue());
}
*/
public boolean setValue(int value)
{
	int index;
	
	

	
	if (this.value.getValue()!=value)
	{
		this.value.setRawValue(value);
		
		for (index=0;index<this.sensorListener.size();index++)
		{
			this.sensorListener.get(index).valueChanged(this);
		}
		return(true);
		
	}
	
	return(false);
}



public int getStatisticalMin() 
{
	return (this.statisticalMin);
}



public boolean setStatisticalMin(int min) 
{
	int index;
	
	if (this.statisticalMin!=min)
	{
		this.statisticalMin=min;
				
		for (index=0;index<this.sensorListener.size();index++)
		{
	//		this.setupListener.get(index).minValueChanged(this);
		}
		return(true);
	}
	
	return(false);
}



public int getStatisticalMax() 
{
	return (this.statisticalMax);
}




public boolean setStatisticalMax(int max) 
{
	int index;
	
	if (this.statisticalMax!=max)
	{
		this.statisticalMax =max;
	
		
		for (index=0;index<this.sensorListener.size();index++)
		{
	//		this.setupListener.get(index).maxValueChanged(this);
		}
		
		return(true);
	}
	
	return(false);
}





public int getRawValue()
{
	return(this.value.getRawValue());

}


public int getRawRange()
{
	return(this.value.getRawRange());
	
}




}
