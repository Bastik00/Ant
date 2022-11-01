package de.hska.lat.robot.component.sensor.vcnl4000;



public enum Vcnl4000FrequencyModes {

	MODE_3_125_MHZ(0,"3.125 MHz"),
	MODE_1_5625_MHZ(1,"1.5625 MHz"),
	MODE_781_25_KHZ(2,"781.25 kHz"),
	MODE_390_625_Khz(3,"390.625 kHz");
	
	
	
	private final int number;
	private final String frequency;
	
	
	
private Vcnl4000FrequencyModes(int number, String frequency) 
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


@Override
public String toString() 
{
	
	return (this.frequency);
};


public static Vcnl4000FrequencyModes get(int index)
{
	
	for (Vcnl4000FrequencyModes value :  Vcnl4000FrequencyModes.values())
	{
		if (value.getNumber()==index)
			return(value);
	}
	
	return(Vcnl4000FrequencyModes.MODE_3_125_MHZ);
	
}


/**
 * get vcnl4000 default frequency mode. This mode is set on Sensor reset
 * @return vcnl4000 default frequency mode
 */
public static Vcnl4000FrequencyModes getDefault()
{
	return (Vcnl4000FrequencyModes.MODE_781_25_KHZ);
}	

}
