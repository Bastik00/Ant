package de.hska.lat.ant_IIIb.devices.legController;

import de.hska.lat.robot.device.control.dataAquisation.DataAquisator;


/**
 * 
 * @author Oktavian Gniot
 *
 * defines aquisition commands for Erbse gait controller 
 */

public class LegControllerDataAquisator {

	
	
	
	/**
	 * command for aquisation of motion controller cpu status 
	 */
	public static final int AQUISATE_CPU_STATUS = 1;
	
	/**
	 * command for aquisation of motion controller com system status
	 */
	public static final int AQUISATE_COM_STATUS = 2;
	
	
	/**
	 * command for aquisation of motion controller servos positions
	 */
	public static final int AQUISATE_SERVOS_POSITIONS = 3;

	
	/**
	 * command for aquisation of motion controller servos positions
	 */
	public static final int AQUISATE_SERVOS_DESTINATIONS = 4;
	
	/**
	 * command for aquisation of motion controller servos statuses
	 */

	public static final int AQUISATE_SERVOS_STATUS = 5; 
	
	/**
	 * command for aquisation of leg controller servos current
	 */

	public static final int AQUISATE_CURRENT_VALUES = 6; 
	
	/**
	 * command for aquisation of leg controller servos current
	 */

	public static final int AQUISATE_CURRENT_CONSUMPTIONS = 7; 

	public static final int AQUISATE_CURRENT_MAX		 = 8;
	
	public static final int AQUISATE_SERVO_ANAOG_RAW_VALUES	 = 9;
	public static final int AQUISATE_TEMPERATURE			 = 10; 	
	
	
		
	/**
	 * aquisation command set for gait controller
	 */
	
	public static DataAquisator[] aquisators ={
		new DataAquisator("cpu status", 100, AQUISATE_CPU_STATUS),
		new DataAquisator("com status", 100, AQUISATE_COM_STATUS),
		new DataAquisator("servos positions", 10, AQUISATE_SERVOS_POSITIONS),
		new DataAquisator("servos destinationss", 10, AQUISATE_SERVOS_DESTINATIONS),
		new DataAquisator("servos status", 10, AQUISATE_SERVOS_STATUS),
		new DataAquisator("servo raw analog", 10, AQUISATE_SERVO_ANAOG_RAW_VALUES),
		new DataAquisator("current values", 10, AQUISATE_CURRENT_VALUES),
		new DataAquisator("current consumptions", 10, AQUISATE_CURRENT_CONSUMPTIONS),
		new DataAquisator("current max", 10, AQUISATE_CURRENT_MAX),
		new DataAquisator("temperature", 100, AQUISATE_TEMPERATURE),
	};
	
}