package de.hska.lat.robot.display.generics.map2D.fieldOfView;



import java.awt.Graphics;

import de.hska.lat.perception.fieldOfView.FieldOfView;





public class LightFieldOfViewDrawer extends FieldOfViewDrawer
{


	
	

public LightFieldOfViewDrawer (FieldOfView<?> fieldOfView)	
{
	super(fieldOfView);
//	this.paint.setStrokeWidth(3);
	
}



public void draw(Graphics graphics)
{

}



/*
@Override
public void draw(Canvas canvas)
{
	
	int enumerator;
	
	float x;
	float z;

	
	float [] position;
	float [] origin;
	LightObservation observation;
	
	
	float intensity;
	int color;
	int heading;
	
	for (enumerator=0;enumerator<fieldOfView.size() ;enumerator++)
	{
		observation = (LightObservation) fieldOfView.getObservation(enumerator);
		if (observation!=null)
		{
			if (observation.isValid())
			{
				
	
			//	position = observation.getDetectedPosition();
				//origin = observation.getObservationPose();
				origin = observation.getSensorPose();
			//	x =displayData.translateXToScreen(position[0]);
			//	z =displayData.translateYToScreen(position[2]);
				
	
				x=displayData.translateXToScreen(origin[0]);
				z=displayData.translateYToScreen(origin[1]);
				heading=(int) (origin[Observation.OBSERVATION_HEADING] * 180 / Math.PI) -90; 
				heading %=360;
				
				color = this.displayColor;
				
				intensity = observation.getIntensity();	
				
				int alpha;
				
				alpha= color & 0xff000000;
				alpha >>>=24;
				alpha *= intensity;
				alpha<<=24;
				alpha &= 0xff000000;
				color &= 0x00ffffff;
				color |=alpha;
				
				this.paint.setColor(color);
				
				canvas.drawPoint(x, z, paint);
				//canvas.drawCircle(x, x, 20, paint);
				
				ovalBounds.set(x-100, z-100, x+100, z+100);
				canvas.drawArc(ovalBounds, heading-30, 60, true, paint);
				
				/*
				 * 	this.origin[Observation.OBSERVATION_X_POS] =  this.pose.getXPosition();
	this.origin[Observation.OBSERVATION_Z_POS] = this.pose.getZPosition();
	this.origin[Observation.OBSERVATION_HEADING]
				 */
/*			}	
	
		}	
	}
	
*/
	
	
	
}
	



