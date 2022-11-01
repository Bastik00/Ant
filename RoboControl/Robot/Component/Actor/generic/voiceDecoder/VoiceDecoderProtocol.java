package de.hska.lat.robot.component.actor.generic.voiceDecoder;

import java.util.ArrayList;


import de.hska.lat.robot.component.actor.ActorProtocol;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_motorBrake;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_motorOn;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_motorStop;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_setMotorPwm;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_setMotorSettings;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Msg_motorPwm;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Stream_motorPwm;

import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;



public class VoiceDecoderProtocol extends ActorProtocol
{

	
	public final int cmdMotorOnId;
	public final int cmdMotorBrakeId;
	public final int cmdMotorStopId;
	
	
	
	
	
	public VoiceDecoderProtocol(int deviceId, 
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
			int cmdMotorStopId)
	{
		super(deviceId, cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId,
				cmdSetPwmId,
				cmdGetPwmId,
				msgPwmId,
				streamPwmId);

		
		this.cmdMotorOnId = cmdMotorOnId;
		this.cmdMotorStopId = cmdMotorStopId;
		this.cmdMotorBrakeId = cmdMotorBrakeId;
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
			new Cmd_setMotorPwm(this.cmdSetValueId), 
			decoder));
	
	
	
	commands.add(new RemoteCommandProcessor(
			 new Cmd_motorOn(this.cmdMotorOnId), 
			 decoder));

	
	 
	commands.add(new RemoteCommandProcessor(
			 new Cmd_motorStop(this.cmdMotorStopId), 
			 decoder));
	 
	
	commands.add(new RemoteCommandProcessor(
			 new Cmd_motorBrake(this.cmdMotorBrakeId), 
			 decoder));
	


	
	commands.add(new RemoteCommandProcessor(
			 new Cmd_setMotorSettings(this.cmdSetSettingsId), 
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
			new Stream_motorPwm(this.streamValuesId), 
			decoder));
		/*	
	streams.add(new RemoteStreamProcessor(
			new Stream_motorPwm(this.streamValuesId), 
			decoder));
	*/
	
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
