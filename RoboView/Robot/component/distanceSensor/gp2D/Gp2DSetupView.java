package de.hska.lat.robot.component.distanceSensor.gp2D;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.distance.DistanceChangeNotifier;
import de.hska.lat.robot.component.generic.distance.DistanceSensor;

import de.hska.lat.robot.component.view.ComponentView;



public class Gp2DSetupView extends ComponentView implements ActionListener, FocusListener,DistanceChangeNotifier,ComponentSettingsChangeNotifier{

	

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static String cmd_set="cmdSet";
	private static String cmd_get="cmdGet";
	private static String cmd_save="cmdSave";
	private static String cmd_load="cmdLoad";
	
	private static String STRING_GET = "get";
	private static String STRING_SET = "set";
	private static String STRING_LOAD = "load";
	private static String STRING_SAVE = "save";
	
	
	private JLabel value;
	private JLabel distance;	
	private JTextField gradient;
	private JTextField offset;
	
	private JSpinner maxDistance;
	
	private DistanceNumberModel distanceNumberModel;
	


	
	protected Gp2D sensor;
	
	

	
	
	
	
public Gp2DSetupView(Gp2D sensor) 
{
	super(sensor.getComponentName(), false);
	
	this.sensor=sensor;
	
	sensor.addSensorListener(this);
	sensor.addSetupListener(this);
	
	buildView(sensor);
	this.setSize(300, 120);
}



private void buildView(Gp2D sensor)
{
	JLabel tmpLabel;
	JButton tmpButton;
	
	tmpLabel = new JLabel("value");
	tmpLabel.setBounds(10,12,60,20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	this.value = new JLabel();
	this.value.setBounds(10,35,60,25);
	this.value.setHorizontalAlignment(SwingConstants.CENTER);
	this.value.setBorder(new LineBorder(Color.BLACK));
	this.add(this.value);
	
	tmpLabel = new JLabel("distance");
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	tmpLabel.setBounds(80,12,60,20);
	this.add(tmpLabel);
	
	this.distance = new JLabel();
	this.distance.setBounds(80,35,60,25);
	this.distance.setHorizontalAlignment(SwingConstants.CENTER);
	this.distance.setBorder(new LineBorder(Color.BLACK));
	this.add(this.distance);
	
	
	tmpLabel = new JLabel("max range");
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	tmpLabel.setBounds(150,12,100,20);
	this.add(tmpLabel);
	
	
	this.distanceNumberModel=new  DistanceNumberModel(200); 
	
	this.maxDistance = new JSpinner(this.distanceNumberModel);
	this.maxDistance.setBounds(150,35,100,25);
	this.add(this.maxDistance);
	
	tmpButton = new JButton(STRING_SET);
	tmpButton.setBounds(10, 67, 50, 22);
	tmpButton.setActionCommand(cmd_set);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_GET);
	tmpButton.setBounds(60, 67, 50, 22);
	tmpButton.setActionCommand(cmd_get);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_SAVE);
	tmpButton.setBounds(10, 88, 50, 22);
	tmpButton.setActionCommand(cmd_save);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	
	tmpButton = new JButton(STRING_LOAD);
	tmpButton.setBounds(60, 88, 50, 22);
	tmpButton.setActionCommand(cmd_load);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	
	
	
	tmpLabel = new JLabel("gradient");	
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	tmpLabel.setBounds(120,68,80,20);
	this.add(tmpLabel);


	tmpLabel = new JLabel("offset");
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	tmpLabel.setBounds(210,68,80,20);
	this.add(tmpLabel);
	

	gradient = new JTextField();
	gradient.setBounds(120, 90, 80, 20);
	gradient.setHorizontalAlignment(SwingConstants.CENTER);
	gradient.addFocusListener(this);
	//gradient.setEditable(false);
	gradient.setBorder(new LineBorder(Color.BLACK));
	this.add(gradient);
	
	offset = new JTextField();
	offset.setBounds(210, 88, 80, 22);
	offset.setHorizontalAlignment(SwingConstants.CENTER);
	offset.addFocusListener(this);
	offset.setBorder(new LineBorder(Color.BLACK));
	this.add(offset);
	
	this.setValues(sensor);
	
}


protected void setValues(Gp2D sensor)
{
	this.distanceNumberModel.setMaxDistance(sensor.getDistanceRange());
	this.gradient.setText(Integer.toString(sensor.getGradient()));
	this.offset.setText(Integer.toString(sensor.getOffset()));
}





@Override
public void actionPerformed(ActionEvent actionEvent) 
{

	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	if (cmd.equals(cmd_set))
	{
		int gradient;
		int offset;
		int maxDistance;
		
		gradient=Integer.valueOf(this.gradient.getText());
		offset=Integer.valueOf(this.offset.getText());
		maxDistance=(int)distanceNumberModel.getDistance()*10;
		this.sensor.remote_setSettings(gradient, offset,maxDistance);
	}
	else if (cmd.equals(cmd_get))
	{
		this.sensor.remote_getSettings();
	}
	else if (cmd.equals(cmd_save))
	{
		this.sensor.remote_saveDefaults();
	}
	else if (cmd.equals(cmd_load))
	{
		this.sensor.remote_loadDefaults();
		this.sensor.remote_getSettings();
	}	
	
}






@Override
public void focusGained(FocusEvent focusEvent) 
{
/*	if (focusEvent.getSource()==offset)
	{
		offsetLock=true;
	}
	else if (focusEvent.getSource()==gradient)
	{
		gradientLock=true;
	}
	else if (focusEvent.getSource()==maxDistance)
	{
		maxDistanceLock=true;
	}
	*/
}

@Override
public void focusLost(FocusEvent focusEvent) 
{
	// TODO Auto-generated method stub
	
}


/*
@Override
public void distanceChanged(DistanceSensor<?, ?, ?> sensor)
{
	this.distance.setText(sensor.getCentimeters()+"cm");
}
*/


/*
@Override
public void gp2GradientChanged(SharpGp2 sensor) 
{
	if (gradientLock==false)
	{
		this.gradient.setText(Integer.toString(sensor.getGradient()));
	}
	
}*/
/*
@Override
public void gp2OffsetChanged(SharpGp2 sensor) 
{
	if (offsetLock==false)
	{
		this.offset.setText(Integer.toString(sensor.getOffset()));
	}
	
}
*/

/*
@Override
public void setupChanged(AnalogDetector<?, ?,  ?> sensor)
{
	// TODO Auto-generated method stub
	
}


*/




@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{

	this.setValues(sensor);
}



@Override
public void distanceChanged(DistanceSensor<?, ?> sensor)
{
	// TODO Auto-generated method stub
	
}








	
}
