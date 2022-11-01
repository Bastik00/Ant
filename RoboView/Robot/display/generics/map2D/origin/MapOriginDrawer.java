package de.hska.lat.robot.display.generics.map2D.origin;



import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.drawer.Map2DDrawer;


public class MapOriginDrawer extends Map2DDrawer
{
	
	
	protected Stroke originStroke = new BasicStroke(1);
	protected int size = 10;
	
	
public MapOriginDrawer(DisplayData displayData)
{
	this.displayData = displayData;
}
	
	
@Override	
public void draw(Graphics2D graphics)
{
	int xPos;
	int yPos;
	
	graphics.setStroke(this.originStroke);
	
	xPos=(int)this.displayData.translateXToScreen(0);
	yPos=(int)this.displayData.translateYToScreen(0);
	
	graphics.setColor(this.displayColor);
	
	graphics.drawLine(xPos-size, yPos, xPos+size, yPos);
	graphics.drawLine(xPos, yPos-size, xPos, yPos+size);
	

}



/**
 * set width of the origin drawers stroke 
 * @param width of the origi drawers stroke
 */

public void setThicknes(int width)
{
	this.originStroke = new BasicStroke(width);
}


/**
 * set size (height & width) of the origin 
 * @param size new size of origin 
 */
public void setSize(int size)
{
	this.size = size;
}


}