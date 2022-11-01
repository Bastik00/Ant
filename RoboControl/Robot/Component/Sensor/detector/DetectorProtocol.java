package de.hska.lat.robot.component.detector;

import de.hska.lat.robot.component.ComponentProtocol;

public class DetectorProtocol extends ComponentProtocol
{

public DetectorProtocol(int deviceId,
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
