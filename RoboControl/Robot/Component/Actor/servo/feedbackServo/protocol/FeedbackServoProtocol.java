package de.hska.lat.robot.component.actor.servo.feedbackServo.protocol;

import de.hska.lat.robot.component.actor.servo.protocol.ServoProtocol;

public class FeedbackServoProtocol extends ServoProtocol
{

	public FeedbackServoProtocol(int deviceId,
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
			int cmdCalibrateServo,
			int streamRawAnalogPositions)
	{
		super(deviceId, cmdSetSettingsId,
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
				cmdMoveId,
				cmdCalibrateServo,
				streamRawAnalogPositions);
		// TODO Auto-generated constructor stub
	}

}
