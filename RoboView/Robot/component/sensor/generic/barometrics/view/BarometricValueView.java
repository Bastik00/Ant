package de.hska.lat.robot.component.generic.barometrics.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.barometric.BarometricValue;
import de.hska.lat.robot.value.lux.LuxValue;
import de.hska.lat.robot.value.view.MissingValueView;


public class BarometricValueView extends ValueView<BarometricValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	
	protected static final String PRESURE_TEXT = "presure";
	protected static final int width = 140;
	protected static final int height = 30;
	
	private JLabel lux;
	
public BarometricValueView(BarometricValue value, boolean embedded)
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
	
	description = new JLabel(PRESURE_TEXT);
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	
	this.lux = new JLabel("-");
	this.lux.setBounds(80,insets.top+5,60,20);
	this.lux.setHorizontalAlignment(SwingConstants.CENTER);
	this.lux.setBorder(new LineBorder(Color.BLACK));
	this.add(this.lux);
}

@Override
protected int getViewWidth()
{
	return(BarometricValueView.width);
}

@Override
protected int getViewHeight()
{
	return(BarometricValueView.height);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.lux.setText(FloatValue.toFormatedFractionString(componentValue.getValue(), 2));
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(BarometricValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new BarometricValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(LuxValue.class.getName(), false));
	}
}



}
