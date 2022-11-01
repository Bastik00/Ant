package de.hska.lat.robot.value.view.current;




import java.awt.Color;
import java.awt.Insets;


import javax.swing.JLabel;
import javax.swing.border.LineBorder;





import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.currentSensor.CurrentSensorChangeNotifier;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.current.CurrentValue;
import de.hska.lat.robot.value.lux.LuxValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;


public class CurrentValueView extends ValueView<CurrentValue> implements CurrentSensorChangeNotifier
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3244786375824253493L;
	
	
	private JLabel level;
	private String tmpString;
	
	protected static final int width = 160;
	protected static final int height = 25;
	
public CurrentValueView(CurrentValue value, boolean embedded, String label) 
{
	super(value, embedded);
	
	this.buildView();
	
	this.value.addListener(this);
	this.tmpString = label;
	
	

}




protected void buildView() 
{
	
	super.buildView();

	Insets insets = this.getBorder().getBorderInsets(this);
	
	JLabel tmpLabel;
	
	tmpLabel=new JLabel(this.tmpString);
	tmpLabel.setBounds(insets.left+5,insets.top+5,60,20);
	this.add(tmpLabel);
	
	this.level=new JLabel("-");
	this.level.setBounds(insets.left+60,insets.top+5,90,20);
	this.level.setBorder(new LineBorder(Color.GRAY));
	this.add(this.level);
	

}



@Override
protected int getViewWidth()
{
	return(CurrentValueView.width);
}

@Override
protected int getViewHeight()
{
	return(CurrentValueView.height);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{

	level.setText(String.valueOf(componentValue.getValue()));
	
}

/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */


public static ValueView<?> createView(CurrentValue value, boolean embedded, String label)
{

	if (value!=null)
	{
		return(new CurrentValueView(value, embedded, label));
	}
	else
	{
		return(new MissingValueView(LuxValue.class.getName(), false));
	}
}




@Override
public void currentValueChanged(CurrentSensor current)
{
	this.level.setText(String.valueOf(current.getTotalValue()));
}

}
