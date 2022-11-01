package de.hska.lat.robot.component.generic.spectralAnalyser.view;




import java.awt.Insets;

import de.hska.lat.robot.component.generic.spectralAnalyzer.SpectralAnalyzer;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;


public class SpectralAnalyzerSetupView extends ComponentSettingsView<SpectralAnalyzer> 
{

	
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 5403502891694705200L;

	
	protected static final int WIDTH = 250;
	protected static final int HEIGHT = 90;
	
	

	
public SpectralAnalyzerSetupView(SpectralAnalyzer sensor)
{
		super(sensor);

		this.resizable = false;
		//component.a.addSensorListener(this);
		buildView();
}


SpectrumGraphicsViewer spectrumViewer;

@Override
protected void buildView()
{
	super.buildView();
	
	Insets insets = this.getInsets();
	
	

	this.addSetButton(insets.left+5, insets.top+60, 50, 22);
	this.addGetButton(insets.left+60, insets.top+60, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+60, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+60, 50, 22);

}
	



@Override
protected int getViewWidth()
{
	return(SpectralAnalyzerSetupView.WIDTH);
}

@Override
protected int getViewHeight()
{
	return(SpectralAnalyzerSetupView.HEIGHT);
}





/**
 * create a new Data view for given spectral analyzer 
 * @param sensorspectral analyzer 
 * @return view for given spectral analyzer 
 */
public static ComponentView createView(SpectralAnalyzer sensor)
{
	 
	if (sensor!=null)
	{
		return(new SpectralAnalyzerSetupView(sensor));
	}
	else
	{
		return(new MissingComponentView(SpectralAnalyzer.class.getName()));
	}
}





@Override
protected boolean setSettings()
{
	// TODO Auto-generated method stub
	return false;
}


	





}
