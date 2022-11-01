package de.hska.lat.robot.component.temperatureSensor.mlx90614;


import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.protocol.Cmd_setMlx90614Settings;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.protocol.Msg_mlx90614Settings;

import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class Mlx90614Protocol extends ComponentProtocol
{

	public final TemperatureSensorProtocol ambientProtocol;
	public final TemperatureSensorProtocol objectProtocol;
	
public Mlx90614Protocol(int deviceId,
		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId,
		int cmdLoadDefaultsId,
		int msgSettings,
		
		
		int cmdGetAmbientTemperature,
		int msgAmbientTemperature,
		int streamAmbientTemperature,
		
		int cmdGetObjectTemperature,
		int msgObjectTemperature,
		int streamObjectTemperature
		)
{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings
			);

	
	
	this.ambientProtocol = new TemperatureSensorProtocol(deviceId,
				 cmdSetSettingsId,
				 cmdGetSettingsId,
				 cmdSaveDefaultsId,
				 cmdLoadDefaultsId,
				 msgSettings,
				 cmdGetAmbientTemperature,
				 msgAmbientTemperature,
				 streamAmbientTemperature
		 );
	
	
	this.objectProtocol = new TemperatureSensorProtocol(deviceId,
				 cmdSetSettingsId,
				 cmdGetSettingsId,
				 cmdSaveDefaultsId,
				 cmdLoadDefaultsId,
				 msgSettings,
				 cmdGetObjectTemperature,
				 msgObjectTemperature,
				 streamObjectTemperature
			 );
	}
	


/**
* get all tmp006 Commands
* @param sensorSet 
* @return
*/
public ArrayList<RemoteCommandProcessor> getCommandProcessors(Mlx90614Set sensorSet)	
{

	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	
	commands.addAll(this.ambientProtocol.getCommandProcessors(sensorSet.getAmbientSensors()));
	commands.addAll(this.objectProtocol.getCommandProcessors(sensorSet.getObjectSensors()));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setMlx90614Settings(this.cmdSetSettingsId), 
			sensorSet));
	
	return(commands);
}




public ArrayList<RemoteMessageProcessor> getMessageProcessors(Mlx90614Set sensorSet)
{

	ArrayList<RemoteMessageProcessor> messages = super.getMessageProcessors(sensorSet);
	
	messages.addAll(this.ambientProtocol.getMessageProcessors(sensorSet.getAmbientSensors()));
	messages.addAll(this.objectProtocol.getMessageProcessors(sensorSet.getObjectSensors()));
	
	
	messages.add(new RemoteMessageProcessor(
			new Msg_mlx90614Settings(this.msgSettingsId), 
			sensorSet));
	
	
	return (messages);
}	



public ArrayList<RemoteStreamProcessor> getStreamProcessors(Mlx90614Set sensorSet)
{

	ArrayList<RemoteStreamProcessor> streams = super.getStreamProcessors(sensorSet);
	
	streams.addAll(this.ambientProtocol.getStreamProcessors(sensorSet.getAmbientSensors()));
	streams.addAll(this.objectProtocol.getStreamProcessors(sensorSet.getObjectSensors()));
	
	
	return (streams);
}	



}


