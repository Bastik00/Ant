package de.hska.lat.robot.component.generic.spectralBand;

import java.util.ArrayList;




import de.hska.lat.robot.component.actor.ActorProtocol;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Msg_motorPwm;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_setSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Stream_spectralBand;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;



public class SpectralBandProtocol extends ActorProtocol
{	
	
	
	public SpectralBandProtocol(int deviceId, 
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdSetValueId,
			int cmdGetValueId,
			int msgValueId,
			int streamValuesId
			)
	{
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdSetValueId,
				cmdGetValueId,
				msgValueId,
				streamValuesId);
	}

	
	
	
	
	/*
	 * 	 this.commandList.add(new RemoteCommandProcessor(
				 new Cmd_motorOn(MotionControllerProtocol.CMD_MOTOR_ON), 
				 agent.getMotors()));

		 
		 
		 this.commandList.add(new RemoteCommandProcessor(
				 new Cmd_setMotorVelocity(MotionControllerProtocol.CMD_SET_MOTOR_VELOCITY), 
				 agent.getMotors()));
		 
		 
		 this.commandList.add(new RemoteCommandProcessor(
				 new Cmd_motorBrake(MotionControllerProtocol.CMD_MOTOR_BRAKE), 
				 agent.getMotors()));
		 

	 */
	

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);

	commands.addAll(super.getCommandProcessors(decoder));
	
	commands.add(new RemoteCommandProcessor(
			new Cmd_setSpectralBand(this.cmdSetValueId),
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
			new Stream_spectralBand(this.streamValuesId), 
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
