package de.hska.lat.robot.component.actor.led;

import java.util.ArrayList;


import de.hska.lat.robot.component.actor.led.protocol.Cmd_setLedColor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;




public class RgbLedProtocol extends LedProtocol
{

	public final int cmdSetColorId;
	public final int streamColors;
	
	public RgbLedProtocol(int deviceId, 
			int cmd_set_settings_id,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettings,
			int cmdSetBrightnessId,
			int cmdGetBrightnessId,
			int msgBrightnessId,
			int streamBrightnessId,
			int cmdSetColorId,
		
			int streamColorsId)
	{
		super(deviceId, cmd_set_settings_id,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettings,
				cmdSetBrightnessId,
				cmdGetBrightnessId,
				msgBrightnessId,
				streamBrightnessId);

	this.cmdSetColorId = cmdSetColorId;
	this.streamColors = streamColorsId;
	
	}



/**
 * get led command processors
 * @param remoteDecoder barometric sensor set
 * @return commands processors for barometric sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder remoteDecoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(remoteDecoder);

	commands.add(new RemoteCommandProcessor(
			new Cmd_setLedColor(this.cmdSetColorId), 
			remoteDecoder));
	
	return(commands);
}
	
/**
 * get led stream processors
 * @param remoteDecoder  led set
 * @return stream processors for led
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder remoteDecoder)
{
	
	ArrayList<RemoteStreamProcessor> streams =super.getStreamProcessors(remoteDecoder);
	
	/*
	streams.add(new RemoteStreamProcessor(
			new Stream_barometricPresures(this.streamValuesId), 
			sensor));
	*/
	
	return (streams);
	
}

/**
 * get led message processors
 * @param remoteDecoder led set
 * @return message processors for led
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder remoteDecoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = super.getMessageProcessors(remoteDecoder);
	
	/*
	messages.add(new RemoteMessageProcessor(
			new Msg_barometricPresure(this.msgValueId), 
			sensorSet));
	
	*/
	return (messages);
}	
}