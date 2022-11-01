package de.hska.lat.robot.component.detector;

import java.util.ArrayList;

import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.detector.DetectorValue;



public abstract class Detector<L extends DetectorChangeNotifier,S extends ComponentSettingsChangeNotifier, P extends ComponentProtocol> extends RobotComponent<L,S,P> 
{

	
	public static final int AXIS_X =0;
	public static final int AXIS_Y =1;
	public static final int AXIS_Z =2;
	
	
	protected FloatVector3D  sensorPosition;
	protected AngularVector3D sensorHeading;
	protected float angleOfView;
	
	
	protected DetectorValue value;
	
public Detector(DetectorMetaData metaData, P protocol)
{
		super(metaData, protocol);
		
		
		this.value = new DetectorValue(metaData.getName()); 
		
		this.sensorPosition = metaData.getPosition().copy();
		this.sensorHeading = metaData.getAngle().copy();
}



public DetectorValue getValue()
{
	return(this.value);
}


public boolean getStatus()
{
	return(this.value.getStatus());
}


public FloatVector3D getSensorPosition()
{
	return(sensorHeading.copy());
}




public AngularVector3D getSensorHeading()
{
	return(this.sensorHeading.copy());
}

/*
public float[] getSensorPose()
{
	float copy[] = new float[6];
	
	
	// recalculate to Origin !!!!!
	
	copy[0] =this.sensorPosition[0];
	copy[1] =this.sensorPosition[1];
	copy[2] =this.sensorPosition[2];
	
	copy[3] =this.sensorHeading[0];
	copy[4] =this.sensorHeading[1];
	copy[5] =this.sensorHeading[2];
	
	return(copy);
}
*/


public float getAngleOfView()
{
	return(this.angleOfView);
}


public void setStatus(boolean status)
{
	
	this.value.setStatus(status);
	
	
	for (DetectorChangeNotifier listener  : this.sensorListener )
	{
		listener.statusChanged(this);
	}

}

public boolean hasContact()
{
	return(this.value.getStatus());
}




@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
	values.add(this.value);	
			
	return (values);
}






}
