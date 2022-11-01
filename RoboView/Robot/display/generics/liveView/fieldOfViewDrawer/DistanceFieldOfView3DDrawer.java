package de.hska.lat.robot.display.generics.liveView.fieldOfViewDrawer;

import javax.media.opengl.GL2;

import de.hska.lat.perception.fieldOfView.DistanceFieldOfView;
import de.hska.lat.perception.observation.DistanceObservation;


public class DistanceFieldOfView3DDrawer extends FieldOfView3DDrawer<DistanceFieldOfView>
{

	protected DistanceObservation observation;
	
public DistanceFieldOfView3DDrawer(DistanceFieldOfView fieldOfView)
{
	super(fieldOfView);	
	observation = fieldOfView.get(0);
}
	
	



public void display(GL2 gl)
{
	
	
	System.out.println("Display !!!!!!!!!!!!!!!!!!!!!!");
}	

}
