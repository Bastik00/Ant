package de.hska.lat.robot.component.actor.generic.motor.velocityMotor;

import java.util.ArrayList;



import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.RpmMotorProtocol;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Stream_motorsVelocity;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.Cmd_getMotorVelocity;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.Cmd_setMotorVelocity;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.Msg_motorVelocity;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;



public class VelocityMotorProtocol extends RpmMotorProtocol
{

	public final int cmdSetVelocityId;
	public final int cmdGetVelocityId;
	public final int streamVelocityId;
	public final int msgVelocityId;
	
	
	
	public VelocityMotorProtocol(int deviceId,
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
			int streamRpmId,
			
			int cmdSetVelocityId,
			int cmdGetVelocityId,
			int msgVelocityId,
			int streamVelocityId)
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
				cmdMotorStopId,
				cmdSetRpmId,
				cmdGetRpmId,
				streamRpmId,
				msgRpmId);
	
		this.cmdSetVelocityId = cmdSetVelocityId;
		this.cmdGetVelocityId = cmdGetVelocityId;
		this.msgVelocityId = msgVelocityId;
		this.streamVelocityId = streamVelocityId;
	}

	
	

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setMotorVelocity(this.cmdSetVelocityId), 
			decoder));
	
	
	
	commands.add(new RemoteCommandProcessor(
			 new Cmd_getMotorVelocity(this.cmdGetVelocityId), 
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
			new Stream_motorsVelocity(this.streamVelocityId), 
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
			new Msg_motorVelocity(this.msgVelocityId), 
			decoder));

	
	
	return (messages);
}	
		
	
	
}
