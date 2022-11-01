package de.hska.lat.robot.component.generic.heading;

import java.util.ArrayList;


import de.hska.lat.robot.component.generic.heading.protocol.Cmd_getHeading;
import de.hska.lat.robot.component.generic.heading.protocol.Msg_heading3D;
import de.hska.lat.robot.component.generic.heading.protocol.Stream_heading3D;

import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Heading3DProtocol extends SensorProtocol

{
	protected final int cmdSetHeadingId;
	
	public Heading3DProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdSetHeadingId,
			int cmdGetHeadingId,
			int msgHeadingId,
			int streamHeadingDataId)
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId, 
				cmdGetHeadingId, 
				msgHeadingId,
				streamHeadingDataId);
		
		this.cmdSetHeadingId = cmdSetHeadingId;
	}






public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getHeading(this.cmdGetValueId), 
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
			new Stream_heading3D(this.streamValuesId), 
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
			new Msg_heading3D(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	

}