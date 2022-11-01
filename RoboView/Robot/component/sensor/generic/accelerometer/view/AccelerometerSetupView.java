package de.hska.lat.robot.component.generic.accelerometer.view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.math.Radiant;



import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.accelerometer.Accelerometer;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerChangeNotifier;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerSetupChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.FloatValue;



public class AccelerometerSetupView extends ComponentView implements AccelerometerChangeNotifier, ChangeListener, ActionListener, AccelerometerSetupChangeNotifier
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JLabel [] acceleration = new JLabel[3];
	private JLabel [] rawValue = new JLabel[3];
	private JLabel [] angle = new JLabel[3];
	private  JSpinner [] offset = new JSpinner [3];
	private  SpinnerNumberModel [] offsetSpinerModel = new SpinnerNumberModel[3];
	
	private JLabel stable;
	
	
	private static final String cmd_set="cmdSet";
	private static final String cmd_get="cmdGet";
	private static final String cmd_save="cmdSave";
	private static final String cmd_load="cmdLoad";
	
	private static final String STRING_GET = "get";
	private static final String STRING_SET = "set";
	private static final String STRING_LOAD = "load";
	private static final String STRING_SAVE = "save";
	private static final String STABLE_TEXT = "stable";
	
	
	
	protected Accelerometer<?,?> acc;
	
public AccelerometerSetupView(Accelerometer<?,?> sensor) 
{
	super(sensor.getComponentName(), false);

	this.acc = sensor;
	
	
	this.acc.addSensorListener(this);
	//this.acc.addSetupListener(this);
	
	
	
	//this.controlListener =  sensor;
	buildView();
	this.setSize(290, 160);
}


//2012.02.13
protected void buildView()
{
	JLabel tmpLabel;
	JButton tmpButton;
	
	tmpLabel= new JLabel("x");
	tmpLabel.setBounds(10, 35, 50, 20);
	this.add(tmpLabel);

	tmpLabel= new JLabel("y");
	tmpLabel.setBounds(10, 55, 50, 20);
	this.add(tmpLabel);
	
	tmpLabel= new JLabel("z");
	tmpLabel.setBounds(10, 75, 50, 20);
	this.add(tmpLabel);
	
	
	tmpLabel= new JLabel("raw");
	tmpLabel.setBounds(40, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);

	tmpLabel= new JLabel("G");
	tmpLabel.setBounds(100, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	tmpLabel= new JLabel("°");
	tmpLabel.setBounds(160, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	tmpLabel= new JLabel("offset");
	tmpLabel.setBounds(220, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	
	
	/****************** x Axis *********/
	

	this.rawValue[Accelerometer.X_AXIS] = new JLabel("");
	this.rawValue[Accelerometer.X_AXIS] .setBounds(40, 35, 50, 20);
	this.rawValue[Accelerometer.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.rawValue[Accelerometer.X_AXIS] );
	
	
	this.acceleration[Accelerometer.X_AXIS] = new JLabel("");
	this.acceleration[Accelerometer.X_AXIS] .setBounds(100, 35, 50, 20);
	this.acceleration[Accelerometer.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.acceleration[Accelerometer.X_AXIS] );
	
	
	this.angle[Accelerometer.X_AXIS] = new JLabel("");
	this.angle[Accelerometer.X_AXIS] .setBounds(160, 35, 50, 20);
	this.angle[Accelerometer.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.X_AXIS] );

	
	offsetSpinerModel[Accelerometer.X_AXIS] = new SpinnerNumberModel(0, -32000,32000,1 );
	this.offset[Accelerometer.X_AXIS]= new JSpinner(offsetSpinerModel[Accelerometer.X_AXIS]);
	this.offset[Accelerometer.X_AXIS].setBounds(220, 35, 50, 20);
	this.offset[Accelerometer.X_AXIS].addChangeListener(this);
	this.add(this.offset[Accelerometer.X_AXIS]);
	
	
	/****************** y Axis *********/

	this.rawValue[Accelerometer.Y_AXIS] = new JLabel("");
	this.rawValue[Accelerometer.Y_AXIS].setBounds(40, 55, 50, 20);
	this.rawValue[Accelerometer.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.rawValue[Accelerometer.Y_AXIS]);
	
	this.acceleration[Accelerometer.Y_AXIS] = new JLabel("");
	this.acceleration[Accelerometer.Y_AXIS].setBounds(100, 55, 50, 20);
	this.acceleration[Accelerometer.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.acceleration[Accelerometer.Y_AXIS]);
	
	this.angle[Accelerometer.Y_AXIS]= new JLabel("");
	this.angle[Accelerometer.Y_AXIS].setBounds(160, 55, 50, 20);
	this.angle[Accelerometer.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.Y_AXIS]);

	offsetSpinerModel[Accelerometer.Y_AXIS] = new SpinnerNumberModel(0, -32000,32000,1 );
	this.offset[Accelerometer.Y_AXIS]= new JSpinner(offsetSpinerModel[Accelerometer.Y_AXIS]);
	this.offset[Accelerometer.Y_AXIS].setBounds(220, 55, 50, 20);
	this.offset[Accelerometer.Y_AXIS].addChangeListener(this);
	this.add(this.offset[Accelerometer.Y_AXIS]);
	
	/****************** z Axis *********/

	this.rawValue[Accelerometer.Z_AXIS] = new JLabel("");
	this.rawValue[Accelerometer.Z_AXIS].setBounds(40, 75, 50, 20);
	this.rawValue[Accelerometer.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.rawValue[Accelerometer.Z_AXIS]);

	this.acceleration[Accelerometer.Z_AXIS]= new JLabel("");
	this.acceleration[Accelerometer.Z_AXIS].setBounds(100, 75, 50, 20);
	this.acceleration[Accelerometer.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.acceleration[Accelerometer.Z_AXIS]);
	
	this.angle[Accelerometer.Z_AXIS] = new JLabel("");
	this.angle[Accelerometer.Z_AXIS].setBounds(160, 75, 50, 20);
	this.angle[Accelerometer.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.Z_AXIS]);
	
	offsetSpinerModel[Accelerometer.Z_AXIS] = new SpinnerNumberModel(0, -32000,32000,1 );
	this.offset[Accelerometer.Z_AXIS]= new JSpinner(offsetSpinerModel[Accelerometer.Z_AXIS]);
	this.offset[Accelerometer.Z_AXIS].setBounds(220, 75, 50, 20);
	this.offset[Accelerometer.Z_AXIS].addChangeListener(this);
	this.add(this.offset[Accelerometer.Z_AXIS]);
	
	
	this.stable = new JLabel();
	this.stable.setBounds(5, 100, 50, 22);
	this.stable.setBorder(new LineBorder(Color.GRAY));
	this.stable.setAlignmentX(CENTER_ALIGNMENT);
	this.add(this.stable);
	
	
	
	
	
	
	tmpButton = new JButton(STRING_SET);
	tmpButton.setBounds(5, 125, 50, 22);
	tmpButton.setActionCommand(cmd_set);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_GET);
	tmpButton.setBounds(55, 125, 50, 22);
	tmpButton.setActionCommand(cmd_get);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);

	tmpButton = new JButton(STRING_SAVE);
	tmpButton.setBounds(105, 125, 50, 22);
	tmpButton.setActionCommand(cmd_save);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	
	tmpButton = new JButton(STRING_LOAD);
	tmpButton.setBounds(155, 125, 50, 22);
	tmpButton.setActionCommand(cmd_load);
	tmpButton.addActionListener(this);
	tmpButton.setBorder(new LineBorder(Color.GRAY));
	add(tmpButton);
	
}




public static ComponentView createView(Accelerometer<?,?> sensor)
{
	 
	if (sensor!=null)
	{
		return(new AccelerometerSetupView(sensor));
	}
	else
	{
		return(new MissingComponentView(Accelerometer.class.getName()));
	}
}


@Override
public void accValueChanged(Accelerometer<?,?> acc)
{
	float [] acceleration;
	float [] heading;
	
	acceleration = acc.getAcceleration();
	heading = acc.getHeading();
	
	
//	this.rawValue[Acc.X_AXIS].setText(String.valueOf(acc.getRawValue(Acc.AccAxis_e.X)));
	
	this.acceleration[Accelerometer.X_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.X_AXIS],3));
	this.angle[Accelerometer.X_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.X_AXIS]),2)+"°");
	
//	this.rawValue[Acc.Y_AXIS].setText(String.valueOf(acc.getRawValue(Acc.AccAxis_e.Y)));
	this.acceleration[Accelerometer.Y_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.Y_AXIS],3));
	this.angle[Accelerometer.Y_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.Y_AXIS]),2)+"°");

//	this.rawValue[Acc.Z_AXIS].setText(String.valueOf(acc.getRawValue(Acc.AccAxis_e.Z)));
	this.acceleration[Accelerometer.Z_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.Z_AXIS],3));
	this.angle[Accelerometer.Z_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.Z_AXIS]),2)+"°");
	
	
	
	
	
	

	if (acc.isStable())
	{
		this.stable.setText(STABLE_TEXT);
	}
	else
	{
		this.stable.setText("");
	}
	
}


@Override
public void stateChanged(ChangeEvent arg0)
{
	// TODO Auto-generated method stub
	
}


@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String cmd;
	
	cmd=actionEvent.getActionCommand();
	
	if (cmd.equals(cmd_set))
	{
		int offsets[] = new int[3];
		int index;
		
		for (index=0;index<3;index++)
		{
			offsets[index]=this.offsetSpinerModel[index].getNumber().intValue();
		}
	//	acc.setAccSettings(offsets[0], offsets[1], offsets[2]);
		
		
		
		try
		{
			Thread.sleep(10);
			this.acc.remote_getSettings();
		}
		catch (InterruptedException e)	{}
		
		
		
	}
	
	
	else if (cmd.equals(cmd_get))
	{
		this.acc.remote_getSettings();
	}
	else if (cmd.equals(cmd_save))
	{
		this.acc.remote_saveDefaults();
	}
	else if (cmd.equals(cmd_load))
	{
		this.acc.remote_loadDefaults();
		this.acc.remote_getSettings();
		
	}	
	
	
	
}


@Override
public void offsetChanged(Accelerometer<?,?> acc)
{
	// TODO Auto-generated method stub
	
}


@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}

}