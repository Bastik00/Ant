package de.hska.lat.robot.morphology.appearance.Editor3D.entities;

import java.util.ArrayList;

public class Figures {

	private ArrayList<Figure> allFigures;

	public ArrayList<Figure> getAllFigures() {
		return allFigures;
	}

	public Figures() {
		allFigures = new ArrayList<Figure>();
	}

	public int addFigure(Figure figure) {
		allFigures.add(figure);
		return allFigures.size() - 1;
	}

	public int addFigure(int index, Figure figure) {
		allFigures.remove(index);
		allFigures.add(index, figure);
		return index;
	}

	public void removeFigure(Figure figure) {
		allFigures.remove(figure);
	}

	public void replaceFigure(Figure oldFigure, Figure newFigure) {
		int index = allFigures.indexOf(oldFigure);
		addFigure(index, newFigure);
	}

	public Figure getFigure(int index) {
		return allFigures.get(index);
	}

	
	public void clearFigures() {
		allFigures.clear();
	}

	public int getSize() {
		return allFigures.size();
	}

}
