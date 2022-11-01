package de.hska.lat.robot.component.generic.spectralAnalyser.view;




import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;



import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;

//public class SpectrumValueViewer extends DroidIcon<DroidIconListener>  
public class SpectrumValueViewer extends JPanel
{

	protected SpectralBandSet sensor;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4165796272769489567L;

	protected static final int MIN_HEIGHT 	= 100 ;
	protected static final int MIN_WIDTH 	= 200 ;
	
	
	public SpectrumValueViewer(SpectralBandSet sensor, int width, int height)
	{
	/*	super("name");
		
		this.minWidth = SpectrumValueViewer.MIN_WIDTH;
		this.minHeight = SpectrumValueViewer.MIN_HEIGHT;
		
	
		
		this.setWidth(width);
		this.setHight(height);
		
		*/

		this.sensor = sensor;

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
		

	}
	
	
	
	protected void drawBars(Graphics2D graphics, int offset, int cellWidth, int cells)
	{
	
		int cell;



		
		
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
			
			
			graphics.drawString("test", offset, 1);
			offset+= cellWidth;
	
		}
		
	}	
	
	



}
