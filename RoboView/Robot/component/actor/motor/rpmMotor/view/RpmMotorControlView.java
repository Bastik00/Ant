package de.hska.lat.robot.component.actor.motor.rpmMotor.view;

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
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.RpmMotor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.ComponentValue;

public class RpmMotorControlView extends ComponentView implements
		ChangeListener, ComponentSettingsChangeNotifier
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	protected static final String STOP_TXT = "stop";
	protected static final String BRAKE_TXT = "brake";

	protected static final String ON_TEXT = "on/off";

	protected static final int SCALE = 0x1ffff;

	protected static final int width = 100;
	protected static final int height = 300;

	protected RpmMotor motor;

	protected JSlider rpmControl;
	protected JSlider velocityControl;

	protected JLabel rpmLabel;
	protected JLabel velocityLabel;

	protected JButton stopButton;
	protected JCheckBox on;

	protected float rmpMax;
	protected float rmpMin;

	protected int velocityMax;
	protected int velocityMin;

	public RpmMotorControlView(RpmMotor motor)
	{
		super(motor.getComponentName(), false);

		this.motor = motor;
		this.motor.addSetupListener(this);
		this.rmpMax = motor.getRpm();
		this.rmpMin = -motor.getRpm();
		buildView();
	}

	@Override
	protected void buildView()
	{

		super.buildView();

		Insets insets = this.getBorder().getBorderInsets(this);

		
		this.on = new JCheckBox(ON_TEXT);
		this.on.setBounds(insets.left + 5, insets.top+5 , getWidth()-insets.left-insets.right-10, 20);
			this.add(this.on);
		
		this.rpmLabel = new JLabel("-",JLabel.CENTER);
		this.rpmLabel.setBounds(insets.left + 5, insets.top+25 , getWidth()-insets.left-insets.right-10, 20);
		this.add(this.rpmLabel);

			
			
		this.rpmControl = new JSlider(JSlider.VERTICAL,(int) this.rmpMin,
				(int) this.rmpMax, 0);
		this.rpmControl.setBounds(insets.left + 5, insets.top+45 , getWidth()-insets.left-insets.right-10, this.getHeight()-insets.top-insets.bottom-90);
		this.rpmControl.addChangeListener(this);
		this.add(this.rpmControl);


	


		JButton tmpButton;
		tmpButton = new JButton(RpmMotorControlView.STOP_TXT);
		tmpButton.setBounds(insets.left + 5, this.getHeight()-insets.top-insets.bottom-40 , getWidth()-insets.left-insets.right-10, 20);
		tmpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				RpmMotorControlView.this.motor.remote_stop();

			}

		});
		this.add(tmpButton);

		tmpButton = new JButton(RpmMotorControlView.BRAKE_TXT);
		tmpButton.setBounds(insets.left + 5, this.getHeight()-insets.top-insets.bottom-20 , getWidth()-insets.left-insets.right-10, 20);
		tmpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				RpmMotorControlView.this.motor.remote_brake();
			}

		});
		this.add(tmpButton);

	}

	@Override
	protected int getViewWidth()
	{
		return (RpmMotorControlView.width);
	}

	@Override
	protected int getViewHeight()
	{
		return (RpmMotorControlView.height);
	}

	public static ComponentView createView(RpmMotor motor)
	{
		if (motor != null)
		{
			return (new RpmMotorControlView(motor));
		} else
		{
			return (new MissingComponentView(RpmMotor.class.getName()));
		}
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent)
	{
		Object source;

		source = changeEvent.getSource();

		if (source == this.rpmControl)
		{
			float rpm;
			rpm = this.rpmControl.getValue();
			// rpm -=(RpmMotorControlView.SCALE/2);
			// rpm /=(RpmMotorControlView.SCALE/2);

			this.rpmLabel.setText(ComponentValue.toFormatedFractionString(rpm,
					2) + "rpm");
			this.motor.getRpmControlValue().setValue(rpm);
		}

	

	}

	@Override
	public void settingsChanged(RobotComponent<?, ?, ?> component)
	{

	/*	this.rmpMax = (int) this.motor.getMaxRpm();
		this.rmpMin = (int) this.motor.getMaxRpm() * -1;
		rpmControl.setMaximum((int)this.rmpMax);
		rpmControl.setMinimum((int)this.rmpMin);
		rpmControl.setValue(0);
	*/	

/*
		this.rpmLabel = new JLabel("-");
		this.rpmLabel.setBounds(insets.left + 5, insets.top + 5, 70, 20);
		this.add(this.rpmLabel);

		this.velocityControl = new JSlider(JSlider.VERTICAL, this.velocityMin,
				this.velocityMax, 0);
		this.velocityControl.setBounds(insets.left + 140, insets.top + 35, 40,
				100);
		this.velocityControl.addChangeListener(this);
		this.add(this.velocityControl);*/
	}

}
