package de.hska.lat.robot.component.thermalCamera.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import de.hska.lat.robot.component.thermalCamera.ThermalFrame;

public class ThermalPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903931939532571593L;

	
	protected ThermalFrame frame = new ThermalFrame(16,4);
	
	protected boolean displayValues	 = true;
	protected boolean displayGrid	 = true;
	protected boolean displayColors  = false;
	protected boolean autorange		 = true;

	
public ThermalPanel()
{
	this.setBorder(new EtchedBorder());
}
	



public void setFrame(ThermalFrame frame)
{
	this.frame = frame;
}
	


protected void drawValues(Graphics2D graphics2D)
{
	
	int frameWidth; 
	int frameHeight;
	
	int width;
	int height;
	
	
	frameWidth = frame.getWidth();
	frameHeight = frame.getHeight();
	
	height = this.getHeight();
	width = this.getWidth();
	
	int xCellSize = (width-10) / frameWidth;
	int yCellSize = (height-10) / frameHeight;
	

	
	//float factor = 0.5f/ (frame.getMax() -	frame.getMin());
	
	float factor = 0.66f/ (310.0f -	284.0f);//frame.getMin());
	

	Stroke originalStroke = graphics2D.getStroke();
	
	graphics2D.setStroke(new BasicStroke( 2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER ));

	
	

	int a =6;
	for (int xCounter=0;xCounter<frameWidth;xCounter++)
	{
		int b =6;
		for (int yCounter=0;yCounter<frameHeight;yCounter++)
		{
			float h = this.frame.get(xCounter,yCounter);
			h -= 280;
			h *= factor;
			
			graphics2D.setColor(Color.getHSBColor(0.66f-h, 1, 1));
			
			graphics2D.fillRoundRect(a, b,(int) xCellSize-2,  yCellSize-2, 3, 3);
			b += yCellSize;	
			
		}
		a += xCellSize;
	//	int a = ((width-10) * xCounter ) / frameWidth;
	//	graphics2D.drawLine(5+a,5, 5+a, height-5);
	}
/*	
	graphics2D.drawLine(5,5, height-5, 5);
	
	{
		int a = ((height-10) * yCounter ) / frameHeight;
		graphics2D.drawLine(5,5+a, width-5, 5+a);
	}
	*/
	
	graphics2D.setStroke(originalStroke);
	
	
	
}



protected void drawGrid(Graphics2D graphics2D)
{
	
	int frameWidth; 
	int frameHeight;
	
	int width;
	int height;
	
	
	frameWidth = frame.getWidth();
	frameHeight = frame.getHeight();
	
	height = this.getHeight();
	width = this.getWidth();
	
	int xCellSize = (width-10) / frameWidth;
	int yCellSize = (height-10) / frameHeight;
	
	graphics2D.setColor(Color.BLACK);

	Stroke originalStroke = graphics2D.getStroke();
	
	graphics2D.setStroke(new BasicStroke( 4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER ));


	int a=5;
	for (int xCounter=0;xCounter<=frameWidth;xCounter++)
	{
		
		graphics2D.drawLine(a,5,a, yCellSize*frameHeight+5);
		a+=xCellSize;
	}
	
	
	a=5;
	for (int yCounter=0;yCounter<=frameHeight;yCounter++)
	{
		graphics2D.drawLine(5,a, xCellSize*frameWidth+5, a);
		a+=yCellSize;
	
	}
	
	
	graphics2D.setStroke(originalStroke);
	
	
	
}




@Override
protected void paintComponent(Graphics graphics)
{
	Graphics2D graphics2D = (Graphics2D) graphics;
	
	super.paintComponent(graphics);
	
	if (this.autorange)
	{
	/*	float min = this.frame.getMin();
		float max = this.frame.getMax();
		
		
		System.out.println(this.frame.getMin());
		System.out.println(this.frame.getMax());
		*/
		
	}
	if (this.displayGrid)
	{
		this.drawGrid(graphics2D);
	}
	if (this.displayValues)
	{
		this.drawValues(graphics2D);
	}
}
	
}
