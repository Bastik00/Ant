package de.hska.lat.robot.component.sensor.tmp006.view;



import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006Address;
import de.hska.lat.robot.component.sensor.tmp006.Tmp006ConversionRate;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.value.view.MissingValueView;


public class Tmp006SetupView extends ComponentSettingsView<Tmp006> implements ComponentSettingsChangeNotifier

{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6199812393064386683L;
	
	protected static final int width = 250;
	protected static final int height = 90;
	
	private static final String ON_TEXT			= "on" ;

	private static final String ADDRESS_TEXT	= "address" ;
	private static final String CONVERSION_RATE_TEXT		= "period" ;

	 
	
	protected JComboBox<Tmp006ConversionRate> conversionRate;
	protected JComboBox<Tmp006Address> addresSelector;
	protected JCheckBox onCheckBox;

	
public Tmp006SetupView(Tmp006 sensor)
{
	super(sensor);
	
	component.addSetupListener(this);
	
	this.buildView();
	this.update();
}


@Override
protected void buildView()
{
	
	super.buildView();

	
	// extended Mode

	
	
	
	JLabel description;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.onCheckBox = new JCheckBox (Tmp006SetupView.ON_TEXT);
	this.onCheckBox.setBounds(insets.left+5,insets.top+5,80,22);
	this.add(this.onCheckBox);


	
	
	
	description = new JLabel(Tmp006SetupView.ADDRESS_TEXT);
	description.setBounds(insets.left+110,insets.top+5, 80,22);
	this.add(description);
	
	this.addresSelector = new JComboBox<Tmp006Address>();
	for(Tmp006Address address : Tmp006Address.values())
	{
		this.addresSelector.addItem(address);
	}
	this.addresSelector.setBounds(insets.left+160,insets.top+5,80,22);
	this.add(this.addresSelector);
	
	
	
	
	description = new JLabel(Tmp006SetupView.CONVERSION_RATE_TEXT);
	description.setBounds(insets.left+110,insets.top+30,80,22);
	this.add(description);
	
	this.conversionRate = new JComboBox<Tmp006ConversionRate>();
	for(Tmp006ConversionRate rate : Tmp006ConversionRate.values())
	{
		this.conversionRate.addItem(rate);
	}
	this.conversionRate.setBounds(insets.left+160,insets.top+30,80,22);
	this.add(this.conversionRate);
	
	

	
	
	
	this.addSetButton(insets.left+5, insets.top+60, 50, 22);
	this.addGetButton(insets.left+60, insets.top+60, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+60, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+60, 50, 22);
	

	
}

@Override
protected int getViewWidth()
{
	return(Tmp006SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Tmp006SetupView.height);
}



protected void update()
{
	this.addresSelector.setSelectedItem(this.component.getI2CAddress());
	this.conversionRate.setSelectedItem(this.component.getConversionRate());
	
}


@Override
protected boolean setSettings()
{
	return(this.component.remote_setSettings((Tmp006Address) this.addresSelector.getSelectedItem(),
				this.onCheckBox.isSelected(),
				(Tmp006ConversionRate)this.conversionRate.getSelectedItem()));
}




public static ComponentView createView(Tmp006 sensor)
{

	if (sensor!=null)
	{
		return(new Tmp006SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Tmp006.class.getName(), false));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
}




}
