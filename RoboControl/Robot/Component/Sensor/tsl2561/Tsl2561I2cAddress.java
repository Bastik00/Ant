package de.hska.lat.robot.component.sensor.tsl2561;

public enum Tsl2561I2cAddress {

	I2C_ADDRESS_29(0,0x29),
	I2C_ADDRESS_39(1,0x39),
	I2C_ADDRESS_49(2,0x49);
	
	
	
	private final int number;
	private final int i2CAddres;
	
	
	
private Tsl2561I2cAddress(int number, int i2CAddres) 
{
	this.number = number;
	this.i2CAddres = i2CAddres;
}


public int getNumber() 
{
	return number;
}



public int getI2cAddress() 
{
	return (this.i2CAddres);
}


@Override
public String toString() 
{
	
	return (this.i2CAddres+" address");
}


/**
 * return TSL2561 sensor i2cAddres
 * @return i2Caddress
 */
public static Tsl2561I2cAddress getDefault()
{
	return (Tsl2561I2cAddress.I2C_ADDRESS_29);
}




public static Tsl2561I2cAddress get(int index)
{
	
	for (Tsl2561I2cAddress value :  Tsl2561I2cAddress.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Tsl2561I2cAddress.getDefault());
	
}

	
}
