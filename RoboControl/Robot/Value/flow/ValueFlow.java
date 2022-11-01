package de.hska.lat.robot.value.flow;

import java.util.ArrayList;
import java.util.ListIterator;

import de.hska.lat.robot.component.value.flow.ValuePoint;
import de.hska.lat.robot.value.ComponentValue;



public class ValueFlow extends ArrayList<ValuePoint> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6994655963633245204L;
	
	private static final int TIME_BASIS_MS 	= 10;
		
	protected int actualPlayed;
	
	protected int actualTime;
	
	protected boolean soft = true;
	
	protected ComponentValue<?> value;
	
	
public ValueFlow(ComponentValue<?> value)
{
	this.value = value;
}
	
	
public boolean addAtTime(int time, float value)
{
	
	ValuePoint dataPoint;
	

	ListIterator<ValuePoint> list = this.listIterator();
	
	while(true)
	{
			if (list.hasNext()== false)
			{
				list.add(new ValuePoint(time, value));
				return(true);
			}
				
			
			dataPoint = list.next();
			if (dataPoint.getTime()==time)
			{
				list.set(new ValuePoint(time, value));
				return(true);
			}
			if (dataPoint.getTime()>time)
			{
				list.previous();
				list.add(new ValuePoint(time, value));
				
				return(true);
			}
	}
	


}
	
/**
 *  return the maximum value for all data points in this ValueFlow 
 * @return maximum value
 */

public float getMaxValue()
{
	float maxValue = Float.NEGATIVE_INFINITY;
	float tmpValue;
	
	for (ValuePoint point : this)
	{
		tmpValue=point.getValue();
		if (tmpValue>maxValue)
		{
			maxValue = tmpValue;
		}
	}
	
	return(maxValue);
}

/**
 * return the minimum value for all data points in this ValueFlow 
 * @return minimum value
 */
public float getMinValue()
{

	float minValue = Float.POSITIVE_INFINITY;
	float tmpValue;
	
	for (ValuePoint point : this)
	{
		tmpValue=point.getValue();
		if (tmpValue<minValue)
		{
			minValue = tmpValue;
		}
	}
	
	return(minValue);

}



public void record(int actualTime)
{
	ValuePoint vp;
	if (this.size()>0)
	{
	vp=this.get(this.size()-1);
	
	if (vp.getValue()!=value.getValue())
	{
		this.add(new ValuePoint(actualTime, value.getValue()));
		//TODO listener change!!!
	}
	}
	else
		this.add(new ValuePoint(actualTime, value.getValue()));

	actualTime++;
}



public boolean removeAtTime(int time)
{
	
	
	
	
	for (ValuePoint dataPoint : this)
	{
		if (dataPoint.getTime()==time)
		{
			this.remove(dataPoint);
			return(true);
		}
	}
	
	return(false);
}
	
	

public void start(int time)
{
	this.actualTime = time;
	ValuePoint dataPoint;
	int index;
	
	for (index=0; index <this.size(); index++)
	{
		dataPoint = this.get(index);
		if (dataPoint.getTime()>time)
		{
			this.actualPlayed= index-1;
			break;
		}
	}
}



public void stop()
{
	
}


public void setNext()
{
	float actualValue;
	
	if (this.size()==0)
		return;
	
	
	if (this.actualPlayed<0)
	{
		actualValue = Float.NaN;
		this.actualTime+=ValueFlow.TIME_BASIS_MS;
		
		if (this.get(0).getTime()<=actualTime)
		{
			this.actualPlayed=0;
		}
	}
	
	else
//	if (this.soft==false)
	{
		//actualValue = this.getHard();
		actualValue = this.getSoft();
	}

	if (actualValue!=Float.NaN)
		{
			value.setValue(actualValue);
		}

	System.out.println(actualValue);
	
}
	


public float getHard()
{
	float actualValue;


	actualValue = this.get(this.actualPlayed).getValue();
	
	this.actualTime+=10;
	
//	System.out.println(" size + :"+this.size()+"     play : "+this.actualPlayed+"     play+1 : "+(this.actualPlayed+1));
	
	if ((this.actualPlayed+1)<this.size())
	{
		if (this.get(this.actualPlayed+1).getTime()<=actualTime)
		{
			this.actualPlayed++;
		}
	
	}
	
	
	return(actualValue);
}





public float getSoft()
{

	
	float actualValue;

	int deltaPrevious;
	int deltaNext;
	

	
	float deltaValue;
	float range;
	
	
	ValuePoint previous;
	ValuePoint next;

	
	
	
	
	previous = this.get(this.actualPlayed);

	if ((this.actualPlayed+1)<this.size())
	{
		next = this.get(this.actualPlayed+1);
		
		deltaPrevious = this.actualTime - previous.getTime();
		deltaNext  = next.getTime() - previous.getTime();
		
		range = next.getValue() - previous.getValue();

		deltaValue = (deltaPrevious *  range) / deltaNext;
		
	
			actualValue = previous.getValue() + deltaValue;
	}
	
	
	else
	{
		actualValue = this.get(this.actualPlayed).getValue();	
	}
	

	
	this.actualTime+=10;
	
//	System.out.println(" size + :"+this.size()+"     play : "+this.actualPlayed+"     play+1 : "+(this.actualPlayed+1));
	
	if ((this.actualPlayed+1)<this.size())
	{
		if (this.get(this.actualPlayed+1).getTime()<=actualTime)
		{
			this.actualPlayed++;
		}
	
	}
	
	
	return(actualValue);
}


}
	

	
	
	
	
	
	



