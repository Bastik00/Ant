package de.hska.lat.robot.component.sensor.tmp006;



public enum Tmp006Address {

	
	I2C_ADDRESS_0X40(0, "0x40"),
	I2C_ADDRESS_0X41(1, "0x41"),
	I2C_ADDRESS_0X42(2, "0x42"),
	I2C_ADDRESS_0X43(3, "0x43"),
	I2C_ADDRESS_0X44(4, "0x44"),
	I2C_ADDRESS_0X45(5, "0x45"),
	I2C_ADDRESS_0X46(6, "0x46"),
	I2C_ADDRESS_0X47(7, "0x47");
	
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Tmp006Address(int number, String frequency) 
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




public static Tmp006Address get(int index)
{
	
	for (Tmp006Address value :  Tmp006Address.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Tmp006Address.I2C_ADDRESS_0X40);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Tmp006Address getDefault()
{
	return(Tmp006Address.I2C_ADDRESS_0X40);
}

}
