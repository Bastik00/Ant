package de.hska.lat.robot.component.generic.colorSensor.view;

import java.awt.Color;
import java.awt.Insets;


import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensor;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensorChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.view.ValueView;


public class ColorSensorDataView  extends SensorDataView<ColorSensor<?,?>> implements ColorSensorChangeNotifier
{

	
	protected static final int width = 180;
	protected static final int height = 120;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	JPanel colorLabel;
	
public ColorSensorDataView(ColorSensor<?,?> sensor)
{
	super(sensor);
	
	sensor.addSensorListener(this);
	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(ColorSensorDataView.width);
}

@Override
protected int getViewHeight()
{
	return(ColorSensorDataView.height);
}




	
public void buildView()
{
	super.buildView();
	ValueView<?> view;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	view= HueValueView.createView(this.sensor.getHueValue(),true );
	view.setLocation(10,insets.top+5);
	this.add(view);
	
	
	view= SaturationValueView.createView(this.sensor.getSaturationValue(),true );
	view.setLocation(10,insets.top+40);
	this.add(view);
	
	
	view= ValueValueView.createView(this.sensor.getValueValue(),true );
	view.setLocation(10,insets.top+75);
	this.add(view);
	
	
	this.colorLabel = new JPanel();
	this.colorLabel.setBorder(new LineBorder(Color.black,1));
	this.colorLabel.setBounds(160,insets.top+5,20,100);
	this.add(this.colorLabel);

}
	
	



public static ComponentView createView(ColorSensor<?,?> sensor)
{

	if (sensor!=null)
	{
		return(new ColorSensorDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(ColorSensor.class.getName()));
	}
}



@Override
public void colorChanged(ColorSensor<?, ?> sensor)
{
	RgbColor rgbColor;
	Color color;
	
	rgbColor = this.sensor.getAsRgb();
	
	color = new Color(rgbColor.red, rgbColor.green, rgbColor.blue); 

	this.colorLabel.setBackground(color);

	
}



}
