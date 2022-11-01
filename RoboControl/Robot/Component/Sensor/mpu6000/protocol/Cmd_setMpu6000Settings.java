package de.hska.lat.robot.component.sensor.mpu6000.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000AccRange;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000ClockSource;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000Dlpf;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000GyroscopeRange;





/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings for a MPU9150 IMU 
 */

public class Cmd_setMpu6000Settings extends RemoteCommand
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setMpu9150Settings";
	protected static final String description = "set settings for a mpu9150Sensor";


	private static final int INDEX_SENSOR 				= 0;
	private static final int INDEX_PARAMETTERS			= 1;
	
	

public Cmd_setMpu6000Settings() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterMpu6000Settings());
	

}
	
	
public Cmd_setMpu6000Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setMpu6000Settings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMpu6000Settings.description);
}



public void setData(int index,
						Mpu6000ClockSource clockSource,
						Mpu6000Dlpf dlpf,
						Mpu6000AccRange accRange,
						Mpu6000GyroscopeRange gyroscopeRange)
{
	(( RemoteParameterUint8) this.get(Cmd_setMpu6000Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterMpu6000Settings) this.get(Cmd_setMpu6000Settings.INDEX_PARAMETTERS)).setClockSource(clockSource);
	(( RemoteParameterMpu6000Settings) this.get(Cmd_setMpu6000Settings.INDEX_PARAMETTERS)).setAccRange(accRange);
	(( RemoteParameterMpu6000Settings) this.get(Cmd_setMpu6000Settings.INDEX_PARAMETTERS)).setGyroscopeRange(gyroscopeRange);
	(( RemoteParameterMpu6000Settings) this.get(Cmd_setMpu6000Settings.INDEX_PARAMETTERS)).setDlpfFrequency(dlpf);

	
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMpu6000Settings.INDEX_SENSOR)).getValue());
}



public static Cmd_setMpu6000Settings getCommand(int id)
{
	Cmd_setMpu6000Settings cmd;
	cmd = new Cmd_setMpu6000Settings(id);
	
	return(cmd);
}



public static Cmd_setMpu6000Settings getCommand(int command,int index, 
		Mpu6000ClockSource clockSource,
		Mpu6000Dlpf dlpf,
		Mpu6000AccRange accRange,
		Mpu6000GyroscopeRange gyroscopeRange
		)
{
	
	Cmd_setMpu6000Settings cmd;
	cmd = Cmd_setMpu6000Settings.getCommand(command);
	cmd.setData(index, clockSource, dlpf, accRange, gyroscopeRange);
	
	return(cmd);
}


}

