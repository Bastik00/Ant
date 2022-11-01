package de.hska.lat.robot.display.generics.liveView.originDrawer;


import javax.media.opengl.GL2;


import de.hska.lat.robot.display.generics.liveView.drawer.Drawer3D;
import de.hska.lat.robot.morphology.appearance.Mesh3D;

public class OriginDrawer extends Drawer3D
{

	protected Mesh3D appearance;
	
	
	@Override	
	public void init(GL2 gl)
	{
		if (this.appearance!=null)
		{
			this.appearance.initMesh(gl);
		}
	}	

	
	@Override
	public void display(GL2 gl)
	{
		this.appearance.drawMesh(gl);
	}	
	
	
}
