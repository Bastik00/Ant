package de.hska.lat.robot.component.generic.collision;

import de.hska.lat.comm.remote.RemoteException;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

import de.hska.lat.robot.component.generic.collision.protocol.Exception_collision;
import de.hska.lat.robot.component.sensor.Sensor;


public class CollisionDetector extends Sensor<CollisionChangeNotifier,ComponentSettingsChangeNotifier,CollisionDetectorProtocol>
{

	public CollisionDetector(ComponentMetaData metaData,
			CollisionDetectorProtocol protocol)
	{
		super(metaData, protocol);
	}


	
	
	
protected boolean processCollision(Exception_collision remoteCollision)
{
	
	for (CollisionChangeNotifier listener : this.sensorListener)
	{
		listener.collisionDetected(remoteCollision.getVector());
	}
	
	return(true);
}	
	
	
	
@Override
public boolean decodeException(RemoteException remoteException)
{
	if (remoteException instanceof Exception_collision)
	{
		return(this.processCollision((Exception_collision) remoteException));
	}
	else
	{
		super.decodeException(remoteException);		
	}

	
	return false;
}	
	
	
}
