package de.hska.lat.robot.display.generics.map2D.pose;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.pose.Pose3D;

import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;
import de.hska.lat.util.math.coordinate.Coordinate2D;


public class PoseDrawer extends Map2DDrawer
{

	protected DisplayData displayData; 
	protected Pose3D pose;

	protected Coordinate2D c1 = new Coordinate2D(0, -10);
	protected Coordinate2D c2 = new Coordinate2D(7, 10);
	protected Coordinate2D c3 = new Coordinate2D(-7, 10);


	protected Polygon robotHousing = new Polygon();

	protected Stroke poseStroke = new BasicStroke(1);
	protected int size = 10;
	
public PoseDrawer(DisplayData displayData, Pose3D pose) 
{
	this.displayData = displayData;
	this.pose=pose;
	
	this.displayColor = Color.RED;
	
}


public void draw(Graphics2D graphics)
{
	int xPos;
	int zPos;

	
	graphics.setColor(this.displayColor);
	graphics.setStroke(this.poseStroke);
	
	AngularVector3D heading = pose.getNormalizedHeading();
	
	FloatVector3D location = pose.getLocation();
	
	xPos=(int)displayData.translateXToScreen(location.x);
	zPos=(int)displayData.translateYToScreen(location.z);

	 this.c1.set(0, -this.size);
	 this.c2.set(7, this.size);
	 this.c3.set(-7, this.size);
	
	this.c1.translate(heading.y);
	this.c2.translate(heading.y);
	this.c3.translate(heading.y);

	this.robotHousing.reset();
	this.robotHousing.addPoint(xPos + (int)this.c1.getX(), zPos + (int)this.c1.getY());
	this.robotHousing.addPoint(xPos + (int)this.c1.getX(), zPos + (int)this.c1.getY());
	this.robotHousing.addPoint(xPos + (int)this.c2.getX(), zPos + (int)this.c2.getY());
	this.robotHousing.addPoint(xPos + (int)this.c3.getX(), zPos + (int)this.c3.getY());
	
	


	graphics.drawPolygon(this.robotHousing);
	
}	


/**
 * set width of the pose drawers stroke 
 * @param width of the origi drawers stroke
 */

public void setThicknes(int width)
{
	this.poseStroke = new BasicStroke(width);
}


/**
 * set size (height & width) of the pose 
 * @param size new size of origin 
 */
public void setSize(int size)
{
	this.size = size;

	

}

}
