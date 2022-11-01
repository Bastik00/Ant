package de.hska.lat.robot.component.actor.motor.view;

import java.awt.Insets;

import java.awt.event.ActionListener;




import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;



import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotor;


import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class MotorSetupView extends ComponentSettingsView<PwmMotor> implements ActionListener, ComponentSettingsChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6139335684222936652L;


	
	protected static final int width = 250;
	protected static final int height = 100;
	
	

	
	
	protected static final String AMBIENT_REFRESH_TEXT = "temperature ";
	protected static final String THERMAL_FRAME_RATE_TEXT = "IR framerate";
	
private MotorSetupView(PwmMotor motor)
{
	super(motor);

	this.component.addSetupListener(this);

	buildView();

}


public void buildView() 
{
	//JLabel tmpLabel;
			
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
//	JSpinner tickPerRevolution;
	/*
	
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
	
*/
		
	this.addSetButton(insets.left+5, insets.top+70, 50, 22);
	this.addGetButton(insets.left+58, insets.top+70, 50, 22);
	
	this.addSaveButton(insets.left+110, insets.top+70, 50, 22);
	this.addLoadButton(insets.left+162, insets.top+70, 50, 22);	
		
}



@Override
protected int getViewWidth()
{
	return(MotorSetupView.width);
}

@Override
protected int getViewHeight()
{
	return(MotorSetupView.height);
}



@Override
protected boolean setSettings()
{
	int tickPerRevolution =4;
	
	return(this.component.remote_setSettings(
			tickPerRevolution));
}



/**
 * create a new data view for 2D heading (an instance of this class)
 * if the given sensor is a null reference, create an error view. 
 * @param sensor an 2D heading sensor
 * @return an instance of this class, or MissingComponentViewe
 */

public static ComponentView createView(PwmMotor motor)
{
	 
	if (motor!=null)
	{
		return(new MotorSetupView(motor));
	}
	else
	{
		return(new MissingComponentView(PwmMotor.class.getName()));
	}
}


@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}




	

}
