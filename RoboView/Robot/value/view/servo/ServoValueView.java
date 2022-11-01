package de.hska.lat.robot.value.view.servo;




import java.awt.Color;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.actor.servo.ServoDestinationValue;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.servo.ServoAngleValue;
import de.hska.lat.robot.value.view.ValueView;
import de.hska.lat.robot.value.view.MissingValueView;



public class ServoValueView extends ValueView<ServoAngleValue>  implements ComponentValueChangeListener
{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5165802217514053434L;
	
	
	private JLabel positionLabel;
	private JLabel destinationLabel;
	private JCheckBox atMinFlag;
	private JCheckBox atMaxFlag;
	private JCheckBox isOnFlag;
	private JCheckBox activeFlag;
	private JCheckBox inverseFlag;
	private JCheckBox stallFlag;


	protected static final int width = 160;
	protected static final int height = 110;
	
	
public ServoValueView(ServoAngleValue value, ServoDestinationValue dest, boolean embedded) 
{
	super(value, embedded);
	
	value.addListener(this);
	
	dest.addListener(this);
	
	buildView();


}




@Override
protected void buildView() 
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel tmpLabel;
	
	tmpLabel=new JLabel("position");
	tmpLabel.setBounds(insets.left+5,insets.top+0,80,20);
	this.add(tmpLabel);
	
	positionLabel=new JLabel("--");
	positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
	positionLabel.setBorder(new LineBorder(Color.black));
	positionLabel.setBounds(insets.left+85,insets.top+0,60,20);
	this.add(positionLabel);
	
	tmpLabel=new JLabel("destination");
	tmpLabel.setBounds(insets.left+5,insets.top+25,80,20);
	this.add(tmpLabel);
	
	destinationLabel=new JLabel("--");
	destinationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	destinationLabel.setBorder(new LineBorder(Color.black));
	destinationLabel.setBounds(insets.left+85,insets.top+25,60,20);
	this.add(destinationLabel);
	
	
	
	this.atMinFlag= new JCheckBox("min");
	this.atMinFlag.setBounds(insets.left+5, insets.top+50,60,20);
	this.atMinFlag.setEnabled(false);
	this.add(this.atMinFlag);
	
	this.atMaxFlag= new JCheckBox("max");
	this.atMaxFlag.setBounds(insets.left+85, insets.top+50,60,20);
	this.atMaxFlag.setEnabled(false);
	this.add(this.atMaxFlag);

	
	
	activeFlag= new JCheckBox("active");
	activeFlag.setBounds(insets.left+5, insets.top+70,60,20);
	activeFlag.setEnabled(false);
	this.add(activeFlag);
	
	
	isOnFlag= new JCheckBox("on");
	isOnFlag.setBounds(insets.left+85, insets.top+70,60,20);
	isOnFlag.setEnabled(false);
	this.add(isOnFlag);

	
	
	inverseFlag= new JCheckBox("inverse");
	inverseFlag.setBounds(insets.left+5,  insets.top+90,70,20);
	inverseFlag.setEnabled(false);
	this.add(inverseFlag);
	
	
	stallFlag= new JCheckBox("stall");
	stallFlag.setBounds(insets.left+85,  insets.top+90,60,20);
	stallFlag.setEnabled(false);
	this.add(stallFlag);
	
}



@Override
protected int getViewWidth()
{
	return(ServoValueView.width);
}

@Override
protected int getViewHeight()
{
	return(ServoValueView.height);
}



/**
 * creates new servo data view and link it given servo 
 * @param servo servo to be connected with created view
 * @return a new servo data view
 */

public static ValueView<?> createView(ServoAngleValue value, ServoDestinationValue dest, boolean embedded)
{

	if (value!=null)
	{
		return(new ServoValueView(value, dest, embedded));
	}
	else
	{
		return(new MissingValueView(ServoAngleValue.class.getName(), false));
	}
}








@Override
public void valueChanged(ComponentValue<?> servoValue)
{
	if (servoValue.getClass().getSimpleName().equals("ServoAngleValue"))
	{
		
		positionLabel.setText(FloatValue.toFormatedFractionString(((ServoAngleValue)servoValue).getPositionAsDegree(), 1)+"°");
	}

	if (servoValue.getClass().getSimpleName().equals("ServoDestinationValue"))
	{
		destinationLabel.setText(FloatValue.toFormatedFractionString(((ServoDestinationValue)servoValue).getPositionAsDegree(), 1 )+"°");
	}
	
	
	
	
	this.atMinFlag.setSelected(value.isAtMin());
	this.atMaxFlag.setSelected(value.isAtMax());
	
	this.activeFlag.setSelected(value.isActive());
	this.isOnFlag.setSelected(value.isOn());
	
	this.inverseFlag.setSelected(value.isInverse());
	this.stallFlag.setSelected(value.isStalling());
}





}
