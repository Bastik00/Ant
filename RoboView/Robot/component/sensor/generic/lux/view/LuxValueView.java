package de.hska.lat.robot.component.generic.lux.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.lux.LuxValue;
import de.hska.lat.robot.value.view.MissingValueView;


public class LuxValueView extends ValueView<LuxValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	protected static final int width = 90;
	protected static final int height = 30;
	
	private JLabel lux;
	
public LuxValueView(LuxValue value, boolean embedded)
{
	super(value,  embedded);
	
	value.addListener(this);
	buildView();
}


@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	this.lux = new JLabel("-");
	this.lux.setBounds(insets.left+5,insets.top+5,80,20);
	this.lux.setHorizontalAlignment(SwingConstants.CENTER);
	this.lux.setBorder(new LineBorder(Color.BLACK));
	this.add(this.lux);
}

@Override
protected int getViewWidth()
{
	return(LuxValueView.width);
}

@Override
protected int getViewHeight()
{
	return(LuxValueView.height);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.lux.setText(FloatValue.toFormatedFractionString(componentValue.getValue(), 2)+" lux");
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(LuxValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new LuxValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(LuxValue.class.getName(), false));
	}
}



}
