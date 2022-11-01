package de.hska.lat.robot.component.actor.motor.view;

import java.awt.Color;
import java.awt.Insets;


import javax.swing.JMenuItem;
import javax.swing.JSeparator;


import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotor;
import de.hska.lat.robot.component.actor.view.ActorDataView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.ui.analogueInstrument.ScaleFragment;
import de.hska.lat.robot.ui.analoguePanelMeter.AnalogueHalfPanelMeter;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.pwm.PwmValue;






public class MotorPwmView extends ActorDataView<PwmMotor> implements ComponentValueChangeListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	
	
	protected static final int width = 100;
	protected static final int height = 70;
	
	

	protected PwmValue pwmValue;	
	protected FloatValue floatValue;	
	
	protected AnalogueHalfPanelMeter panelMeter;
	
	protected static final String GRAPHIC_TEXT	= "graphic";
	protected static final String CMD_GRAPHIC	= "cmdGrapic";
	
	
	protected static final String graphicModeKey = ".graphicMode"; 
	protected JMenuItem showGraphicMenueItem;
	
protected MotorPwmView(PwmMotor motor)
{
	super(motor);

	this.pwmValue = motor.getPwmValue();

	this.pwmValue.addListener(this);
	
	buildView( );

}


@Override
protected void buildView()
{
	

	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.panelMeter = new AnalogueHalfPanelMeter(this.pwmValue.getName(),0,this.pwmValue.getMaxRange());
	
	
	this.panelMeter.setBounds(insets.left, insets.top,this.getWidth()-insets.right-insets.left, (this.getWidth()-insets.right-insets.left)/2+20);
	this.panelMeter.setTickCount(10);
	this.panelMeter.setScaleUnit(" % PWM");
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
protected void makePopupMenu()
{
	super.makePopupMenu();
	
	this.contextMenue.add(new JSeparator());
	 this.showGraphicMenueItem = this.addCheckBoxMenuItem(this.contextMenue , MotorPwmView.GRAPHIC_TEXT, MotorPwmView.CMD_GRAPHIC);
}




@Override
protected int getViewWidth()
{
	return(MotorPwmView.width);
}

@Override
protected int getViewHeight()
{
	return(MotorPwmView.height);
}



/**
 * create a new view for a RPM value as analog instrument
 * @param rpmValue
 * @return
 */

public static ComponentView createView(PwmMotor motor)
{
	if (motor!=null)
	{
		return(new MotorPwmView(motor));
	}
	else
	{
		return(new MissingComponentView(PwmMotor.class.getName()));
	}
}





@Override
protected void close() 
{
	this.pwmValue.removeListener(this);
}


@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.floatValue.setValue(componentValue.getValue()*100);
}

}
