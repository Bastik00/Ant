package de.hska.lat.robot.component.sensor.bmp085;


import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.barometric.BarometricSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Bmp085Protocol extends ComponentProtocol
{

	
	public final TemperatureSensorProtocol temperatureProtocol;
	public final BarometricSensorProtocol presureProtocol;
	
	
public Bmp085Protocol(int deviceId,
		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId,
		int cmdLoadDefaultsId,
		int msgSettings,
		
		int cmdGetTemperatureId,
		int msgTemperatureId,
		int streamTemperatureId,
		
		int cmdGetPresureId,
		int msgPresureId,
		int streamPresureId

		
		)
{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings
			);
	

	
	this.temperatureProtocol = new TemperatureSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			
			cmdGetTemperatureId,
			msgTemperatureId,
			streamTemperatureId);
	
	
	this.presureProtocol = new BarometricSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			
			cmdGetPresureId,
			msgPresureId,
			streamPresureId
			);
}






/**
 * get BMP085 sensor command processors
 * @param sensorSet BMP085 sensor set
 * @return commands processors for BMP085 sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(Bmp085Set sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = new ArrayList<RemoteCommandProcessor>();
	
	commands.addAll(this.temperatureProtocol.getCommandProcessors(sensorSet.getTemperatureSensors()));
	commands.addAll(this.presureProtocol.getCommandProcessors(sensorSet.getPresureSensors()));
	
	
	return(commands);
}
	
/**
 * get BMP085 sensor stream processors
 * @param sensor  BMP085 sensor set
 * @return stream processors for BMP085 sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(Bmp085Set sensorSet)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(this.temperatureProtocol.getStreamProcessors(sensorSet.getTemperatureSensors()));
	streams.addAll(this.presureProtocol.getStreamProcessors(sensorSet.getPresureSensors()));
	
	return (streams);
	
}

/**
 * get BMP085 sensor message processors
 * @param sensor  BMP085 sensor set
 * @return message processors for BMP085 sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(Bmp085Set sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(this.temperatureProtocol.getMessageProcessors(sensorSet.getTemperatureSensors()));
	messages.addAll(this.presureProtocol.getMessageProcessors(sensorSet.getPresureSensors()));
	
	return (messages);
}	



}
