package de.hska.lat.robot.component.generic.spectralSynthesizer.view;




import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;


import de.hska.lat.robot.component.generic.spectralAnalyser.view.SpectrumGraphicsViewer;
import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;


public class SpectrumEditor extends SpectrumGraphicsViewer implements MouseListener, MouseMotionListener
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4165796272769489567L;

	protected static final int MIN_HEIGHT 	= 100 ;
	protected static final int MIN_WIDTH 	= 200 ;
	
	
	public SpectrumEditor(SpectralBandSet sensor, int width, int height)
	{
		super( sensor,  width,  height);
		
		this.minWidth = SpectrumEditor.MIN_WIDTH;
		this.minHeight = SpectrumEditor.MIN_HEIGHT;
		
		
		
		this.setWidth(width);
		this.setHight(height);
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new SpectrumViewTask(), 100, 100);
	}

	
	


	
	
	



class SpectrumViewTask extends TimerTask
{


    @Override
	public void run() 
    {

    	SpectrumEditor.this.invalidate();
    	SpectrumEditor.this.repaint();

    //	Behavior.this.informations.capture();
    //	Behavior.this.behave();


    }
}


public int calculateIndex(int xPos)
{


	int cellWidth;
	int cells;
	int offset;
		
	cells = this.sensor.size();
	cellWidth = this.getWidth()/cells;


	offset = this.getWidth() -2 - (cellWidth*cells);
	offset/=2;
	
	xPos -= offset;
	xPos /=cellWidth;
	

	return(xPos);
}




public float calculateValue(int yPos)
{
	float value;
	float cellHeight;
	
	cellHeight = this.getHeight()-2;
	
	
	value = yPos / cellHeight;
	
	if (value >1.0f )
	{
		value =1.0f;
	}
	else if (value <0.0f)
	{
		value = 0.0f;
	}

	return(1.0f - value);
}


protected void procesInputEvent(MouseEvent event)
{
	int index;
	float value;
	
	if (this.sensor == null)
		return;
	
	// TODO Auto-generated method stub
	index = this.calculateIndex(event.getX());
	if (index < this.sensor.size())
	{
	
		value = calculateValue(event.getY());
		this.sensor.get(index).setSpectralBandValue(value);
		this.sensor.get(index).remote_setValue(value);
	}
}


@Override
public void mouseDragged(MouseEvent event)
{
	this.procesInputEvent(event);
}






@Override
public void mouseMoved(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}






@Override
public void mouseClicked(MouseEvent event)
{
//	this.procesInputEvent(event);
}






@Override
public void mouseEntered(MouseEvent arg0)
{
	// TODO Auto-generated method stub
	
}






@Override
public void mouseExited(MouseEvent arg0)
{
	
}




@Override
public void mousePressed(MouseEvent event)
{
	this.procesInputEvent(event);
}






@Override
public void mouseReleased(MouseEvent arg0)
{

}

}
