package de.hska.lat.robot.component.sensor.mpu6000.protocol;

import de.hska.lat.comm.remote.RemoteMessage;

import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000AccRange;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000ClockSource;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000Dlpf;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000GyroscopeRange;

/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_mpu6000Settings extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "setMpu9150Settings";
	protected static final String description = "set settings for a mpu9150Sensor";


	private static final int INDEX_SENSOR 				= 0;
	private static final int INDEX_MIN_RANGE			= 1;

	

public Msg_mpu6000Settings() 
{
	this.add(new RemoteParameterUint8("index","servo index"));
	this.add(new RemoteParameterMpu6000Settings());
}
	
	
public Msg_mpu6000Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_mpu6000Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_mpu6000Settings.description);
}



public void setData(int index,
						Mpu6000ClockSource clockSource,
						Mpu6000Dlpf dlfp,
						Mpu6000AccRange accRange,
						Mpu6000GyroscopeRange gyroscopeRange)
{
	(( RemoteParameterUint8) this.get(Msg_mpu6000Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterMpu6000Settings) this.get(Msg_mpu6000Settings.INDEX_MIN_RANGE)).setClockSource(clockSource);
	(( RemoteParameterMpu6000Settings) this.get(Msg_mpu6000Settings.INDEX_MIN_RANGE)).setAccRange(accRange);
	(( RemoteParameterMpu6000Settings) this.get(Msg_mpu6000Settings.INDEX_MIN_RANGE)).setGyroscopeRange(gyroscopeRange);

}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_mpu6000Settings.INDEX_SENSOR)).getValue());
}



public static Msg_mpu6000Settings getCommand(int id)
{
	Msg_mpu6000Settings cmd;
	cmd = new Msg_mpu6000Settings(id);
	
	return(cmd);
}



public static Msg_mpu6000Settings getCommand(int command,int index, 
		Mpu6000ClockSource clockSource,
		Mpu6000Dlpf dlpf,
		Mpu6000AccRange accRange,
		Mpu6000GyroscopeRange gyroscopeRange
		)
{
	
	Msg_mpu6000Settings cmd;
	cmd = Msg_mpu6000Settings.getCommand(command);
	cmd.setData(index, clockSource, dlpf, accRange, gyroscopeRange);
	
	return(cmd);
}


}

