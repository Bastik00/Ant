package de.hska.lat.robot.component.generic.collision;

import java.util.ArrayList;

import de.hska.lat.robot.component.generic.collision.protocol.Exception_collision;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteExceptionProcessor;


public class CollisionDetectorProtocol extends SensorProtocol
{

	public int exceptionCollisionId;
	
	public CollisionDetectorProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int exceptionCollisionId) 
	{

		
	super(deviceId, cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			0,
			0,
			0);
	
	this.exceptionCollisionId = exceptionCollisionId;

}

	


public ArrayList<RemoteExceptionProcessor> getExceptionProcessors(CollisionDetector colosionDetector)	
{
	
	ArrayList<RemoteExceptionProcessor> exceptions = new ArrayList<RemoteExceptionProcessor>();

	exceptions.addAll(super.getExceptionProcessors(colosionDetector));
	
	exceptions.add(new  RemoteExceptionProcessor(
			new Exception_collision(this.exceptionCollisionId), 
			colosionDetector));
	
	return(exceptions);
}



}