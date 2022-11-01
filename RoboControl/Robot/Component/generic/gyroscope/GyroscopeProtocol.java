package de.hska.lat.robot.component.generic.gyroscope;


import java.util.ArrayList;


import de.hska.lat.robot.component.generic.gyroscope.protocol.Cmd_getAngularRates;
import de.hska.lat.robot.component.generic.gyroscope.protocol.Msg_angularRates;
import de.hska.lat.robot.component.generic.gyroscope.protocol.Stream_angularRates;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class GyroscopeProtocol extends SensorProtocol
{


	
	public GyroscopeProtocol(int deviceId, 
				int cmdSetSettingsId, 
				int cmdGetSettingsId,
				int cmdSaveDefaultsId,
				int cmdLoadDefaultsId,
				int msgSettingsId,
				
				int cmdGetAngularRatesId,
				int msgAngularRatesId,
				int streamAngularRatesId)
	
	
	
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId, 
				cmdLoadDefaultsId,
				msgSettingsId, 
				cmdGetAngularRatesId,
				msgAngularRatesId,
				streamAngularRatesId);
		
	
	}

	

/**
 * get Imu command processors
 * @param sensorSet Imu set
 * @return commands processors for Imu
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getAngularRates(this.cmdGetValueId), 
			decoder));
	
	return(commands);
}
	
/**
 * get Imu stream processors
 * @param sensorSet  Imu set
 * @return stream processors for Imu
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(super.getStreamProcessors(decoder));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_angularRates(this.streamValuesId), 
			decoder));
	
	
	return (streams);
	
}

/**
 * get Imu message processors
 * @param sensorSet  Imu  set
 * @return message processors for Imu set
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_angularRates(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	
	
	
}
