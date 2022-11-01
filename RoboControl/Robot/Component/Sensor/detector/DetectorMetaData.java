package de.hska.lat.robot.component.detector;

import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;

public class DetectorMetaData extends ComponentMetaData
{

	FloatVector3D position;
	 AngularVector3D  angle;
	
	
	
public DetectorMetaData(ComponentMetaData componentData,FloatVector3D position, AngularVector3D  angle)
{
	super(componentData);
	
	
	if (position!=null)
	{
		this.position = position;
	}
	else
	{
		this.position = new FloatVector3D();
	}
	
	if (angle!=null)
	{
		this.angle = angle;
	}
	else
	{
		this.angle= new AngularVector3D();

	}
}


public FloatVector3D getPosition()
{
	return(this.position);
}



public AngularVector3D getAngle()
{
	return(this.angle);
}





}
