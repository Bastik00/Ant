package de.hska.lat.robot.component.distanceSensor.srf10;

import de.hska.lat.robot.component.generic.distance.DistanceSensorProtocol;

public class Srf10Protocol extends DistanceSensorProtocol
{

	public Srf10Protocol(
			int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId, 
			int msgSettings,
			int cmdGetDistanceId,
			int msgDistanceId,
			int streamDistanceId)

{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettings,
			
			cmdGetDistanceId,
			msgDistanceId,
			streamDistanceId);
		// TODO Auto-generated constructor stub
	}

}
