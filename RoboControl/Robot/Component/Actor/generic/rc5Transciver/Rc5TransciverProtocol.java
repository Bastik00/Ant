package de.hska.lat.robot.component.actor.generic.rc5Transciver;

import java.util.ArrayList;

import de.hska.lat.robot.component.actor.ActorProtocol;

import de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol.Cmd_rc5SendCommand;
import de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol.Msg_rc5Test;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Rc5TransciverProtocol extends ActorProtocol
{

	public Rc5TransciverProtocol(int deviceId, 
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdSendRc5CommandId,
			int cmdGetPwmId,
			int msgPwmId,
			int streamPwmId)
	{
		super(deviceId, cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdSendRc5CommandId,
				cmdGetPwmId,
				msgPwmId,
				streamPwmId);



	}


public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_rc5SendCommand(this.cmdSetValueId), 
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
	
/*	streams.add(new RemoteStreamProcessor(
			new Msg_rc5Test(this.streamValuesId), 
			decoder));
	*/
	
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
			new Msg_rc5Test(this.msgValueId), 
			decoder));
	
	return (messages);
}	
	


}
