package de.hska.lat.robot.component.generic.accelerometer;

import java.util.ArrayList;


import de.hska.lat.robot.component.generic.accelerometer.protocol.Cmd_getAcceleration;
import de.hska.lat.robot.component.generic.accelerometer.protocol.Msg_acceleration;
import de.hska.lat.robot.component.generic.accelerometer.protocol.Stream_accelerations;

import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class AccelerometerProtocol extends SensorProtocol
{


	
	public AccelerometerProtocol(int deviceId, 
			int cmdSetSettingsId, 
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdGetValueId,
			int msgValueId,
			int streamAccValuesId)
{
	super(deviceId, 
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId, 
			cmdLoadDefaultsId,
			msgSettingsId, 
			cmdGetValueId,
			msgValueId, 
			streamAccValuesId);


	
	}






/**
 * get Accelerometer  command processors
 * @param sensorSet Accelerometer  set
 * @return commands processors for Accelerometer 
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getAcceleration(this.cmdGetValueId), 
			decoder));
	
	return(commands);
}
	


/**
 * get Accelerometer  stream processors
 * @param sensor  Accelerometer  set
 * @return stream processors for Accelerometer 
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(super.getStreamProcessors(decoder));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_accelerations(this.streamValuesId), 
			decoder));
	
	
	return (streams);
	
}

/**
 * get Accelerometer message processors
 * @param sensor  Accelerometer  set
 * @return message processors for Accelerometer 
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_acceleration(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	





}
