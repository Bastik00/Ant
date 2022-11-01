package de.hska.lat.robot.component.openServo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoChangeNotifier;
import de.hska.lat.robot.component.actor.servo.ServoSetupChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;


import de.hska.lat.robot.value.FloatValue;


public class OpenServoControlView extends ComponentView  implements ServoChangeNotifier,ServoSetupChangeNotifier, ChangeListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String CMD_ACTIVE	="cmdActive";
	
	
	private static final String STEP_STRING = "step width";
	
	private JSlider slider;

	
	private JLabel minPos;
	private JLabel maxPos;
	private JLabel actualPosition;
	private JCheckBox onCheckBox;
	
	private JSpinner stepSize;
	private StepWidthNumberModel stepWidth;
	private int position;
	
	
	
	protected Servo servo;
	
public OpenServoControlView(String name,Servo servo) 
{
	super(name, false);
	
	this.servo = servo;
	buildView();
	
	this.setSize(300, 90);
}



@Override
protected void buildView()
{
	
	slider=new JSlider();
	slider = new JSlider(); 
	slider.setBounds(55,25,182,30);
	slider.setMaximum(0);
	slider.setMinimum(0);
	slider.setValue(0);
	slider.setMajorTickSpacing(30);
	slider.setMinorTickSpacing(10);
	slider.setPaintTicks(true); 
	slider.addChangeListener(this);
	add(slider);

	
	actualPosition=new JLabel("-°");
	actualPosition.setBounds(122,05,40,20);
	actualPosition.setHorizontalAlignment(SwingConstants.CENTER);
	add(actualPosition);
	
	
	minPos=new JLabel("-90°");
	minPos.setBounds(10,35,40,20);
	minPos.setHorizontalAlignment(SwingConstants.CENTER);
	add(minPos);

	
	maxPos=new JLabel("90°");
	maxPos.setBounds(245,35,40,20);
	maxPos.setHorizontalAlignment(SwingConstants.CENTER);
	add(maxPos);
	
	
	onCheckBox=new JCheckBox("Active");
	onCheckBox.setBounds(10, 55, 80, 20);
	onCheckBox.setSelected(false);
	onCheckBox.setActionCommand(CMD_ACTIVE);
	onCheckBox.addActionListener(this);
	add(onCheckBox);
	
	
	
	
	
	JLabel tmpLabel = new JLabel(STEP_STRING);
	tmpLabel.setBounds(110, 55, 80, 25);
	add(tmpLabel);
	

	stepWidth = new StepWidthNumberModel();
	
	stepSize = new JSpinner(stepWidth);
	stepSize.setBounds(180, 55, 40, 25);
	stepSize.addChangeListener(this);
	add(stepSize);
	
	


}





private void updatePosition(int position)
{
	this.position=position;
	actualPosition.setText(String.valueOf(position)+"°");
}



@Override
public void actionPerformed(ActionEvent actionEvent) 
{

	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	 if (cmd.equals(CMD_ACTIVE))
	{
		 if (onCheckBox.isSelected())
		 {
			 this.servo.remote_servoOn();
		 }
		 else
		 {
			 this.servo.remote_servoOff();
		 }
		 
	}
	
}



@Override
public void stateChanged(ChangeEvent event) 
{
	if (event.getSource()==slider)
	{
		
		if (this.slider.hasFocus()==true) 
		{
			if (this.position!=slider.getValue())
			{
				updatePosition(slider.getValue());
			//	servoListener.setServoPosition(-1, position);
				this.servo.remote_moveTo(slider.getValue());
			}	
		}
	}
		/*
		if (this.sliderLock==false)
		{
			if (this.position!=slider.getValue())
			{
				updatePosition(slider.getValue());
				servoListener.servoMoveTo(-1, slider.getValue());
	
			}
		}
		this.sliderLock=false;
	}*/
	
	else if (event.getSource()==stepSize)
	{
		if (stepWidth.hasChanged())
		{
			this.servo.remote_setServoSpeed(stepWidth.getIndex());
		}
		
	}
}



@Override
public void servoPositionChanged(Servo servo) 
{
	// TODO Auto-generated method stub
	
}






@Override
public void isActive(Servo servo)
{
	// Not of your concern -> ignore
	
}



@Override
public void isStalling(Servo servo) 
{
	// Not of your concern -> ignore
	
}



@Override
public void servoSpeedChanged(int servo, int speed) 
{
	// TODO Auto-generated method stub
	
}

@Override
public void isOn(Servo servo) 
{
	onCheckBox.setSelected(servo.isOn());
	
}

@Override
public void servoSetupChanged(Servo servo) {
	maxPos.setText(FloatValue.toFormatedFractionString(servo.getMaxRange(), 2)+"°");
	slider.setMaximum((int)(servo.getMaxRange()));
	
	minPos.setText(FloatValue.toFormatedFractionString(servo.getMinRange(), 2)+"°");
	slider.setMinimum((int)(servo.getMinRange()));
	
	// possibly some code for servoOffsetChanged
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}



@Override
public void forceFeedbackOn(Servo servo)
{
	// TODO Auto-generated method stub
	
}



@Override
public void positionFeedbackOn(Servo servo)
{
	// TODO Auto-generated method stub
	
}



@Override
public void servoForceThresholdChanged(int globalId, int threshold)
{
	// TODO Auto-generated method stub
	
}



@Override
public void servoForcePositionChanged(int globalId, int threshold)
{
	// TODO Auto-generated method stub
	
}



@Override
public void isAtMin(Servo servo)
{
	// TODO Auto-generated method stub
	
}



@Override
public void isAtMax(Servo servo)
{
	// TODO Auto-generated method stub
	
}

}
