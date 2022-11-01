package de.hska.lat.robot.component.actor.servo.protocol;


import de.hska.lat.robot.component.actor.ActorProtocol;

public class ServoProtocol extends ActorProtocol
{
	
	public final int servoOnCommandId;
	public final int servoOffCommandId;
	public final int forceFeedbackOnCmdId;
	public final int forceFeedbackOffCmdId;
	public final int positionFeedbackOnCmdId;
	public final int positionFeedbackOffCmdId;
	
	public final int getServoStatusCommandId;
	public final int getServoSpeedCommandId;
	public final int getServoPositionCommandId;
	
	public final int moveServoToAtSpeedCommandId;
	public final int moveServoToCommandId;
	public final int setServoPositionCommandId;
	public final int setServoSpeedCommandId;
	public final int setServoForceThresholdCommandId;
	public final int getServoForceThresholdCommandId;
	public final int setServoForcePositionCommandId;
	public final int getServoForcePositionCommandId;
	public final int cmdMoveId;
	
	public final int cmdCalibrateServoId;
	
	public final int streamRawAnalogPositions;
	
public ServoProtocol(int deviceId,
		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId, 
		int cmdLoadDefaultsId,
		int msgSettings,
		
		int servoOnCommandId, 
		int servoOffCommandId,
		
		int forceFeedbackOnCmdId,
		int forceFeedbackOffCmdId,
		int positionFeedbackOnCmdId,
		int positionFeedbackOffCmdId,
		
		int getServoStatusCommandId,
		int getServoPositionCommandId,
		int getServoSpeedCommandId,

		
		int moveServoToCommandId, 
		int moveServoToAtSpeedCommandId,
		
		int setServoPositionCommandId,
		int setServoSpeedCommandId,
		
		int setServoForceThresholdId,
		int getServoForceThresholdId,
		
		int setServoForcePositionId,
		int getServoForcePositionId,
		
		int cmdMoveId, 
		int cmdCalibrateServoId,
		int streamRawAnalogPositions
		)
{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId, 
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings, 0, 0, 0, 0
			);

	
	this.servoOnCommandId = servoOnCommandId;
	this.servoOffCommandId = servoOffCommandId; 
	
	this.forceFeedbackOnCmdId = forceFeedbackOnCmdId;
	this.forceFeedbackOffCmdId = forceFeedbackOffCmdId;
	this.positionFeedbackOnCmdId = positionFeedbackOnCmdId;
	this.positionFeedbackOffCmdId = positionFeedbackOffCmdId;
	
	this.getServoStatusCommandId = getServoStatusCommandId;
	this.getServoSpeedCommandId = getServoSpeedCommandId;
	this.getServoPositionCommandId = getServoPositionCommandId;
	
	this.moveServoToCommandId = moveServoToCommandId; 
	this.moveServoToAtSpeedCommandId = moveServoToAtSpeedCommandId; 
	
	this.setServoPositionCommandId = setServoPositionCommandId;
	this.setServoSpeedCommandId = setServoSpeedCommandId;
	
	this.setServoForceThresholdCommandId = setServoForceThresholdId;
	this.getServoForceThresholdCommandId =  getServoForceThresholdId;

	this.setServoForcePositionCommandId = setServoForcePositionId;
	this.getServoForcePositionCommandId = getServoForcePositionId;
	
	this.cmdMoveId = cmdMoveId;
	this.cmdCalibrateServoId = cmdCalibrateServoId;
	this.streamRawAnalogPositions  = streamRawAnalogPositions;
	
}



}
