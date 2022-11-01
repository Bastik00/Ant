package de.hska.lat.robot.component.generic.colorSensor.view;

import java.awt.Color;
import java.awt.Insets;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.generic.colorSensor.ValueValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class ValueValueView extends ValueView<ValueValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	protected static final int width = 140;
	protected static final int height = 30;
	
	
	private JLabel value;
	
public ValueValueView(ValueValue value, boolean embedded)
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
	
	description = new JLabel("value");
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	
	this.value = new JLabel("-");
	this.value.setBounds(80,insets.top+5,60,20);
	this.value.setHorizontalAlignment(SwingConstants.CENTER);
	this.value.setBorder(new LineBorder(Color.BLACK));
	this.add(this.value);
}

@Override
protected int getViewWidth()
{
	return(ValueValueView.width);
}

@Override
protected int getViewHeight()
{
	return(ValueValueView.height);
}




@Override
public void valueChanged(ComponentValue<?> componentValue)
{

	ValueValue value = (ValueValue) componentValue;
	this.value.setText(FloatValue.toFormatedFractionString(value.getValue(),2));

}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(ValueValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new ValueValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(ValueValue.class.getName(), embedded));
	}
}


}
