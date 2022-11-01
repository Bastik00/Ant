package de.hska.lat.robot.editor3d.editor.listener;

import java.util.ArrayList;
import java.util.List;

import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Cuboid;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class FigureHilighter {
	public List<Cuboid> highlightCuboids;

	public FigureHilighter() {
		highlightCuboids = new ArrayList<>();
	}

	/**
	 * 
	 * @param eventHandler
	 *            the eventhandler of the model
	 * @param figureID
	 *            id of the Figure to highlights
	 */
	public void hilight(FiguresEventHandler eventHandler, int figureID) {
		Figure figure = eventHandler.getFigures().getFigure(figureID);
		float[] vertices = figure.getVertices();

		for (int i = 0; i < vertices.length / 10; i++) {
			Cuboid cuboidOnCoordinate = new Cuboid(eventHandler, "Point", "",
					1.0f, 1.0f, 0.0f, 1.0f);
			cuboidOnCoordinate.updateCuboid(5, 5, 5, vertices[(i * 10)],
					vertices[(i * 10) + 1], vertices[(i * 10) + 2]);
			highlightCuboids.add(cuboidOnCoordinate);
			
		}
	}
	
	/**
	 * 
	 * @param eventHandler
	 * @param figureID
	 * @param coordinateID
	 */
	public void hilightSelectedCoordinate(FiguresEventHandler eventHandler, int figureID, int coordinateID) {
		Figure figure = eventHandler.getFigures().getFigure(figureID);
		float[] vertices = figure.getCoordinate(coordinateID);

			Cuboid cuboidOnCoordinate = new Cuboid(eventHandler, "Point", "",
					0.8f, 0.0f, 0.6f, 1.0f);

			cuboidOnCoordinate.updateCuboid(7, 7, 7, vertices[0], vertices[1], vertices[2]);
			highlightCuboids.add(cuboidOnCoordinate);
			
	}


	/**
	 * Removes the current highlighting from the model
	 */
	public void unHighlight() {
		for (Cuboid cuboid : highlightCuboids) {
			cuboid.removeFigure();
		}
		highlightCuboids = new ArrayList<>();
	}

}
