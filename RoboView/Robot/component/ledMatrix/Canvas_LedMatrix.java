package de.hska.lat.robot.component.ledMatrix;

import de.hska.lat.robot.component.ledMatrix.LedMatrix;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;



public class Canvas_LedMatrix extends Canvas{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected  int CellSize = 20;	
	protected  int CellXCount = 7;
	protected static final int CellYCount = 5;
	public static final int CellMargin =1;

	
	protected LedMatrix matrixData;
	
public	Canvas_LedMatrix()
{
	setSize(160,120);
}
	


@Override
public void update (Graphics MyGraphics) 
{
	drawDisplay (MyGraphics);
	drawPixels(MyGraphics);  
}


public void paint (Graphics MyGraphics) 
{
	drawDisplay (MyGraphics);
	drawPixels(MyGraphics);  
}




public void drawDisplay (Graphics MyGraphics) 
{
	int xCounter;
	int yCounter;	
	
	MyGraphics.setColor(Color.DARK_GRAY);
	for (xCounter=0;xCounter<CellXCount;xCounter++)
		{
		for (yCounter=0;yCounter<CellYCount;yCounter++)
			{
			MyGraphics.drawRect((xCounter*CellSize), (yCounter*CellSize),CellSize,CellSize);
			}
		}
	MyGraphics.setColor(Color.black);
	for (xCounter=0;xCounter<CellXCount;xCounter++)
			{
			for (yCounter=0;yCounter<CellYCount;yCounter++)
			{
				MyGraphics.fillRect((xCounter*CellSize)+CellMargin, (yCounter*CellSize)+CellMargin,CellSize-(2*CellMargin),CellSize-(2*CellMargin));
			}
		}
	
	  
	}






//public void update(Graphics MyGraphics)
public void drawPixels (Graphics MyGraphics) 
{

	
	int xCounter;
	int yCounter;
	

	if (MyGraphics==null)
		return;
	
	MyGraphics.setColor(Color.DARK_GRAY);
	for (xCounter=0;xCounter<CellXCount;xCounter++)
		{
		for (yCounter=0;yCounter<CellYCount;yCounter++)
		{
		
	//	MyGraphics.setColor(matrixData.getPoint(xCounter, yCounter));
			
		MyGraphics.fillOval((xCounter*CellSize)+CellMargin, (yCounter*CellSize)+CellMargin,CellSize-(2*CellMargin),CellSize-(2*CellMargin));
		}
	}

	// 

}





public void setImage(LedMatrix matrixData) {
	this.matrixData=matrixData;
	
}

}



