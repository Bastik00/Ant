package de.hska.lat.robot.component.sensor.tmp006;

import java.util.ArrayList;


import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.sensor.tmp006.protocol.Cmd_setTmp006Settings;
import de.hska.lat.robot.component.sensor.tmp006.protocol.Msg_tmp006Settings;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Tmp006Protocol extends ComponentProtocol
{

	public final TemperatureSensorProtocol ambientTemperatureProtocol;
	public final TemperatureSensorProtocol objectTemperatureProtocol;
	
	
	public Tmp006Protocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettings,
			int cmdGetAmbientTemperatureId,
			int msgAmbientTemperatureId,
			int streamAmbientTemperatureId,
			int cmdGetObjectTemperatureId,
			int msgObjectTemperatureId,
			int streamObjectTemperatureId)
	{
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId, 
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettings
				);
	
		
		
	this.ambientTemperatureProtocol = new 	TemperatureSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId, 
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			cmdGetAmbientTemperatureId,
			msgAmbientTemperatureId,
			streamAmbientTemperatureId
			);

	
	this.objectTemperatureProtocol = new 	TemperatureSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId, 
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			cmdGetObjectTemperatureId,
			msgObjectTemperatureId,
			streamObjectTemperatureId
			);
		
	}

	
/**
 * get all tmp006 Commands
 * @param sensorSet 
 * @return
 */
public ArrayList<RemoteCommandProcessor> getCommandProcessors(Tmp006Set sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	
	commands.addAll(this.ambientTemperatureProtocol.getCommandProcessors(sensorSet.getAmbientSensors()));
	commands.addAll(this.objectTemperatureProtocol.getCommandProcessors(sensorSet.getObjectSensors()));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setTmp006Settings(this.cmdGetSettingsId), 
			sensorSet));
	
	return(commands);
}
	
	


public ArrayList<RemoteMessageProcessor> getMessageProcessors(Tmp006Set sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = super.getMessageProcessors(sensorSet);
	
	messages.addAll(this.ambientTemperatureProtocol.getMessageProcessors(sensorSet.getAmbientSensors()));
	messages.addAll(this.objectTemperatureProtocol.getMessageProcessors(sensorSet.getObjectSensors()));
	
	
	messages.add(new RemoteMessageProcessor(
			new Msg_tmp006Settings(this.msgSettingsId), 
			sensorSet));
	
	
	return (messages);
}	



public ArrayList<RemoteStreamProcessor> getStreamProcessors(Tmp006Set sensorSet)
{
	
	ArrayList<RemoteStreamProcessor> streams = super.getStreamProcessors(sensorSet);
	
	streams.addAll(this.ambientTemperatureProtocol.getStreamProcessors(sensorSet.getAmbientSensors()));
	streams.addAll(this.objectTemperatureProtocol.getStreamProcessors(sensorSet.getObjectSensors()));
	

	return (streams);
}	


}
