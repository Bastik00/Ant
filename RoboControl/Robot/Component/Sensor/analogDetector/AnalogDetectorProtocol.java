package de.hska.lat.robot.component.analogDetector;

import de.hska.lat.robot.component.detector.DetectorProtocol;

public class AnalogDetectorProtocol extends DetectorProtocol
{

	public AnalogDetectorProtocol(int deviceId,
	int cmdSetSettingsId, 
	int cmdGetSettingsId,
	int cmdSaveDefaultsId,
	int cmdLoadDefaultsId,
	int msgSettings)
{


super(deviceId,
		cmdSetSettingsId,
		cmdGetSettingsId,
		cmdSaveDefaultsId,
		cmdLoadDefaultsId,
		msgSettings);

}
	
}
