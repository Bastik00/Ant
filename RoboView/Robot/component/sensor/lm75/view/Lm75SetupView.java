package de.hska.lat.robot.component.sensor.lm75.view;



import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.lm75.Lm75;
import de.hska.lat.robot.component.sensor.lm75.Lm75Address;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.value.view.MissingValueView;


public class Lm75SetupView extends ComponentSettingsView<Lm75> implements ComponentSettingsChangeNotifier

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
	

	 
	
	
	protected JComboBox<Lm75Address> addresSelector;
	protected JCheckBox onCheckBox;
	protected JCheckBox extendedCheckBox;
	
public Lm75SetupView(Lm75 sensor)
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
	
	
	this.onCheckBox = new JCheckBox (Lm75SetupView.ON_TEXT);
	this.onCheckBox.setBounds(insets.left+5,insets.top+5,80,22);
	this.add(this.onCheckBox);

	
	this.extendedCheckBox = new JCheckBox (Lm75SetupView.EXTENDED_TEXT);
	this.extendedCheckBox.setBounds(insets.left+5,insets.top+30,80,22);
	this.add(this.extendedCheckBox);
	
	
	
	
	description = new JLabel(Lm75SetupView.ADDRESS_TEXT);
	description.setBounds(insets.left+110,insets.top+5, 80,22);
	this.add(description);
	
	this.addresSelector = new JComboBox<Lm75Address>();
	for(Lm75Address address : Lm75Address.values())
	{
		this.addresSelector.addItem(address);
	}
	this.addresSelector.setBounds(insets.left+160,insets.top+5,80,22);
	this.add(this.addresSelector);
	
	
	

	
	
	
	this.addSetButton(insets.left+5, insets.top+60, 50, 22);
	this.addGetButton(insets.left+60, insets.top+60, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+60, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+60, 50, 22);
	

	
}

@Override
protected int getViewWidth()
{
	return(Lm75SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(Lm75SetupView.height);
}



protected void update()
{
	this.addresSelector.setSelectedItem(this.component.getI2CAddress());
	this.extendedCheckBox.setSelected(this.component.isInExtendedMode());
}


@Override
protected boolean setSettings()
{
	return(this.component.remote_setSettings((Lm75Address) this.addresSelector.getSelectedItem(),
				this.onCheckBox.isSelected(),
				this.extendedCheckBox.isSelected()));
}




public static ComponentView createView(Lm75 sensor)
{

	if (sensor!=null)
	{
		return(new Lm75SetupView(sensor));
	}
	else
	{
		return(new MissingValueView(Lm75.class.getName(), false));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
}




}
