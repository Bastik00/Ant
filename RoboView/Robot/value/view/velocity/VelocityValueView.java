package de.hska.lat.robot.value.view.velocity;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.velocity.VelocityValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class VelocityValueView extends ValueView<VelocityValue> implements ComponentValueChangeListener

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	
	protected static final String PRESURE_TEXT = "velocity";
	protected static final int width = 180;
	protected static final int height = 30;
	
	private JLabel velocity;
	
public VelocityValueView(VelocityValue value)
{
	super(value, false);
	
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
	
	this.velocity = new JLabel("-");
	this.velocity.setBounds(80,insets.top+5,60,20);
	this.velocity.setHorizontalAlignment(SwingConstants.CENTER);
	this.velocity.setBorder(new LineBorder(Color.BLACK));
	this.add(this.velocity);
}

@Override
protected int getViewWidth()
{
	return(VelocityValueView.width);
}

@Override
protected int getViewHeight()
{
	return(VelocityValueView.height);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.velocity.setText(FloatValue.toFormatedFractionString(componentValue.getValue(), 2));
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(VelocityValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new VelocityValueView(value));
	}
	else
	{
		return(new MissingValueView(VelocityValue.class.getName(), embedded));
	}
}



}
