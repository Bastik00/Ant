package de.hska.lat.robot.component.sensor.vcnl4000.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000AveragingModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000FrequencyModes;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000IrCurrent;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings for a MPU9150 IMU 
 */

public class Cmd_setVcnl4000Settings extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setMpu9150Settings";
	protected static final String description = "set settings for a mpu9150Sensor";


	private static final int INDEX_SENSOR 				= 0;
	private static final int INDEX_PARAMETTERS			= 1;
	
	

public Cmd_setVcnl4000Settings() 
{
	this.add(new RemoteParameterUint8("index","mpu9150 sensor index"));
	this.add(new RemoteParameterVcnl4000Settings());
	

}
	
	
public Cmd_setVcnl4000Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setVcnl4000Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setVcnl4000Settings.description);
}



public void setData(int index,
		Vcnl4000IrCurrent			irCurrent,
		Vcnl4000AveragingModes		averagingMode,
		Vcnl4000FrequencyModes 		proximityFrequency,
		boolean 					autoConversion,
		boolean 					autoCompensation)
{
	(( RemoteParameterUint8) this.get(Cmd_setVcnl4000Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterVcnl4000Settings) this.get(Cmd_setVcnl4000Settings.INDEX_PARAMETTERS)).setIrCurrent(irCurrent);
	(( RemoteParameterVcnl4000Settings) this.get(Cmd_setVcnl4000Settings.INDEX_PARAMETTERS)).setAveragingMode(averagingMode);
	(( RemoteParameterVcnl4000Settings) this.get(Cmd_setVcnl4000Settings.INDEX_PARAMETTERS)).setProximityFrequency(proximityFrequency);
	(( RemoteParameterVcnl4000Settings) this.get(Cmd_setVcnl4000Settings.INDEX_PARAMETTERS)).setAutoConversion(autoConversion);
	(( RemoteParameterVcnl4000Settings) this.get(Cmd_setVcnl4000Settings.INDEX_PARAMETTERS)).setAutoCompensation(autoCompensation);

	
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setVcnl4000Settings.INDEX_SENSOR)).getValue());
}



public static Cmd_setVcnl4000Settings getCommand(int id)
{
	Cmd_setVcnl4000Settings cmd;
	cmd = new Cmd_setVcnl4000Settings(id);
	
	return(cmd);
}



public static Cmd_setVcnl4000Settings getCommand(int command,int index, 
		Vcnl4000IrCurrent			irCurrent,
		Vcnl4000AveragingModes		averagingMode,
		Vcnl4000FrequencyModes 		proximityFrequency,
		boolean 					autoConversion,
		boolean 					autoCompensation
		)
{
	
	Cmd_setVcnl4000Settings cmd;
	cmd = Cmd_setVcnl4000Settings.getCommand(command);
	cmd.setData(index, irCurrent, averagingMode, proximityFrequency, autoConversion, autoCompensation);
	
	return(cmd);
}


}

