package de.hska.lat.robot.device.generic.dataHub;

import de.hska.lat.robot.device.control.dataAquisation.DataAquisator;


/**
 * 
 * @author Oktavian Gniot
 *
 * defines aquisition commands for generic data hub 
 */

public class DataHubAquisator {

	
	/**
	 * command for aquisation of data hub cpu status 
	 */
	public static final int AQUISATE_CPU_STATUS = 1;
	
	/**
	 * command for aquisation of data hub com system status
	 */
	public static final int AQUISATE_COM_STATUS = 2;
	
	
	
	/**
	 * aquisation command set for data hub controller
	 */
	
	public static DataAquisator[] aquisators ={
		new DataAquisator("cpu status",100,AQUISATE_CPU_STATUS),
		new DataAquisator("com status",100,AQUISATE_COM_STATUS),

	};
	
}