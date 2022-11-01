package de.hska.lat.robot.display.generics.map2D.fieldOfView;




import java.awt.Graphics2D;

import de.hska.lat.perception.fieldOfView.FieldOfView;






public class UltrasonicFieldOfViewDrawer extends FieldOfViewDrawer
{


//	protected RectF drawingArea = new RectF();	
	

public UltrasonicFieldOfViewDrawer (FieldOfView<?> fieldOfView)	
{
	super(fieldOfView);
	
/*	this.paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(3);
	paint.setMaskFilter(new BlurMaskFilter(3, Blur.INNER));
	*/
}


	protected int beamWidth = 15;




@Override
public void draw(Graphics2D graphics)
{
/*	
	int enumerator;
	
	float x;
	float z;
	float distance;
	float angle;
	
	float [] origin;
	DistanceObservation observation;
	
	
	
	for (enumerator=0;enumerator<fieldOfView.size() ;enumerator++)
	{
		observation = (DistanceObservation) fieldOfView.getObservation(enumerator);
		
		if (observation.isValid())
		{
			
			distance = observation.getDistance();
			//origin = observation.getObservationPose();
			origin = observation.getSensorPose();
			
			x =displayData.translateXToScreen(origin[Observation.OBSERVATION_X_POS]);
			z =displayData.translateYToScreen(origin[Observation.OBSERVATION_Z_POS]);
			

			
			
			distance=displayData.toScreenLenght(observation.getDistance());
			
			
		//	canvas.drawPoint(x, z, paint);
			
		//	drawingArea.set(x-distance, z-distance, x+distance, z+distance);

			
			angle = (float) (origin[Observation.OBSERVATION_HEADING] * 180/ Math.PI);;			
			
			
			angle = (angle-this.beamWidth) -90;
			
			angle %=360;
			
			
	//		graphics.drawArc(arg0, arg1, arg2, arg3, arg4, arg5)
			
		//	canvas.drawArc(drawingArea, angle, this.beamWidth*2, false, paint);
			
			
			
			
			
			origin=observation.getDetectedPosition();
			
			
			x =displayData.translateXToScreen(origin[0]);
			z =displayData.translateYToScreen(origin[2]);
			
		graphics.drawLine((int)(x-5),(int) z,(int)( x+5),(int) z);
		//	canvas.drawPoint(x, z, paint);
			
		}

				
	}
	

*/	
	
	
}

	
	
/*
 * 
protected void calculateDistance (DistanceSensor<?,?> sensor) 
{
	
	int distance;
	float [] angle;
	float [] vector = new float[3];
	
	distance=sensor.getMilimeters();

	if (distance>0)
	{
		
	this.valid=true;
	
	
	angle = sensor.getSensorAngle();
	vector [Transformation.X_AXIS] = 0;
	vector [Transformation.Y_AXIS] = 0;
	vector [Transformation.Z_AXIS] = distance;
			
	Transformation.rotate(angle, vector);
	
	
	this.detectedPosition[Transformation.X_AXIS] = vector [Transformation.X_AXIS];
	this.detectedPosition[Transformation.Y_AXIS] = vector [Transformation.Y_AXIS];
	this.detectedPosition[Transformation.Z_AXIS] = vector [Transformation.Z_AXIS];
			
	System.out.println("Position"+	vector [Transformation.X_AXIS] +" : "+vector [Transformation.Y_AXIS] +" : "+vector [Transformation.Z_AXIS] );
	}
	else
	{
		this.valid=false;
	}
	// Translate distance (x,y)
	// pose ....




}

	
 */

}
