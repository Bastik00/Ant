package de.hska.lat.robot.valueScope;

public class ValueScopeScreenData
{

	int timeScale = 10 ;
	int timeOffset = 0;
	
	
	
	
	
	
	
public int getTimeScale()
{
	return(this.timeScale);
}


public void zoomTimescale(int delta)
{
	this.timeScale-=delta;
	
	if (this.timeScale<10)
	{
		this.timeScale=10;
	}
}



public int getTimeOffset()
{
	return(this.timeOffset);
}
	


public void moveOffset(int delta)
{
	
	this.timeOffset += this.timeScale * delta;
	
	
	if (this.timeOffset<0)
	{
		this.timeOffset=0;
	}

	System.out.println("this.timeOffset "+this.timeOffset);
}

	
}
