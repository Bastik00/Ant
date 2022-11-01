package de.hska.lat.robot.component.generic.heading;



import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.heading.protocol.Cmd_setHeading2D;
import de.hska.lat.robot.component.generic.heading.protocol.Stream_heading2D;



import de.hska.lat.robot.value.angular.AngularValue;


public class Heading2D extends RobotComponent<HeadingChangeNotifier,ComponentSettingsChangeNotifier, Heading2DProtocol >

{

	public enum RotationAxis_e {X,Y,Z}; 
	
	protected AngularValue  heading = new  AngularValue("heading");
	
		
	RotationAxis_e rotationAxis = RotationAxis_e.Y; 
	
public Heading2D(ComponentMetaData metaData, Heading2DProtocol protocol,	RotationAxis_e rotationAxis)
{
	super(metaData, protocol);
	this.rotationAxis = rotationAxis;
}


/**
 * get actual heading
 * @return actual heading
 */

public float getHeading() 
{
	return(this.heading.getValue());
}


/**
 * get this heading rotation axis
 * @return rotation axis
 */

public RotationAxis_e getRotationAxis()
{
	return (this.rotationAxis);
}

public AngularValue getHeadingValue()
{
	return(this.heading);
}

/**
 * 
 * @param newheading
 * @return
 */
public boolean remote_setHeading(float newHeading)
{
	
	if (this.componentProtocol==null)
		return(false);
	
	return(sendData(Cmd_setHeading2D.getCommand(this.componentProtocol.cmdSetHeadingId, newHeading)));
}


/**
 * process heading information. Heading information in 2D room is simply an rotation vector of one axis.
 * Rotation axis of this heading is defined in constructor of Heading2D class. 
 * 
 * @param remoteData Remote data Packet containing actual heading. 
 */


public void processHeading(Stream_heading2D remoteData)
{
	
	this.heading.setValue(remoteData.getHeading());
	
	
	float x=0;
	float y=0;
	float z=0;
	
	
	switch(this.rotationAxis)
	{
	case X:
		x= this.heading.getValue();
		break;
	case Y:
		y = this.heading.getValue();
		break;
	case Z:
		z = this.heading.getValue();
		break;
	default:
		break;
	
	}
	
	for (HeadingChangeNotifier listener : this.sensorListener)
	{
		listener.headingChanged(new AngularVector3D (x,y,z));
	}
	
}

	

@Override
public boolean decodeStream(RemoteStream remoteStreamData)
{
	
	if (remoteStreamData instanceof Stream_heading2D)
	{
		this.processHeading((Stream_heading2D) remoteStreamData);
		return(true);
	}

	
	return(false);
}




	
}
