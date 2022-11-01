package de.hska.lat.behavior.information;

import java.util.ArrayList;

public class InformationSet extends ArrayList<Information>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2504658949139880471L;

	
	
/**
 * get actua maximal value of this set
 * @return actual max value in set 
 */
public Information getMax()
{
	
	float max;
	Information maxInformation = null;
	
	if (this.size()==0)
		return(maxInformation);
	
	max = Float.NEGATIVE_INFINITY;
	
	for (Information information : this)
	{
		if (information.getValue()>max)
		{
			max = information.getValue();
			maxInformation = information;
		}
	}
	
	return(maxInformation);
}


/**
 * get actua minimal value of this set
 * @return actual minimal value in set
 */
public Information getMin()
{
	
	float min;
	Information minInformation = null;
	
	if (this.size()==0)
		return(minInformation);
	
	min = Float.POSITIVE_INFINITY;
	
	for (Information information : this)
	{
		if (information.getValue()<min)
		{
			min = information.getValue();
			minInformation = information;
		}
	}
	
	return(minInformation);
}


/**
 * get actual range between max and min value
 * @return range of values
 */
public float getRange()
{
	if (this.size()==0)
		return(0);
	
	return(this.getMax().getValue()-this.getMin().getValue());
}



/**
 * get the average value of all informations in this set
 * @return average value of this set
 */
public float getAverage()
{
	float average = 0;
	
	if (this.size()==0)
			return(0);
	
	for (Information information : this)
	{
		average += information.getValue();
	}
	average /= this.size();
	
	
	return(average);
}




public float getMedian()
{
	if (this.size()==0)
		return(0);
	
	float values [] = new float[this.size()];

	
	int index=0;
	
	for (Information informtion : this)
	{
		values[index++] = informtion.getValue();
	}
	

	
	return(0);
}



public void capture()
{
	for (Information information : this)
	{
		information.capture();
	}
}


public boolean hasChanged()
{
	boolean changed = false;
	for (Information information : this)
	{
		changed |=information.hasChanged();
	}
	
	return(changed);
}


}
