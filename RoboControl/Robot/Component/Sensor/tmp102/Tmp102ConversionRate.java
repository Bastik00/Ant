package de.hska.lat.robot.component.sensor.tmp102;



public enum Tmp102ConversionRate {

	CONVERSION_RATE_0_25(0, "0.25 Hz"),
	CONVERSION_RATE_1(1, "1 Hz"),
	CONVERSION_RATE_4(2, "4 Hz"),
	CONVERSION_RATE_8(3, "8 Hz");
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Tmp102ConversionRate(int number, String frequency) 
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




public static Tmp102ConversionRate get(int index)
{
	
	for (Tmp102ConversionRate value :  Tmp102ConversionRate.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Tmp102ConversionRate.CONVERSION_RATE_4);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Tmp102ConversionRate getDefault()
{
	return(Tmp102ConversionRate.CONVERSION_RATE_4);
}

}
