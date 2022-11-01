package de.hska.lat.robot.component.sensor.mpu6000;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerProtocol;
import de.hska.lat.robot.component.generic.compass.CompassProtocol;
import de.hska.lat.robot.component.generic.gyroscope.GyroscopeProtocol;
import de.hska.lat.robot.component.generic.imu.ImuProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Mpu6000Protocol extends ComponentProtocol {
	
	public final GyroscopeProtocol gyroscopeProtocol;
	public final AccelerometerProtocol accelerometerProtocol;
	public final ImuProtocol imuProtocol;
	public final CompassProtocol compassProtocol;
	public final TemperatureSensorProtocol temperatureSensorProtocol;
	
	public Mpu6000Protocol (int deviceId,

		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId,
		int cmdLoadDefaultsId,
		int msgSettingsId,
		

		int cmdGetAccelerationId,
		int msgAccelerationId, 
		int streamAccelerationId,

		int cmdGetAngularRaresId,
		int msgAngularRaresId, 
		int cmdStreamAngularRaresId,

		int cmdGetMagneticFieldId,
		int msgMagneticFieldId, 
		int streamMagneticFieldId,

		
		int getTemperatureId,
		int msgTemperatureId, 
		int streamTemperatureId,
		
		int cmdGetAnglesOfRotationId,
		int msgAnglesOfRotationId, 
		int streamAnglesOfRotationId
		)

	
	{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId
			);
	
	

	

	this.gyroscopeProtocol = new GyroscopeProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetAngularRaresId,
			msgAngularRaresId,
			cmdStreamAngularRaresId);
	
	
	this.accelerometerProtocol = new AccelerometerProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetAccelerationId,
			msgAccelerationId,
			streamAccelerationId
			);
	
	this.imuProtocol = new ImuProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetAnglesOfRotationId,
			msgAnglesOfRotationId,
			streamAnglesOfRotationId
			);
	
	this.compassProtocol = new CompassProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetMagneticFieldId,
			msgMagneticFieldId,
			streamMagneticFieldId
			);
	
	this.temperatureSensorProtocol = new TemperatureSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId, 
			getTemperatureId,
			msgTemperatureId,
			streamTemperatureId
			);
	
	}
	
	public ArrayList<RemoteCommandProcessor> getCommandProcessors(Mpu6000Set mpu9150Set)
	{
		ArrayList<RemoteCommandProcessor> commands =super.getCommandProcessors(mpu9150Set);
		
		commands.addAll(this.accelerometerProtocol.getCommandProcessors(mpu9150Set.getAccelerometerSet()));
		commands.addAll(this.gyroscopeProtocol.getCommandProcessors(mpu9150Set.getGyroscopeSet()));
		commands.addAll(this.compassProtocol.getCommandProcessors(mpu9150Set.getCompassSet()));
		commands.addAll(this.temperatureSensorProtocol.getCommandProcessors(mpu9150Set.getTemperatureSensorSet()));
		
		return(commands);
	}
	
	
	
	public ArrayList<RemoteMessageProcessor> getMessageProcessors(Mpu6000Set mpu9150Set)
	{
		ArrayList<RemoteMessageProcessor> messages =super.getMessageProcessors(mpu9150Set);
		
		messages.addAll(this.accelerometerProtocol.getMessageProcessors(mpu9150Set.getAccelerometerSet()));
		messages.addAll(this.gyroscopeProtocol.getMessageProcessors(mpu9150Set.getGyroscopeSet()));
		messages.addAll(this.compassProtocol.getMessageProcessors(mpu9150Set.getCompassSet()));
		messages.addAll(this.temperatureSensorProtocol.getMessageProcessors(mpu9150Set.getTemperatureSensorSet()));
		
		return(messages);
	}
		
	
	
	public ArrayList<RemoteStreamProcessor> getStreamProcessors(Mpu6000Set mpu9150Set)
	{
		ArrayList<RemoteStreamProcessor> streams =super.getStreamProcessors(mpu9150Set);
		
		streams.addAll(this.accelerometerProtocol.getStreamProcessors(mpu9150Set.getAccelerometerSet()));
		streams.addAll(this.gyroscopeProtocol.getStreamProcessors(mpu9150Set.getGyroscopeSet()));
		streams.addAll(this.compassProtocol.getStreamProcessors(mpu9150Set.getCompassSet()));
		streams.addAll(this.temperatureSensorProtocol.getStreamProcessors(mpu9150Set.getTemperatureSensorSet()));
		
		return(streams);
	}
		
	
}
