package de.hska.lat.robot.component.touchSensor;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.analogDetector.AnalogDetector;
import de.hska.lat.robot.component.detector.Detector;
import de.hska.lat.robot.component.touchSensor.TouchSensorChangeNotifier;

import de.hska.lat.robot.component.touchSensor.TouchSensor;
import de.hska.lat.robot.component.view.ComponentView;


public class TouchSensorSetupView  extends ComponentView implements MouseListener, ActionListener, ChangeListener, TouchSensorChangeNotifier, ComponentSettingsChangeNotifier {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String STRING_MIN = "Thres:";
	private static final String STRING_MAX = "Value:";

	JSpinner treshold;

	JProgressBar presureBar;
	JSlider tresholdPoint;
	
	JLabel maxValue;
	JLabel minValue;
	JCheckBox inverse;
	
	
	private SpinnerNumberModel tresholdSpinerModel;
	
	private static String cmd_set="cmdSet";
	private static String cmd_get="cmdGet";
	private static String cmd_save="cmdSave";
	private static String cmd_load="cmdLoad";
	
	private static String STRING_GET = "get";
	private static String STRING_SET = "set";
	private static String STRING_LOAD = "load";
	private static String STRING_SAVE = "save";
	
	TouchSensor controlListener;
	
public TouchSensorSetupView(String name, int range,TouchSensor controlListener) 
{
	super(name, false);
	buildView(range);
	this.setSize(210, 100);
	this.controlListener=controlListener;
}



private void buildView(int range)
{
	JLabel tmpLabel;
	JButton tmpButton;
	
	tmpLabel= new JLabel(STRING_MIN);
	tmpLabel.setBounds(10, 45, 40, 15);
	this.add(tmpLabel);
	

	this.minValue = new JLabel();
	this.minValue.setBounds(50,45,100,15);
	this.add(this.minValue);
	
	
	tmpLabel= new JLabel(STRING_MAX);
	tmpLabel.setBounds(100, 45, 40, 15);
	this.add(tmpLabel);
	
	this.maxValue = new JLabel();
	this.maxValue.setBounds(140,45,100,15);
	this.add(this.maxValue);
	
	
	this.inverse = new JCheckBox("I");
	this.inverse.setBounds(190,45,100,15);
	this.add(this.inverse);
	
	
	this.presureBar = new JProgressBar(0,range);
	this.presureBar.setStringPainted(true);
	this.presureBar.setForeground(Color.GREEN);
	this.presureBar.setBackground(Color.RED);
	this.presureBar.setBounds(10,25,120,15);

	this.add(this.presureBar);
	
	
	this.tresholdPoint = new JSlider(0,20);
	this.tresholdPoint.setEnabled(true);
	this.tresholdPoint.setBounds(10,15,120,10);
	this.tresholdPoint.setPaintTrack(false);
	this.tresholdPoint.addChangeListener(this);
	this.add(this.tresholdPoint);

	
	
	
	this.tresholdSpinerModel = new SpinnerNumberModel(0, 0,20,1 );
	this.treshold= new JSpinner(this.tresholdSpinerModel);
	this.treshold.setBounds(145,20,60,20);
	this.treshold.addChangeListener(this);
	this.add(this.treshold);
	
	

	tmpButton = new JButton(STRING_SET);
	tmpButton.setBounds(5, 65, 50, 22);
	tmpButton.setActionCommand(cmd_set);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_GET);
	tmpButton.setBounds(55, 65, 50, 22);
	tmpButton.setActionCommand(cmd_get);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_SAVE);
	tmpButton.setBounds(105, 65, 50, 22);
	tmpButton.setActionCommand(cmd_save);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	
	tmpButton = new JButton(STRING_LOAD);
	tmpButton.setBounds(155, 65, 50, 22);
	tmpButton.setActionCommand(cmd_load);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	

}










public void actionPerformed(ActionEvent actionEvent) 
{

	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	if (cmd.equals(cmd_set))
	{
	//	this.controlListener.setToutchSensorSettings(-1, 0, this.inverse.isSelected(), tresholdSpinerModel.getNumber().intValue());
	}
	else if (cmd.equals(cmd_get))
	{
		
		this.controlListener.remote_getSettings();

	}
	else if (cmd.equals(cmd_save))
	{
		this.controlListener.remote_saveDefaults();
	}
	else if (cmd.equals(cmd_load))
	{
		this.controlListener.remote_loadDefaults();
		this.controlListener.remote_getSettings();
	}	
	
	

	
}


@Override
public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void stateChanged(ChangeEvent event) 
{
	
	if (event.getSource()==this.treshold )
	{
		Number number;
		this.tresholdSpinerModel.getValue();
		number=this.tresholdSpinerModel.getNumber().intValue();
		if (number!=null)
		{
			if (this.tresholdPoint.hasFocus()==false)
			{
				this.tresholdPoint.setValue(number.intValue());
			}
		}
	//		((AnalogSensor) data).setTreshold(number.intValue());

	}
	else if(event.getSource()==this.tresholdPoint)
	{
		int value=this.tresholdPoint.getValue();
		
		if (this.treshold.hasFocus()==false)
		{
			this.treshold.setValue(value);
		}
		
	//	((AnalogSensor) data).setTreshold(tresholdPoint.getValue());
	//	tresholdPoint.setValue(((PresureSensor) data).getTreshold());
		//tresholdSpinerModel.set
	
	}
}




@Override
public void statusChanged(Detector<?, ?, ?> sensor)
{
	// TODO Auto-generated method stub
	
}



@Override
public void valueChanged(AnalogDetector<?,?,?> sensor)
{
	int value;
	
	value = sensor.getRawValue();
	
	this.presureBar.setString(String.valueOf(value));
	this.presureBar.setValue(value);
}





@Override
public void settingsChanged(RobotComponent<?, ?, ?> sensor)
{
/*	this.tresholdPoint.setValue(5);
	this.treshold.setValue(((TouchSensor)sensor).getDetectionFactor());
	this.inverse.setSelected(sensor.isInverse());
	this.minValue.setText(String.valueOf(sensor.getTreshold()));
	this.maxValue.setText(String.valueOf(sensor.getRawValue()));
	*/
}



@Override
public void statisticChanged(AnalogDetector<?,  ?, ?> sensor)
{
	this.minValue.setText(String.valueOf(sensor.getStatisticalMin()));
	this.maxValue.setText(String.valueOf(sensor.getStatisticalMax()));
}



@Override
public void ProximityValueChanged(TouchSensor sensor) {
	// TODO Auto-generated method stub
	
}





}
