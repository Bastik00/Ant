package de.hska.lat.robot.component.sensor.mlx90620;

public enum Mlx90620AmbientRefreshRate {

	AMBIENT_RATE_2(3,2),
	AMBIENT_RATE_4(2,4),	
	AMBIENT_8(1,8),
	AMBIENT_16(0,16);
	
	
	
	private final int number;
	private final int rate;
	
	
	
	
private Mlx90620AmbientRefreshRate(int number, int rate) 
{
	this.number = number;
	this.rate = rate;
}


public int getNumber() 
{
	return number;
}



public int getRate() 
{
	return rate;
}



public String toString() 
{
	
	return (this.getRate()+"Hz");
};

	
}
