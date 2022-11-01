package de.hska.lat.behavior.information.filter;

public class PulseFilter extends InformationFilter
{

	protected float basis; 
	protected boolean follow;
	protected boolean absolute;
	protected float threshold;
	
protected PulseFilter(int minWidth)
{
	super(minWidth);
	
}


public void setThreshold(float threshold, boolean absolute)
{
	this.threshold = threshold;
	this.absolute = absolute;
}


	@Override
	public void put(float value)
	{
		
		float difference;

		
		if (absolute)
		{
			difference = threshold;
		}
		else
		{
			difference =  (value * threshold);
		}
		
		if (!this.follow)
		{
			if (this.basis> (value + difference))
			{
				this.follow = true;
			}
			else if (this.basis< (value - difference))
			{
				
				this.follow = true;
			}
				
			this.follow = false;		
			this.basis = value;
			
		}
		else
		{
			if (this.basis< (value + difference))
			{
				this.follow = false;
			}
			else if (this.basis> (value - difference))
			{
				this.follow = false;
			}
			else
			{
				// if time -> out
			}
		}
		// TODO Auto-generated method stub
		// value 
	}


	@Override
	protected float calculate()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	
}
