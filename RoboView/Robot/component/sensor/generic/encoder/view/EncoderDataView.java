package de.hska.lat.robot.component.generic.encoder.view;

import java.awt.Insets;


import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.generic.encoder.Encoder;
import de.hska.lat.robot.component.generic.luxSensor.LuxSensor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.view.ValueView;


public class EncoderDataView  extends SensorDataView<Encoder> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	
	
public EncoderDataView(Encoder sensor)
{
	super(sensor);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(EncoderDataView.width);
}

@Override
protected int getViewHeight()
{
	return(EncoderDataView.height);
}




	
public void buildView()
{
	super.buildView();
	ValueView<?> view;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	view= EncoderTicksValueView.createView(this.sensor.getEncoderTicksValue(), true );
	view.setLocation(10,insets.top+5);
	this.add(view);

}
	
	



public static ComponentView createView(Encoder sensor)
{

	if (sensor!=null)
	{
		return(new EncoderDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(LuxSensor.class.getName()));
	}
}



}
