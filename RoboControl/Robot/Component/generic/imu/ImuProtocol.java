package de.hska.lat.robot.component.generic.imu;


import java.util.ArrayList;


import de.hska.lat.robot.component.generic.imu.protocol.Cmd_getAnglesOfRotation;
import de.hska.lat.robot.component.generic.imu.protocol.Msg_anglesOfRotation;
import de.hska.lat.robot.component.generic.imu.protocol.Stream_anglesOfRotation;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class ImuProtocol extends SensorProtocol

{

	
	public ImuProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdGetAnglesOfRotationId,
			int msgGetAnglesOfRotationId,
			int streamHeadingDataId)
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdGetAnglesOfRotationId,
				msgGetAnglesOfRotationId,
				streamHeadingDataId);
		
	}


/**
 * get gyroscope command processors
 * @param sensorSet gyroscope set
 * @return commands processors for gyroscope
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getAnglesOfRotation(this.cmdGetValueId), 
			decoder));
	
	return(commands);
}
	
/**
 * get gyroscope stream processors
 * @param sensorSet  gyroscope set
 * @return stream processors for gyroscope
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(super.getStreamProcessors(decoder));
	streams.add(new RemoteStreamProcessor(
			new Stream_anglesOfRotation(this.streamValuesId), 
			decoder));
	
	
	return (streams);
	
}

/**
 * get gyroscope message processors
 * @param sensorSet  gyroscope  set
 * @return message processors for gyroscope
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_anglesOfRotation(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	
		
	
	
}
