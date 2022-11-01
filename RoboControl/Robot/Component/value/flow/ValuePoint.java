package de.hska.lat.robot.component.value.flow;

public class ValuePoint
{

	
	
	protected int time;
	protected float value;
	
	
public ValuePoint(int time,float value)
{
	this.time = time;
	this.value = value;
}
	


public float getValue()
{
	return(this.value);
}

public int getTime()
{
	return(this.time);
}

}
