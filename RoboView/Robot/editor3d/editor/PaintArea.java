package de.hska.lat.robot.editor3d.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.Transient;
import java.util.Vector;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.editor3d.editor.Editor.Axes;
import de.hska.lat.robot.editor3d.editor.listener.EditorEventHandler;
import de.hska.lat.robot.morphology.appearance.Mesh3D;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;
import de.hska.lat.robot.openGl.camera.Camera;
import com.jogamp.opengl.util.FPSAnimator;

/**
 * Provides the view of the editor
 * 
 * @author moritz
 * 
 */
public class PaintArea extends JPanel implements GLEventListener,
		MouseListener, MouseMotionListener, ActionListener, FiguresListener {

	private static final long serialVersionUID = 1L;
	private GLJPanel panel = new GLJPanel();
	private static GLU glu = new GLU();
	private FPSAnimator animator;
	private JPopupMenu contextMenu;
	private GL2 glX;
	private Camera camera = new Camera();
	private ArrayList<ArrayList<float[]>> plainCoordinates;
	private ArrayList<ArrayList<float[]>> allCoordinates;
	private EditorEventHandler editorEventHandler;
	private EditorInfo editorInfo;
	private FiguresEventHandler eventHandler;
	private Figures figures;
	private float[] modelViewMatrix;
	private float[] projectionMatrix;
	private int[] viewPort;
	private boolean isArrayListUpdate = false;
	private Vector<Mesh3D> meshList;
	private float stdFovy;
	private float zoomfactor;
	private boolean zoomChanged;
	private Point clickPoint;
	private static int CAMERA_MOVE_FACTOR = 50;

	public PaintArea(FPSAnimator animator, FiguresEventHandler eventHandler,
			EditorInfo editorInfo) {
		this.addMouseListener(this);
		this.eventHandler = eventHandler;
		this.eventHandler.addFiguresListener(this);
		this.editorInfo = editorInfo;
		this.animator = animator;
		this.plainCoordinates = new ArrayList<>();
		this.setLayout(null);
		this.editorEventHandler = new EditorEventHandler(eventHandler,
				editorInfo);
		meshList = new Vector<Mesh3D>();
		panel.setBounds(0, 0, 400, 350);
		panel.setBackground(Color.black);
		add(panel);
		initializeCamera();
		zoomChanged = false;
		figures = eventHandler.getFigures();
		stdFovy = 50.0f;
		animator.add(panel);
		setFigures();

		modelViewMatrix = new float[16];
		projectionMatrix = new float[16];
		viewPort = new int[4];
		zoomfactor = 1;
		makeContextMenu();
	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension(350, 320);
	}

	public void setFigures() {
		for (int i = 0; i < figures.getSize(); i++) {
			Mesh3D figureMesh = new Mesh3D(null, figures.getFigure(i)
					.getVertices(), figures.getFigure(i).getElements());

			meshList.add(figureMesh);
		}

		animator.start();

		this.add(panel);
		panel.addGLEventListener(this);
	}

	/**
	 *This method projects all 3D coordinates into 2D view 
	 * @param gl2
	 */
	private void updatePlainCoordinates(GL2 gl2) {
		GL gl = gl2.getGL();
		this.glX = gl2;
		Figures figures = eventHandler.getFigures();

		ArrayList<ArrayList<float[]>> vertizes = new ArrayList<ArrayList<float[]>>();

		ArrayList<ArrayList<float[]>> pointsOnPlane = new ArrayList<ArrayList<float[]>>();
		for (Figure figure : figures.getAllFigures()) {
			if (figure.isVisibile()) {
				ArrayList<float[]> newFiguresVertizes = new ArrayList<>();

				float[] allVertizesOfFigure = figure.getVertices();
				for (int i = 0; i < allVertizesOfFigure.length; i += 10) {
					float[] currentVertex = new float[3];
					currentVertex[0] = allVertizesOfFigure[i];
					currentVertex[1] = allVertizesOfFigure[i + 1];
					currentVertex[2] = allVertizesOfFigure[i + 2];
					newFiguresVertizes.add(currentVertex);
				}
				vertizes.add(newFiguresVertizes);
			} else {
				vertizes.add(null);
			}
		}
		allCoordinates = vertizes;

		float[] modelViewMatrix = new float[16];
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, modelViewMatrix, 0);

		float[] projectionViewMatrix = new float[16];
		gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projectionViewMatrix, 0);

		int[] viewPort = new int[4];
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewPort, 0);

		for (ArrayList<float[]> figureVertexList : vertizes) {
			if (figureVertexList != null) {

				ArrayList<float[]> newFiguresVertizesInPlane = new ArrayList<>();
				for (float[] currentVertex : figureVertexList) {

					float[] currVertexInPlane = new float[3];

					glu.gluProject(currentVertex[0], currentVertex[1],
							currentVertex[2], modelViewMatrix, 0,
							projectionViewMatrix, 0, viewPort, 0,
							currVertexInPlane, 0);
					currVertexInPlane[1] = viewPort[3] - currVertexInPlane[1]
							- 1;
					newFiguresVertizesInPlane.add(currVertexInPlane);
				}
				pointsOnPlane.add(newFiguresVertizesInPlane);
			}
			this.plainCoordinates = pointsOnPlane;
			updateVectors(gl);
		}
	}

	/**
	 * updates the model to the new set of figures
	 * @param gl
	 * @return
	 */
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
					Mesh3D figureMesh = new Mesh3D(null, figure.getVertices(),
							figure.getElements());

					meshList.add(figureMesh);
					figureMesh.initMesh(gl);
				}
				updatePlainCoordinates(gl);
			}

			isArrayListUpdate = false;
			animator.resume();

		}
		return true;

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}

	/**
	 * initializes openGL drawings
	 */
	public void init(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();

		gl.setSwapInterval(1);

		gl.glShadeModel(GLLightingFunc.GL_FLAT);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthMask(true);
		gl.glDepthFunc(GL.GL_ALWAYS);
		gl.glDepthRange(0.0, 1.0);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glEnable(GL.GL_BLEND); // Enable blending.
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_LINE_STIPPLE);
		glu.gluLookAt(this.camera.getCameraPosition().x,
				this.camera.getCameraPosition().y,
				this.camera.getCameraPosition().z,
				this.camera.getViewpointPosition().x,
				this.camera.getViewpointPosition().y,
				this.camera.getViewpointPosition().z,
				this.camera.getUpVector().x, this.camera.getUpVector().y,
				this.camera.getUpVector().z);

		for (Mesh3D mesh : this.meshList) {
			mesh.initMesh(gl);
		}
		updatePlainCoordinates(gLDrawable.getGL().getGL2());
		drawAxes(gl);
		editorEventHandler.reset();
	}

	/**
	 * Shows the axes of the coordinate system
	 * 
	 * @param gl
	 */
	private void drawAxes(GL2 gl) {
		editorEventHandler.drawAxes(gl);
	}

	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		if (height <= 0) {
			height = 1;
		}

		float h = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(50.0f, h, 1.0, 1000.0);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();

	}

	@Override
	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);

		gl.glLoadIdentity();

		glu.gluLookAt(this.camera.getCameraPosition().x,
				this.camera.getCameraPosition().y,
				this.camera.getCameraPosition().z,
				this.camera.getViewpointPosition().x,
				this.camera.getViewpointPosition().y,
				this.camera.getViewpointPosition().z,
				this.camera.getUpVector().x, this.camera.getUpVector().y,
				this.camera.getUpVector().z);
		checkForUpdates(gl);
		for (Mesh3D mesh : this.meshList) {
			mesh.drawMeshForEditor(gl);
		}
		drawAxes(gl);
		gl.glLoadIdentity();
		if (isArrayListUpdate) {
			updatePlainCoordinates(gl);
		}
		if (zoomChanged) {
			zoomfactor = eventHandler.getZoomfactor();
			zoomChanged(zoomfactor, gl);
			zoomChanged = false;
		}
	}

	/**
	 * Handles Actions from the contextMenu
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			moveCamera((JButton) arg0.getSource());
			return;
		}
		int[] closestPointIfAny = this.editorEventHandler.getClosestPointIfAny(
				clickPoint.x, clickPoint.y);
		if (arg0.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) arg0.getSource();
			if (item.getName().equals("rmPoint")) {
				Cuboid cube = new Cuboid(eventHandler, closestPointIfAny[0]);
				cube.deletePointFromFigure(closestPointIfAny[1]);

			} else if (item.getName().equals("rmFigure")) {
				eventHandler.getFigures().removeFigure(
						eventHandler.getFigures().getFigure(
								closestPointIfAny[0]));
			}
		}
		eventHandler.notifyUpdate(null);
		editorEventHandler.reset();
	}

	@Override
	public void wasUpdated(FiguresEvent event) {
		isArrayListUpdate = true;
		figures = eventHandler.getFigures();
		if (eventHandler.getZoomfactor() != zoomfactor) {
			zoomChanged = true;
		}
	}

	private void initializeCamera() {
		this.camera.setCameraPosition(new FloatVector3D((float) editorInfo
				.getCameraposition().getPos()[0], (float) editorInfo
				.getCameraposition().getPos()[1], (float) editorInfo
				.getCameraposition().getPos()[2]));

		this.camera.setViewpointPosition(new FloatVector3D((float) editorInfo
				.getCameraposition().getLookAt()[0], (float) editorInfo
				.getCameraposition().getLookAt()[1], (float) editorInfo
				.getCameraposition().getLookAt()[2]));

		this.camera.setUpVector(new FloatVector3D((float) editorInfo
				.getCameraposition().getUp()[0], (float) editorInfo
				.getCameraposition().getUp()[1], (float) editorInfo
				.getCameraposition().getUp()[2]));

	}

	public Camera getCamera() {
		return camera;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.clickPoint = e.getPoint();
		if (SwingUtilities.isLeftMouseButton(e)) {
			isArrayListUpdate = editorEventHandler.handleMouseClicks(
					e.getPoint(), plainCoordinates, eventHandler,
					modelViewMatrix, projectionMatrix, viewPort, glX, glu,
					allCoordinates, e);
			wasUpdated(null);
		} else {
			int[] closestPointIfAny = this.editorEventHandler
					.getClosestPointIfAny(clickPoint.x, clickPoint.y);

			if (closestPointIfAny != null) {
				contextMenu.show(e.getComponent(), e.getX(), e.getY());
			} else {
				editorEventHandler.reset();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	private void updateVectors(GL gl) {
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, modelViewMatrix, 0);
		gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projectionMatrix, 0);
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewPort, 0);
	}

	/**
	 * Adjusts the field of view for a zoomed representation of the model
	 * @param factor
	 * @param gl2
	 */
	public void zoomChanged(float factor, GL2 gl2) {
		gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl2.glLoadIdentity();
		glu.gluPerspective(stdFovy / factor, viewPort[2] / viewPort[3], 1f,
				1000.0f);
		gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl2.glLoadIdentity();
	}

	/**
	 * Makes the contextmenu for right-click on points
	 */
	private void makeContextMenu() {
		this.contextMenu = new JPopupMenu();

		JMenuItem add = this.contextMenu.add("Remove Point");
		JMenuItem add2 = this.contextMenu.add("Remove Figure");
		add.setName("rmPoint");
		add2.setName("rmFigure");
		add.addActionListener(this);
		add2.addActionListener(this);
	}

	/**
	 * Handles the JButtons from the camera-control bar
	 * @param button
	 */
	private void moveCamera(JButton button) {
		String buttonName = button.getName();
		if (buttonName.equals("up")) {
			if (editorInfo.getIgnoredAxis().equals(Axes.Z)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y
						+ CAMERA_MOVE_FACTOR, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y + CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.Y)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y,
						camera.getCameraPosition().z - CAMERA_MOVE_FACTOR));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z - CAMERA_MOVE_FACTOR));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.X)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y
						+ CAMERA_MOVE_FACTOR, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y + CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().z));
			}
		} else if (buttonName.equals("down")) {
			if (editorInfo.getIgnoredAxis().equals(Axes.Z)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y
						- CAMERA_MOVE_FACTOR, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y - CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.Y)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y,
						camera.getCameraPosition().z + CAMERA_MOVE_FACTOR));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z + CAMERA_MOVE_FACTOR));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.X)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y
						- CAMERA_MOVE_FACTOR, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y - CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().z));
			}
		} else if (buttonName.equals("left")) {
			if (editorInfo.getIgnoredAxis().equals(Axes.Z)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x - CAMERA_MOVE_FACTOR, camera
						.getCameraPosition().y, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x - CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.Y)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x - CAMERA_MOVE_FACTOR, camera
						.getCameraPosition().y, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x - CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.X)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y,
						camera.getCameraPosition().z + CAMERA_MOVE_FACTOR));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z + CAMERA_MOVE_FACTOR));
			}
		} else if (buttonName.equals("right")) {
			if (editorInfo.getIgnoredAxis().equals(Axes.Z)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x + CAMERA_MOVE_FACTOR, camera
						.getCameraPosition().y, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x + CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.Y)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x + CAMERA_MOVE_FACTOR, camera
						.getCameraPosition().y, camera.getCameraPosition().z));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x + CAMERA_MOVE_FACTOR, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z));
			} else if (editorInfo.getIgnoredAxis().equals(Axes.X)) {
				this.camera.setCameraPosition(new FloatVector3D(camera
						.getCameraPosition().x, camera.getCameraPosition().y,
						camera.getCameraPosition().z - CAMERA_MOVE_FACTOR));
				this.camera.setViewpointPosition(new FloatVector3D(camera
						.getViewpointPosition().x, camera
						.getViewpointPosition().y, camera
						.getViewpointPosition().z - CAMERA_MOVE_FACTOR));
			}
		}
	}

	/**
	 * 
	 * @return the editorEventhandler of this editor
	 */
	public EditorEventHandler getEditorEventHandler() {
		return editorEventHandler;
	}
}
