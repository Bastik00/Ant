package de.hska.lat.robot.morphology.appearance.Editor3D.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEvent;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class Import implements ContentHandler {
	private FiguresEventHandler eventHandler;
	private Figures figures;
	private Figure figure;
	
	private String currentValue;
	private int pointIndex;
	private float x;
	private float y;
	private float z;
	private float r;
	private float g;
	private float b;
	private float a;
	
	private int elementIndex;
	
	

	public Import(FiguresEventHandler eventHandler, String path) {
		this.eventHandler = eventHandler;
		this.figures = eventHandler.getFigures();

		figures.clearFigures();
		
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
		if (localName.equals("Figure")) {
		    figure = new Figure();
		}
		if (localName.equals("Point")) {
			pointIndex = Integer.parseInt(atts.getValue("id"));
		}

		if (localName.equals("PointId")) {
			elementIndex = Integer.parseInt(atts.getValue("id"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		

		if (localName.equals("Name")) {
			figure.setName(currentValue);
		}
		if (localName.equals("Type")) {
			figure.setType(currentValue);
		}
		
		
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
			
			figure.addElement(elementIndex,(char)Integer.parseInt(currentValue, 10));
		}
		

		if (localName.equals("Point")) {
			figure.addCoordinate(pointIndex, x, y, z);
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
			figure.setColor(r, g, b, a);
		}

		if (localName.equals("Figure")) {
			figure.setVisibile(true);
			figure.setStatus("done");
			figures.addFigure(figure);
		}
		

		if (localName.equals("Figures")) {
			eventHandler.notifyUpdate(new FiguresEvent(this));
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
}
	
