package de.hska.lat.robot.component.openServo;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoChangeNotifier;
import de.hska.lat.robot.component.actor.servo.ServoSetupChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;



public class OpenServoSetupView extends ComponentView  implements ChangeListener, ActionListener, ServoChangeNotifier, ServoSetupChangeNotifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSlider slider;
	private JLabel actualPosition;

	private JSpinner maxRange;
	private JSpinner minRange;
	private JSpinner offset;
	
	private JCheckBox onCheckBox;
	private JCheckBox reverseCheckBox;
	
	private static final String CMD_ON	="cmdOn";
	
	SpinnerNumberModel minSpinerModel ;
	SpinnerNumberModel maxSpinerModel ;
	SpinnerNumberModel offsetSpinerModel;
	
	
	private JSpinner stepSize;
	private StepWidthNumberModel stepWidth;
	
	private static String cmd_set="cmdSet";
	private static String cmd_get="cmdGet";
	private static String cmd_save="cmdSave";
	private static String cmd_load="cmdLoad";
	
	private static String STRING_GET = "get";
	private static String STRING_SET = "set";
	private static String STRING_LOAD = "load";
	private static String STRING_SAVE = "save";
	
	private float position;
	
	protected Servo servo;
	
public OpenServoSetupView(String name, Servo servo) 
{
	super(name, false);
	buildView();

	this.setSize(300, 115);
	this.servo=servo;
}



@Override
protected void buildView()
{
	
	JLabel tmpLabel;

	JButton tmpButton;
	
	tmpLabel=new JLabel("position");
	tmpLabel.setBounds(50,15,100,20);
	this.add(tmpLabel);
	
	this.actualPosition=new JLabel("0°");
	this.actualPosition.setBounds(122,15,40,20);
	this.actualPosition.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(this.actualPosition);
	
	this.slider = new JSlider(); 
	this.slider.setBounds(55,30,182,30);
	this.slider.setMaximum(90);
	this.slider.setMinimum(-90);
	this.slider.setValue(0);
	this.slider.setMajorTickSpacing(30);
	this.slider.setMinorTickSpacing(10);
	this.slider.setPaintTicks(true); 
	this.slider.addChangeListener(this);
	this.add(this.slider);
	

	this.minSpinerModel = new SpinnerNumberModel(0,-90,90,1 );
	this.maxSpinerModel = new SpinnerNumberModel(0, -90,90, 1);
	
	this.minRange= new JSpinner(this.minSpinerModel);
	this.minRange.setBounds(10,35,40,20);
	this.minRange.addChangeListener(this);
	this.add(this.minRange);
	
	this.maxRange= new JSpinner(this.maxSpinerModel);
	this.maxRange.setBounds(245,35,40,20);
	this.maxRange.addChangeListener(this);
	this.add(this.maxRange);
	 

	
	this.onCheckBox=new JCheckBox("On");
	this.onCheckBox.setBounds(10, 60, 80, 20);
	this.onCheckBox.setSelected(false);
	this.onCheckBox.setActionCommand(CMD_ON);
	this.onCheckBox.addActionListener(this);
	this.add(this.onCheckBox);
	
	this.reverseCheckBox=new JCheckBox("Reverse");
	this.reverseCheckBox.setBounds(90, 60, 80, 20);
	this.reverseCheckBox.setSelected(false);
//	reverseCheckBox(BCMD_DRAV_DIRECT);
	this.reverseCheckBox.addActionListener(this);
	this.add(this.reverseCheckBox);
	
	
	tmpLabel= new JLabel("Offset");
	tmpLabel.setBounds(200, 60, 40, 20);
	this.add(tmpLabel);
	 
	
	this.offsetSpinerModel = new SpinnerNumberModel(550, 120, 980, 1);

	this.offset= new JSpinner(this.offsetSpinerModel);
	this.offset.setBounds(245, 60, 40, 20);
	this.offset.addChangeListener(this);
	this.add(this.offset);
	
	
	tmpButton = new JButton(STRING_SET);
	tmpButton.setBounds(5, 85, 50, 22);
	tmpButton.setActionCommand(cmd_set);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);

	tmpButton = new JButton(STRING_GET);
	tmpButton.setBounds(60, 85, 50, 22);
	tmpButton.setActionCommand(cmd_get);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);

	tmpButton = new JButton(STRING_SAVE);
	tmpButton.setBounds(115, 85, 50, 22);
	tmpButton.setActionCommand(cmd_save);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);
	
	tmpButton = new JButton(STRING_LOAD);
	tmpButton.setBounds(170, 85, 50, 22);
	tmpButton.setActionCommand(cmd_load);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	this.add(tmpButton);
	
	this.stepWidth = new StepWidthNumberModel();
	
	this.stepSize = new JSpinner(stepWidth);
	this.stepSize.setBounds(245, 85, 40, 20);
	this.stepSize.addChangeListener(this);
	add(this.stepSize);
	
}



private void updatePosition(float position)
{
	this.position=position;
	this.actualPosition.setText(String.valueOf(position)+"°");
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
				this.updatePosition(slider.getValue());
				this.servo.remote_setServoPosition(this.position);
			}	
		}


	}
	
	else if (event.getSource()==minRange)
		{
		this.maxSpinerModel.setMinimum((Comparable<?>) minSpinerModel.getNumber());
		this.slider.setMinimum(  minSpinerModel.getNumber().intValue());
		}
	
	else if (event.getSource()==maxRange)
		{
		this.minSpinerModel.setMaximum((Comparable<?>) maxSpinerModel.getNumber());
		this.slider.setMaximum(  maxSpinerModel.getNumber().intValue());
		}
	
}



@Override
public void actionPerformed(ActionEvent actionEvent) 
{

	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	if (cmd.equals(cmd_set))
	{
		int minRange = this. minSpinerModel.getNumber().intValue();
		int maxRange =this.maxSpinerModel.getNumber().intValue();
		int offset =  this.offsetSpinerModel.getNumber().intValue();
		boolean inverse = this.reverseCheckBox.isSelected();

		this.servo.remote_setServoDefaults(minRange, maxRange, offset,0,inverse);
	}
	else if (cmd.equals(cmd_get))
	{
		this.servo.remote_getSettings();
	}
	else if (cmd.equals(cmd_save))
	{
		this.servo.remote_saveDefaults();
	}
	else if (cmd.equals(cmd_load))
	{
		this.servo.remote_loadDefaults();
	}	
	
	
	else if (cmd.equals(CMD_ON))
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
public void servoPositionChanged(Servo servo) 
{
	this.actualPosition.setText(String.valueOf(servo.getPositionAsDegree())+"°");
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
public void servoSpeedChanged(int globalId, int speed)
{
	this.stepWidth.setValue(speed);
	
}

@Override
public void servoSetupChanged(Servo servo) 
{
	this.offsetSpinerModel.setValue(servo.getOffset());
	this.maxSpinerModel.setValue((int)(servo.getMaxRange()));
	this.minSpinerModel.setValue((int)(servo.getMinRange()));
	this.reverseCheckBox.setSelected(servo.isReverse());
}

@Override
public void isOn(Servo servo) 
{
	this.onCheckBox.setSelected(servo.isOn());
	
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
