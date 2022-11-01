package de.hska.lat.robot.component.generic.accelerometer;


import java.util.ArrayList;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.acc.AccelerationValue;
import de.hska.lat.robot.value.angular.AngularValue;






public class Accelerometer <S extends ComponentSettingsChangeNotifier,  P extends AccelerometerProtocol> 
					extends  Sensor <AccelerometerChangeNotifier,S,P> 


		
{

	
	public static final int X_AXIS =0 ;
	public static final int Y_AXIS =1 ;
	public static final int Z_AXIS =2 ;
	
	
	public enum AccAxis_e {X,Y,Z};
	
	
	protected AngularValue[] angle = new AngularValue[3]; 
	

	
	protected boolean stable = false;
	protected float stableRange = 0.05f;
	
	protected int [] offset = new int[3];
	
	protected float gRange = 2;
	protected int adcRagne =127;
	
	
	
	protected float totalGValue;
	
	
	protected AccelerationValue [] acceleration = new AccelerationValue[3];  
	
//2012.02.13 OG
public Accelerometer(ComponentMetaData metaData,P protocol) 
{
		super(metaData, protocol);

		this.angle[Accelerometer.X_AXIS] = new AngularValue(this.getComponentName()+"x axis");
		this.angle[Accelerometer.Y_AXIS] = new AngularValue(this.getComponentName()+"x axis");
		this.angle[Accelerometer.Z_AXIS] = new AngularValue(this.getComponentName()+"x axis");
		
		this.acceleration[Accelerometer.X_AXIS] = new AccelerationValue(this.getComponentName()+" x axis",this.adcRagne,this.gRange);
		this.acceleration[Accelerometer.Y_AXIS] = new AccelerationValue(this.getComponentName()+" y axis",this.adcRagne,this.gRange);
		this.acceleration[Accelerometer.Z_AXIS] = new AccelerationValue(this.getComponentName()+" z axis",this.adcRagne,this.gRange);
		
}

/*
public void setOffsets(int offsetX, int offsetY, int offsetZ)
{
	
	this.offset[Accelerometer.X_AXIS] = offsetX;
	this.offset[Accelerometer.Y_AXIS] = offsetY;
	this.offset[Accelerometer.Z_AXIS] = offsetZ;
}
*/

/**
 * set accelerations for this sensor
 * @param acceleration 3D vector with n ew accelerations
 */

public void setAccelerations( FloatVector3D acceleration)
{
	
	this.acceleration[Accelerometer.X_AXIS].setValue(acceleration.x);
	this.acceleration[Accelerometer.Y_AXIS].setValue(acceleration.y);
	this.acceleration[Accelerometer.Z_AXIS].setValue(acceleration.z);
	

	for (AccelerometerChangeNotifier listener:   sensorListener)
	{
		listener.accValueChanged(this);	
	}	

	this.checkStable();

}


protected void checkStable()
{

	
	float accX = this.acceleration[Accelerometer.X_AXIS].getValue();
	float accY = this.acceleration[Accelerometer.Y_AXIS].getValue();
	float accZ = this.acceleration[Accelerometer.Z_AXIS].getValue();

	
	this.totalGValue =(float) Math.sqrt((accX*accX)
								+(accY*accY)
								+(accZ*accZ));
	

	if ((this.totalGValue> (1-this.stableRange)) && (this.totalGValue< (1+this.stableRange)))
	{
		this.stable=true;
	}
	else
	{
		this.stable=false;
	}

	
	
	this.angle[Accelerometer.X_AXIS].setValue(calculateAngle(accX));
	this.angle[Accelerometer.Y_AXIS].setValue(calculateAngle(accY));
	this.angle[Accelerometer.Z_AXIS].setValue(calculateAngle(accZ));
	
}

/**
 * calculate angle for given acceleration value
 * @param gValue 
 * @return angle in radiant
 */

protected float calculateAngle(float acceleration)
{
	float angle;
	
	/*if (acceleration==1)
	{
		angle=90f;
	}
	else*/
	{
		angle = (float) ((acceleration *Math.PI)/2); //(Math.asin(gValue) *180 /3.14);
	}
	
	return(angle);
	
}




/**
 * get sensor acceleration 
 * @return sensor acceleration for x,y, and z axis in g
 */
public float[] getAcceleration()
{
	float [] acceleration = new float[3];
	
	
	acceleration[Accelerometer.X_AXIS] = this.acceleration[Accelerometer.X_AXIS].getValue();
	acceleration[Accelerometer.Y_AXIS] = this.acceleration[Accelerometer.Y_AXIS].getValue();
	acceleration[Accelerometer.Z_AXIS] = this.acceleration[Accelerometer.Z_AXIS].getValue();

	return(acceleration);
}


/**
 * get sensor heading
 * @return sensor heading (angles) for x,y, and z axis
 */
public float[] getHeading()
{
	float [] heading = new float[3];
	
	
	heading[Accelerometer.X_AXIS] = this.angle[Accelerometer.X_AXIS].getValue();
	heading[Accelerometer.Y_AXIS] = this.angle[Accelerometer.Y_AXIS].getValue();
	heading[Accelerometer.Z_AXIS] = this.angle[Accelerometer.Z_AXIS].getValue();

	return(heading);
}



/**
 * get the discrete analog value offset for an axis
 * @param axis
 * @return
 */

public int getOffset(AccAxis_e axis)
{
	switch (axis)
	{
		case X:
			return(this.offset[Accelerometer.X_AXIS]);
			
		case Y:
			return(this.offset[Accelerometer.Y_AXIS]);
	
		case Z:
			return(this.offset[Accelerometer.Z_AXIS]);
	
		default:
			return(0);
	}
}

 




/**
 * Query if angle values are stable (correct) -> no actual movement -> x²+y²+z² are near 1g 
 * @return true if stable false if not
 */

public boolean isStable()
{
	return(this.stable);
}




public float getStableRange()
{
	return(this.stableRange);
}

/**
 * set new difference from 1G which is considered as stable value (sensor not moving)
 * @param stableRange
 */

public void getStableRange(float stableRange)
{
	this.stableRange = stableRange;
}






@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	values.add(this.acceleration[Accelerometer.X_AXIS]);
	values.add(this.acceleration[Accelerometer.Y_AXIS]);
	values.add(this.acceleration[Accelerometer.Z_AXIS]);
			
	return (values);
}



}
