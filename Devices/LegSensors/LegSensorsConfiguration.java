package de.hska.lat.ant_IIIb.devices.legSensors.components;



public enum LegSensorsConfiguration
{


	FRONT_LEFT_LEG_VCNL4020 ("front left",1),
	CENTER_LEFT_LEG_4020 ("center left",0),
	BACK_LEFT_LEG_VCNL4020 ("back left",2),
	
	
	FRONT_RIGHT_LEG_4020 ("front right",4),
	CENTER_RIGHT_LEG_VCNL4020 ("center right",3),
	BACK_RIGHT_LEG_VCNL4020 ("cack right",5),
	
	
	
	FRONT_LEFT_LEG_LED ("front left",1),
	CENTER_LEFT_LEG_LED ("center left",0),
	BACK_LEFT_LEG_LED ("back left",2),
	
	FRONT_RIGHT_LEG_LED ("front right",4),
	CENTER_RIGHT_LEG_LED ("center right",3),
	BACK_RIGHT_LED ("cack right",5),
	


	
	;

	private static final String DEVICE_NAME ="leg controller mk1";

 	/**
	 * @uml.property  name="name"
	 */
 	private String name;
 	/**
	 * @uml.property  name="localId"
	 */
 	private int localId;

private LegSensorsConfiguration(String name,int localId)
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
