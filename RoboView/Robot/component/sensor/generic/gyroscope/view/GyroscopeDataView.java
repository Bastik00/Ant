package de.hska.lat.robot.component.generic.gyroscope.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.generic.gyroscope.Gyroscope;
import de.hska.lat.robot.component.generic.gyroscope.GyroscopeChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;


import de.hska.lat.robot.value.FloatValue;



public class GyroscopeDataView extends SensorDataView<Gyroscope<?,?>> implements GyroscopeChangeNotifier{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4084226404414195863L;

	protected static final int width = 90;
	protected static final int height = 80;
	
	protected static final String GRAD_TEXT = "grad";
	protected static final String RADIANT_TEXT = "radiant";
	
	private JLabel xValue;
	private JLabel yValue;
	private JLabel zValue;

	
	protected JMenuItem showGradItem;
	protected JMenuItem showRadiantItem;
	
public GyroscopeDataView(Gyroscope<?,?> sensor) 
{
	super(sensor);

	sensor.addSensorListener(this);
	buildView();

}


//2012.02.13
@Override
protected void buildView()
{
	
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	JLabel tmpLabel;
	
	tmpLabel= new JLabel("x");
	tmpLabel.setBounds(insets.left+5, insets.top+5, 30, 20);
	this.add(tmpLabel);

	this.xValue= new JLabel("");
	this.xValue.setBounds(insets.left+25, insets.top+5, 70, 20);
	this.xValue.setBorder(new LineBorder(Color.BLACK));
	this.add(xValue);
	
	
	tmpLabel= new JLabel("y");
	tmpLabel.setBounds(insets.left+5, insets.top+30, 30, 20);
	this.add(tmpLabel);
	
	this.yValue= new JLabel("");
	this.yValue.setBounds(insets.left+25, insets.top+30, 70, 20);
	this.yValue.setBorder(new LineBorder(Color.BLACK));
	this.add(yValue);
	
	tmpLabel= new JLabel("z");
	tmpLabel.setBounds(insets.left+5, insets.top+55, 30, 20);
	this.add(tmpLabel);
	
	this.zValue= new JLabel("");
	this.zValue.setBounds(insets.left+25, insets.top+55, 70, 20);
	this.zValue.setBorder(new LineBorder(Color.BLACK));
	this.add(zValue);
}




@Override
public void rateChanged(Gyroscope<?,?> gyroscope)
{
	FloatVector3D angularRate = gyroscope.getAngularRates();
	String unit;
	int fraction;
	if (this.showGradItem.isSelected())
	{
		
		angularRate.x = Radiant.convertRadiantToDegree(angularRate.x);
		angularRate.y = Radiant.convertRadiantToDegree(angularRate.y);
		angularRate.z = Radiant.convertRadiantToDegree(angularRate.z);
		unit = "°";
		fraction = 1;
	}
	else
	{
		unit = "rad";
		fraction = 3;
	}
	
	String value;
	value = FloatValue.toFormatedFractionString(angularRate.x,fraction);
	this.xValue.setText((value+unit));
	
	value = FloatValue.toFormatedFractionString(angularRate.y,fraction);
	this.yValue.setText((value+unit));
	
	value = FloatValue.toFormatedFractionString(angularRate.z,fraction);
	this.zValue.setText((value+unit));
	
	
}




@Override
protected void makePopupMenu()
{
	super.makePopupMenu();
	
	this.contextMenue.add(new JSeparator());
	
	 ButtonGroup group = new ButtonGroup();
 
	 
	 
	 this.showGradItem = this.addRadioButtonMenuItem(this.contextMenue , GRAD_TEXT, "");
	 this.showGradItem.setSelected(false);

	 this.showRadiantItem = this.addRadioButtonMenuItem(this.contextMenue , RADIANT_TEXT, "");

	 
	  group.add(this.showGradItem);
	  group.add(this.showRadiantItem);
	  
}



@Override
protected int getViewWidth()
{
	return(GyroscopeDataView.width);
}

@Override
protected int getViewHeight()
{
	return(GyroscopeDataView.height);
}



/**
 * create a new Data view for given Gyroscope sensor
 * @param sensor gyroscope sensor
 * @return view for given gyroscope sensor
 */
public static ComponentView createView(Gyroscope<?,?> sensor)
{
	 
	if (sensor!=null)
	{
		return(new GyroscopeDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Gyroscope.class.getName()));
	}
}



}