package de.hska.lat.robot.component.generic.heading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.heading.protocol.Stream_heading3D;


import de.hska.lat.robot.value.angular.AngularValue;


public class Heading3D extends RobotComponent<HeadingChangeNotifier,ComponentSettingsChangeNotifier, Heading3DProtocol >

{

	
	public static final int X	= 0;
	public static final int Y 	= 1;
	public static final int Z 	= 2;
	
	protected AngularValue[] heading = new  AngularValue[3];
	
	final Lock lock = new ReentrantLock();
	
public Heading3D(ComponentMetaData metaData, Heading3DProtocol protocol)
{
	super(metaData, protocol);
	
	this.heading[Heading3D.X] =  new  AngularValue("pitch", true);
	this.heading[Heading3D.Y] =  new  AngularValue("roll", true);
	this.heading[Heading3D.Z] =  new  AngularValue("yaw", true);
}


/**
 * get actual heading
 * @return heading as 3D vector
 */
public AngularVector3D  getHeading() 
{
	AngularVector3D heading;
	
	lock.lock();
	heading = new AngularVector3D(this.heading[Heading3D.X].getValue(),
					this.heading[Heading3D.Y].getValue(),
					this.heading[Heading3D.Z].getValue());
	lock.unlock();
	
	return(heading);
}





public void processHeadingValues(Stream_heading3D remoteData)
{
	AngularVector3D heading;
	
	heading = remoteData.getHeading();
	
	lock.lock();
	
	this.heading[Heading3D.X].setValue(heading.x);
	this.heading[Heading3D.Y].setValue(heading.y);
	this.heading[Heading3D.Z].setValue(heading.z);
	lock.unlock();
	
	for (HeadingChangeNotifier listener : this.sensorListener)
	{
		listener.headingChanged(this.getHeading());
	}
	
}

	

@Override
public boolean decodeStream(RemoteStream remoteStreamData)
{
	
	if (remoteStreamData instanceof Stream_heading3D)
	{
		this.processHeadingValues((Stream_heading3D) remoteStreamData);
		return(true);
	}

	
	return(false);
}


public void remote_setHeading(FloatVector3D floatVector3D)
{
	// TODO Auto-generated method stub
	
}


	
}
