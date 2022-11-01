package de.hska.lat.robot.component.sensor.tsl2591;

import de.hska.lat.robot.component.ComponentProtocol;

public class Tsl2591Protocol extends ComponentProtocol

{
	public final int streamTemplateDataId;
	
	public Tsl2591Protocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int streamTemplateDataId)
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId);
		
		this.streamTemplateDataId = streamTemplateDataId;
	}

}
