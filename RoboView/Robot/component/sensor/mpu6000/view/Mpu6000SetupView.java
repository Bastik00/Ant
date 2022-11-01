package de.hska.lat.robot.component.sensor.mpu6000.view;

import java.awt.Insets;

import java.awt.event.ActionListener;


import javax.swing.JComboBox;
import javax.swing.JLabel;






import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000AccRange;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000ClockSource;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000Dlpf;
import de.hska.lat.robot.component.sensor.mpu6000.Mpu6000GyroscopeRange;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class Mpu6000SetupView extends ComponentSettingsView<Mpu6000> implements ActionListener, ComponentSettingsChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6139335684222936652L;


	
	protected static final int width = 250;
	protected static final int height = 150;
	
	
	protected JComboBox<Mpu6000AccRange> accRange;
	protected JComboBox<Mpu6000GyroscopeRange> gyroscopeRange;
	protected JComboBox<Mpu6000ClockSource> clockSource;
	protected JComboBox<Mpu6000Dlpf> dlpf;
	
	protected static final String ACCELEROMETER_RANGE_TEXT = "Accelerometer range";
	protected static final String GYROSCOPE_RANGE_TEXT = "Gyroscope range";
	protected static final String CLOCK_SOURCE_TEXT = "Clock source";
	protected static final String DLPF_BANDWIDTH_TEXT = "DLPF bandwidth";
	
private Mpu6000SetupView(Mpu6000 mpu9150)
{
	super(mpu9150);

	this.component.addSetupListener(this);

	buildView();

}


public void buildView() 
{
	JLabel tmpLabel;
			
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	tmpLabel = new JLabel(Mpu6000SetupView.CLOCK_SOURCE_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+10, 80, 20);
	this.add(tmpLabel);
	
	
	this.clockSource = new JComboBox<Mpu6000ClockSource>();
	
	for(Mpu6000ClockSource rate : Mpu6000ClockSource.values())
	{
		this.clockSource.addItem(rate);
	}
	this.clockSource.setBounds(insets.left+90,insets.top+10, 120, 20);
	this.clockSource.addActionListener(this);
	this.clockSource.setSelectedItem(this.component.getClockSource());
	this.add(this.clockSource);
	

	
	
	
	tmpLabel = new JLabel(Mpu6000SetupView.ACCELEROMETER_RANGE_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+35, 80, 20);
	this.add(tmpLabel);
	
	this.accRange = new JComboBox<Mpu6000AccRange>();
	
	for(Mpu6000AccRange rate : Mpu6000AccRange.values())
	{
		this.accRange.addItem(rate);
	}
	
	this.accRange.setBounds(insets.left+90,insets.top+35, 120, 20);
	this.accRange.addActionListener(this);
	this.accRange.setSelectedItem(this.component.getAccRange());
	this.add(this.accRange);
	
	
	
	
	tmpLabel = new JLabel(Mpu6000SetupView.GYROSCOPE_RANGE_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+60, 80, 20);
	this.add(tmpLabel);
	
	
	this.gyroscopeRange = new JComboBox<Mpu6000GyroscopeRange>();
	
	for(Mpu6000GyroscopeRange rate : Mpu6000GyroscopeRange.values())
	{
		this.gyroscopeRange.addItem(rate);
	}
	this.gyroscopeRange.setBounds(insets.left+90,insets.top+60, 120, 20);
	this.gyroscopeRange.addActionListener(this);
	this.gyroscopeRange.setSelectedItem(this.component.getGyroscopeRange());
	this.add(this.gyroscopeRange);
	

	
	
	
	
	tmpLabel = new JLabel(Mpu6000SetupView.DLPF_BANDWIDTH_TEXT);
	tmpLabel.setBounds(insets.left+10,insets.top+85, 80, 20);
	this.add(tmpLabel);
	
	
	this.dlpf = new JComboBox<Mpu6000Dlpf>();
	
	for(Mpu6000Dlpf rate : Mpu6000Dlpf.values())
	{
		this.dlpf.addItem(rate);
	}
	this.dlpf.setBounds(insets.left+90,insets.top+85, 120, 20);
	this.dlpf.addActionListener(this);
	this.dlpf.setSelectedItem(this.component.getDlpfRange());
	this.add(this.dlpf);
	

	
	
	
	this.addSetButton(insets.left+5, insets.top+110, 50, 22);
	this.addGetButton(insets.left+58, insets.top+110, 50, 22);
	
	this.addSaveButton(insets.left+110, insets.top+110, 50, 22);
	this.addLoadButton(insets.left+162, insets.top+110, 50, 22);	
		
}



@Override
protected int getViewWidth()
{
	return(Mpu6000SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Mpu6000SetupView.height);
}



@Override
protected boolean setSettings()
{
	
	

	return(this.component.remote_setSettings(
			(Mpu6000ClockSource)this.clockSource.getSelectedItem(),
			(Mpu6000Dlpf)this.dlpf.getSelectedItem(),
			(Mpu6000AccRange)this.accRange.getSelectedItem(),
			(Mpu6000GyroscopeRange)this.gyroscopeRange.getSelectedItem()
			));
	
	
}




public static ComponentView createView(Mpu6000 sensor)
{
	 
	if (sensor!=null)
	{
		return(new Mpu6000SetupView(sensor));
	}
	else
	{
		return(new MissingComponentView(Mpu6000.class.getName()));
	}
}




@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}

	

}
