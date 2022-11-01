package de.hska.lat.robot.component.voltmeter;

import de.hska.lat.robot.component.ComponentProtocol;


public class VoltmeterProtocol extends ComponentProtocol
{
	protected final int cmdGetVoltageId;
	protected final int msgVoltageId;
	protected final int streamVoltageId;
	
public VoltmeterProtocol(int deviceId,
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
			msgSettings
			);
	
	this.cmdGetVoltageId = cmdGetVoltageId;
	this.msgVoltageId = msgVoltageId;
	this.streamVoltageId = streamVoltageId;
}



}
