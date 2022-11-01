package de.hska.lat.robot.morphology.appearance;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.pose.Pose3D;

public class AppearanceModel
{

	protected ArrayList <OrganAppearance> organList = new ArrayList <OrganAppearance>();
	



	protected Pose3D pose = new Pose3D();
	
	//public AppearanceModel(Robot robot)	
public AppearanceModel()
{	
}


public void init(GL2 gl,Pose3D pose)
{
	
	//this.pose = robot.getPose();
	
	
  
	
	for (OrganAppearance organ :organList)
	{
		organ.build(gl);
	}
	
}	
	
	



public void draw(GL2 gl)
{

	this.setPose(gl);
	
	for (OrganAppearance organ :organList)
	{
		organ.draw(gl);
	}
	
	

}	


public void setPose(GL2 gl)
{
	FloatVector3D location;
	AngularVector3D heading;
	

	location = pose.getLocation();
	heading = pose.getNormalizedHeading();
	    
	gl.glTranslatef(location.x, location.y, location.z);


	gl.glRotatef(Radiant.convertRadiantToDegree(heading.z), 0.0f, 0.0f, 1.0f);
	gl.glRotatef(Radiant.convertRadiantToDegree(heading.x), 1.0f, 0.0f, 0.0f);
	gl.glRotatef(Radiant.convertRadiantToDegree(heading.y), 0.0f, 1.0f, 0.0f);
}


public ArrayList<OrganAppearance> getOrganList() {
	return organList;
}

}
