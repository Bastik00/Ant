package de.hska.lat.robot.component.sensor.tmp102;



public enum Tmp102Address {

	I2C_ADDRESS_0X48(0, "0x48"),
	I2C_ADDRESS_0X49(1, "0x49"),
	I2C_ADDRESS_0X4A(2, "0x4A"),
	I2C_ADDRESS_0X4B(3, "0x4B");
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Tmp102Address(int number, String frequency) 
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




public static Tmp102Address get(int index)
{
	
	for (Tmp102Address value :  Tmp102Address.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Tmp102Address.I2C_ADDRESS_0X48);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Tmp102Address getDefault()
{
	return(Tmp102Address.I2C_ADDRESS_0X48);
}

}
