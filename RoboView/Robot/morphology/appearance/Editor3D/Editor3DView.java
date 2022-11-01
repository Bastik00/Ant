package de.hska.lat.robot.morphology.appearance.Editor3D;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.morphology.appearance.Mesh3D;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;
import de.hska.lat.robot.openGl.camera.Camera;
import de.hska.lat.view3D.cameraControl.CameraControl;

import com.jogamp.opengl.util.FPSAnimator;



public class Editor3DView extends DisplayFrame implements GLEventListener, ActionListener, FiguresListener {

	private static final long serialVersionUID = 1L;
	private GLJPanel panel  = new GLJPanel();
	private static GLU glu = new GLU();
	private static FPSAnimator animator  = new FPSAnimator(30);
	private JPopupMenu contextMenue;
	
	private Camera camera = new Camera();
	
	private FiguresEventHandler eventHandler;
	private Figures figures;

	private boolean isArrayListUpdate = false;

	private Vector<Mesh3D> meshList;
	
	public Editor3DView(FiguresEventHandler eventHandler) {
		super("Editor3D MainView", true, true, true, true);
		
		this.eventHandler = eventHandler;
		eventHandler.addFiguresListener(this);
		this.figures = eventHandler.getFigures();
		
		meshList = new Vector<Mesh3D>();
		
		this.setLayout(null);
		this.setSize(522,540);
		this.show();
		
		this.makePopupMenu();	
		animator.add(this.panel);
		setFigures();
	}
	
	public void setFigures() {
		for (int i = 0; i < figures.getSize(); i++) {
			Mesh3D figureMesh = new Mesh3D(null, figures.getFigure(i).getVertices(), figures.getFigure(i).getElements());

			meshList.add(figureMesh);
		}
		
		animator.start();
		
		panel.setBounds(5,5,500,500);
		this.add(panel);
		panel.addGLEventListener(this);
	    
	}
	
	private boolean checkForUpdates(GL2 gl) {
		if (isArrayListUpdate) {
			animator.pause();
			int meshSize = meshList.size();
			for (int c = 0; c < meshSize; c++) {
				meshList.remove(0);
			}
			
			for (int i = 0; i < figures.getSize(); i++) {
				Figure figure = figures.getFigure(i);
				if (figure.isVisibile()) {
					Mesh3D figureMesh = new Mesh3D(null, figure.getVertices(), figure.getElements());
	
					meshList.add(figureMesh);
					figureMesh.initMesh(gl);
				}
			}
			

			isArrayListUpdate = false;
			animator.resume();
			
		}
		return true;
	}
	

	@Override
	public void dispose(GLAutoDrawable arg0) {}
	
	public void init(GLAutoDrawable gLDrawable) 
	{
	    GL2 gl = gLDrawable.getGL().getGL2();
	    
	    gl.setSwapInterval(1);


	    gl.glShadeModel(GLLightingFunc.GL_FLAT);
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    gl.glClearDepth(1.0f);
	    gl.glEnable(GL.GL_DEPTH_TEST);
	    gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	    gl.glEnable(GL.GL_BLEND); //Enable blending.
	    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
	    
	    glu.gluLookAt((int)this.camera.getCameraPosition().x,
    			(int)this.camera.getCameraPosition().y,
    			(int)this.camera.getCameraPosition().z,
    			
    			(int)this.camera.getViewpointPosition().x,
    			(int)this.camera.getViewpointPosition().y,
    			(int)this.camera.getViewpointPosition().z,
    			
    			(int)this.camera.getUpVector().x,
    			(int)this.camera.getUpVector().y,
    			(int)this.camera.getUpVector().z);
    		
	    
		for (Mesh3D  mesh : this.meshList)
		{
			mesh.initMesh(gl);
		}
	}
	
	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) 
	{
	    GL2 gl = gLDrawable.getGL().getGL2();
	    if (height <= 0) {
	        height = 1;
	    }
	    float h = (float) width / (float) height;
	    gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
	    gl.glLoadIdentity();
	    glu.gluPerspective(50.0f, h, 1.0, 10000.0);
	    
	    gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	    gl.glLoadIdentity();
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
    		
	    
	
	    checkForUpdates(gl);
	    for (Mesh3D  mesh : this.meshList)
		{
			mesh.drawMesh(gl);
		}
		
	   gl.glLoadIdentity();
	}
	
	
	private void makePopupMenu()
	{
		this.contextMenue = new JPopupMenu();
		MouseListener popupListener = new PopupListener();
		this.addMenuItem(this.contextMenue , "Camera", "aa ");
		this.addMouseListener(popupListener);
	}
	
	private JMenuItem addMenuItem(JPopupMenu popupMenu, String text, String command) {
		  JMenuItem  menuItem = new JMenuItem(text);
		   menuItem.addActionListener(this);
		   menuItem.setActionCommand(command);
		   popupMenu.add(menuItem);
	
		   return(menuItem); 
	}
	
	class PopupListener extends MouseAdapter {
		public Point mousePosition;
		public void mousePressed(MouseEvent e) 
	    {
	    	maybeShowPopup(e);
	    }
	
	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }
	    
	    private void maybeShowPopup(MouseEvent e) {
	    	contextMenue.show(e.getComponent(), e.getX(), e.getY());
	    }
	    public void mouseMoved(MouseEvent e) {}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		this.getDesktopPane().add(new CameraControl(this.camera)); 
	}
	
	
	@Override
	public void wasUpdated(FiguresEvent event) {
		isArrayListUpdate = true;
		figures = eventHandler.getFigures();
	}
}
