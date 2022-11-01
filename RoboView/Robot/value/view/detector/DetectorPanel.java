package de.hska.lat.robot.value.view.detector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class DetectorPanel extends JPanel 
{


	private static final long serialVersionUID = 1L;

	private boolean status;
	
	
@Override
public void paintComponents(Graphics graphics)
{
	super.paintComponents(graphics);
	drawDetector(graphics);
}
	

@Override
protected void paintBorder(Graphics graphics)
{
	//Do not paint a border
}

@Override
protected void paintComponent(Graphics graphics) 
{
	super.paintComponent(graphics);
	this.drawDetector(graphics);
}

	
@Override
public void paint (Graphics graphics) 
{
	super.paint(graphics);
	this.drawDetector (graphics);
}


private void drawDetector(Graphics graphics)
{
	Graphics2D g = (Graphics2D)graphics;
	g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON );
	
	int width;
	int height;
	
	width = this.getWidth();
	height = this.getHeight();
	
	graphics.setColor(Color.DARK_GRAY);
	g.drawOval(0, 0, width-1, height-1);
	
	
	if (status)
	{
		graphics.setColor(Color.GREEN);
		graphics.fillOval(1, 1, width-2, height-2);
	}
	
	
}

public void setStatus(boolean status)
{
	this.status = status;
}




	
}
