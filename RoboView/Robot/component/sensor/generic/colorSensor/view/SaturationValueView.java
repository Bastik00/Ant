package de.hska.lat.robot.component.generic.colorSensor.view;

import java.awt.Color;
import java.awt.Insets;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.generic.colorSensor.SaturationValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class SaturationValueView extends ValueView<SaturationValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	protected static final int width = 140;
	protected static final int height = 30;
	
	
	private JLabel saturation;
	
public SaturationValueView(SaturationValue value, boolean embedded)
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
	
	description = new JLabel("saturation");
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	
	this.saturation = new JLabel("-");
	this.saturation.setBounds(80,insets.top+5,60,20);
	this.saturation.setHorizontalAlignment(SwingConstants.CENTER);
	this.saturation.setBorder(new LineBorder(Color.BLACK));
	this.add(this.saturation);
}

@Override
protected int getViewWidth()
{
	return(SaturationValueView.width);
}

@Override
protected int getViewHeight()
{
	return(SaturationValueView.height);
}




@Override
public void valueChanged(ComponentValue<?> componentValue)
{

	SaturationValue saturation = (SaturationValue) componentValue;
	this.saturation.setText(FloatValue.toFormatedFractionString(saturation.getValue(),2));
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(SaturationValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new SaturationValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(SaturationValue.class.getName(), embedded));
	}
}


}
