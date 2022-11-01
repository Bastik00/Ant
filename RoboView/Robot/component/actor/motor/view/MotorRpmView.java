package de.hska.lat.robot.component.actor.motor.view;

import java.awt.Color;
import java.awt.Insets;


import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.ui.analogueInstrument.ScaleFragment;
import de.hska.lat.robot.ui.analoguePanelMeter.AnalogueHalfPanelMeter;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.rpm.RpmValue;



public class MotorRpmView extends ComponentView implements ComponentValueChangeListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	
	
	protected static final int width = 100;
	protected static final int height = 70;
	
	

	protected RpmValue rpmValue;	
	protected FloatValue floatValue;	
	
	protected AnalogueHalfPanelMeter panelMeter;
	
	
	
protected MotorRpmView(RpmValue rpmValue)
{
	super(rpmValue.getName()+" RPM", false);

	this.rpmValue = rpmValue;

	this.rpmValue.addListener(this);
	
	buildView( );

}


@Override
protected void buildView()
{
	
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.panelMeter = new AnalogueHalfPanelMeter(this.rpmValue.getName(),0,this.rpmValue.getMaxRange());
	
	
	this.panelMeter.setBounds(insets.left, insets.top,this.getWidth()-insets.right-insets.left, (this.getWidth()-insets.right-insets.left)/2+20);
	this.panelMeter.setTickCount(10);
	this.panelMeter.setScaleUnit(" RPM");
	this.panelMeter.setFraction(1);
	this.floatValue=this.panelMeter.getValue();
	this.floatValue.setRange(-100, 100);
	
	ScaleFragment scala[] = new ScaleFragment[6];

	scala[0] = new ScaleFragment(Color.RED,20);	
	scala[1] = new ScaleFragment(Color.YELLOW,30);
	scala[2] = new ScaleFragment(Color.GREEN,50);
	

	scala[3] = new ScaleFragment(Color.GREEN,50);
	scala[4] = new ScaleFragment(Color.YELLOW,30);
	scala[5] = new ScaleFragment(Color.RED,20);	
	this.panelMeter.setScale(scala);
	
	this.add(this.panelMeter);
	
	

}



@Override
protected int getViewWidth()
{
	return(MotorRpmView.width);
}

@Override
protected int getViewHeight()
{
	return(MotorRpmView.height);
}



/**
 * create a new view for a RPM value as analog instrument
 * @param rpmValue
 * @return
 */

public static ComponentView createView(RpmValue rpmValue)
{
	if (rpmValue!=null)
	{
		return(new MotorRpmView(rpmValue));
	}
	else
	{
		return(new MissingComponentView(PwmMotor.class.getName()));
	}
}



@Override
public void valueChanged(ComponentValue<?> rpmValue)
{
	this.floatValue.setValue(Math.abs(rpmValue.getValue()));
	
}


@Override
protected void close() 
{
	this.rpmValue.removeListener(this);
}

}
