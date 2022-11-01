package de.hska.lat.robot.component.generic.compass;


import java.util.ArrayList;


import de.hska.lat.robot.component.generic.compass.protocol.Cmd_getMagneticField;
import de.hska.lat.robot.component.generic.compass.protocol.Msg_geomagneticField;
import de.hska.lat.robot.component.generic.compass.protocol.Stream_geomagneticField;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class CompassProtocol extends SensorProtocol
{
	
	

	public CompassProtocol(int deviceId, 
			int cmdSetSettingsId, 
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdGetMagneticFieldId,
			int msgMagneticFieldId,
			int streamMagneticFieldId
			)
	{
	super(deviceId, 
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId, 
			cmdLoadDefaultsId,
			msgSettingsId, 
			cmdGetMagneticFieldId,
			msgMagneticFieldId,
			streamMagneticFieldId);
	
	
	
	}




/**
 * get compass command processors
 * @param sensorSet decoder for this commands
 * @return commands processors for comapss 
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getMagneticField(this.cmdGetValueId), 
			decoder));
	
	return(commands);
}
	


/**
 * get compass  stream processors
 * @param decoder decoder for this streams
 * @return stream processors for compass 
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	streams.addAll(super.getStreamProcessors(decoder));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_geomagneticField(this.streamValuesId), 
			decoder));
	
	
	return (streams);
	
}

/**
 * get compass message processors
 * @param decoder decoder for this messages
 * @return message processors for compass 
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_geomagneticField(this.msgValueId), 
			decoder));
	
	
	return (messages);
}	

}
