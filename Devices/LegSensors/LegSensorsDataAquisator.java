package de.hska.lat.ant_IIIb.devices.legSensors;

import de.hska.lat.robot.device.control.dataAquisation.DataAquisator;


/**
 * 
 * @author Oktavian Gniot
 *
 * defines aquisition commands for bipedal front foot sensors controllers 
 */

public class LegSensorsDataAquisator {

	
	/**
	 * command for aquisation of leg controller cpu status 
	 */
	public static final int AQUISATE_CPU_STATUS = 1;
	
	/**
	 * command for aquisation of leg controller com system status
	 */
	public static final int AQUISATE_COM_STATUS = 2;
	

	public static final int VCNL4000_AQUISATE_LUX 			= 3;
	public static final int VCNL4000_AQUISATE_DISTANCE		= 4; 


	
	/**
	 * aquisation command set for foot front controller
	 */
	public static DataAquisator[] aquisators ={
		new DataAquisator("cpu status",100,AQUISATE_CPU_STATUS),
		new DataAquisator("com status",100,AQUISATE_COM_STATUS),
		new DataAquisator("VCNL4000 lux",100,VCNL4000_AQUISATE_LUX),
		new DataAquisator("VCNL4000 distance",100,VCNL4000_AQUISATE_DISTANCE),

};
	
}
