package de.hska.lat.robot.component.sensor.tmp006;



public enum Tmp006ConversionRate {

	CONVERSION_RATE_0_25(4, "0.25 Hz"),
	CONVERSION_RATE_0_5(3, "0.5 Hz"),
	CONVERSION_RATE_1(2, "1 Hz"),
	CONVERSION_RATE_2(1, "2 Hz"),
	CONVERSION_RATE_4(0, "4 Hz");
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Tmp006ConversionRate(int number, String frequency) 
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




public static Tmp006ConversionRate get(int index)
{
	
	for (Tmp006ConversionRate value :  Tmp006ConversionRate.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Tmp006ConversionRate.CONVERSION_RATE_4);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Tmp006ConversionRate getDefault()
{
	return(Tmp006ConversionRate.CONVERSION_RATE_4);
}

}
