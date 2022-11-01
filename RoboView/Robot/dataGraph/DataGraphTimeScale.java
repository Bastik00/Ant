package de.hska.lat.robot.dataGraph;


import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class DataGraphTimeScale extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, DataGraphTimeScaleSettingsListener
{

	private static final long serialVersionUID = -770958824136944551L;

	protected boolean mouseDragged;

	protected DataGraphTimeScaleSettings timeScaleSettings;

	protected int movementXOrigin;
	protected int movementYOrigin;

public DataGraphTimeScale(DataGraphTimeScaleSettings timeScaleSettings)
{
//	this.setLayout(null);
	this.timeScaleSettings = timeScaleSettings;
	//this.control = control;
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.addMouseWheelListener(this);
	this.timeScaleSettings.addListener(this);
}



@Override
public void mouseClicked(MouseEvent mouseEvent)
{
}


private static final int RASTER_SIZE = 50;


@Override
protected void paintComponent(Graphics graphics)
{

	super.paintComponent(graphics);
	
	FontMetrics fontMetrics;
	int width =this.getWidth();
	int height = this.getHeight();
	int descriptionWidth;
	int gridOffset;
	int gridX;
	
	int timescale = this.timeScaleSettings.getTimeScale();
	int timeOffset;
	
	String description;
	
	graphics.drawLine(0, height-1, width, height-1);
	
	fontMetrics = graphics.getFontMetrics();
	
	
	
	timeOffset = this.timeScaleSettings.getTimeOffset();
	
	
	gridOffset = ((timeOffset / timescale) % RASTER_SIZE);
	

	
	for (gridX=RASTER_SIZE-gridOffset;gridX<width;gridX+=RASTER_SIZE)
	{
		graphics.drawLine(gridX,(height-3), gridX, (height));
	}
	
	gridOffset = ((timeOffset / timescale) % (RASTER_SIZE*2));
	
	int time;
	

	time = timeOffset - gridOffset * timescale + (timescale * RASTER_SIZE * 2);
	
	
	for (gridX = (RASTER_SIZE * 2 ) - gridOffset; gridX < width; gridX += (RASTER_SIZE * 2))
	{
		graphics.drawLine(gridX,(height - 5), gridX, height);
		
		
		description = (time)+"ms";
		time+= timescale *RASTER_SIZE*2;

		descriptionWidth=fontMetrics.stringWidth(description);
		
		graphics.drawString(description, gridX - (descriptionWidth/2), (height-5));
	}
	


}









public void update()
{
	this.invalidate();
	this.repaint();
}


@Override
public void mouseEntered(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0)
{
	// TODO Auto-generated method stub

	
}



@Override
public void mousePressed(MouseEvent mouseEvent)
{

	this.mouseDragged = false;
	this.movementXOrigin = mouseEvent.getX();
	this.movementYOrigin= mouseEvent.getY();
}

@Override
public void mouseReleased(MouseEvent mouseEvent)
{
}



@Override
public void mouseDragged(MouseEvent mouseEvent)
{
	this.mouseDragged = true;

	int delta;
	delta = this.movementXOrigin - mouseEvent.getX();
	this.movementXOrigin = mouseEvent.getX();
	
	if (SwingUtilities.isLeftMouseButton(mouseEvent))
	{
		this.timeScaleSettings.moveXOffset(delta);
		
	}
	else if (SwingUtilities.isRightMouseButton(mouseEvent))
	{
		this.timeScaleSettings.scaleTimeScale(delta);
	}
	else
	{
		return;
	}
	

}


@Override
public void mouseMoved(MouseEvent mouseEvent)
{
}



@Override
public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
{
}



@Override
public void timeOffsetChanged(int timeOffset)
{
	this.update();
}




@Override
public void timeScaleChanged(float scale)
{

	System.out.println("time scale changed");
	this.update();
}



@Override
public void xRasterSizeChanged(int rasterSize)
{

	System.out.println("raster size changed");
	this.update();
}


	
	
}
