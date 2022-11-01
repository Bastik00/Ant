package de.hska.lat.robot.component.sensor.bmp085.view;


import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.bmp085.Bmp085;
import de.hska.lat.robot.component.sensor.bmp085.Bmp085Resolution;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;

import de.hska.lat.robot.value.view.MissingValueView;


public class Bmp085SetupView extends ComponentSettingsView<Bmp085>  implements ComponentSettingsChangeNotifier
{

	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7507161591602758947L;
	
	
	protected static final int width = 220;
	protected static final int height = 60;
	
	protected JComboBox<Bmp085Resolution> resolutionSelector;
	
	
	private static final String PERIOD_TEXT		= "period/resolution" ;

	
	protected final String[] CONVERSION_PERIODS = {"4.5ms/16Bit","7.5ms/17Bit","13.5/18Bit","25.5/19Bit"};
	
public Bmp085SetupView(Bmp085 sensor)
{
	super(sensor);
	
	this.buildView();
	
	this.component.addSetupListener(this);
	
	
}


@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel description;

	
	description = new JLabel(Bmp085SetupView.PERIOD_TEXT);
	description.setBounds(insets.left+5,insets.top+5,100,22);
	this.add(description);
	
	this.resolutionSelector = new JComboBox<Bmp085Resolution>();
	for(Bmp085Resolution resolution : Bmp085Resolution.values())
	{
		this.resolutionSelector.addItem(resolution);
	}
	this.resolutionSelector.setBounds(insets.left+110,insets.top+5,100,22);
	this.add(this.resolutionSelector);
	
	
	this.addSetButton(insets.left+5, insets.top+30, 50, 22);
	this.addGetButton(insets.left+60, insets.top+30, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+30, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+30, 50, 22);
	
}


protected void update()
{
	this.resolutionSelector.setSelectedItem(this.component.getResolution());
}

@Override
protected int getViewWidth()
{
	return(Bmp085SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Bmp085SetupView.height);
}


@Override
protected boolean setSettings()
{
	return(this.component.remote_setSettings((Bmp085Resolution)this.resolutionSelector.getSelectedItem()));
}





/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ComponentView createView(Bmp085 sensor)
{

	if (sensor!=null)
	{
		return(new Bmp085SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Bmp085.class.getName(), false));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
	
}




}
