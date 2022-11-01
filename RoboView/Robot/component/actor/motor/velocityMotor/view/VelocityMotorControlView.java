package de.hska.lat.robot.component.actor.motor.velocityMotor.view;

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
import de.hska.lat.robot.component.actor.generic.motor.velocityMotor.VelocityMotor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.ComponentValue;

public class VelocityMotorControlView extends ComponentView implements
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

	protected static final int width = 200;
	protected static final int height = 160;

	protected VelocityMotor motor;

	protected JSlider velocityControl;


	protected JLabel velocityLabel;

	protected JButton stopButton;
	protected JCheckBox on;

	protected int rmpMax;
	protected int rmpMin;

	protected int velocityMax;
	protected int velocityMin;

	public VelocityMotorControlView(VelocityMotor motor)
	{
		super(motor.getComponentName(), false);

		this.motor = motor;
		this.motor.addSetupListener(this);
		this.rmpMax = 150;
		this.rmpMin = -150;
		this.velocityMax = 100;
		this.velocityMin = -100;
		buildView();
	}

	@Override
	protected void buildView()
	{

		super.buildView();

		Insets insets = this.getBorder().getBorderInsets(this);



		this.velocityControl = new JSlider(JSlider.VERTICAL, this.velocityMin,
				this.velocityMax, 0);
		this.velocityControl.setBounds(insets.left + 140, insets.top + 35, 40,
				100);
		this.velocityControl.addChangeListener(this);
		this.add(this.velocityControl);

		this.velocityLabel = new JLabel("-");
		this.velocityLabel.setBounds(insets.left + 145, insets.top + 5, 70, 20);
		this.add(this.velocityLabel);

		this.on = new JCheckBox(ON_TEXT);
		this.on.setBounds(insets.left + 55, insets.top + 5, 70, 20);
		this.add(this.on);

		JButton tmpButton;
		tmpButton = new JButton(VelocityMotorControlView.STOP_TXT);
		tmpButton.setBounds(insets.left + 55, insets.top + 35, 70, 20);
		tmpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				VelocityMotorControlView.this.motor.remote_stop();

			}

		});
		this.add(tmpButton);

		tmpButton = new JButton(VelocityMotorControlView.BRAKE_TXT);
		tmpButton.setBounds(insets.left + 55, insets.top + 65, 70, 20);
		tmpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				VelocityMotorControlView.this.motor.remote_brake();
			}

		});
		this.add(tmpButton);

	}

	@Override
	protected int getViewWidth()
	{
		return (VelocityMotorControlView.width);
	}

	@Override
	protected int getViewHeight()
	{
		return (VelocityMotorControlView.height);
	}

	public static ComponentView createView(VelocityMotor motor)
	{
		if (motor != null)
		{
			return (new VelocityMotorControlView(motor));
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

		if (source == this.velocityControl)
		{
			float mps;
			mps = this.velocityControl.getValue();

			this.velocityLabel.setText(ComponentValue.toFormatedFractionString(
					mps, 2) + "m/s");
			this.motor.getVelocityControlValue().setValue(mps);
		}

	}

	@Override
	public void settingsChanged(RobotComponent<?, ?, ?> component)
	{

		this.rmpMax = (int) this.motor.getMaxRpm();
		this.rmpMin = (int) this.motor.getMaxRpm() * -1;
	//	rpmControl.setMaximum(this.rmpMax);
	//	rpmControl.setMinimum(this.rmpMin);
	//	rpmControl.setValue(0);
		

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
