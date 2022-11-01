package de.hska.lat.robot.component.generic.colorSensor;


import java.util.ArrayList;

import de.hska.lat.robot.component.generic.colorSensor.protocol.Cmd_getHsvColor;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Cmd_getRgbColor;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Msg_hsvColor;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Msg_rgbColor;

import de.hska.lat.robot.component.generic.colorSensor.protocol.Stream_hsvColors;
import de.hska.lat.robot.component.generic.colorSensor.protocol.Stream_rgbColors;
import de.hska.lat.robot.component.sensor.SensorProtocol;

import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;


public class ColorSensorProtocol extends SensorProtocol
{
	
	public final int cmdGetRgbColorId;
	public final int msgRgbColorId;
	public final int streamRgbColorsId;
	
public ColorSensorProtocol(int deviceId,
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
	super(deviceId,cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetHsvColorId,
			msgHsvColorId,
			streamHsvColorsId);
	
	this.msgRgbColorId = msgRgbColorId;
	this.cmdGetRgbColorId = cmdGetRgbColorId;
	this.streamRgbColorsId = streamRgbColorsId;
}






public ArrayList<RemoteCommandProcessor> getCommandProcessors(ColorSensorSet<?,?> sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getHsvColor(this.cmdGetValueId), 
			sensorSet));

	commands.add(new RemoteCommandProcessor(
			new Cmd_getRgbColor(this.cmdGetRgbColorId), 
			sensorSet));
	

	
	
	return(commands);
}
	
/**
 * get color sensor stream processors
 * @param sensor  color sensor set
 * @return stream processors for color sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(ColorSensorSet<?,?> sensor)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	
	streams.add(new RemoteStreamProcessor(
			new Stream_hsvColors(this.streamValuesId), 
			sensor));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_rgbColors(this.streamRgbColorsId), 
			sensor));
	
	return (streams);
	
}

/**
 * get color sensor message processors
 * @param sensor  color sensor set
 * @return message processors for color sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(ColorSensorSet<?,?> sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(sensorSet));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_hsvColor(this.msgValueId), 
			sensorSet));
	

	messages.add(new RemoteMessageProcessor(
			new Msg_rgbColor(this.msgRgbColorId), 
			sensorSet));
	
	
	return (messages);
}	





}
