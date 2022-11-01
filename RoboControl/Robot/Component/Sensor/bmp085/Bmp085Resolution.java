package de.hska.lat.robot.component.sensor.bmp085;



public enum Bmp085Resolution {

	RESOLUTION_16BIT(0, "4.5ms/16Bit"),
	RESOLUTION_17BIT(1, "7.5ms/17Bit"),
	RESOLUTION_18BIT(2, "13.5/18Bit"),
	RESOLUTION_19BIT(3, "25.5/19Bit");
	

	private final int number;
	private final String frequency;
	
	
	
private Bmp085Resolution(int number, String frequency) 
{
	this.number = number;
	this.frequency = frequency;
}


public int getNumber() 
{
	return (this.number);
}



public String getFrequency() 
{
	return (this.frequency);
}



public String toString() 
{
	
	return (this.frequency);
};




public static Bmp085Resolution get(int index)
{
	
	for (Bmp085Resolution value :  Bmp085Resolution.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Bmp085Resolution.RESOLUTION_16BIT);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Bmp085Resolution getDefault()
{
	return(Bmp085Resolution.RESOLUTION_16BIT);
}

}
