package de.hska.lat.robot.component.battery;

import java.awt.Color;
import java.awt.Insets;


import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.ui.analogueInstrument.ScaleFragment;
import de.hska.lat.robot.ui.analoguePanelMeter.AnalogueQuaterPanelMeter;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.voltage.VoltageValue;



public class BatteryVoltageView extends ComponentView implements ComponentValueChangeListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	
	
	protected static final int width = 100;
	protected static final int height = 100;
	
	

	protected VoltageValue voltageValue;	
	protected FloatValue floatValue;	
	
	protected AnalogueQuaterPanelMeter panelMeter;
	
	
	
protected BatteryVoltageView(VoltageValue voltageValue)
{
	super(voltageValue.getName()+" V", false);

	this.voltageValue = voltageValue;

	this.voltageValue.addListener(this);
	
	buildView( );

}


@Override
protected void buildView()
{
	
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.panelMeter = new AnalogueQuaterPanelMeter(this.voltageValue.getName()); //,0,this.rpmValue.getMaxRange());
	
	
	this.panelMeter.setBounds(insets.left, insets.top,this.getWidth()-insets.right-insets.left, (this.getWidth()-insets.right-insets.left));
	this.panelMeter.setTickCount(10);
	this.panelMeter.setScaleUnit(" V");
	this.floatValue=this.panelMeter.getValue();
	this.floatValue.setRange(0, 90);
	
	ScaleFragment scala[] = new ScaleFragment[3];
	
	
	scala[0] = new ScaleFragment(Color.RED,40);
	scala[1] = new ScaleFragment(Color.BLUE,30);
	scala[2] = new ScaleFragment(Color.GREEN,30);	
	this.panelMeter.setScale(scala);
	
	this.add(this.panelMeter);
	
	

}



@Override
protected int getViewWidth()
{
	return(BatteryVoltageView.width);
}

@Override
protected int getViewHeight()
{
	return(BatteryVoltageView.height);
}



/**
 * create a new view for a RPM value as analog instrument
 * @param rpmValue
 * @return
 */

public static ComponentView createView(VoltageValue voltageValue)
{
	if (voltageValue!=null)
	{
		return(new BatteryVoltageView(voltageValue));
	}
	else
	{
		return(new MissingComponentView(Battery.class.getName()));
	}
}



@Override
public void valueChanged(ComponentValue<?> voltageValue)
{
	this.floatValue.setValue(Math.abs(voltageValue.getValue()));
	
}


@Override
protected void close() 
{
	this.voltageValue.removeListener(this);
}

}
