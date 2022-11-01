package de.hska.lat.robot.component.sensor.lm75;



public enum Lm75Mode {

	MODE_COMPERATOR(0, "comperator"),
	MODE_INTERRUPT(1, "interrupt");

	
	
	private final int number;
	private final String mode;
	
	
	
private Lm75Mode(int number, String mode) 
{
	this.number = number;
	this.mode = mode;
}


public int getNumber() 
{
	return (this.number);
}



public String getMode() 
{
	return (this.mode);
}



public String toString() 
{
	
	return (this.mode);
};




public static Lm75Mode get(int index)
{
	
	for (Lm75Mode value :  Lm75Mode.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Lm75Mode.MODE_COMPERATOR);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Lm75Mode getDefault()
{
	return(Lm75Mode.MODE_COMPERATOR);
}

}
