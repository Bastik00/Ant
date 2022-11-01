package de.hska.lat.robot.component.sensor.vcnl4000;


public enum Vcnl4000IrCurrent {

	CURRENT_0MA(0,0),
	CURRENT_10MA(1,10),
	CURRENT_20MA(2,20),
	CURRENT_30MA(3,30),
	CURRENT_40MA(4,40),
	CURRENT_50MA(5,50),
	CURRENT_60MA(6,60),
	CURRENT_70MA(7,70),
	CURRENT_80MA(8,80),
	CURRENT_90MA(9,90),
	CURRENT_100MA(10,100),
	CURRENT_110MA(11,110),
	CURRENT_120MA(12,120),
	CURRENT_130MA(13,130),
	CURRENT_140MA(14,140),
	CURRENT_150MA(15,150),
	CURRENT_160MA(16,160),
	CURRENT_170MA(17,170),
	CURRENT_180MA(18,180),
	CURRENT_190MA(19,190),
	CURRENT_200MA(20,200);
	

	
	
	private final int number;
	private final int current;
	
	
	
private Vcnl4000IrCurrent(int number, int current) 
{
	this.number = number;
	this.current = current;
}


public int getNumber() 
{
	return number;
}



public int getCurrent() 
{
	return (this.current);
}


public static Vcnl4000IrCurrent get(int index)
{
	
	for (Vcnl4000IrCurrent value :  Vcnl4000IrCurrent.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Vcnl4000IrCurrent.CURRENT_20MA);
	
}



@Override
public String toString() 
{
	
	return (this.current+"ma");
}

/**
 * get VCNL 4000 default IR LED current. This current is set on sensor reset. 
 * @return VCNL default IR current 
 */
public static Vcnl4000IrCurrent getDefault()
{
	return (Vcnl4000IrCurrent.CURRENT_20MA);
};

	
}
