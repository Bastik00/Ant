package de.hska.lat.robot.component.generic.temperatureSensor;


import java.util.ArrayList;

import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Cmd_getTemperature;
import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Msg_temperature;
import de.hska.lat.robot.component.generic.temperatureSensor.protocol.Stream_temperatures;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;



public class TemperatureSensorProtocol extends SensorProtocol
{
	
	


		
public TemperatureSensorProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId, 
			int msgSettingsId,
			
			int getTemperatureId,
			int msgTemperatureId,
			int streamTemperatureId
			)
{
	super(deviceId,cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			getTemperatureId,
			msgTemperatureId,
			streamTemperatureId);
	
}


/**
 * get Temperature sensor command processors
 * @param sensorSet temperature sensor set
 * @return commands processors for temperature sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getTemperature(this.cmdGetValueId), 
			decoder));
	
	return(commands);
}
	
/**
 * get temperature sensor stream processors
 * @param sensor  temperature sensor set
 * @return stream processors for temperature sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	streams.addAll(super.getStreamProcessors(decoder));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_temperatures(this.streamValuesId), 
			decoder));
	
	
	return (streams);
	
}

/**
 * get temperature sensor message processors
 * @param sensor  temperature sensor set
 * @return message processors for temperature sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_temperature(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	



}
