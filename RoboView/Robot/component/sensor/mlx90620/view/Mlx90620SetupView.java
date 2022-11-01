package de.hska.lat.robot.component.sensor.mlx90620.view;

import java.awt.Insets;

import java.awt.event.ActionListener;


import javax.swing.JComboBox;
import javax.swing.JLabel;





import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.mlx90620.Mlx90620;
import de.hska.lat.robot.component.sensor.mlx90620.Mlx90620AmbientRefreshRate;
import de.hska.lat.robot.component.sensor.mlx90620.Mlx90620IrFrameRate;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class Mlx90620SetupView extends ComponentSettingsView<Mlx90620> implements ActionListener, ComponentSettingsChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6139335684222936652L;


	
	protected static final int width = 250;
	protected static final int height = 100;
	
	
	protected JComboBox<Mlx90620AmbientRefreshRate> ambientRefreshRate;
	protected JComboBox<Mlx90620IrFrameRate> thermalFrameRate;
	
	
	protected static final String AMBIENT_REFRESH_TEXT = "temperature ";
	protected static final String THERMAL_FRAME_RATE_TEXT = "IR framerate";
	
private Mlx90620SetupView(Mlx90620 mlx90620)
{
	super(mlx90620);

	this.component.addSetupListener(this);

	buildView();

}


public void buildView() 
{
	JLabel tmpLabel;
			
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	tmpLabel = new JLabel(Mlx90620SetupView.AMBIENT_REFRESH_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+10, 80, 20);
	this.add(tmpLabel);
	
	this.ambientRefreshRate = new JComboBox<Mlx90620AmbientRefreshRate>();
	
	for(Mlx90620AmbientRefreshRate rate : Mlx90620AmbientRefreshRate.values())
	{
		this.ambientRefreshRate.addItem(rate);
	}
	
	this.ambientRefreshRate.setBounds(insets.left+90,insets.top+10, 100, 20);
	this.ambientRefreshRate.addActionListener(this);
	this.ambientRefreshRate.setSelectedItem(component.getTA_refreshRate());
	this.add(this.ambientRefreshRate);
	
	
	
	
	tmpLabel = new JLabel(Mlx90620SetupView.THERMAL_FRAME_RATE_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+40, 80, 20);
	this.add(tmpLabel);
	
	
	this.thermalFrameRate = new JComboBox<Mlx90620IrFrameRate>();
	
	for(Mlx90620IrFrameRate rate : Mlx90620IrFrameRate.values())
	{
		this.thermalFrameRate.addItem(rate);
	}
	this.thermalFrameRate.setBounds(insets.left+90,insets.top+40, 100, 20);
	this.thermalFrameRate.addActionListener(this);
	this.thermalFrameRate.setSelectedItem(component.getIR_refreshRate());
	this.add(this.thermalFrameRate);
	

		
	this.addSetButton(insets.left+5, insets.top+70, 50, 22);
	this.addGetButton(insets.left+58, insets.top+70, 50, 22);
	
	this.addSaveButton(insets.left+110, insets.top+70, 50, 22);
	this.addLoadButton(insets.left+162, insets.top+70, 50, 22);	
		
}



@Override
protected int getViewWidth()
{
	return(Mlx90620SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Mlx90620SetupView.height);
}



@Override
protected boolean setSettings()
{
	
	return(this.component.remote_setSettings(
			(Mlx90620AmbientRefreshRate)this.ambientRefreshRate.getSelectedItem(),
			(Mlx90620IrFrameRate)this.thermalFrameRate.getSelectedItem()));
}



/**
 * create a new data view for 2D heading (an instance of this class)
 * if the given sensor is a null reference, create an error view. 
 * @param sensor an 2D heading sensor
 * @return an instance of this class, or MissingComponentViewe
 */

public static ComponentView createView(Mlx90620 sensor)
{
	 
	if (sensor!=null)
	{
		return(new Mlx90620SetupView(sensor));
	}
	else
	{
		return(new MissingComponentView(Mlx90620.class.getName()));
	}
}





@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}

	

}
