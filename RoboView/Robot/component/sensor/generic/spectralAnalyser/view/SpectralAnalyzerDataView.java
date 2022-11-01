package de.hska.lat.robot.component.generic.spectralAnalyser.view;




import java.awt.Insets;

import de.hska.lat.robot.component.generic.spectralAnalyzer.SpectralAnalyzer;
import de.hska.lat.robot.component.generic.spectralAnalyzer.SpectralAnalyzerChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.ui.controlElement.knob.Knob;



public class SpectralAnalyzerDataView extends SensorDataView<SpectralAnalyzer> implements SpectralAnalyzerChangeNotifier{

	
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 5403502891694705200L;

	protected static final int WIDTH = 400;
	protected static final int HEIGHT = 200;
	
public SpectralAnalyzerDataView(SpectralAnalyzer sensor)
{
		super(sensor);

		this.resizable = true;
		sensor.addSensorListener(this);
		buildView();
}


SpectrumGraphicsViewer spectrumViewer;

@Override
protected void buildView()
{
	super.buildView();
	
	Insets insets = this.getInsets();
	
	
	this.spectrumViewer = new SpectrumGraphicsViewer (this.sensor.getSpectralBandSet(),10,20);
	this.add(this.spectrumViewer);
	
	this.spectrumViewer.setBounds(insets.left, insets.top, this.getWidth()-insets.right-insets.left-40, this.getHeight()-insets.bottom-insets.top-20);

	//SpectrumValueViewer

	Knob knob;
	
	knob = new Knob();
	
	this.add(knob);
	knob.setBounds(this.getWidth()-insets.right-insets.left-38, 20, 40, 60);

}
	


@Override
protected int getViewWidth()
{
	if (this.resizable)
		return(this.actualWidth);
	
	return(SpectralAnalyzerDataView.WIDTH);
}

@Override
protected int getViewHeight()
{
	if (this.resizable)
		return(this.actualHeight);
	
	return(SpectralAnalyzerDataView.HEIGHT);
}


protected  void onResize()
{
	this.buildView();
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
		return(new SpectralAnalyzerDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(SpectralAnalyzer.class.getName()));
	}
}



@Override
public void spectrumChanged(SpectralAnalyzer analyser)
{
	this.spectrumViewer.invalidate();
	this.spectrumViewer.repaint();
}


	





}
