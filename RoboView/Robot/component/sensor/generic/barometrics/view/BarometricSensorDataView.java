package de.hska.lat.robot.component.generic.barometrics.view;

import java.awt.Insets;


import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.generic.barometric.BarometricSensor;

import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.view.ValueView;


public class BarometricSensorDataView  extends SensorDataView<BarometricSensor<?,?>> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	
	
public BarometricSensorDataView(BarometricSensor<?,?> sensor)
{
	super(sensor);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(BarometricSensorDataView.width);
}

@Override
protected int getViewHeight()
{
	return(BarometricSensorDataView.height);
}




	
public void buildView()
{
	super.buildView();
	ValueView<?> view;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	view= BarometricValueView.createView(this.sensor.getPresureValue(),true );
	view.setLocation(10,insets.top+5);
	this.add(view);

}
	
	



public static ComponentView createView(BarometricSensor<?,?> sensor)
{

	if (sensor!=null)
	{
		return(new BarometricSensorDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(BarometricSensor.class.getName()));
	}
}


}
