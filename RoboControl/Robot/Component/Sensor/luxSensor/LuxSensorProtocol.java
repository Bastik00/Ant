package de.hska.lat.robot.component.generic.luxSensor;

import java.util.ArrayList;

import de.hska.lat.robot.component.generic.luxSensor.protocol.Cmd_getLux;
import de.hska.lat.robot.component.generic.luxSensor.protocol.Msg_lux;
import de.hska.lat.robot.component.generic.luxSensor.protocol.Stream_lux;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class LuxSensorProtocol extends SensorProtocol
{
	
	


		
public LuxSensorProtocol(int deviceId,
			int cmdSetSettingsId, 
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId, 
			int msgSettingsId,
			
			int cmdGetLuxId,
			int msgLuxId,
			int streamLuxId
			)
{
	super(deviceId, 
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdGetLuxId,
			msgLuxId,
			streamLuxId);
	
	

}



/**
 * get lux sensor command processors
 * @param sensorSet lux sensor set
 * @return commands processors for lux sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(LuxSensorSet<?,?> sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getLux(this.cmdGetValueId), 
			sensorSet));
	
	return(commands);
}
	
/**
 * get lux sensor stream processors
 * @param sensor  lux sensor set
 * @return stream processors for lux sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(LuxSensorSet<?,?> sensor)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	
	streams.add(new RemoteStreamProcessor(
			new Stream_lux(this.streamValuesId), 
			sensor));
	
	
	return (streams);
	
}

/**
 * get lux sensor message processors
 * @param sensor  lux sensor set
 * @return message processors for lux sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(LuxSensorSet<?,?> sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(sensorSet));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_lux(this.msgValueId), 
			sensorSet));
	
	
	return (messages);
}	






}
