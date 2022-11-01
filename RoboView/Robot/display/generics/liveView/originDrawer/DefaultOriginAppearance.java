package de.hska.lat.robot.display.generics.liveView.originDrawer;

import javax.media.opengl.GL2;

import de.hska.lat.robot.morphology.appearance.Mesh3D;


public class DefaultOriginAppearance extends Mesh3D
{

	
	private static final float [] vertexes ={

		
		 0.0f, 0.0f, 0.0f,   // middle
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		
		-60f, 0.0f, 0.0f,   // Left
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
		 
		 60f, 0.0f, 0.0f,   // right
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
		 
		 0f, 60.0f, 0.0f,   // up
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
		 0f, -60.0f, 0.0f,   // down
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
		 
		 0f, 0.0f, 60.0f,   // up
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
		 0f, 0.0f, -60.0f,   // down
		 1.0f, 1.0f, 1.0f,
		 1.0f,1.0f,1.0f,1.0f,
		 
	};
	
	
	private static final char [] elements ={
		1,2,0,
		3,4,0,
		5,6,0,
	};
	
	
public DefaultOriginAppearance()
{
	super(null, vertexes, elements);
	
	this.primitiveType = GL2.GL_LINES;

}

}
