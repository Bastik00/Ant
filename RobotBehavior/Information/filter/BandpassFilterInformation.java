package de.hska.lat.behavior.information.filter;

public class BandpassFilterInformation extends FilterInformation
{

	private float frequency;
	private int timeBase;
	
	public BandpassFilterInformation(float frequency, int timeBase)
	{
		this.frequency = frequency;
		this.timeBase = timeBase;
	}

	public float getFrequency()
	{
		return frequency;
	}

	public void setFrequency(float frequency)
	{
		this.frequency = frequency;
	}

	public int getTimeBase()
	{
		return timeBase;
	}

	public void setTimeBase(int timeBase)
	{
		this.timeBase = timeBase;
	}
	
	
	
public BandpassFilterInformation copy()
{
	return(new BandpassFilterInformation(this.frequency, this.timeBase));
	
	
	
}
	
}
