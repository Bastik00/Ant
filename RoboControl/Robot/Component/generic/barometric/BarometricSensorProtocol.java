package de.hska.lat.robot.component.generic.barometric;


import java.util.ArrayList;

import de.hska.lat.robot.component.generic.barometric.protocol.Cmd_getBarometricPresure;
import de.hska.lat.robot.component.generic.barometric.protocol.Msg_barometricPresure;
import de.hska.lat.robot.component.generic.barometric.protocol.Stream_barometricPresures;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class BarometricSensorProtocol extends SensorProtocol
{
	
	
	 /**
	  * id of this component getDistance command
	  */


		
public BarometricSensorProtocol(int deviceId,
			int cmdSetSettingsId, 
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdloadDefaultsId, 
			int msgSettingsId,
			
			int cmdGetPresureId,
			int msgPresureId,
			int streamPresureId
			)
{
	super(deviceId,cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdloadDefaultsId,
			msgSettingsId,
			cmdGetPresureId,
			msgPresureId,
			streamPresureId);
	
	

}



/**
 * get barometric sensor command processors
 * @param sensorSet barometric sensor set
 * @return commands processors for barometric sensor
 */

public ArrayList<RemoteCommandProcessor> getCommandProcessors(BarometricSensorSet<?,?> sensorSet)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(sensorSet);
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_getBarometricPresure(this.cmdGetValueId), 
			sensorSet));
	
	return(commands);
}
	
/**
 * get barometric sensor stream processors
 * @param sensor  barometric sensor set
 * @return stream processors for barometric sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(BarometricSensorSet<?,?> sensor)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	
	
	streams.add(new RemoteStreamProcessor(
			new Stream_barometricPresures(this.streamValuesId), 
			sensor));
	
	
	return (streams);
	
}

/**
 * get barometric sensor message processors
 * @param sensor  barometric sensor set
 * @return message processors for barometric sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(BarometricSensorSet<?,?> sensorSet)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(sensorSet));
	
	messages.add(new RemoteMessageProcessor(
			new Msg_barometricPresure(this.msgValueId), 
			sensorSet));
	
	
	return (messages);
}	




}
