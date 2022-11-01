package de.hska.lat.robot.component.generic.temperature.view;

import java.awt.Insets;


import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.view.ValueView;


public class TemperatureSensorDataView  extends SensorDataView<TemperatureSensor<?,?>> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	
	
public TemperatureSensorDataView(TemperatureSensor<?,?> sensor)
{
	super(sensor);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(TemperatureSensorDataView.width);
}

@Override
protected int getViewHeight()
{
	return(TemperatureSensorDataView.height);
}




	
public void buildView()
{
	super.buildView();
	ValueView<?> view;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	view= TemperatureValueView.createView(this.sensor.getTemperatureValue(),true );
	view.setLocation(10,insets.top+5);
	this.add(view);

}
	
	



public static ComponentView createView(TemperatureSensor<?,?> sensor)
{

	if (sensor!=null)
	{
		return(new TemperatureSensorDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(TemperatureSensor.class.getName()));
	}
}



}
