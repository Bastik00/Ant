package de.hska.lat.robot.component.sensor.lm75;

import java.util.ArrayList;


import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Cmd_setTmp102Settings;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Msg_tmp102Settings;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;

public class Lm75Protocol extends TemperatureSensorProtocol
{

	public Lm75Protocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettings,
			int getTemperatureId,
			int msgTemperatureId,
			int streamTemperatureId)
	{
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId, 
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettings,
				getTemperatureId,
				msgTemperatureId,
				streamTemperatureId);
	
	}

	
/**
 * get all tmp102 Commands
 * @param sensorSet 
 * @return
 */
public ArrayList<RemoteCommandProcessor> getCommandProcessors(Lm75Set sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setTmp102Settings(this.cmdGetSettingsId), 
			sensorSet));
	
	return(commands);
}
	
	


public ArrayList<RemoteMessageProcessor> getMessageProcessors(Lm75Set sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(sensorSet));
	
	
	messages.add(new RemoteMessageProcessor(
			new Msg_tmp102Settings(this.msgSettingsId), 
			sensorSet));
	
	
	return (messages);
}	
	
}
