package de.hska.lat.robot.component.generic.spectralAnalyser.view;




import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;
import de.hska.lat.robot.ui.icon.DroidIcon;
import de.hska.lat.robot.ui.icon.DroidIconListener;

public class SpectrumViewer extends DroidIcon<DroidIconListener>  
{

	protected SpectralBandSet sensor;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4165796272769489567L;

	protected static final int MIN_HEIGHT 	= 100 ;
	protected static final int MIN_WIDTH 	= 200 ;
	
	
	protected SpectrumValueViewer valueViewer;
	protected SpectrumGraphicsViewer graphicsViewer;
	
	public SpectrumViewer(SpectralBandSet sensor, int width, int height)
	{
		super("");
		
		this.minWidth = SpectrumViewer.MIN_WIDTH;
		this.minHeight = SpectrumViewer.MIN_HEIGHT;
		
		this.sensor = sensor;
		
		this.setWidth(width);
		this.setHight(height);
		
		this.valueViewer = new SpectrumValueViewer(sensor, 0, 0);
		this.graphicsViewer = new SpectrumGraphicsViewer(sensor, 0, 0);
		

	}

	
	


	



}
