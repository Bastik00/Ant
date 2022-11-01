package de.hska.lat.robot.value.view.rpm;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.rpm.RpmValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class RpmValueView extends ValueView<RpmValue> implements ComponentValueChangeListener

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	
	protected static final String PRESURE_TEXT = "RPM";
	protected static final int width = 120;
	protected static final int height = 30;
	
	private JLabel rpm;
	
public RpmValueView(RpmValue value, boolean embedded)
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
	description.setBounds(10,insets.top+5,40,20);
	this.add(description);
	
	this.rpm = new JLabel("-");
	this.rpm.setBounds(40,insets.top+5,80,20);
	this.rpm.setHorizontalAlignment(SwingConstants.CENTER);
	this.rpm.setBorder(new LineBorder(Color.BLACK));
	this.add(this.rpm);
}

@Override
protected int getViewWidth()
{
	return(RpmValueView.width);
}

@Override
protected int getViewHeight()
{
	return(RpmValueView.height);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.rpm.setText(FloatValue.toFormatedFractionString(componentValue.getValue(), 2));
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(RpmValue rpm, boolean embedded)
{

	if (rpm!=null)
	{
		return(new RpmValueView(rpm, embedded));
	}
	else
	{
		return(new MissingValueView(RpmValue.class.getName(), false));
	}
}



}
