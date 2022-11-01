package de.hska.lat.robot.component.text;

import de.hska.lat.robot.component.ComponentProtocol;

public class TextProtocol extends ComponentProtocol

{
	public final int cmdGetText;
	public final int msgTextFragment;
	
	public TextProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdGetText,
			int msgTextFragment)
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId);
		
		this.cmdGetText = cmdGetText;
		this.msgTextFragment = msgTextFragment;
	}

}
