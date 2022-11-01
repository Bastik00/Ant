package de.hska.lat.robot.component.openServo;




import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;



public class OpenServoDataView extends ComponentView  implements ServoChangeNotifier{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel positionLabel;
	private JCheckBox atMinFlag;
	private JCheckBox atMaxFlag;
	private JCheckBox isOnFlag;
	private JCheckBox activeFlag;
	private JCheckBox inverseFlag;
	private JCheckBox stallFlag;


	
public OpenServoDataView(String name) 
{
	super(name, false);
	
	buildView();
	this.setSize(160, 110);

}




@Override
protected void buildView() 
{
	JLabel tmpLabel;
	
	tmpLabel=new JLabel("position");
	tmpLabel.setBounds(10,20,50,20);
	this.add(tmpLabel);
	
	positionLabel=new JLabel("--");
	positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
	positionLabel.setBorder(new LineBorder(Color.black));
	positionLabel.setBounds(60,20,40,20);
	this.add(positionLabel);
	
	
	
	atMinFlag= new JCheckBox("min");
	atMinFlag.setBounds(10, 40,60,20);
	atMinFlag.setEnabled(false);
	this.add(atMinFlag);
	
	atMaxFlag= new JCheckBox("max");
	atMaxFlag.setBounds(90, 40,60,20);
	atMaxFlag.setEnabled(false);
	this.add(atMaxFlag);

	
	activeFlag= new JCheckBox("active");
	activeFlag.setBounds(10, 60,60,20);
	activeFlag.setEnabled(false);
	this.add(activeFlag);
	
	
	isOnFlag= new JCheckBox("on");
	isOnFlag.setBounds(90, 60,60,20);
	isOnFlag.setEnabled(false);
	this.add(isOnFlag);

	
	inverseFlag= new JCheckBox("inverse");
	inverseFlag.setBounds(10, 80,70,20);
	inverseFlag.setEnabled(false);
	this.add(inverseFlag);
	
	
	stallFlag= new JCheckBox("stall");
	stallFlag.setBounds(90, 80,60,20);
	stallFlag.setEnabled(false);
	this.add(stallFlag);
	
}




@Override
public void servoPositionChanged(Servo servo)
{
	positionLabel.setText(String.valueOf(servo.getAngleValue()));
}








@Override
public void isActive(Servo servo) 
{
	activeFlag.setSelected(servo.isActive());

}




@Override
public void isStalling(Servo servo) 
{
	stallFlag.setSelected(servo.isStalling());
	
}





@Override
public void isOn(Servo servo) 
{
	isOnFlag.setSelected(servo.isOn());
	
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
