package de.hska.lat.robot.component.template;

import de.hska.lat.robot.component.ComponentProtocol;

public class TemplateComponentProtocol extends ComponentProtocol

{
	public final int streamTemplateDataId;
	
	public TemplateComponentProtocol(int deviceId,
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
