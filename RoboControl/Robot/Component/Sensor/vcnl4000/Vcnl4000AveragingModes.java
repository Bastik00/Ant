package de.hska.lat.robot.component.sensor.vcnl4000;

public enum Vcnl4000AveragingModes {

	AVERAGING_1(0,0),
	AVERAGING_2(1,12),
	AVERAGING_4(2,4),
	AVERAGING_8(3,8),
	AVERAGING_16(4,16),
	AVERAGING_32(5,32),
	AVERAGING_64(6,64),
	AVERAGING_128(7,128);
	
	
	
	private final int number;
	private final int averaging;
	
	
	
private Vcnl4000AveragingModes(int number, int averaging) 
{
	this.number = number;
	this.averaging = averaging;
}


public int getNumber() 
{
	return number;
}



public int getAveraging() 
{
	return (this.averaging);
}


@Override
public String toString() 
{
	
	return (this.averaging+" samples");
}


/**
 * return sensors default averaging mode (32 conversions). This mode is set automatic on sensors reset.
 * @return sensors default averaging mode.
 */
public static Vcnl4000AveragingModes getDefault()
{
	return (Vcnl4000AveragingModes.AVERAGING_32);
}


public static Vcnl4000AveragingModes get(int index)
{
	
	for (Vcnl4000AveragingModes value :  Vcnl4000AveragingModes.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Vcnl4000AveragingModes.AVERAGING_32);
	
}

	
}
