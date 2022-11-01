package de.hska.lat.robot.morphology.appearance;

import java.util.Vector;

import javax.media.opengl.GL2;

public class OrganAppearance
{

	protected Vector<Mesh3D> meshList = new Vector<Mesh3D>();
	


public void build(GL2 gl)
{
	
	for (Mesh3D  mesh : this.meshList)
	{
		mesh.initMesh(gl);
	}
}
	
public void draw(GL2 gl)
{
	
	
	for (Mesh3D  mesh : this.meshList)
	{
		mesh.drawMesh(gl);
	}
	

	
	
}

public Vector<Mesh3D> getMeshList() {
	return meshList;
}
	
}
