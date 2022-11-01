package de.hska.lat.robot.component.sensor.adjdS311;


import java.util.ArrayList;

import de.hska.lat.robot.component.generic.colorSensor.ColorSensorProtocol;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.component.sensor.adjdS311.protocol.Cmd_setAdjdS311Settings;
import de.hska.lat.robot.component.sensor.adjdS311.protocol.Msg_adjdS311Settings;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class AdjdS311Protocol extends SensorProtocol
{

	
	public final ColorSensorProtocol colorProtocol;

	
	
public AdjdS311Protocol(int deviceId,
		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId,
		int cmdLoadDefaultsId, 
		int msgSettingsId,
		
		int cmdGetHsvColorId,
		int cmdGetRgbColorId,
		int msgHsvColorId,
		int msgRgbColorId,
		int streamHsvColorsId,
		int streamRgbColorsId

		
		)
{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			
			cmdGetHsvColorId,
			msgHsvColorId,
			streamHsvColorsId
			);
	

	
	this.colorProtocol = new ColorSensorProtocol(deviceId,
			 cmdSetSettingsId,
			 cmdGetSettingsId,
			 cmdSaveDefaultsId,
			 cmdLoadDefaultsId, 
			 msgSettingsId,
			
			cmdGetHsvColorId,
			cmdGetRgbColorId,
			msgHsvColorId,
			msgRgbColorId,
			streamHsvColorsId,
			streamRgbColorsId);
	
	/*
	this.presureProtocol = new BarometricSensorProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			
			cmdGetPresureId,
			msgPresureId,
			streamPresureId
			);
	*/
}






/**
 * get ADJD-S311 sensor command processors
 * @param sensorSet ADJD-S311 sensor set
 * @return commands processors for ADJD-S311 sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(AdjdS311Set sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = new ArrayList<RemoteCommandProcessor>();
	
	commands.addAll(this.colorProtocol.getCommandProcessors(sensorSet.getColorSensors()));
//	commands.addAll(this.presureProtocol.getCommandProcessors(sensorSet.getPresureSensors()));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setAdjdS311Settings(this.cmdSetSettingsId), 
			sensorSet));
	
	return(commands);
}
	
/**
 * get ADJD-S311 sensor stream processors
 * @param sensor  ADJD-S311 sensor set
 * @return stream processors for ADJD-S311 sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(AdjdS311Set sensorSet)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(this.colorProtocol.getStreamProcessors(sensorSet.getColorSensors()));
//	streams.addAll(this.presureProtocol.getStreamProcessors(sensorSet.getPresureSensors()));
	
	return (streams);
	
}

/**
 * get ADJD-S311 sensor message processors
 * @param sensor  ADJD-S311 sensor set
 * @return message processors for ADJD-S311 sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(AdjdS311Set sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(this.colorProtocol.getMessageProcessors(sensorSet.getColorSensors()));
//	messages.addAll(this.presureProtocol.getMessageProcessors(sensorSet.getPresureSensors()));
	
	
	messages.add(new RemoteMessageProcessor(
			new Msg_adjdS311Settings(this.msgSettingsId), 
			sensorSet));
	
	return (messages);
}	



}
