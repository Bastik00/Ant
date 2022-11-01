package de.hska.lat.robot.component.sensor.mpu6000;

public enum Mpu6000Dlpf {

	FILTER_BANDWIDTH_260(0,"260 / 256 "),
	FILTER_BANDWIDTH_184(1,"184 / 188"),	
	FILTER_BANDWIDTH_94(2,"94 / 98"),
	FILTER_BANDWIDTH_44(3,"44 / 42"),
	FILTER_BANDWIDTH_21(4,"21 / 20"),
	FILTER_BANDWIDTH_10(5,"10 / 10"),
	FILTER_BANDWIDTH_5(6,"5 / 5"),
	FILTER_BANDWIDTH_RESERVED(7,"reserved");
	
	
	private final int number;
	private final String description ;
	
	
	
	
private Mpu6000Dlpf(int number,String description ) 
{
	this.number = number;
	this.description = description;
}


public int getNumber() 
{
	return number;
}



public String getDescription() 
{
	return (this.description);
}



public String toString() 
{
	
	return (this.description);
};


/**
 * get low pas filter bandwidth index 
 * @param index 
 * @return
 */
public static Mpu6000Dlpf get(int index)
{
	
	for (Mpu6000Dlpf value :  Mpu6000Dlpf.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(FILTER_BANDWIDTH_260);
	
}
	
}
