package de.hska.lat.robot.component.battery;

import de.hska.lat.robot.component.ComponentProtocol;


public class BatteryProtocol extends ComponentProtocol
{

	public final int streamVoltageId;
	
	
	
public BatteryProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId,
			int msgSettings,
			int cmdGetVoltageId,
			int msgVoltageId,
			int streamVoltageId)

{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings);
			
	
	this.streamVoltageId = streamVoltageId;
	
}



}
