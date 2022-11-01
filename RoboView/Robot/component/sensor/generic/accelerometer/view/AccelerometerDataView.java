package de.hska.lat.robot.component.generic.accelerometer.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.hska.lat.math.Radiant;


import de.hska.lat.robot.component.generic.accelerometer.Accelerometer;
import de.hska.lat.robot.component.generic.accelerometer.AccelerometerChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.FloatValue;



public class AccelerometerDataView extends SensorDataView<Accelerometer<?,?>> implements AccelerometerChangeNotifier
{

	private static final long serialVersionUID = 1278095290232532315L;
	private JLabel [] value = new JLabel[3];
	private JLabel [] angle = new JLabel[3];
	
	
	protected static final int width = 160;
	protected static final int height = 80;
	
public AccelerometerDataView(Accelerometer<?,?> acc) 
{
	super(acc);

	acc.addSensorListener(this);
	
	buildView();
}

@Override
protected void buildView()
{
	
	super.buildView();

	JLabel tmpLabel;
	
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	tmpLabel= new JLabel("G");
	tmpLabel.setBounds(40, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	tmpLabel= new JLabel("�");
	tmpLabel.setBounds(100, 15, 50, 20);
	tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(tmpLabel);
	
	
	
	tmpLabel= new JLabel("x");
	tmpLabel.setBounds(insets.left+5, insets.top+5, 40, 20);
	this.add(tmpLabel);

	this.value[Accelerometer.X_AXIS] = new JLabel("");
	this.value[Accelerometer.X_AXIS] .setBounds(40, 35, 50, 20);
	this.value[Accelerometer.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.value[Accelerometer.X_AXIS] );
	
	this.angle[Accelerometer.X_AXIS] = new JLabel("");
	this.angle[Accelerometer.X_AXIS] .setBounds(100, 35, 50, 20);
	this.angle[Accelerometer.X_AXIS] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.X_AXIS] );

	
	
	
	tmpLabel= new JLabel("y");
	tmpLabel.setBounds(insets.left+5, insets.top+30, 40, 20);
	this.add(tmpLabel);
	
	this.value[Accelerometer.Y_AXIS] = new JLabel("");
	this.value[Accelerometer.Y_AXIS].setBounds(40, 55, 50, 20);
	this.value[Accelerometer.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.value[Accelerometer.Y_AXIS]);
	
	this.angle[Accelerometer.Y_AXIS]= new JLabel("");
	this.angle[Accelerometer.Y_AXIS].setBounds(100, 55, 50, 20);
	this.angle[Accelerometer.Y_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.Y_AXIS]);

	
	tmpLabel= new JLabel("z");
	tmpLabel.setBounds(insets.left+5, insets.top+55, 40, 20);
	this.add(tmpLabel);
	
	this.value[Accelerometer.Z_AXIS]= new JLabel("");
	this.value[Accelerometer.Z_AXIS].setBounds(40, 75, 50, 20);
	this.value[Accelerometer.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.value[Accelerometer.Z_AXIS]);
	
	this.angle[Accelerometer.Z_AXIS] = new JLabel("");
	this.angle[Accelerometer.Z_AXIS].setBounds(100, 75, 50, 20);
	this.angle[Accelerometer.Z_AXIS].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Accelerometer.Z_AXIS]);
}


@Override
protected int getViewWidth()
{
	return(AccelerometerDataView.width);
}

@Override
protected int getViewHeight()
{
	return(AccelerometerDataView.height);
}


@Override
public void accValueChanged(Accelerometer<?,?> acc)
{
	float [] acceleration;
	float [] heading;
	
	acceleration = acc.getAcceleration();
	heading = acc.getHeading();
	
	this.value[Accelerometer.X_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.X_AXIS],3));
	this.angle[Accelerometer.X_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.X_AXIS]),2)+"�");
	
	this.value[Accelerometer.Y_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.Y_AXIS],3));
	this.angle[Accelerometer.Y_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.Y_AXIS]),2)+"�");
	
	this.value[Accelerometer.Z_AXIS].setText(FloatValue.toFormatedFractionString(acceleration[Accelerometer.Z_AXIS],3));
	this.angle[Accelerometer.Z_AXIS] .setText(FloatValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading[Accelerometer.Z_AXIS]),2)+"�");
	

}


public static ComponentView createView(Accelerometer<?,?> sensor)
{
	 
	if (sensor!=null)
	{
		return(new AccelerometerDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Accelerometer.class.getName()));
	}
}




}