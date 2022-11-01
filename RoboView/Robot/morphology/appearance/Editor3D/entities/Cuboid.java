package de.hska.lat.robot.morphology.appearance.Editor3D.entities;

import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresListener;

public class Cuboid implements FiguresListener {
	
	private Figures figures;
	private Figure figure;
	private int figureId;
	FiguresEventHandler eventHandler;
	public Cuboid(
			FiguresEventHandler eventHandler,
			String figureType,
			String name,
			float colorR,
			float colorG,
			float colorB,
			float colorA
	) {
		
		this(eventHandler, figureType, name);
		setColor(colorR, colorG, colorB, colorA);
	}

	public Cuboid(
			FiguresEventHandler eventHandler,
			String figureType,
			String name
	) {

		this.eventHandler = eventHandler;
		this.figures = eventHandler.getFigures();
		figure = new Figure();
		figure.setType(figureType);
		figure.setName(name);
		createCuboid(0, 0, 0, 0, 0, 0);
		
		figure.setStatus("edit");
		
	}
	
	public Cuboid(FiguresEventHandler eventHandler, int figureId) {

		this.figures = eventHandler.getFigures();
		
		this.figureId = figureId;
		figure = eventHandler.getFigures().getFigure(figureId);
		
		figure.setStatus("edit");
		
	}

	public void createCuboid(float widthX, float widthY, float widthZ, float centerX, float centerY, float centerZ) {


		figure.addCoordinate(0, centerX - widthX / 2, centerY + widthY / 2, centerZ - widthZ / 2);
		figure.addCoordinate(1, centerX + widthX / 2, centerY + widthY / 2, centerZ - widthZ / 2);
		figure.addCoordinate(2, centerX + widthX / 2, centerY - widthY / 2, centerZ - widthZ / 2);
		figure.addCoordinate(3, centerX - widthX / 2, centerY - widthY / 2, centerZ - widthZ / 2);

		figure.addCoordinate(4, centerX - widthX / 2, centerY - widthY / 2, centerZ + widthZ / 2);
		figure.addCoordinate(5, centerX - widthX / 2, centerY + widthY / 2, centerZ + widthZ / 2);
		figure.addCoordinate(6, centerX + widthX / 2, centerY + widthY / 2, centerZ + widthZ / 2);
		figure.addCoordinate(7, centerX + widthX / 2, centerY - widthY / 2, centerZ + widthZ / 2);

		
		char[] elements = {
				0,1,2,
				2,3,0,
				4,5,6,
				6,7,4,
				0,4,7,
				0,3,7,
				0,4,5,
				0,1,5,
				1,2,6,
				1,5,6,
				2,3,7,
				2,6,7
			};
			
		figure.addElements(elements);
		
		setFigureId(figures.addFigure(figure));
	}
	
	
	public void updateCuboid(float widthX, float widthY, float widthZ, float centerX, float centerY, float centerZ) {
		figure.updateCoordinate(0, centerX + widthX / 2, centerY + widthY / 2, centerZ - widthZ / 2);
		figure.updateCoordinate(1, centerX + widthX / 2, centerY - widthY / 2, centerZ - widthZ / 2);
		figure.updateCoordinate(2, centerX - widthX / 2, centerY - widthY / 2, centerZ - widthZ / 2);
		figure.updateCoordinate(3, centerX - widthX / 2, centerY + widthY / 2, centerZ - widthZ / 2);

		figure.updateCoordinate(4, centerX + widthX / 2, centerY + widthY / 2, centerZ + widthZ / 2);
		figure.updateCoordinate(5, centerX + widthX / 2, centerY - widthY / 2, centerZ + widthZ / 2);
		figure.updateCoordinate(6, centerX - widthX / 2, centerY - widthY / 2, centerZ + widthZ / 2);
		figure.updateCoordinate(7, centerX - widthX / 2, centerY + widthY / 2, centerZ + widthZ / 2);
		
	}
	
	public void addVertex(int vertexId, float x, float y, float z) {
		figure.addCoordinate(vertexId, x, y, z);
	}
	
	public void removeVertex (int vertexId) {
		figure.removePoint(vertexId);
	}
	
	public void addElements(int vertexA, int vertexB, int vertexC) {
		figure.addElement(figure.getElements().length, (char)Integer.parseInt(String.valueOf(vertexA), 10));
		figure.addElement(figure.getElements().length, (char)Integer.parseInt(String.valueOf(vertexB), 10));
		figure.addElement(figure.getElements().length, (char)Integer.parseInt(String.valueOf(vertexC), 10));
	}
	
	public void removeLastElements() {
		figure.deleteLastElements();
	}
	
	public void updateVertex(int selectedPoint, float vertexX, float vertexY, float vertexZ) {
		figure.updateCoordinate(
				selectedPoint,
				vertexX,
				vertexY,
				vertexZ
		);
		
	}
	
	public void deletePointFromFigure(int pointId) {
		figure.removePoint(pointId);
		
		char[] elements = figure.getElements();
		for (int i = 0; i < (elements.length / 3); i++) {
			figure.deleteLastElements();
		}

		for (int i = 0; i < (elements.length / 3); i++) {

			int value1 = (int)elements[i * 3];
			int value2 = (int) elements[(i * 3) + 1];
			int value3 = (int) elements[(i * 3) + 2];
			
			
			if (
					value1 != pointId
				 && value2 != pointId
				 && value3 != pointId
			) {
				if (value1 > pointId) {
					value1 = value1 - 1;
				}
				if (value2 > pointId) {
					value2 = value2 - 1;
				}
				if (value3 > pointId) {
					value3 = value3 - 1;
				}
				
				char[] newElements = new char[3];
				newElements[0] = (char) value1;
				newElements[1] = (char) value2;
				newElements[2] = (char) value3;
				
				figure.addElements(newElements);
				//figure.deleteElements(i);
			}
		}
	}
	
	// löscht die 3 letzten Element einträge
	public void deleteLastElements() {
		figure.deleteLastElements();
	}
	
	public void removeFigure() {
		figures.removeFigure(figure);
	}

	public void replaceFigure(Figure newFigure) {		
		figures.replaceFigure(figure, newFigure);
	}
	

	public String getName() {
		return figure.getName();
	}

	public void setName(String name) {
		figure.setName(name);
	}
	
	public int getFigureId() {
		return figureId;
	}

	public void setFigureId(int figureId) {
		this.figureId = figureId;
	}

	public String getStatus() {
		return figure.getStatus();
	}

	public void setStatus(String status) {
		figure.setStatus(status);
	}

	@Override
	public void wasUpdated(FiguresEvent e) {

		figure = eventHandler.getFigures().getFigure(figureId);
		
	}

	public float[] getVertices() {
		return figure.getVertices();
	}

	
	public char[] getElements() {
		return figure.getElements();
	}
	
	
	
	public void setColor(float colorR, float colorG, float colorB, float colorA) {
		
		figure.setColor(colorR, colorG, colorB, colorA);
	}
	public float getColorR() {
		return figure.getColorR();
	}
	public float getColorG() {
		return figure.getColorG();
	}
	public float getColorB() {
		return figure.getColorB();
	}
	public float getColorA() {
		return figure.getColorA();
	}
}
