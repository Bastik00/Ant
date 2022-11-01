package de.hska.lat.robot.component.sensor.pixyCamera;

import de.hska.lat.robot.component.ComponentProtocol;


public class PixyCameraProtocol extends ComponentProtocol {

	public PixyCameraProtocol(int deviceId, int cmdSetSettingsId,
			int cmdGetSettingsId, int cmdSaveDefaultsId, int cmdLoadDefaultsId,
			int msgSettingsId) {
		super(deviceId, cmdSetSettingsId, cmdGetSettingsId, cmdSaveDefaultsId,
				cmdLoadDefaultsId, msgSettingsId);
	}

}
