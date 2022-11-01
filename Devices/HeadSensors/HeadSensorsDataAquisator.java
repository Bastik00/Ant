package de.hska.lat.ant_IIIb.devices.headSensors;

import de.hska.lat.robot.device.control.dataAquisation.DataAquisator;


/**
 * 
 * @author Oktavian Gniot
 *
 * defines aquisition commands for bipedal front foot sensors controllers 
 */

public class HeadSensorsDataAquisator {

	
	/**
	 * command for aquisation of leg controller cpu status 
	 */
	public static final int AQUISATE_CPU_STATUS = 1;
	
	/**
	 * command for aquisation of leg controller com system status
	 */
	public static final int AQUISATE_COM_STATUS = 2;
	
	
	
	public static final int MLX90614_AQUISATE_AMBIENT 		= 3;
	public static final int MLX90614_AQUISATE_OBJECT 		= 4;
	public static final int VCNL4000_AQUISATE_LUX 			= 5;
	public static final int VCNL4000_AQUISATE_DISTANCE		= 6; 
	public static final int BMP085_AQUISATE_TEMPERATURE 	= 7;
	public static final int BMP085_AQUISATE_PRESURE		 	= 8;




	
	/**
	 * aquisation command set for foot front controller
	 */
	public static DataAquisator[] aquisators ={
		new DataAquisator("cpu status",100,AQUISATE_CPU_STATUS),
		new DataAquisator("com status",100,AQUISATE_COM_STATUS),
		new DataAquisator("Mxl90614 ambient temperature",100,MLX90614_AQUISATE_AMBIENT),
		new DataAquisator("Mxl90614 object temperature",100,MLX90614_AQUISATE_OBJECT),
		new DataAquisator("VCNL4000 lux",100,VCNL4000_AQUISATE_LUX),
		new DataAquisator("VCNL4000 distance",100,VCNL4000_AQUISATE_DISTANCE),
		new DataAquisator("Bmp085 temperature",10,BMP085_AQUISATE_TEMPERATURE),
		new DataAquisator("Bmp085 presure",10,BMP085_AQUISATE_PRESURE),


};
	
}
