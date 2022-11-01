package de.hska.lat.robot.component.muscle;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;

import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

public class MuscleControlView extends ComponentView implements ChangeListener,
		ActionListener, ComponentChangeNotifier, ComponentSettingsChangeNotifier
{
	private static final long serialVersionUID = 1L;

//	private static final String CMD_ACTIVE = "cmdActive";

//	private static final String STEP_STRING = "step width";

	private int position;
	private JLabel actualPosition;
	private JLabel minPos;
	private JLabel maxPos;
	private JSlider slider;

	// protected ServoControlInterface servoListener;
	protected Muscle muscle;
	protected static final int width = 280;
	protected static final int height = 80;

	public MuscleControlView(Muscle muscle)
	{
		super(muscle.getComponentName(), false);

		this.muscle = muscle;

		muscle.addSensorListener(this);
		muscle.addSetupListener(this);

		buildView();
	}

	@Override
	protected void buildView()
	{

		super.buildView();

		Insets insets = this.getBorder().getBorderInsets(this);

		slider = new JSlider();
		slider.setBounds(insets.left + 50, insets.top + 20, 182, 30);
		slider.setMaximum(100);
		slider.setMinimum(-100);
		slider.setValue(0);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);
		add(slider);

		actualPosition = new JLabel("0");
		actualPosition.setBounds(insets.left + 117, insets.top + 5, 40, 20);
		actualPosition.setHorizontalAlignment(SwingConstants.CENTER);
		add(actualPosition);

		minPos = new JLabel("-100");
		minPos.setBounds(insets.left + 5, insets.top + 30, 40, 20);
		minPos.setHorizontalAlignment(SwingConstants.CENTER);
		add(minPos);

		maxPos = new JLabel("100");
		maxPos.setBounds(insets.left + 240, insets.top + 30, 40, 20);
		maxPos.setHorizontalAlignment(SwingConstants.CENTER);
		add(maxPos);

		this.updateValues(muscle);
	}

	@Override
	protected int getViewWidth()
	{
		return (MuscleControlView.width);
	}

	@Override
	protected int getViewHeight()
	{
		return (MuscleControlView.height);
	}

	private void updatePosition(int position)
	{
		this.position = position;
		actualPosition.setText(String.valueOf(position));
	}

	protected void updateValues(Muscle muscle)
	{

		System.out.println("updateValues: " + muscle.toString());
		/*
		 * int min = (int)Radiant.convertRadiantToDegree(servo.getMinRange());
		 * int max = (int)Radiant.convertRadiantToDegree(servo.getMaxRange());
		 * 
		 * 
		 * this.minPos.setText(min+"?"); this.slider.setMinimum(min);
		 * 
		 * this.maxPos.setText(max+"?"); this.slider.setMaximum(max);
		 */
	}

	/**
	 * creates new muscle control view and link it given muscle
	 * 
	 * @param muscle
	 *            muscle to be connected with created view
	 * @return a new muscle control view
	 */

	public static ComponentView createView(Muscle muscle)
	{
		if (muscle != null)
		{
			return (new MuscleControlView(muscle));
		} else
		{
			return (new MissingComponentView(Muscle.class.getName()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{

	//	String cmd;

	//	cmd = actionEvent.getActionCommand();

		/*
		 * if (cmd.equals(CMD_ACTIVE)) { if (onCheckBox.isSelected()) {
		 * this.servo.remote_servoOn(); } else { this.servo.remote_servoOff(); }
		 * 
		 * }
		 */
		// else
		{
			super.actionPerformed(actionEvent);
		}
	}

	@Override
	public void stateChanged(ChangeEvent event)
	{
		if (event.getSource() == slider)
		{

			if (this.slider.hasFocus() == true)
			{
				if (this.position != slider.getValue())
				{
					updatePosition(slider.getValue()); //
					//servoListener.setServoPosition(-1, position);
					float tension = (float) (slider.getValue()) / 100.0f;
					System.out.println("stateChanged: " + muscle.getMetaName() + " - tension = " + tension);
					
					this.muscle.remote_setTension(tension);
				}
			}
		}

	}

	@Override
	public void settingsChanged(RobotComponent<?, ?, ?> component)
	{
		// TODO Auto-generated method stub
		
	}

}
