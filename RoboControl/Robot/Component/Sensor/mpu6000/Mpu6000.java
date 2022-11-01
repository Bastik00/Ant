package de.hska.lat.robot.component.sensor.mpu6000;

import java.util.ArrayList;


import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.mpu6000.protocol.Cmd_setMpu6000Settings;

import de.hska.lat.robot.value.ComponentValue;

public class Mpu6000 extends RobotComponent<Mpu6000ChangeNotifier,ComponentSettingsChangeNotifier,Mpu6000Protocol >
{

	
	protected Mpu6000Accelerometer accelerometer;
	
	protected Mpu6000Gyroscope gyroscope;

	protected Mpu6000Compass compass;
	protected Mpu6000TemperatureSensor temperatureSensor;
	
	
	
	protected Mpu6000AccRange	 		accRange = Mpu6000AccRange.ACC_RANGE_2G;
	protected Mpu6000GyroscopeRange		gyroscopeRange = Mpu6000GyroscopeRange.GYROSCOPE_RANGE_250;
	protected Mpu6000ClockSource 		clockSource = Mpu6000ClockSource.CLOCK_SELECTION_INTERNAL_8_MHZ;
	protected Mpu6000Dlpf				dlpf = Mpu6000Dlpf.FILTER_BANDWIDTH_260;
	
	
	
	public Mpu6000(ComponentMetaData metaData, Mpu6000Protocol protocol)
	{
		super(metaData, protocol);

		this.gyroscope = new Mpu6000Gyroscope(metaData, protocol.gyroscopeProtocol);
		
		this.accelerometer = new Mpu6000Accelerometer(metaData,protocol.accelerometerProtocol);

		this.temperatureSensor =  new Mpu6000TemperatureSensor(metaData, protocol.temperatureSensorProtocol);
		
		this.compass = new Mpu6000Compass(metaData, protocol.compassProtocol);
		
	
		
		
		
		
	}

	public Mpu6000Gyroscope getGyroscope()
	{
		return (this.gyroscope);
	}

	
/**
 * get Mpu 9150 Accelerometer	
 * @return Mpus 9150 Accelerometer
 */
	
public Mpu6000Accelerometer getAccelerometer()
{
	return (this.accelerometer);
}




	public Mpu6000Compass getCompass()
	{
		return (this.compass);
	}

	public Mpu6000TemperatureSensor getTemperatureSensor()
	{
		return (this.temperatureSensor);
	}
	
	@Override
	public ArrayList<ComponentValue<?>> getDataValues()
	{
		
		ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
		
		values.addAll(this.gyroscope.getDataValues());
		values.addAll(this.accelerometer.getDataValues());
		values.addAll(this.compass.getDataValues());
		values.addAll(this.temperatureSensor.getDataValues());
		
		return (values);
	}

	
	
	

	
public Mpu6000ClockSource getClockSource()
{
	return (this.clockSource);
}

public Mpu6000AccRange getAccRange()
{
	return (this.accRange);
}
	

public Mpu6000GyroscopeRange getGyroscopeRange()
{
	return (this.gyroscopeRange);
}

public Mpu6000Dlpf getDlpfRange()
{
	return (this.dlpf);
}
	



public boolean remote_setSettings(
									Mpu6000ClockSource clockSource,
									Mpu6000Dlpf dlpf,
									Mpu6000AccRange accRange,
									Mpu6000GyroscopeRange gyroscopeRange
		)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMpu6000Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			clockSource,
			dlpf,
			accRange, 
			gyroscopeRange
			)));
}





@Override
public void setTransmitter(RemoteDataTransmitter transmitter)
{
	super.setTransmitter(transmitter);
	
	
	this.accelerometer.setTransmitter(transmitter);
	this.gyroscope.setTransmitter(transmitter);
	this.compass.setTransmitter(transmitter);
	this.temperatureSensor.setTransmitter(transmitter);
	
}





}
