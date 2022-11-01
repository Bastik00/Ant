package de.hska.lat.behavior.arbiter.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.arbiter.component.servo.ServoArbiter;
import de.hska.lat.behavior.arbiter.component.servo.ServoRotate;
import de.hska.lat.behavior.arbiter.component.servo.ServoRotateTo;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.math.Radiant;
import de.hska.lat.robot.RobotSettings;
import de.hska.lat.robot.value.FloatValue;

public class ServoArbiterViewer extends ComponentArbiterViewer<ServoArbiter>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -695153242689035885L;

	
	protected JLabel servoAction;
	protected JLabel servoParameter1Value;
	protected JLabel servoParameter2Value;
	
	protected JLabel servoParameter1Name;
	protected JLabel servoParameter2Name;
	
	
	
	public ServoArbiterViewer(RobotBehavior<?> behavior, ServoArbiter arbiter, RobotSettings settings)
	{
		super(behavior, arbiter, settings);

	


		
	}

	

protected void build()
{
	
	this.setBorder(new TitledBorder("Servo: "+arbiter.getName()));
	this.setLayout(null);
	
	this.setSize(170, 100);
	
	Insets insets = this.getInsets();
	this.servoAction = new JLabel("", SwingConstants.CENTER);
	
	this.servoAction.setBounds(insets.left,insets.top,140,25);
	this.servoAction.setBorder(new LineBorder(Color.black,1));
	this.servoAction.setAlignmentX(Component.RIGHT_ALIGNMENT);
	this.add(this.servoAction);

	this.servoParameter1Name = new JLabel();
	this.servoParameter1Name.setBounds(insets.left,insets.top+22,70,25);
	this.add(this.servoParameter1Name);
	
	this.servoParameter1Value = new JLabel();
	this.servoParameter1Value.setBounds(insets.left+80,insets.top+22,50,25);
	this.add(this.servoParameter1Value);
	
	this.servoParameter2Name = new JLabel();
	this.servoParameter2Name.setBounds(insets.left,insets.top+40,70,25);
	this.add(this.servoParameter2Name);
	
	this.servoParameter2Value = new JLabel();
	this.servoParameter2Value.setBounds(insets.left+80,insets.top+40,50,25);
	this.add(this.servoParameter2Value);
	
}
	
	
public void update(ServoArbiter arbiter)
{
	ArbiterAction action;
	
	action = arbiter.getActiveAction();

	if (action!=null)
	{
		this.servoAction.setText(action.getName());
	}
	else
	{
		this.servoAction.setText("-");
		
	}
	if (action instanceof ServoRotateTo)
	{
		float value;

		servoParameter1Name.setText("destination :");
		
		value = Radiant.convertRadiantToDegree(((ServoRotateTo) action).getDestination());
		servoParameter1Value.setText(FloatValue.toFormatedFractionString(value, 2)+"°");
		
		servoParameter2Name.setText("velocity ");
		
		value = Radiant.convertRadiantToDegree(((ServoRotateTo) action).getVelocity());
		servoParameter2Value.setText(FloatValue.toFormatedFractionString(value, 2)+"°/s");
	}
	else if (action instanceof ServoRotate)
	{
		float value;

		servoParameter1Name.setText("velocity ");
		
		value = Radiant.convertRadiantToDegree(((ServoRotate) action).getVelocity());
		servoParameter1Value.setText(FloatValue.toFormatedFractionString(value, 2)+"°/s");
	}
		
	else
	{
		servoParameter1Name.setText("");
		servoParameter1Value.setText("");
		
		servoParameter2Name.setText("");
		servoParameter2Value.setText("");
	}
	
	
	this.invalidate();
	this.repaint();

}


@Override
public void arbiterChanged(Arbiter arbiter)
{
	 update((ServoArbiter) arbiter);
}
	
	
}
