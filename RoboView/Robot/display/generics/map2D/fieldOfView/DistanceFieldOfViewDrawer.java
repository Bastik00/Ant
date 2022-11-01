package de.hska.lat.robot.display.generics.map2D.fieldOfView;



import de.hska.lat.perception.fieldOfView.FieldOfView;





public class DistanceFieldOfViewDrawer extends FieldOfViewDrawer
{



public DistanceFieldOfViewDrawer (FieldOfView<?> fieldOfView)	
{
	super(fieldOfView);
//	this.paint.setStrokeWidth(3);
	
}

}


/*
@Override
public void draw(Canvas canvas)
{
	
	int enumerator;
	
	float x;
	float z;

	float previousX=0;
	float previousZ=0;
	
	boolean previousValid = false;
	
	float [] position;
	float [] origin;
	DistanceObservation observation;
	
	
	
	
	
	for (enumerator=0;enumerator<fieldOfView.size() ;enumerator++)
	{
		observation = (DistanceObservation) fieldOfView.getObservation(enumerator);
		
		if (observation.isValid())
		{
			

			position = observation.getDetectedPosition();
			origin = observation.getObservationPose();
			
			x =displayData.translateXToScreen(position[0]);
			z =displayData.translateYToScreen(position[2]);
			

			
		

			displayData.translateXToScreen(origin[Observation.OBSERVATION_X_POS]);
			displayData.translateYToScreen(origin[Observation.OBSERVATION_Z_POS]);
			
	
			
			
			if (previousValid == true)
				canvas.drawLine(previousX, previousZ, x, z, paint);
			
			
			previousX = x;
			previousZ = z;
			previousValid =true;
		}
		else
		{
			previousValid =false;
		}
				
	}
	

	
	
	
}
	*/
	

	

