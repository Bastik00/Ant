package de.hska.lat.robot.component.distanceSensor.gp2D;

import de.hska.lat.robot.component.generic.distance.DistanceSensorProtocol;

public class Gp2DProtocol extends DistanceSensorProtocol
{

public Gp2DProtocol(int deviceId,
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
}



}
