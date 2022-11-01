package de.hska.lat.robot.component.actor.servo.forceFeedback.protocol;

import de.hska.lat.robot.component.actor.servo.feedbackServo.protocol.FeedbackServoProtocol;

public class ForceFeedbackServoProtocol extends FeedbackServoProtocol
{

	public ForceFeedbackServoProtocol(int deviceId,
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
			int cmdMove,
			int cmdCalibrateServo,
			int streamRawAnalogPositions)
	{
		super(deviceId,
				cmdSetSettingsId, 
				cmdGetSettingsId, 
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettings, 
				servoOnCommandId, 
				servoOffCommandId,
				forceFeedbackOnCmdId, 
				forceFeedbackOffCmdId, 
				positionFeedbackOnCmdId,
				positionFeedbackOffCmdId, 
				getServoStatusCommandId,
				getServoPositionCommandId, 
				getServoSpeedCommandId,
				moveServoToCommandId, 
				moveServoToAtSpeedCommandId,
				setServoPositionCommandId,
				setServoSpeedCommandId,
				setServoForceThresholdId, 
				getServoForceThresholdId,
				setServoForcePositionId,
				getServoForcePositionId,
				cmdMove,
				cmdCalibrateServo,
				streamRawAnalogPositions);


	}

}
