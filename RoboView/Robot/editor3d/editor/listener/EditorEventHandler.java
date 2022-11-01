package de.hska.lat.robot.editor3d.editor.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.editor3d.editor.EditorInfo;
import de.hska.lat.robot.editor3d.line3d.Line3D;
import de.hska.lat.robot.editor3d.palette.Palette.Tool;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class EditorEventHandler {

	private final int INITIAL_STATE = 1;
	private final int POINT_SELECTED_STATE = 2;
	private final int CLICK_TOLERANCE = 3;
	private int currentState;
	private int[] selectedCoordinate;
	private float[] modelViewMatrix;
	private float[] projectionMatrix;
	private int[] viewPort;
	private GLU glu;
	private FiguresEventHandler eventHandler;
	private Point clickPosition;
	private Tool previousTool;
	private FigureHilighter highlighter;
	private List<int[]> triangles;
	private List<ArrayList<float[]>> triangles2D;
	private List<ArrayList<float[]>> triangles3D;

	/**
	 * Contains coords in the following form : figures, vertizes, vertex
	 */
	private ArrayList<ArrayList<float[]>> plainCoordinates;

	public EditorEventHandler(FiguresEventHandler eventHandler, EditorInfo info) {
		this.eventHandler = eventHandler;
		currentState = INITIAL_STATE;
		previousTool = Tool.HAND;
		highlighter = new FigureHilighter();

	}

	/**
	 * 
	 * @param clickposition
	 * @param plainCoordinates
	 * @param eventHandler
	 * @param modelViewMatrix
	 * @param projectionMatrix
	 * @param viewPort
	 * @param gl2
	 * @param glu
	 * @param vertices
	 * @return
	 */
	public boolean handleMouseClicks(Point clickposition,
			ArrayList<ArrayList<float[]>> plainCoordinates,
			FiguresEventHandler eventHandler, float[] modelViewMatrix,
			float[] projectionMatrix, int[] viewPort, GL2 gl2, GLU glu,
			ArrayList<ArrayList<float[]>> vertices, MouseEvent e) {

		Point coordsInView = clickposition;
		Figures figures = eventHandler.getFigures();
		this.plainCoordinates = plainCoordinates;
		this.modelViewMatrix = modelViewMatrix;
		this.projectionMatrix = projectionMatrix;
		this.viewPort = viewPort;
		this.clickPosition = clickposition;
		this.glu = glu;
		boolean returnState = false;
		highlighter.unHighlight();
		if (!eventHandler.getTool().equals(previousTool)) {
			currentState = INITIAL_STATE;
			selectedCoordinate = null;
			highlighter.unHighlight();
			previousTool = eventHandler.getTool();
		}
		if (eventHandler.getTool().equals(Tool.HAND)) {
			if (currentState == INITIAL_STATE) {
				selectedCoordinate = new int[3];
				int[] figureAndVertexOfClickedPoint = getClosestPointIfAny(
						coordsInView.x, coordsInView.y);
				if (figureAndVertexOfClickedPoint == null) {
					returnState = false;
				}
				if (figureAndVertexOfClickedPoint != null) {
					selectedCoordinate = figureAndVertexOfClickedPoint;
					currentState = POINT_SELECTED_STATE;
					highlighter.hilight(eventHandler,
							figureAndVertexOfClickedPoint[0]);
					highlighter.hilightSelectedCoordinate(eventHandler,
							figureAndVertexOfClickedPoint[0],
							figureAndVertexOfClickedPoint[1]);
					eventHandler
							.setSelectedFigure(figureAndVertexOfClickedPoint[0]);
					generateTriangles2d3d(selectedCoordinate[0]);
				}
			} else if (currentState == POINT_SELECTED_STATE) {
				FloatVector3D pointInModel = projectPointIntoModel(
						coordsInView, selectedCoordinate);

				figures.getFigure(selectedCoordinate[0]).updateCoordinate(
						selectedCoordinate[1], pointInModel.x, pointInModel.y,
						pointInModel.z);

				currentState = INITIAL_STATE;
				selectedCoordinate = null;
				eventHandler.setSelectedFigure(-1);
				returnState = true;
				highlighter.unHighlight();
			}
		} else if (eventHandler.getTool().equals(Tool.TRIANGLE)) {
			currentState = INITIAL_STATE;

			for (int i = 0; i < figures.getAllFigures().size(); i++) {
				generateTriangles2d3d(i);
				returnState = clickedOnLine();
				i++;
				if (returnState) {
					break;
				}
			}
		} else if (eventHandler.getTool().equals(Tool.SPLIT_TRIANGLE)) {

			List<int[]> positionsOfPointInTriangle = null;
			List<Integer> affectedTriangles = new ArrayList<>();

			for (int i = 0; i < figures.getAllFigures().size(); i++) {
				positionsOfPointInTriangle = getPositionOfPointInTriangle(i);
				if (positionsOfPointInTriangle != null) {
					break;
				}
			}
			if (positionsOfPointInTriangle == null
					|| positionsOfPointInTriangle.size() == 0) {
				return false;
			}

			for (int[] value : positionsOfPointInTriangle) {
				affectedTriangles.add(value[0]);
			}

			Collections.sort(affectedTriangles);
			Collections.reverse(affectedTriangles);
			FloatVector3D generatePoint = generatePoint(
					positionsOfPointInTriangle.get(0)[0],
					positionsOfPointInTriangle.get(0)[1],
					positionsOfPointInTriangle.get(0)[2]);
			int newPointID = (eventHandler.getFigures()
					.getFigure(positionsOfPointInTriangle.get(0)[3])
					.getVertices().length / 10);
			Cuboid cube = new Cuboid(eventHandler,
					positionsOfPointInTriangle.get(0)[3]);
			cube.addVertex(newPointID, generatePoint.x, generatePoint.y,
					generatePoint.z);

			int i = 0;
			for (int[] triangleValues : positionsOfPointInTriangle) {
				splitTriangle(affectedTriangles.get(i), triangleValues[1],
						triangleValues[2], triangleValues[3], newPointID);
				i++;
			}
		}
		eventHandler.notifyUpdate(null);
		return returnState;
	}

	/**
	 * Takes start, end and newpoint and creates two new triangles from it, then inserts it into the model
	 * @param triangle
	 * @param startPoint
	 * @param endPoint
	 * @param figureID
	 * @param newPointID
	 */
	private void splitTriangle(int triangle, int startPoint, int endPoint,
			int figureID, int newPointID) {
		int startpointID = this.triangles.get(triangle)[startPoint];
		int endpointID = this.triangles.get(triangle)[endPoint];
		int thirdpoint = 3 - (startPoint + endPoint); 
		int thirdpointID = this.triangles.get(triangle)[thirdpoint];
		int[] newTriangle1 = { startpointID, newPointID, thirdpointID };
		int[] newTriangle2 = { newPointID, endpointID, thirdpointID };

		eventHandler.getFigures().getFigure(figureID).removeTriangle(triangle);
		Cuboid cube = new Cuboid(eventHandler, figureID);
		cube.addElements(newTriangle1[0], newTriangle1[1], newTriangle1[2]);
		cube.addElements(newTriangle2[0], newTriangle2[1], newTriangle2[2]);
		System.out.println("vor update");
		cube.setStatus("done");
		eventHandler.notifyUpdate(null);

	}

	/**
	 * resets the state machine
	 */
	public void reset() {
		currentState = INITIAL_STATE;
		highlighter.unHighlight();
		selectedCoordinate = null;
		eventHandler.setSelectedFigure(-1);
		eventHandler.notifyUpdate(null);
	}

	/**
	 * 
	 * @return The figure and vertex index in @link{plainCoordinates} of the
	 *         point clicked by the user null if there is none
	 * 
	 */
	public int[] getClosestPointIfAny(float xClicked, float yClicked) {
		int figureIndex = 0;
		int vertexIndex = 0;
		int[] closestPoint = null;
		double diff = (double) CLICK_TOLERANCE;
		if (plainCoordinates == null)
			return null;
		for (ArrayList<float[]> figures : plainCoordinates) {
			for (float[] vertex : figures) {
				Point clickedPoint = new Point((int) xClicked, (int) yClicked);
				Point pointFromModel = new Point((int) vertex[0],
						(int) vertex[1]);

				if ((clickedPoint.distance(pointFromModel)) < diff) {
					closestPoint = new int[] { figureIndex, vertexIndex };
					diff = clickedPoint.distance(pointFromModel);
				}
				++vertexIndex;
			}
			vertexIndex = 0;
			++figureIndex;
		}
		return closestPoint;
	}

	/**
	 * Projects a 2D point into the model
	 * @param point
	 * @param selectedFigure
	 * @return
	 */
	private FloatVector3D projectPointIntoModel(Point point,
			int[] selectedFigure) {

		float[] pointInModel = new float[3];

		int realY = viewPort[3] - point.y - 1;
		float[] coordsIn2D = plainCoordinates.get(selectedFigure[0]).get(
				selectedFigure[1]);

		glu.gluUnProject(point.x, realY, coordsIn2D[2], modelViewMatrix, 0,
				projectionMatrix, 0, viewPort, 0, pointInModel, 0);

		return new FloatVector3D(pointInModel[0], pointInModel[1],
				pointInModel[2]);
	}

	/**
	 * 
	 * @return If the User clicked on the line, this method returns the two
	 *         points this click intersected
	 */
	private boolean clickedOnLine() {
		for (int i = 0; i < eventHandler.getFigures().getSize(); i++) {
			int[] result;
			if ((result = getPositionOfPointInTriangle(i).get(0)) != null) {
				createAndAddNewTriangle(result[0], result[1], result[2],
						result[3]);
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates the values for triangle2d and triangle3d. This way we know
	 * which figure is currently in scope
	 * 
	 * @param figureID
	 */
	private void generateTriangles2d3d(int figureID) {
		Figure figure = eventHandler.getFigures().getFigure(figureID);
		char[] allElements = eventHandler.getFigures().getFigure(figureID)
				.getElements();

		float[] vertices = figure.getVertices();
		triangles = new ArrayList<>();
		for (int i = 0; i < allElements.length; i += 3) {
			int[] triangle = new int[3];
			triangle[0] = (int) allElements[i];
			triangle[1] = (int) allElements[i + 1];
			triangle[2] = (int) allElements[i + 2];
			triangles.add(triangle);
		}

		triangles3D = new ArrayList<>();
		for (int[] triangle : triangles) {
			ArrayList<float[]> triangle3D = new ArrayList<>();
			float[] point1OfTriangle = new float[3];
			point1OfTriangle[0] = vertices[(triangle[0]) * 10]; // X
			point1OfTriangle[1] = vertices[(triangle[0]) * 10 + 1]; // Y
			point1OfTriangle[2] = vertices[(triangle[0]) * 10 + 2]; // Z

			float[] point2OfTriangle = new float[3];
			point2OfTriangle[0] = vertices[(triangle[1]) * 10];
			point2OfTriangle[1] = vertices[(triangle[1]) * 10 + 1];
			point2OfTriangle[2] = vertices[(triangle[1]) * 10 + 2];

			float[] point3OfTriangle = new float[3];
			point3OfTriangle[0] = vertices[(triangle[2]) * 10];
			point3OfTriangle[1] = vertices[(triangle[2]) * 10 + 1];
			point3OfTriangle[2] = vertices[(triangle[2]) * 10 + 2];
			triangle3D.add(point1OfTriangle);
			triangle3D.add(point2OfTriangle);
			triangle3D.add(point3OfTriangle);
			triangles3D.add(triangle3D);
		}

		triangles2D = new ArrayList<>();
		ArrayList<float[]> coordinatesOfFigure = plainCoordinates.get(figureID);

		for (int[] triangle : triangles) {
			ArrayList<float[]> triangle2D = new ArrayList<>();
			float[] point1 = coordinatesOfFigure.get((triangle[0]));
			float[] point2 = coordinatesOfFigure.get((triangle[1]));
			float[] point3 = coordinatesOfFigure.get((triangle[2]));
			triangle2D.add(point1);
			triangle2D.add(point2);
			triangle2D.add(point3);
			triangles2D.add(triangle2D);
		}
	}

	/**
	 * 
	 * @param figureID
	 *            the id of the current figure
	 * @return thie triangle, start and endpoint of the Line on which the point
	 *         is, and the figureID
	 */
	private List<int[]> getPositionOfPointInTriangle(int figureID) {
		generateTriangles2d3d(figureID);
		int currentTriangle = 0;
		ArrayList<int[]> positionsOfPointInTriangles = new ArrayList<>();
		for (ArrayList<float[]> triangle : triangles2D) {
			Line2D.Float line12 = new Line2D.Float(triangle.get(0)[0],
					triangle.get(0)[1], triangle.get(1)[0], triangle.get(1)[1]);
			if (line12.ptSegDist(clickPosition.x, clickPosition.y) < CLICK_TOLERANCE) {
				positionsOfPointInTriangles.add(new int[] { currentTriangle, 0,
						1, figureID });
			}

			Line2D.Float line23 = new Line2D.Float(triangle.get(1)[0],
					triangle.get(1)[1], triangle.get(2)[0], triangle.get(2)[1]);
			if (line23.ptSegDist(clickPosition.x, clickPosition.y) < CLICK_TOLERANCE) {
				positionsOfPointInTriangles.add(new int[] { currentTriangle, 1,
						2, figureID });
			}

			Line2D.Float line31 = new Line2D.Float(triangle.get(2)[0],
					triangle.get(2)[1], triangle.get(0)[0], triangle.get(0)[1]);
			if (line31.ptSegDist(clickPosition.x, clickPosition.y) < CLICK_TOLERANCE) {
				positionsOfPointInTriangles.add(new int[] { currentTriangle, 2,
						0, figureID });
			}
			currentTriangle++;
		}
		return positionsOfPointInTriangles;
	}

	/**
	 * Creates and inserts the new triangle 
	 * @param triangle
	 * @param startpoint
	 * @param endpoint
	 * @param figureID
	 */
	private void createAndAddNewTriangle(int triangle, int startpoint,
			int endpoint, int figureID) {

		FloatVector3D pointInSegmentWithDistance = generatePoint(triangle,
				startpoint, endpoint);

		int startPointID = triangles.get(triangle)[startpoint];
		int endPointID = triangles.get(triangle)[endpoint];
		new NewMeshWithNoUI(eventHandler, pointInSegmentWithDistance,
				endPointID, startPointID, figureID);
		highlighter.hilightSelectedCoordinate(eventHandler, figureID,startpoint);
		highlighter.hilightSelectedCoordinate(eventHandler, figureID, endpoint);
		}

	/**
	 * Generates the point at clickposition
	 * @param triangle
	 * @param startpoint
	 * @param endpoint
	 * @return
	 */
	private FloatVector3D generatePoint(int triangle, int startpoint,
			int endpoint) {
		Point a2d = new Point(
				(int) triangles2D.get(triangle).get(startpoint)[0],
				(int) triangles2D.get(triangle).get(startpoint)[1]);

		Point b2d = new Point((int) triangles2D.get(triangle).get(endpoint)[0],
				(int) triangles2D.get(triangle).get(endpoint)[1]);

		FloatVector3D a3d = new FloatVector3D(triangles3D.get(triangle).get(
				startpoint)[0], triangles3D.get(triangle).get(startpoint)[1],
				triangles3D.get(triangle).get(startpoint)[2]);

		FloatVector3D b3d = new FloatVector3D(triangles3D.get(triangle).get(
				endpoint)[0], triangles3D.get(triangle).get(endpoint)[1],
				triangles3D.get(triangle).get(endpoint)[2]);

		double distance2d = a2d.distance(b2d);
		double factor = a2d.distance(clickPosition) / distance2d;
		double distance3d = a3d.distance(b3d);

		double distanceAP3d = factor * distance3d;
		Line3D line = new Line3D(a3d.copy(), b3d.copy());
		FloatVector3D pointInSegmentWithDistance = line
				.getPointInSegmentWithDistance(distanceAP3d);
		return pointInSegmentWithDistance;
	}

	@SuppressWarnings("static-access")
	public void drawAxes(GL2 gl) {
		// X - Axis red
		gl.glBegin(gl.GL_LINES);
		gl.glColor4f(1.0f, 0.0f, 0.0f, .4f);
		gl.glVertex3f(-1000.0f, 0.0f, 0.0f);
		gl.glVertex3f(1000.0f, 0.0f, 0.0f);
		gl.glEnd();
		// Y - Axis green
		gl.glBegin(gl.GL_LINES);
		gl.glColor4f(0.0f, 1.0f, 0.0f, .4f);
		gl.glVertex3f(0.0f, -1000.0f, 0.0f);
		gl.glVertex3f(0.0f, 1000.0f, 0.0f);
		gl.glEnd();
		// Z - Axis blue
		gl.glBegin(gl.GL_LINES);
		gl.glColor4f(0.0f, 0.0f, 1.0f, .4f);
		gl.glVertex3f(0.0f, 0.0f, 1000.0f);
		gl.glVertex3f(0.0f, 0.0f, -1000.0f);
		gl.glEnd();
	}

}