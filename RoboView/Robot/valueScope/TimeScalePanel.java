package de.hska.lat.robot.valueScope;


import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class TimeScalePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{


/**
	 * 
	 */
	private static final long serialVersionUID = -770958824136944551L;

	
	
	protected static final int DATA_AREA_Y_OFFSET = 20;
	

	protected boolean mouseDragged;

	protected ValueScopeScreenControl controll;
	
	protected ValueScopeScreenData screenData;

	protected int movementXOrigin;
	protected int movementYOrigin;

public TimeScalePanel(ValueScopeScreenData screenData, ValueScopeScreenControl controll)
{
//	this.setLayout(null);
	this.screenData = screenData;
	this.controll = controll;
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.addMouseWheelListener(this);
}



@Override
public void mouseClicked(MouseEvent mouseEvent)
{
}



public void setTimescale()
{
	
}


private static final int RASTER_SIZE = 50;

@Override
public void paint (Graphics graphics) 
{
	FontMetrics fontMetrics;
	int width =this.getWidth();
//	int height = this.getHeight();
	
	int descriptionWidth;
	int gridOffset;
	int gridX;
	
	int timescale = this.screenData.getTimeScale();
	int timeOffset;
	
	String description;
	
	graphics.drawLine(0, 20, width, 20);
	
	fontMetrics = graphics.getFontMetrics();
	
	
	
	timeOffset = screenData.getTimeOffset();
	
	
	gridOffset = ((timeOffset / timescale) % RASTER_SIZE);
	

	
	for (gridX=RASTER_SIZE-gridOffset;gridX<width;gridX+=RASTER_SIZE)
	{
		graphics.drawLine(gridX,(TimeScalePanel.DATA_AREA_Y_OFFSET-2), gridX, (TimeScalePanel.DATA_AREA_Y_OFFSET+2));
	}
	
	gridOffset = ((timeOffset / timescale) % (RASTER_SIZE*2));
	
	int time;
	

	time=timeOffset-gridOffset*timescale+(timescale *RASTER_SIZE*2);
	
	
	for (gridX=(RASTER_SIZE*2)-gridOffset;gridX<width;gridX+=(RASTER_SIZE*2))
	{
		graphics.drawLine(gridX,(TimeScalePanel.DATA_AREA_Y_OFFSET-5), gridX, (TimeScalePanel.DATA_AREA_Y_OFFSET+5));
		
		
		description = (time)+"ms";
		time+= timescale *RASTER_SIZE*2;

		descriptionWidth=fontMetrics.stringWidth(description);
		
		graphics.drawString(description, gridX - (descriptionWidth/2), (TimeScalePanel.DATA_AREA_Y_OFFSET-5));
	}
	


}








protected int timeToScreen(int time)
{
	return(time / this.screenData.getTimeScale());
}


protected int timeFromScreen(int xPos)
{
	return(xPos  * this.screenData.getTimeScale());
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

	this.mouseDragged =false;
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
	this.mouseDragged =true;

	int delta;
	delta = this.movementXOrigin - mouseEvent.getX();
	this.movementXOrigin = mouseEvent.getX();
	
	if (SwingUtilities.isLeftMouseButton(mouseEvent))
	{
		this.controll.moved(delta);
	}
	else if (SwingUtilities.isRightMouseButton(mouseEvent))
	{
		
		this.controll.timeScaleChanged(delta);
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

	
	
}
