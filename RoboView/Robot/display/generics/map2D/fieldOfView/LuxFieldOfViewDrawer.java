package de.hska.lat.robot.display.generics.map2D.fieldOfView;



import java.awt.Graphics2D;

import de.hska.lat.perception.fieldOfView.FieldOfView;







public class LuxFieldOfViewDrawer extends FieldOfViewDrawer
{


	
	

public LuxFieldOfViewDrawer (FieldOfView<?> fieldOfView)	
{
	super(fieldOfView);

}


@Override
public void draw(Graphics2D graphics)
{
	
/*
	
	LuxFieldOfView fov;
	
	fov = (LuxFieldOfView) this.fieldOfView;
	

	float x;
	float z;

	
	float [] position;
	float [] origin;
	//LightObservation observation;
	
	
	float intensity;
	Color color;
	int heading;
	
	// auto Intensity;

	fov.getMax();
	fov.getMin();
	
	
	for (LuxObservation observation : fov)
	{

		
		if (observation!=null)
		{
			if (observation.isValid())
			{
				System.out.println(observation.getValue());
				
				origin = observation.getObservationPose();
				
				
		//		observation.getSensorPosition();
				
				x=displayData.translateXToScreen(origin[Observation.OBSERVATION_X_POS]);
				z=displayData.translateYToScreen(origin[Observation.OBSERVATION_Z_POS]);
				heading=(int) (origin[Observation.OBSERVATION_Y_HEADING] * 180 / Math.PI) -90; 
				heading %=360;
				
				
				
				intensity = 0.00001f * observation.getLux();	
				color = new Color(this.displayColor.getRed(), this.displayColor.getGreen(), this.displayColor.getBlue(), 100);

				graphics.setColor(color);
				graphics.drawOval((int)x,(int) z, 10, 10);
				//canvas.drawCircle(x, x, 20, paint);
				
	//			ovalBounds.set(x-100, z-100, x+100, z+100);
				//graphics.drawArc(ovalBounds, heading-30, 60, true, paint);
			}
		}	
		
	}
	*/
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
	



