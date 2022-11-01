package de.hska.lat.robot.component.sensor.mpu6000;



public enum Mpu6000AccRange {

	ACC_RANGE_2G(0,2),
	ACC_RANGE_4G(1,4),	
	ACC_RANGE_8G(2,8),
	ACC_RANGE_16G(3,16);
	
	
	
	private final int number;
	private final int rate;
	
	
	
	
private Mpu6000AccRange(int number, int rate) 
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
	
	return (this.getRate()+"g");
};

public static Mpu6000AccRange get(int index)
{
	
	for (Mpu6000AccRange value :  Mpu6000AccRange.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Mpu6000AccRange.ACC_RANGE_2G);
	
}	
}
