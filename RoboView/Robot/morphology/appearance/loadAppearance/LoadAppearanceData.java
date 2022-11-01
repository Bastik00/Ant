package de.hska.lat.robot.morphology.appearance.loadAppearance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class LoadAppearanceData implements ContentHandler {
	private String currentValue;
	
	ArrayList<float[]> vertices;
	ArrayList<float[]> colors;
	ArrayList<Character> elements;
	private float x;
	private float y;
	private float z;
	private float r;
	private float g;
	private float b;
	private float a;
	
	private int countOfVertices;
	private int numberOfPointsInFigure;
	
	public LoadAppearanceData(String path) {
		vertices = new ArrayList<float[]>();
		elements = new ArrayList<Character>();
		colors = new ArrayList<float[]>();
		
		countOfVertices = 0;
		numberOfPointsInFigure = 0;
		try {
			
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		    
			// Pfad zur XML Datei
			FileReader reader = new FileReader(path);
			
			InputSource inputSource = new InputSource(reader);
	
			// PersonenContentHandler wird übergeben
			xmlReader.setContentHandler(this);
	
			// Parsen wird gestartet
			xmlReader.parse(inputSource);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
	    currentValue = new String(ch, start, length);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("Points")) {
			numberOfPointsInFigure = 0;
		}
		if (localName.equals("Point")) {
			numberOfPointsInFigure++;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if (localName.equals("x")) {
			x = Float.parseFloat(currentValue);
		}
		if (localName.equals("y")) {
			y = Float.parseFloat(currentValue);
		}
		if (localName.equals("z")) {
			z = Float.parseFloat(currentValue);
		}
		
		if (localName.equals("PointId")) {
			elements.add((char) (Integer.parseInt(currentValue, 10) + countOfVertices));
		}
		

		if (localName.equals("Point")) {
			float[] vertex = new float[3];
			vertex[0] = x;
			vertex[1] = y;
			vertex[2] = z;
			
			vertices.add(vertex);
		}
		
		
		if (localName.equals("R")) {
			r = Float.parseFloat(currentValue);
		}
		if (localName.equals("G")) {
			g = Float.parseFloat(currentValue);
		}
		if (localName.equals("B")) {
			b = Float.parseFloat(currentValue);
		}

		if (localName.equals("A")) {
			a = Float.parseFloat(currentValue);
		}
		
		if (localName.equals("Color")) {
			for (int i = 0; i < numberOfPointsInFigure; i++) {
				float[] color = new float[4];
				color[0] = r;
				color[1] = g;
				color[2] = b;
				color[3] = a;
				colors.add(color);
			}
		}

		if (localName.equals("Triangles")) {
			countOfVertices += numberOfPointsInFigure;
		}
		
	}



	public void endDocument() throws SAXException {}
	public void endPrefixMapping(String prefix) throws SAXException {}
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {}
	public void processingInstruction(String target, String data)
			throws SAXException {}
	public void setDocumentLocator(Locator locator) {  }
	public void skippedEntity(String name) throws SAXException {}
	public void startDocument() throws SAXException {}
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {}
	
	
	public float[] getVertices() {
		System.out.println("JONAS: " + vertices.size());
		System.out.println("JONAS: " + colors.size());
		
		float[] arrVertices = new float[(vertices.size() * 10)];
		int i = 0;
		for (float[] vertex : vertices) {
			
			arrVertices[(i * 10) + 0] = vertex[0];
			arrVertices[(i * 10) + 1] = vertex[1];
			arrVertices[(i * 10) + 2] = vertex[2];
			arrVertices[(i * 10) + 3] = 1.0f;
			arrVertices[(i * 10) + 4] = 1.0f;
			arrVertices[(i * 10) + 5] = 1.0f;
			arrVertices[(i * 10) + 6] = colors.get(i)[0];
			arrVertices[(i * 10) + 7] = colors.get(i)[1];
			arrVertices[(i * 10) + 8] = colors.get(i)[2];
			arrVertices[(i * 10) + 9] = colors.get(i)[3];
			
			i++;
		}
		
		
		return arrVertices;
	}

	public char[] getElements() {
		char[] arrElements = new char[(elements.size())];
		int i = 0;
		for (char element: elements) {
			arrElements[i] = element;
			
			i++;
			
		}
		return arrElements;
	}
	
}
