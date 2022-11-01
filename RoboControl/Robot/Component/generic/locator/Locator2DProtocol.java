package de.hska.lat.robot.component.generic.locator;


import java.util.ArrayList;

import de.hska.lat.robot.component.generic.locator.protocol.Cmd_getLocation;
import de.hska.lat.robot.component.generic.locator.protocol.Cmd_setLocation2D;
import de.hska.lat.robot.component.generic.locator.protocol.Msg_location2D;
import de.hska.lat.robot.component.generic.locator.protocol.Stream_location2D;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Locator2DProtocol extends SensorProtocol
{

	public final int cmdSetLocationId;
	
	
	public Locator2DProtocol(int deviceId, 
				int cmdSetSettingsId, 
				int cmdGetSettingsId,
				int cmdSaveDefaultsId,
				int cmdLoadDefaultsId,
				int msgSettingsId,
				int cmdSetLocationId,
				int cmdGetLocationId,
				int msgLocationId,
				int streamLocationId)
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId, 
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdGetLocationId,
				msgLocationId,
				streamLocationId);
		
	
		this.cmdSetLocationId = cmdSetLocationId;
	}



public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getLocation(this.cmdGetValueId), 
			decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setLocation2D(this.cmdSetLocationId), 
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
			new Stream_location2D(this.streamValuesId), 
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
			new Msg_location2D(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	

	
}
