package de.hska.lat.robot.component.generic.encoder.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;





import de.hska.lat.robot.component.generic.encoder.EncoderTicksValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.view.ValueView;


import de.hska.lat.robot.value.lux.LuxValue;
import de.hska.lat.robot.value.view.MissingValueView;


public class EncoderTicksValueView extends ValueView<EncoderTicksValue> implements ComponentValueChangeListener

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	
	protected static final String PRESURE_TEXT = "velocity";
	protected static final int width = 180;
	protected static final int height = 30;
	
	private JLabel velocity;
	
public EncoderTicksValueView(EncoderTicksValue value, boolean embedded)
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
	
	this.velocity = new JLabel("-");
	this.velocity.setBounds(80,insets.top+5,60,20);
	this.velocity.setHorizontalAlignment(SwingConstants.CENTER);
	this.velocity.setBorder(new LineBorder(Color.BLACK));
	this.add(this.velocity);
}

@Override
protected int getViewWidth()
{
	return(EncoderTicksValueView.width);
}

@Override
protected int getViewHeight()
{
	return(EncoderTicksValueView.height);
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(EncoderTicksValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new EncoderTicksValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(LuxValue.class.getName(), false));
	}
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.velocity.setText(componentValue.getValue()+" ticks");
}



}
