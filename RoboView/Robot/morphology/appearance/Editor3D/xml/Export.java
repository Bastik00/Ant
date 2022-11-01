package de.hska.lat.robot.morphology.appearance.Editor3D.xml;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figure;
import de.hska.lat.robot.morphology.appearance.Editor3D.entities.Figures;
import de.hska.lat.robot.morphology.appearance.Editor3D.event.FiguresEventHandler;

public class Export {
    private StreamResult out;
    private TransformerHandler th;
    private AttributesImpl atts;
    
    //private FiguresEventHandler eventHandler;
    private Figures figures;
    
    
    
    public Export(FiguresEventHandler eventHandler, String path){
    	//this.eventHandler = eventHandler;
    	this.figures = eventHandler.getFigures();
    	
        try{
            out = new StreamResult(path);
            initXML();
            for (int i = 0; i < figures.getSize(); i++)
                process(figures.getFigure(i));
            closeXML();
        }
        catch (Exception e) {
        	e.printStackTrace(); 
        }
    }
    
    public void initXML() throws TransformerConfigurationException, SAXException {
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        th = tf.newTransformerHandler();
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        // pretty XML output:
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        th.setResult(out);
        th.startDocument();
        
        atts = new AttributesImpl();
        th.startElement("","","Figures", atts);
    }
    
    public void process(Figure figure) throws SAXException {

        atts.clear();
        
        th.startElement("", "", "Figure", atts);

        atts.clear();
        
    	th.startElement("", "", "Name", atts);
        char[] name = figure.getName().toCharArray();

        th.characters(name, 0, name.length);
        th.endElement("", "", "Name");

    	th.startElement("", "", "Type", atts);
        char[] type = figure.getType().toCharArray();

        th.characters(type, 0, type.length);
        th.endElement("", "", "Type");

        
        atts.clear();
    	th.startElement("", "", "Points", atts);
    	
        atts.clear();
        float[] vertexes = figure.getVertices();

        for (int i = 0; i < (vertexes.length / 10); i++) {
	        atts.addAttribute("", "", "id", "", "" + i);
	        th.startElement("", "", "Point", atts);
	
	        atts.clear();
	        
	        th.startElement("", "", "x", atts);
	        char[] x = Float.toString(vertexes[0 + (10 * i)]).toCharArray();
	        th.characters(x, 0, x.length);
	        th.endElement("", "", "x");
	        
	        th.startElement("", "", "y", atts);
	        char[] y = Float.toString(vertexes[1 + (10 * i)]).toCharArray();
	        th.characters(y, 0, y.length);
	        th.endElement("", "", "y");
	        
	        th.startElement("", "", "z", atts);
	        char[] z = Float.toString(vertexes[2 + (10 * i)]).toCharArray();
	        th.characters(z, 0, z.length);
	        th.endElement("", "", "z");

	        th.endElement("", "", "Point");
        }
        

        th.endElement("", "", "Points");

        th.startElement("", "", "Triangles", atts);
        char[] elements = figure.getElements();

        for (int i = 0; i < (elements.length); i = i + 3) {
        	
        	char[] charValue1 = new char[1];
        	charValue1[0] = Character.forDigit(elements[i], 10);

        	char[] charValue2 = new char[1];
        	charValue2[0] = Character.forDigit(elements[i + 1], 10);
        	
        	char[] charValue3 = new char[1];
        	charValue3[0] = Character.forDigit(elements[i + 2], 10);
        	
	        atts.clear();
        	th.startElement("", "", "Triangle", atts);
	        
        	
        	atts.addAttribute("", "", "id", "", "" + i);
	        th.startElement("", "", "PointId", atts);
	        
	        th.characters(charValue1, 0, 1);
	        th.endElement("", "", "PointId");
	        

	        atts.clear();

        	atts.addAttribute("", "", "id", "", "" + (i + 1));
	        th.startElement("", "", "PointId", atts);
	        
	        th.characters(charValue2, 0, 1);
	        th.endElement("", "", "PointId");
	        

	        atts.clear();

        	atts.addAttribute("", "", "id", "", "" + (i + 2));
	        th.startElement("", "", "PointId", atts);
	        
	        th.characters(charValue3, 0, 1);
	        th.endElement("", "", "PointId");

	    	th.endElement("", "", "Triangle");
	        
        }
        atts.clear();
        

        th.endElement("", "", "Triangles");
        
    	th.startElement("", "", "Color", atts);
    	
    	th.startElement("", "", "R", atts);
        char[] r = Float.toString(figure.getColorR()).toCharArray();
        th.characters(r, 0, r.length);
        th.endElement("", "", "R");
        

    	th.startElement("", "", "G", atts);
        char[] g = Float.toString(figure.getColorG()).toCharArray();
        th.characters(g, 0, g.length);
        th.endElement("", "", "G");
        

    	th.startElement("", "", "B", atts);
        char[] b = Float.toString(figure.getColorB()).toCharArray();
        th.characters(b, 0, b.length);
        th.endElement("", "", "B");
        

    	th.startElement("", "", "A", atts);
        char[] a = Float.toString(figure.getColorA()).toCharArray();
        th.characters(a, 0, a.length);
        th.endElement("", "", "A");
        

    	th.endElement("", "", "Color");
        
    	


        th.endElement("", "", "Figure");
        
    }
    
    public void closeXML() throws SAXException {
        th.endElement("","","Figures");
        th.endDocument();
    }
    
    
}

