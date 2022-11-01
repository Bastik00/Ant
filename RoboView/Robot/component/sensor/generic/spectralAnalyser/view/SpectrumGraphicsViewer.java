package de.hska.lat.robot.component.generic.spectralAnalyser.view;



import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;


import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;
import de.hska.lat.robot.ui.icon.DroidIcon;
import de.hska.lat.robot.ui.icon.DroidIconListener;

public class SpectrumGraphicsViewer extends DroidIcon<DroidIconListener>  
{

	protected SpectralBandSet sensor;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4165796272769489567L;

	protected static final int MIN_HEIGHT 	= 100 ;
	protected static final int MIN_WIDTH 	= 200 ;
	
	
	public SpectrumGraphicsViewer(SpectralBandSet sensor, int width, int height)
	{
		super("name");
		
		this.minWidth = SpectrumGraphicsViewer.MIN_WIDTH;
		this.minHeight = SpectrumGraphicsViewer.MIN_HEIGHT;
		
		this.sensor = sensor;
		
		this.setWidth(width);
		this.setHight(height);
		
		

		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new SpectrumViewTask(), 100, 100);
	}

	
	


	
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);
		
		if (this.sensor==null)
				return;
	
	

		int cellWidth;
		int cells;
		int offset;

		
			
		cells = sensor.size();
		cellWidth = this.getWidth()/cells;


		offset = this.getWidth() -2 - (cellWidth*cells);
		offset/=2;
		
		
	
		
		this.drawBars( graphics,  offset,  cellWidth,  cells);
		
		// if drawGrid
		this.drawGrid( graphics,  offset,  cellWidth,  cells);
		
	//	this.drawScale( graphics,  offset,  cellWidth,  cells);
		//if drawGridScale
	}
	
	
	
	protected void drawBars(Graphics2D graphics, int offset, int cellWidth, int cells)
	{
	
		int cell;
		int celHeight;
		int verticalOffset;
		
		
		celHeight = this.getHeight()-2;

		
		GradientPaint greenYellow = new GradientPaint(0,celHeight*2 /5,Color.YELLOW,0,celHeight*4 /5, Color.GREEN);
		GradientPaint yellowReed = new GradientPaint(0,0,Color.RED,0,celHeight *2 /5, Color.YELLOW);
		
		
	
		
		
		for (cell=0; cell < cells; cell ++)
		{
			float value = this.sensor.get(cell).getSpectralBandValue().getValue();
			
			if (value > 1.0f )
			{
				value = 1.0f;
			}
			else if (value<0.f)
			{
				value=0.0f;
			}
			
			value= 1-value;
			
			graphics.setPaint(greenYellow);
			graphics.fillRect(offset, celHeight *2 /5 , cellWidth, celHeight);
			
			graphics.setPaint(yellowReed);
			graphics.fillRect(offset, 2 , cellWidth, celHeight *2 /5);

			
			verticalOffset = (int)( value * celHeight);			

			graphics.setColor(this.getBackground());
			graphics.fillRect(offset, 2 , cellWidth, verticalOffset);
			
			
			offset+= cellWidth;
	
		}
		
	}	
	
	

protected void drawGrid(Graphics2D graphics, int offset, int cellWidth, int cells)
{
	int cell;
	
	
	for (cell=0; cell < cells; cell ++)
	{

		graphics.setColor(Color.black);
		graphics.drawRect(offset, 0, cellWidth, this.getHeight()-1);
		
		offset+= cellWidth;

	}
}
	


protected void drawScale(Graphics2D graphics, int offset, int cellWidth, int cells)
{
	int cell;
	
	
	for (cell=0; cell < cells; cell ++)
	{
	//	graphics.draw
		graphics.setColor(Color.black);
		graphics.drawRect(offset, 0, cellWidth, this.getHeight()-1);
		
		offset+= cellWidth;

	}
}	


class SpectrumViewTask extends TimerTask
{


    @Override
	public void run() 
    {

    	SpectrumGraphicsViewer.this.invalidate();
    	SpectrumGraphicsViewer.this.repaint();

    //	Behavior.this.informations.capture();
    //	Behavior.this.behave();


    }
}



}
