package de.hska.lat.robot.component.sensor.lm75;



public enum Lm75Address {

	I2C_ADDRESS_0X48(0, "0x48"),
	I2C_ADDRESS_0X49(1, "0x49"),
	I2C_ADDRESS_0X4A(2, "0x4A"),
	I2C_ADDRESS_0X4B(3, "0x4B"),
	I2C_ADDRESS_0X4C(3, "0x4C"),
	I2C_ADDRESS_0X4D(3, "0x4D"),
	I2C_ADDRESS_0X4E(3, "0x4E"),
	I2C_ADDRESS_0X4F(3, "0x4F");
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Lm75Address(int number, String frequency) 
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




public static Lm75Address get(int index)
{
	
	for (Lm75Address value :  Lm75Address.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Lm75Address.I2C_ADDRESS_0X48);
	
}

/**
 * get sensors default conversion rate
 * @return sensors default conversion rate
 */
public static Lm75Address getDefault()
{
	return(Lm75Address.I2C_ADDRESS_0X48);
}

}
