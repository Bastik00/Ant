package de.hska.lat.robot.component.generic.temperature.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class TemperatureValueView extends ValueView<TemperatureValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	protected static final int width = 140;
	protected static final int height = 30;
	
	
	protected static final String KELVIN_TEXT = "display as kelvin";
	protected static final String CELSIUS_TEXT = "display as celcius";
	
	protected JMenuItem showCelsiusItem;
	protected JMenuItem showKelvinItem;
	
	private JLabel temperature;
	
public TemperatureValueView(TemperatureValue value, boolean embedded)
{
	super(value, embedded);
	
	value.addListener(this);
	buildView();
}


@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel description;
	
	description = new JLabel("temperature");
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	
	this.temperature = new JLabel("-");
	this.temperature.setBounds(80,insets.top+5,60,20);
	this.temperature.setHorizontalAlignment(SwingConstants.CENTER);
	this.temperature.setBorder(new LineBorder(Color.BLACK));
	this.add(this.temperature);
}

@Override
protected int getViewWidth()
{
	return(TemperatureValueView.width);
}

@Override
protected int getViewHeight()
{
	return(TemperatureValueView.height);
}


@Override
protected void makePopupMenu()
{
	super.makePopupMenu();
	
	this.contextMenue.add(new JSeparator());
	
	 ButtonGroup group = new ButtonGroup();
 
	 
	 
	 this.showKelvinItem = this.addRadioButtonMenuItem(this.contextMenue , KELVIN_TEXT, "ValueGraph.CMD_ADD");
	 showKelvinItem.setSelected(false);

	 this.showCelsiusItem = this.addRadioButtonMenuItem(this.contextMenue , CELSIUS_TEXT, "ValueGraph.CMD_ADD");

	 
	  group.add(this.showKelvinItem);
	  group.add(this.showCelsiusItem);
	  
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{

	TemperatureValue temperature = (TemperatureValue) componentValue;
	
		
	if (this.showCelsiusItem.isSelected())
	{
		this.temperature.setText(FloatValue.toFormatedFractionString(temperature.getAsCelsius(), 2)+" °C");
	}
	else
	{
		this.temperature.setText(FloatValue.toFormatedFractionString(temperature.getValue(), 2)+" K");		 
	}
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(TemperatureValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new TemperatureValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(TemperatureValue.class.getName(), false));
	}
}


}
