package de.hska.lat.robot.valueScope;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;




public class ValueScopeDataPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264157108604046816L;

	protected ValueScope valueScope;
	
public ValueScopeDataPanel()
{
	
}
	





@Override
protected void paintComponent(Graphics graphics)
{
	
	super.paintComponent(graphics);

//	if (this.valueScope.size()>0)
	{
	this.drawGrid(graphics);	
	}
	
	// draw default 
//	this.drawData(graphics);

}



public void drawGrid (Graphics graphics) 
{
	
	int width =this.getWidth();
	int height = this.getHeight();
	
	int gridSize = 50;
	
//	ValueFlow valueFlow = this.valueScope.get(0);
	
	int a = height / gridSize;
	a = (height - (a * gridSize))/2;
	
	int yPos;
	
	for (yPos=a;yPos<height; yPos+=gridSize)
	{

		graphics.setColor(Color.GRAY);
		
		graphics.drawLine(0, yPos,  width, yPos);
		
	//	graphics.setColor(Color.LIGHT_GRAY);
	//	graphics.drawLine((ValueGraph.DATA_AREA_X_OFFSET), zeroPos,  width, zeroPos);
	}
	
//	valueFlow.g
	
	// valueRange
	
	/*
	int gridOffset;
	int gridX;
	
	int timescale = this.screenData.getTimeScale();
	int timeOffset;

	int yPos;
	int zeroPos;
	 

	
	

	graphics.setColor(Color.LIGHT_GRAY);
	

	graphics.drawString(FloatValue.toFormatedFractionString(this.displayMaxRange, 2), 0, 10);
	graphics.drawString(FloatValue.toFormatedFractionString(this.displayMinRange, 2), 0, height);

	
	
	zeroPos = (int) ((this.displayMaxRange * height)/this.displayRange); 

	
	if ((0<this.displayMaxRange) && ( 0> this.displayMinRange))
	{

		graphics.setColor(Color.GRAY);
		
		graphics.drawLine((ValueGraph.DATA_AREA_X_OFFSET-5), zeroPos,  ValueGraph.DATA_AREA_X_OFFSET, zeroPos);
		
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.drawLine((ValueGraph.DATA_AREA_X_OFFSET), zeroPos,  width, zeroPos);
	}
	
	
	
	graphics.drawLine(ValueGraph.DATA_AREA_X_OFFSET, 0,  ValueGraph.DATA_AREA_X_OFFSET,height);
	
	for (yPos = zeroPos; yPos>0; yPos-=24)
	{
		graphics.drawLine((ValueGraph.DATA_AREA_X_OFFSET), yPos,  width,yPos);

		this.drawValueLeftFrom(graphics, this.valueFromScreen(yPos),  ValueGraph.DATA_AREA_X_OFFSET -2 , yPos);
	}

	
	for (yPos = zeroPos; yPos<this.getHeight(); yPos+=24)
	{
		graphics.drawLine((ValueGraph.DATA_AREA_X_OFFSET), yPos,  width, yPos);
	
		this.drawValueLeftFrom(graphics, this.valueFromScreen(yPos),  ValueGraph.DATA_AREA_X_OFFSET -2 , yPos);
	}
	
	
	
	timeOffset = screenData.getTimeOffset();
	gridOffset = ((timeOffset / timescale) % X_RASTER_SIZE);
	
	
	
	for (gridX=X_RASTER_SIZE-gridOffset+DATA_AREA_X_OFFSET;gridX<width;gridX+=X_RASTER_SIZE)
	{
		graphics.drawLine(gridX,0, gridX, height);

	}
	*/

}


}
