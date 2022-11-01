package de.hska.lat.robot.component.sensor.mpu6000;



public enum Mpu6000GyroscopeRange {

	GYROSCOPE_RANGE_250(0,250),
	GYROSCOPE_RANGE_500(1,500),	
	GYROSCOPE_RANGE_1000(2,1000),
	GYROSCOPE_RANGE_2000(3,2000);
	
	
	
	private final int number;
	private final int rate;
	
	
	
	
private Mpu6000GyroscopeRange(int number, int rate) 
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
	
	return (this.getRate()+"°");
};

	

public static Mpu6000GyroscopeRange get(int index)
{
	
	for (Mpu6000GyroscopeRange value :  Mpu6000GyroscopeRange.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Mpu6000GyroscopeRange.GYROSCOPE_RANGE_250);
	
}
}
