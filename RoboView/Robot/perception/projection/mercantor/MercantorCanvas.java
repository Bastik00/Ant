package de.hska.lat.robot.perception.projection.mercantor;

import java.awt.Canvas;
import java.awt.Graphics;

public class MercantorCanvas extends Canvas
{

	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



@Override
public void paint (Graphics graphics) 
{
	System.out.println("drawGrid(graphics)");
	// if drawGrid 
	drawGrid(graphics);
	
//		drawDisplay (MyGraphics);
//		drawPixels(MyGraphics);  
}	
			



public void config()
{
	int xOffset = 20;
	int yOffset = 20;
	
	int xSize;
	int ySize;
	
	xSize=this.getWidth() - (xOffset*2);
	ySize=this.getHeight() - (yOffset*2);
	
	
	if ((xSize*2)>ySize)
	{
		xSize = ySize *2;
	}
	else
	{
		ySize = xSize /2;
	}
	
	
	xOffset= (this.getWidth() - xSize) /2;
	yOffset= (this.getHeight() - ySize) /2;
	
	
	
}



public void drawGrid(Graphics graphics)
{
	
}




}
