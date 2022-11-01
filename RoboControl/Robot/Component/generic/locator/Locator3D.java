package de.hska.lat.robot.component.generic.locator;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.FloatVector2D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.locator.protocol.Stream_location3D;
import de.hska.lat.robot.value.FloatValue;

public class Locator3D extends
		Locator<LocatorChangeNotifier, ComponentSettingsChangeNotifier, Locator3DProtocol>
{

	public static final int X_AXIS	= 0;
	public static final int Y_AXIS	= 1;
	public static final int Z_AXIS	= 2;
	
	protected FloatValue[] location = new FloatValue[3]; 
	
public Locator3D(ComponentMetaData metaData, Locator3DProtocol protocol)
{
	super(metaData, protocol);
	this.location[Locator3D.X_AXIS] = new FloatValue("x");
	this.location[Locator3D.Y_AXIS] = new FloatValue("y");
	this.location[Locator3D.Z_AXIS] = new FloatValue("z");	
}





public FloatVector2D getLocation()
{
	FloatVector2D location;
	
	location = new FloatVector2D(this.location[Locator2D.X_AXIS].getValue(),
			this.location[Locator2D.Y_AXIS].getValue());
	
	
	return (location);
}




public void setLocation(FloatVector3D location)
{
	
	this.location[Locator3D.X_AXIS].setValue(location.x);
	this.location[Locator3D.Y_AXIS].setValue(location.y);
	this.location[Locator3D.Z_AXIS].setValue(location.z);
		
	
	for (LocatorChangeNotifier listener:   sensorListener)
	{
		listener.locationChanged(location);	
	}	
}



public void remote_setActualLocation(FloatVector3D floatVector3D)
{
	// TODO Auto-generated method stub
	
}





public void processLocation(Stream_location3D remoteData)
{
	this.setLocation(remoteData.getLocation());
}




 
@Override
public boolean decodeStream(RemoteStream remoteStreamData)
{
	
	if (remoteStreamData instanceof Stream_location3D)
	{
		this.processLocation((Stream_location3D) remoteStreamData);
	
	}
	else
	{
		return(false);	
	}
	
	return(true);
	
}










}
