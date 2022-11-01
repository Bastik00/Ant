package de.hska.lat.robot.component.actor.motor.view;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.ComponentValue;




public class MotorControlView extends ComponentView implements ChangeListener, ComponentSettingsChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	
	protected static final String STOP_TXT = "stop";
	protected static final String BRAKE_TXT = "brake";
	
	

	
	protected static final String ON_TEXT = "on/off";
	protected static final String FOLLOW_TEXT = "follow";
	
	
	
	protected static final int SCALE = 0x1ffff;
	
	protected static final int width = 100;
	protected static final int height = 160;
	
	
	protected PwmMotor motor;
	
	
	protected JSlider speed;
	protected JLabel speedLabel;
	
	protected JButton stopButton;
	protected JCheckBox on;
	
public MotorControlView(PwmMotor motor)
{
	super(motor.getComponentName(), false);


	this.motor = motor;

	buildView();
}


@Override
protected void buildView()
{
	
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.speed = new JSlider(JSlider.VERTICAL, 0, MotorControlView.SCALE, MotorControlView.SCALE/2);
	this.speed.setBounds(insets.left+70, insets.top+5, 40, 100);
	//this.speed
	this.speed.addChangeListener(this);
	this.add(this.speed);
	
	
	this.speedLabel = new JLabel("-");
	this.speedLabel.setBounds(insets.left+5, insets.top+5, 70, 20);
	this.add(this.speedLabel);
	
	this.on = new JCheckBox(ON_TEXT);
	this.on.setBounds(insets.left+5, insets.top+5, 70, 20);
	this.add(this.on);
	
	
	JButton tmpButton; 
	tmpButton = new JButton(MotorControlView.STOP_TXT);
	tmpButton.setBounds(insets.left+5, insets.top+35, 70, 20);
	tmpButton.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			MotorControlView.this.motor.remote_stop();

		}
		
	});
	this.add(tmpButton);
	
	

	
	tmpButton = new JButton(MotorControlView.BRAKE_TXT);
	tmpButton.setBounds(insets.left+5, insets.top+65, 70, 20);
	tmpButton.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			MotorControlView.this.motor.remote_brake();
		}
		
	});
	this.add(tmpButton);
	
}



@Override
protected int getViewWidth()
{
	return(MotorControlView.width);
}

@Override
protected int getViewHeight()
{
	return(MotorControlView.height);
}





public static ComponentView createView(PwmMotor motor)
{
	if (motor!=null)
	{
		return(new MotorControlView(motor));
	}
	else
	{
		return(new MissingComponentView(PwmMotor.class.getName()));
	}
}




@Override
public void stateChanged(ChangeEvent changeEvent)
{
	Object source;
	
	source = changeEvent.getSource();
	
	if (source == this.speed)
	{	
		float  velocity;
		velocity =this.speed.getValue();
		velocity -=(MotorControlView.SCALE/2);
		velocity /=(MotorControlView.SCALE/2);
			
		this.speedLabel.setText(ComponentValue.toFormatedFractionString(velocity*100, 2)+"%");
		this.motor.getControlValue().setValue(velocity);
	}

	
}


@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}


}
