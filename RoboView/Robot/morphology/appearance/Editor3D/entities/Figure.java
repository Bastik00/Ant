package de.hska.lat.robot.morphology.appearance.Editor3D.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Figure implements Serializable {

	private static final long serialVersionUID = 4362967098679379079L;
	private ArrayList<Object> vertices;
	private ArrayList<Object> elements;
	private String type;
	private boolean visibile = true;
	private String name;
	private String status;
	private float colorR;
	private float colorG;
	private float colorB;
	private float colorA;

	public Figure() {
		vertices = new ArrayList<Object>();
		elements = new ArrayList<Object>();
		colorR = 1.0f;
		colorG = 1.0f;
		colorB = 1.0f;
		colorA = 1.0f;
	}

	public void addCoordinate(int index, float x, float y, float z) {

		float arrVertices[] = new float[10];

		arrVertices[0] = x;
		arrVertices[1] = y;
		arrVertices[2] = z;
		arrVertices[3] = 1.0f;
		arrVertices[4] = 1.0f;
		arrVertices[5] = 1.0f;
		arrVertices[6] = colorR;
		arrVertices[7] = colorG;
		arrVertices[8] = colorB;
		arrVertices[9] = colorA;

		vertices.add(index, arrVertices);

	}

	public void addElements(char[] arrElement) {
		for (int i = 0; i < arrElement.length; i++) {
			elements.add(i, arrElement[i]);
		}
	}

	public void addElement(int index, char element) {
		elements.add(element);
	}

	public float[] getVertices() {
		float arrVertices[] = new float[vertices.size() * 10];
		for (int x = 0; x < (vertices.size() * 10); x++) {
			arrVertices[x] = 99.999f;
		}

		for (int i = 0; i < vertices.size(); i++) {
			float arrVertexPoint[] = (float[]) vertices.get(i);
			for (int j = 0; j < 10; j++) {
				arrVertices[(i * 10) + j] = arrVertexPoint[j];
			}
		}

		return arrVertices;
	}

	public char[] getElements() {

		char arrElements[] = new char[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			arrElements[i] = (Character) elements.get(i);
		}
		return arrElements;
	}

	public void updateCoordinate(int index, float x, float y, float z) {
		float coordinate[] = (float[]) vertices.get(index);
		coordinate[0] = x;
		coordinate[1] = y;
		coordinate[2] = z;
		vertices.remove(index);
		vertices.add(index, coordinate);
	}

	public float[] getCoordinate(int index) {
		float coordinate[] = (float[]) vertices.get(index);
		return new float[]{coordinate[0],coordinate[1],coordinate[2]};
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void deleteLastElements() {
		for (int i = 0; i < 3; i++) {
			elements.remove(elements.size() - 1);
		}
	}

	public void deleteElements(int elementId) {
		elements.remove(elementId);
	}

	public void removePoint(int index) {
		vertices.remove(index); 
	}
	
	public void removeTriangle(int triangleID){
		int firstelement = triangleID*3;
		elements.remove(firstelement);
		elements.remove(firstelement);
		elements.remove(firstelement);
	}

	public void setColor(float colorR, float colorG, float colorB, float colorA) {
		this.colorR = colorR;
		this.colorG = colorG;
		this.colorB = colorB;
		this.colorA = colorA;

		for (int i = 0; i < vertices.size(); i++) {
			float arrVertices[] = (float[]) vertices.get(i);
			arrVertices[6] = colorR;
			arrVertices[7] = colorG;
			arrVertices[8] = colorB;
			arrVertices[9] = colorA;
			vertices.remove(i);
			vertices.add(i, arrVertices);
		}
	}

	public float getColorR() {
		return colorR;
	}

	public float getColorG() {
		return colorG;
	}

	public float getColorB() {
		return colorB;
	}

	public float getColorA() {
		return colorA;
	}
}
