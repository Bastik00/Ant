package de.hska.lat.robot.component.generic.imu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

import de.hska.lat.robot.component.generic.imu.protocol.Msg_anglesOfRotation;
import de.hska.lat.robot.component.generic.imu.protocol.Stream_anglesOfRotation;
import de.hska.lat.robot.component.sensor.Sensor;


import de.hska.lat.robot.value.angular.AngularValue;


public class Imu extends  Sensor <ImuChangeNotifier,ComponentSettingsChangeNotifier, ImuProtocol> 



{

	
	public static final int PITCH	= 0;
	public static final int ROLL 	= 1;
	public static final int YAW 	= 2;
	
	protected AngularValue[] angles = new  AngularValue[3];
	
	final Lock lock = new ReentrantLock();
	
public Imu(ComponentMetaData metaData, ImuProtocol protocol)
{
	super(metaData, protocol);
	
	this.angles[Imu.PITCH] =  new  AngularValue("pitch", true);
	this.angles[Imu.ROLL] =  new  AngularValue("roll", true);
	this.angles[Imu.YAW] =  new  AngularValue("yaw", true);
}


/**
 * get actual imu angles for Pitch, roll & yaw
 * @return rates as 3D vector
 */

public AngularVector3D  getAngles() 
{
	
	AngularVector3D angles;
	
	lock.lock();
	angles = new AngularVector3D(this.angles[Imu.PITCH].getValue(),
	                this.angles[Imu.ROLL].getValue(),
					this.angles[Imu.YAW].getValue());
	lock.unlock();
	
	return(angles);
}


public void setAnglesOfRotation(AngularVector3D anglesOfRotation)
{
	this.angles[Imu.PITCH].setValue(anglesOfRotation.x);
    this.angles[Imu.ROLL].setValue(anglesOfRotation.y);
	this.angles[Imu.YAW].setValue(anglesOfRotation.z);
	
	for (ImuChangeNotifier listener: this.sensorListener)
	{
		listener.imuChanged(this);
	}
}







public void processAnglesOfRotation(Stream_anglesOfRotation anglesOfRotation) 
{
	this.setAnglesOfRotation(anglesOfRotation.getAnglesOfRotation());
}



public void processAnglesOfRotation(Msg_anglesOfRotation anglesOfRotation)
{
	this.setAnglesOfRotation(anglesOfRotation.getAnglesOfRotation());
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_anglesOfRotation)
	{
		processAnglesOfRotation((Msg_anglesOfRotation) remoteData);
	}
	else
	{
		super.decodeMessage(remoteData);		
	}

	
	return false;
}



@Override
public boolean decodeStream(RemoteStream remoteData)
{
	if (remoteData instanceof Stream_anglesOfRotation)
	{
		this.processAnglesOfRotation((Stream_anglesOfRotation)remoteData);

	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return(true);	
}




	
}
