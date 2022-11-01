package de.hska.lat.robot.display.generics.liveView;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import de.hska.lat.robot.display.generics.liveView.fieldOfViewDrawer.FieldOfView3DDrawer;
import de.hska.lat.robot.display.generics.liveView.originDrawer.DefaultOriginDrawer;
import de.hska.lat.robot.display.generics.liveView.originDrawer.OriginDrawer;
import de.hska.lat.view3D.View3D;



public class LiveView extends View3D
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -862180044924327432L;

	
	protected OriginDrawer origin = new DefaultOriginDrawer();
	protected ArrayList<FieldOfView3DDrawer<?>> fieldOfViewsList = new ArrayList <FieldOfView3DDrawer<?>>();
	
	
	
public LiveView()
{
	super("live view");
}
	/*
	@Override	
	public void setRobot(Robot robot)
	{
		
		

		this.animator.start();
		
	    this.appearanceModel = robot.getAppearanceModel();
	    this.robot = robot;
		
		panel.setBounds(0,30,500,500);
		this.add(panel);
		panel.addGLEventListener(this);
	   
	    
	    
	}
*/
@Override
public void init(GLAutoDrawable gLDrawable) 
{
	super.init(gLDrawable);
    final GL2 gl = gLDrawable.getGL().getGL2();
	
	if (this.origin!=null)
	{
		this.origin.init(gl);	
	}
	
	if (this.appearanceModel!=null)
	{
		this.appearanceModel.init(gl,this.robot.getPose());
	}
	
}





@Override
public void display(GLAutoDrawable gLDrawable)
{
    final GL2 gl = gLDrawable.getGL().getGL2();
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
 
    gl.glLoadIdentity();
    
    
    glu.gluLookAt((int)this.camera.getCameraPosition().x,
			(int)this.camera.getCameraPosition().y,
			(int)this.camera.getCameraPosition().z,
			
			(int)this.camera.getViewpointPosition().x,
			(int)this.camera.getViewpointPosition().y,
			(int)this.camera.getViewpointPosition().z,
			
			(int)this.camera.getUpVector().x,
			(int)this.camera.getUpVector().y,
			(int)this.camera.getUpVector().z);
		
    
    
//	gl.glLoadIdentity();

    if (this.origin!=null)
    {   
    	this.origin.display(gl);
    }
// draw other things

 
    
    for (FieldOfView3DDrawer<?> fovDrawer : fieldOfViewsList)
    {
    	 fovDrawer.display(gl);
    }

    
	if (this.appearanceModel!=null)
	{
		//t.drawMesh(gl);
		//gl.glLoadIdentity();
	    this.appearanceModel.draw(gl);
	}
	
       

    
}



/*	
@Override
public void display(GLAutoDrawable gLDrawable)
{
    final GL2 gl = gLDrawable.getGL().getGL2();
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
 
    gl.glLoadIdentity();
    
    
    float [] cameraData = camera.getData(); 
    
    glu.gluLookAt(cameraData[Camera.xPosition],
			cameraData[Camera.yPosition],
			cameraData[Camera.zPosition],
			
			cameraData[Camera.xView],
			cameraData[Camera.yView],
			cameraData[Camera.zView],
			
			cameraData[Camera.xUp],
			cameraData[Camera.yUp],
			cameraData[Camera.zUp]);
    


    if (this.origin!=null)
    {   
    	this.origin.display(gl);
    }
    */
// draw other things
/*
    for (FieldOfView3DDrawer<?> fovDrawer : fieldOfViewsList)
    {
    	   gl.glLoadIdentity();
    	   fovDrawer.display(gl);
    }
  */   
   /* 
	if (this.appearanceModel!=null)
	{
	   gl.glLoadIdentity();
	   this.appearanceModel.draw(gl);
	}
	

    

    
}
*/
	
	
	
}
