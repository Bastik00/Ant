package de.hska.lat.robot.component.generic.colorSensor.view;

import java.awt.Color;
import java.awt.Insets;


import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.component.generic.colorSensor.HueValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class HueValueView extends ValueView<HueValue>

{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -4070503616283079668L;
	
	protected static final int width = 140;
	protected static final int height = 30;
	
	
	private JLabel hue;

	
	protected JMenuItem showDegree;
	protected JMenuItem showRadiant;
	
public HueValueView(HueValue value, boolean embedded)
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
	
	description = new JLabel("hue");
	description.setBounds(10,insets.top+5,80,20);
	this.add(description);
	
	this.hue = new JLabel("-");
	this.hue.setBounds(80,insets.top+5,60,20);
	this.hue.setHorizontalAlignment(SwingConstants.CENTER);
	this.hue.setBorder(new LineBorder(Color.BLACK));
	this.add(this.hue);
}

@Override
protected int getViewWidth()
{
	return(HueValueView.width);
}

@Override
protected int getViewHeight()
{
	return(HueValueView.height);
}




@Override
public void valueChanged(ComponentValue<?> componentValue)
{

	HueValue hue = (HueValue) componentValue;
	
		
//	if (this.showRadiant.isSelected())
	{
	//	this.hue.setText(FloatValue.toFormatedFractionString((hue.getValue()), 2)+" rad");
	}
//	else
	{
		this.hue.setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(hue.getValue()), 0)+"°");
	}
	
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(HueValue value, boolean embedded)
{

	if (value!=null)
	{
		return(new HueValueView(value, embedded));
	}
	else
	{
		return(new MissingValueView(HueValue.class.getName(), embedded));
	}
}


}
