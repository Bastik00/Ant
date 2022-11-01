package de.hska.lat.robot.component.generic.spectralSynthesizer.view;



import java.awt.Insets;

import de.hska.lat.robot.component.generic.spectralSynthesizer.SpectralSynthesizer;
import de.hska.lat.robot.component.view.ComponentView;

public class SpectralSynthesizerControlView extends ComponentView
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5183474855597120203L;
	
	protected SpectralSynthesizer synthesizer;
	
	protected static final int WIDTH = 400;
	protected static final int HEIGHT = 200;
	
	protected SpectrumEditor spectrumEditor; 
	
	public SpectralSynthesizerControlView(SpectralSynthesizer synthesizer)
	{
		super(synthesizer.getComponentName(), false);
		// TODO Auto-generated constructor stub

		this.synthesizer = synthesizer;
		
		this.resizable = true;
		
		this.buildView();
		
	}

	
	protected void buildView()
	{	
		super.buildView();
		
		Insets insets = this.getInsets();
		
		this.spectrumEditor = new SpectrumEditor (this.synthesizer.getSpectralBandSet(),100,200);
		this.add(this.spectrumEditor);
		
		this.spectrumEditor.setBounds(insets.left, insets.top, this.getWidth()-insets.right-insets.left, this.getHeight()-insets.bottom-insets.top);

	}
	
	

@Override
protected int getViewWidth()
{
	if (this.resizable)
		return(this.actualWidth);
	
	return(SpectralSynthesizerControlView.WIDTH);
}

@Override
protected int getViewHeight()
{
	if (this.resizable)
		return(this.actualHeight);
	
	return(SpectralSynthesizerControlView.HEIGHT);
}


protected  void onResize()
{
	this.buildView();
}

	
	
}
