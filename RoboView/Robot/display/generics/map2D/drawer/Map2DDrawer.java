package de.hska.lat.robot.display.generics.map2D.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.ui.colorSelector.ColorSelectorListener;


public class Map2DDrawer implements ColorSelectorListener
{

//	protected Paint paint = new Paint();
	protected Color displayColor = Color.white;
	
	protected DisplayData displayData;
	
public Map2DDrawer()
{
//	this.displayColor = RobotSettings.recoverInt(this.getClass().getName()+"color",0);
//	this.paint.setColor(displayColor);
}
	
public void setDisplayData(DisplayData displayData)
{
	this.displayData= displayData;
}
	
/**
 * set display color of this drawer	
 * @param color new color for drawing
 */
	
public void setDisplayColor(Color color)
{
	this.displayColor = color;
//	this.paint.setColor(displayColor);
	
		
	//RobotSettings.saveInt(this.getClass().getName()+"color",color);
	
}
	
/**
 * return actual drawing color
 * @return actual drawing color
 */

public Color getDisplayColor()
{
	return(this.displayColor);
}
	
/*
public void draw(Canvas canvas)
{
	
	
	
	
}

*/


public void draw(Graphics2D graphics)
{
	
}




@Override
public void colorChanged(Color color) 
{
	this.setDisplayColor(color);
}



}
