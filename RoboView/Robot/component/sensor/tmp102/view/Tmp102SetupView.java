package de.hska.lat.robot.component.sensor.tmp102.view;



import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102Address;
import de.hska.lat.robot.component.sensor.tmp102.Tmp102ConversionRate;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.value.view.MissingValueView;


public class Tmp102SetupView extends ComponentSettingsView<Tmp102> implements ComponentSettingsChangeNotifier

{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6199812393064386683L;
	
	protected static final int width = 250;
	protected static final int height = 90;
	
	private static final String ON_TEXT			= "on" ;
	private static final String EXTENDED_TEXT	= "extended" ;

	private static final String ADDRESS_TEXT	= "address" ;
	private static final String CONVERSION_RATE_TEXT		= "period" ;

	 
	
	protected JComboBox<Tmp102ConversionRate> conversionRate;
	protected JComboBox<Tmp102Address> addresSelector;
	protected JCheckBox onCheckBox;
	protected JCheckBox extendedCheckBox;
	
public Tmp102SetupView(Tmp102 sensor)
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
	
	
	this.onCheckBox = new JCheckBox (Tmp102SetupView.ON_TEXT);
	this.onCheckBox.setBounds(insets.left+5,insets.top+5,80,22);
	this.add(this.onCheckBox);

	
	this.extendedCheckBox = new JCheckBox (Tmp102SetupView.EXTENDED_TEXT);
	this.extendedCheckBox.setBounds(insets.left+5,insets.top+30,80,22);
	this.add(this.extendedCheckBox);
	
	
	
	
	description = new JLabel(Tmp102SetupView.ADDRESS_TEXT);
	description.setBounds(insets.left+110,insets.top+5, 80,22);
	this.add(description);
	
	this.addresSelector = new JComboBox<Tmp102Address>();
	for(Tmp102Address address : Tmp102Address.values())
	{
		this.addresSelector.addItem(address);
	}
	this.addresSelector.setBounds(insets.left+160,insets.top+5,80,22);
	this.add(this.addresSelector);
	
	
	
	
	description = new JLabel(Tmp102SetupView.CONVERSION_RATE_TEXT);
	description.setBounds(insets.left+110,insets.top+30,80,22);
	this.add(description);
	
	this.conversionRate = new JComboBox<Tmp102ConversionRate>();
	for(Tmp102ConversionRate rate : Tmp102ConversionRate.values())
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
	return(Tmp102SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Tmp102SetupView.height);
}



protected void update()
{
	this.addresSelector.setSelectedItem(this.component.getI2CAddress());
	this.conversionRate.setSelectedItem(this.component.getConversionRate());
	this.extendedCheckBox.setSelected(this.component.isInExtendedMode());
}


@Override
protected boolean setSettings()
{
	return(this.component.remote_setSettings((Tmp102Address) this.addresSelector.getSelectedItem(),
				this.onCheckBox.isSelected(),
				this.extendedCheckBox.isSelected(),
				(Tmp102ConversionRate)this.conversionRate.getSelectedItem()));
}




public static ComponentView createView(Tmp102 sensor)
{

	if (sensor!=null)
	{
		return(new Tmp102SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Tmp102.class.getName(), false));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
}




}
