package de.hska.lat.robot.component.sensor.mpu6000;



public enum Mpu6000ClockSource {

	CLOCK_SELECTION_INTERNAL_8_MHZ(0,"internal 8 Mhz"),
	CLOCK_SELECTION_GYROSCOPE_X_AXIS(1,"gyroscope x"),	
	CLOCK_SELECTION_GYROSCOPE_Y_AXIS(2,"gyroscope y"),
	CLOCK_SELECTION_GYROSCOPE_Z_AXIS(3,"gyroscope z"),
	CLOCK_SELECTION_PLL_32_768KHZ(4,"PLL 32.768Khz"),
	CLOCK_SELECTION_PLL19_2_MHZ(5,"PLL 19.2Mhz"),
	CLOCK_SELECTION_RESERVED(6,"reserved"),
	CLOCK_SELECTION_STOP(7,"stop");
	
	
	private final int number;
	private final String description ;
	
	
	
	
private Mpu6000ClockSource(int number,String description ) 
{
	this.number = number;
	this.description = description;
}


public int getNumber() 
{
	return number;
}



public String getDescription() 
{
	return (this.description);
}



public String toString() 
{
	
	return (this.getDescription());
};

	

public static Mpu6000ClockSource get(int index)
{
	
	for (Mpu6000ClockSource value :  Mpu6000ClockSource.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Mpu6000ClockSource.CLOCK_SELECTION_INTERNAL_8_MHZ);
	
}


}
