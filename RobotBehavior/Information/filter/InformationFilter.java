package de.hska.lat.behavior.information.filter;

public abstract class InformationFilter
{

	protected float[] data; 
	
	protected int writePointer;
	protected int readPointer;
	protected float value;
	
protected InformationFilter(int deepth)
{
	this.data = new float[deepth];
}
	
/**
 * put new value in this filter	
 * @param value information type dependent value
 */
public  synchronized void put(float value)
{

	if (this.writePointer>=this.data.length)
	{
		this.writePointer=0;
	}
		
	this.data[this.writePointer] = value;
	this.writePointer++;
	

	
	this.value = this.calculate();

}

/**
 * calculate filters new output value
 * @return filters new output value
 */
protected abstract float calculate();


public float get()
{
	return (this.value);
}
	
}
