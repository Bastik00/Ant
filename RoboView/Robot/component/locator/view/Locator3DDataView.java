package de.hska.lat.robot.component.locator.view;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import de.hska.lat.math.vector.FloatVector3D;


import de.hska.lat.robot.component.generic.locator.Locator2D;
import de.hska.lat.robot.component.generic.locator.Locator3D;
import de.hska.lat.robot.component.generic.locator.LocatorChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.FloatValue;



public class Locator3DDataView extends ComponentView implements LocatorChangeNotifier{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4084226404414195863L;

	protected static final int width = 110;
	protected static final int height = 60;
	
	protected static final String MILIMETER_TEXT = "mm";
	protected static final String CENTIMETER_TEXT = "cm";
	protected static final String METER_TEXT = "m";
	
	private JLabel xLocation;
	private JLabel yLocation;
	

	
	protected JMenuItem showMilimmeterItem;
	protected JMenuItem showCentimeterItem;
	protected JMenuItem showMeterItem;
	
public Locator3DDataView(Locator3D sensor) 
{
	super(sensor.getComponentName(), false);

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

	this.xLocation= new JLabel("");
	this.xLocation.setBounds(insets.left+25, insets.top+5, 70, 20);
	this.xLocation.setBorder(new LineBorder(Color.BLACK));
	this.add(xLocation);
	
	
	tmpLabel= new JLabel("y");
	tmpLabel.setBounds(insets.left+5, insets.top+30, 30, 20);
	this.add(tmpLabel);
	
	this.yLocation= new JLabel("");
	this.yLocation.setBounds(insets.left+25, insets.top+30, 70, 20);
	this.yLocation.setBorder(new LineBorder(Color.BLACK));
	this.add(yLocation);
	

}




@Override
public void locationChanged(FloatVector3D location)
{
		String unit = "";
	float fraction = 1;
	
	
	if (this.showMilimmeterItem.isSelected())
	{
		unit = "mm";
		fraction = 1f;
	}
	else if(this.showCentimeterItem.isSelected())
	{
		unit = "cm";
		fraction = 10f;
	}
	else if(this.showMeterItem.isSelected())
	{
		unit = "m";
		fraction = 1000f;
	}

	String value;
	value = FloatValue.toFormatedFractionString(location.x*fraction,2);
	this.xLocation.setText((value+unit));
	
	value = FloatValue.toFormatedFractionString(location.z*fraction,2);
	this.yLocation.setText((value+unit));
	
}




@Override
protected void makePopupMenu()
{
	super.makePopupMenu();
	
	this.contextMenue.add(new JSeparator());
	
	 ButtonGroup group = new ButtonGroup();
 
	 
	 
	 this.showMilimmeterItem = this.addRadioButtonMenuItem(this.contextMenue , Locator3DDataView.METER_TEXT, "");
	 this.showMilimmeterItem.setSelected(false);

	 this.showCentimeterItem = this.addRadioButtonMenuItem(this.contextMenue , Locator3DDataView.CENTIMETER_TEXT, "");

	 this.showMeterItem = this.addRadioButtonMenuItem(this.contextMenue , Locator3DDataView.METER_TEXT, "");
	 
	 
	 
	  group.add(this.showMilimmeterItem);
	  group.add(this.showCentimeterItem);
	  group.add(this.showMeterItem);
	  
}



@Override
protected int getViewWidth()
{
	return(Locator3DDataView.width);
}

@Override
protected int getViewHeight()
{
	return(Locator3DDataView.height);
}



/**
 * create a new Data view for given Gyroscope sensor
 * @param sensor gyroscope sensor
 * @return view for given gyroscope sensor
 */
public static ComponentView createView(Locator3D sensor)
{
	 
	if (sensor!=null)
	{
		return(new Locator3DDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Locator2D.class.getName()));
	}
}



}