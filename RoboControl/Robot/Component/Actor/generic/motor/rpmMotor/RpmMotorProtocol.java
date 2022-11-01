package de.hska.lat.robot.component.actor.generic.motor.rpmMotor;

import java.util.ArrayList;


import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotorProtocol;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Msg_motorPwm;

import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Cmd_getMotorRpm;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Cmd_setMotorRpm;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Stream_motorsRpm;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;



public class RpmMotorProtocol extends PwmMotorProtocol
{

	public final int cmdSetRpmId;
	public final int cmdGetRpmId;
	public final int streamRpmId;
	public final int msgRpmId;
	
	
	
	public RpmMotorProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdSetPwmId,
			int cmdGetPwmId, 
			int msgPwmId,
			int streamPwmId,
			int cmdMotorOnId,
			int cmdMotorBrakeId,
			int cmdMotorStopId,
			
			
			int cmdSetRpmId,
			int cmdGetRpmId,
			int msgRpmId,
			int streamRpmId
			)
	{
	
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdSetPwmId,
				cmdGetPwmId,
				msgPwmId,
				streamPwmId,
				cmdMotorOnId,
				cmdMotorBrakeId,
				cmdMotorStopId);
	
		this.cmdSetRpmId = cmdSetRpmId;
		this.cmdGetRpmId = cmdGetRpmId;
		this.streamRpmId = streamRpmId;
		this.msgRpmId = msgRpmId;
	}

	
	

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setMotorRpm(this.cmdSetValueId), 
			decoder));
	
		
	commands.add(new RemoteCommandProcessor(
			 new Cmd_getMotorRpm(this.cmdGetValueId), 
			 decoder));

	


	
	return(commands);
}
	
/**
 * get temperature sensor stream processors
 * @param sensor  temperature sensor set
 * @return stream processors for temperature sensor
 */

public ArrayList<RemoteStreamProcessor> getStreamProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
	streams.addAll(super.getStreamProcessors(decoder));
	
	streams.add(new RemoteStreamProcessor(
			new Stream_motorsRpm(this.streamValuesId), 
			decoder));

	
	return (streams);
	
}

/**
 * get temperature sensor message processors
 * @param sensor  temperature sensor set
 * @return message processors for temperature sensor
 */

public ArrayList<RemoteMessageProcessor> getMessageProcessors(RemoteDecoder decoder)
{
	
	ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();
	
	messages.addAll(super.getMessageProcessors(decoder));

	messages.add(new RemoteMessageProcessor(
			new Msg_motorPwm(this.msgValueId), 
			decoder));
	
	
	
	return (messages);
}	
		
	
	
}
