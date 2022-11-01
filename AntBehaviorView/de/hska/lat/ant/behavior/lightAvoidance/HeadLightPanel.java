package de.hska.lat.ant.behavior.lightAvoidance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import de.hska.lat.ant.behavior.head.light.LightFollower;
import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterChangeNotifier;
import de.hska.lat.math.Radiant;

public class HeadLightPanel extends JPanel implements ArbiterChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7215335726696988539L;

	
	
public HeadLightPanel(LightFollower behavior)
{
 behavior.addChangeListener(this);
 init();
}




public void paint(Graphics g)
{
	Graphics2D g2 = (Graphics2D) g;
	a(g2);
	
}

public float intensity;



	protected Polygon centerPolygon;
	protected Polygon leftPolygon;
	protected Polygon rightPolygon;
	
protected void init()
{
	int size;
	size =50;

	
	int x0 = (int) (size * Math.sin(Radiant.convertDegreeToRadiant(21))); 
	int y0 = (int) (size * Math.cos(Radiant.convertDegreeToRadiant(21)));
	
	int x1 = (int) (size * Math.sin(Radiant.convertDegreeToRadiant(56))); 
	int y1 = (int) (size * Math.cos(Radiant.convertDegreeToRadiant(56)));
	
	
	int xCenter = size*3/2;
	int yCenter = size;
	
	 this.centerPolygon = new Polygon();
		//this.centerPolygon.addPoint(size/4,0);
	 	this.centerPolygon.addPoint(xCenter-x0, yCenter-y0);
		this.centerPolygon.addPoint(xCenter ,size);
		this.centerPolygon.addPoint(xCenter+x0, yCenter-y0);
		this.centerPolygon.addPoint(xCenter-x0, yCenter-y0);
	 
	 

		
		
		 this.leftPolygon = new Polygon();
		 	this.leftPolygon.addPoint(xCenter-x0, yCenter-y0);
			this.leftPolygon.addPoint(xCenter ,size);
			this.leftPolygon.addPoint(xCenter+x1, yCenter-y1);
			this.leftPolygon.addPoint(xCenter-x0, yCenter-y0);
			
			
		 this.rightPolygon = new Polygon();
		 	this.rightPolygon.addPoint(xCenter-x0, yCenter-y0);
			this.rightPolygon.addPoint(xCenter ,size);
			this.rightPolygon.addPoint(xCenter+x1, yCenter-y1);
			this.rightPolygon.addPoint(xCenter-x0, yCenter-y0);	
		
	 
}


protected void a(Graphics2D graphics)
{
	Color color = new Color(intensity,intensity,intensity);
	graphics.setColor(color);
	
	int size;
	size =50;
	
	int xSize = size*2;
	int ySize = size*3/2;

	// Left Side
	color = new Color(this.leftSideIntensity,this.leftSideIntensity,this.leftSideIntensity);
	graphics.setColor(color);
	graphics.fillArc(0, 0, xSize, ySize, 146, 50);
	
	// Left 	
	color = new Color(this.leftIntensity,this.leftIntensity,this.leftIntensity);
	graphics.setColor(color);
	graphics.fillArc(0, 0, xSize, ySize, 111, 35);

	// center
	color = new Color(this.centerIntensity,this.centerIntensity,this.centerIntensity);
	graphics.setColor(color);
	graphics.fillArc(0, 0, xSize, ySize, 69, 42);
	
	
	// right
	color = new Color(this.rightIntensity,this.rightIntensity,this.rightIntensity);
	graphics.setColor(color);
	graphics.fillArc(0, 0, xSize, ySize, 69, -50);
	

	// Right Side
	color = new Color(this.rightSideIntensity,this.rightSideIntensity,this.rightSideIntensity);
	graphics.setColor(color);
	graphics.fillArc(0, 0, xSize, ySize, 34, -50);

}



protected float leftSideIntensity;
protected float leftIntensity;
protected float centerIntensity;
protected float rightIntensity;
protected float rightSideIntensity;



protected float scaleIntensity(float intensity, float offset, float scale)
{
	float value;
	
	value = (intensity - offset)  / scale;
	
	if (value >1)
		value =1;

	if (value <0)
		value =0;
	
	return(value);
}



@Override
public void arbiterChanged(Arbiter arbiter)
{
	this.repaint();
	
	LightFollower lightAvoidance = (LightFollower)arbiter; 
	
	
	float max = lightAvoidance.getMaxIntensity();
	float min = lightAvoidance.getMinIntensity();
	
	float scale;
	scale= (max - min);
	
	this.leftSideIntensity = scaleIntensity(lightAvoidance.getIntensity(0) , min  , scale) ;
	this.leftIntensity = scaleIntensity(lightAvoidance.getIntensity(1) , min  , scale) ;
	this.centerIntensity = scaleIntensity(lightAvoidance.getIntensity(2) , min  , scale) ;
	this.rightIntensity = scaleIntensity(lightAvoidance.getIntensity(3) , min  , scale) ;
	this.rightSideIntensity = scaleIntensity(lightAvoidance.getIntensity(4) , min  , scale) ;
	
	
	
}



	
}
