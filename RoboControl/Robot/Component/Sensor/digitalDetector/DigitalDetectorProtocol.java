package de.hska.lat.robot.component.digitalDetector;

import de.hska.lat.robot.component.detector.DetectorProtocol;

public class DigitalDetectorProtocol extends DetectorProtocol
{

	public final int streamDetectorStatusId;

	public DigitalDetectorProtocol(int deviceId,
			int setSettingsCommandId,
			int getSettingsCommandId,
			int saveDefaultsCommandId,
			int loadDefaultsCommandId,
			int msgSettings,
			int streamDetectorStatusId)
	{
		super(deviceId,
				setSettingsCommandId,
				getSettingsCommandId,
				saveDefaultsCommandId,
				loadDefaultsCommandId,
				msgSettings);
		
		this.streamDetectorStatusId = streamDetectorStatusId;
		// TODO Auto-generated constructor stub
	}

}
