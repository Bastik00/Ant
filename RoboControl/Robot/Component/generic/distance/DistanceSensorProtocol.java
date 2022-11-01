package de.hska.lat.robot.component.generic.distance;

import java.util.ArrayList;


import de.hska.lat.robot.component.generic.distance.protocol.Cmd_getDistance;
import de.hska.lat.robot.component.generic.distance.protocol.Msg_distance;
import de.hska.lat.robot.component.generic.distance.protocol.Stream_distances;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class DistanceSensorProtocol extends SensorProtocol
{
	

public DistanceSensorProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId, 
			int msgSettingsId,
			
			int cmdGetDistanceId,
			int msgDistanceId,
			int streamDistanceId
			)
{
	super(deviceId, 
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetDistanceId,
			msgDistanceId,
			streamDistanceId);
	

	
}



/**
 * get lux sensor command processors
 * @param sensorSet lux sensor set
 * @return commands processors for lux sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(DistanceSensorSet<?,?> sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getDistance(this.cmdGetValueId), 
			sensorSet));
	
	return(commands);
}
	
/**
 * get lux sensor stream processors
 * @param sensor  lux sensor set
 * @return stream processors for lux sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(DistanceSensorSet<?,?> sensor)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	
	streams.add(new RemoteStreamProcessor(
			new Stream_distances(this.streamValuesId), 
			sensor));
	
	
	return (streams);
	
}

/**
 * get lux sensor message processors
 * @param sensor  lux sensor set
 * @return message processors for lux sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(DistanceSensorSet<?,?> sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(sensorSet));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_distance(this.msgValueId), 
			sensorSet));
	
	
	return (messages);
}	




}
