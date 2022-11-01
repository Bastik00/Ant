package de.hska.lat.ant_IIIb.devices.legController;




public enum LegControllerConfiguration
{

	LEFT_SERVO ("left",0), 
	CENTER_SERVO ("center",1),
	RIGHT_SERVO ("right",2),
	HEAD_SERVO ("head",3),

	
	
	LEFT_SERVO_CURRENT ("left",0), 
	CENTER_SERVO_CURRENT ("center",1),
	RIGHT_SERVO_CURRENT ("right",2),
	
	LEFT_TEMPERATURE_SENSOR ("left",0), 
	CENTER_TEMPERATURE_SENSOR ("center",1),
	RIGHT_TEMPERATURE_SENSOR ("right",2),
	REGULATOR_TEMPERATURE_SENSOR("regulator",3),

	;

	private static final String DEVICE_NAME ="Leg controller";

 	/**
	 * @uml.property  name="name"
	 */
 	private String name;
 	/**
	 * @uml.property  name="localId"
	 */
 	private int localId;

private LegControllerConfiguration(String name,int localId)
{
	this.localId=localId;
	this.name=name;
}


/**
 * @return
 * @uml.property  name="localId"
 */
public int getLocalId()
{
	return(this.localId);
}

/**
 * @return
 * @uml.property  name="name"
 */
public String getName()
{
	return(this.name);
}



public static String getDeviceName()
{
	return(DEVICE_NAME);
}

}
