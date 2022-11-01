package de.hska.lat.robot.component.heading;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;

import de.hska.lat.robot.component.generic.heading.Heading3D;
import de.hska.lat.robot.component.generic.heading.HeadingChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.value.FloatValue;



public class Heading3DDataView extends ComponentView implements HeadingChangeNotifier
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2829415251479825982L;


	private JLabel [] angle = new JLabel[3];
	
	protected static final String GRAD_TEXT = "grad";
	protected static final String RADIANT_TEXT = "radiant";
	
	protected static final int width = 100;
	protected static final int height = 80;
	
	protected JMenuItem showGradItem;
	protected JMenuItem showRadiantItem;
	
public Heading3DDataView(Heading3D headingDetector) 
{
	super(headingDetector.getComponentName(), false);

	headingDetector.addSensorListener(this);
	
	buildView();
}


//2012.02.13
@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	JLabel tmpLabel;
	

	
	tmpLabel= new JLabel("pith");
	tmpLabel.setBounds(insets.left+5, insets.top+5, 40, 20);
	this.add(tmpLabel);

	this.angle[Heading3D.X] = new JLabel("");
	this.angle[Heading3D.X] .setBounds(insets.left+35, insets.top+5, 70, 20);
	this.angle[Heading3D.X] .setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Heading3D.X] );

	
	
	
	tmpLabel= new JLabel("roll");
	tmpLabel.setBounds(insets.left+5, insets.top+30, 50, 20);
	this.add(tmpLabel);
	
	
	this.angle[Heading3D.Y]= new JLabel("");
	this.angle[Heading3D.Y].setBounds(insets.left+35, insets.top+30, 70, 20);
	this.angle[Heading3D.Y].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Heading3D.Y]);

	
	tmpLabel= new JLabel("yaw");
	tmpLabel.setBounds(insets.left+5, insets.top+55, 50, 20);
	this.add(tmpLabel);

	
	this.angle[Heading3D.Z] = new JLabel("");
	this.angle[Heading3D.Z].setBounds(insets.left+35, insets.top+55, 70, 20);
	this.angle[Heading3D.Z].setBorder(new LineBorder(Color.BLACK));
	this.add(this.angle[Heading3D.Z]);
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
	return(Heading3DDataView.width);
}

@Override
protected int getViewHeight()
{
	return(Heading3DDataView.height);
}



@Override
public void headingChanged(AngularVector3D heading)
{


	String unit;
	int fraction;
	if (this.showGradItem.isSelected())
	{
		
		heading.x = Radiant.convertRadiantToDegree(heading.x);
		heading.y = Radiant.convertRadiantToDegree(heading.y);
		heading.z = Radiant.convertRadiantToDegree(heading.z);
		unit = "°";
		fraction = 1;
	}
	else
	{
		unit = "rad";
		fraction = 3;
	}
	
	String value;
	value = FloatValue.toFormatedFractionString(heading.x,fraction);
	this.angle[Heading3D.X] .setText((value+unit));
	
	value = FloatValue.toFormatedFractionString(heading.y,fraction);
	this.angle[Heading3D.Y] .setText((value+unit));
	
	value = FloatValue.toFormatedFractionString(heading.z,fraction);
	this.angle[Heading3D.Z] .setText((value+unit));
	
	
	


}


public static ComponentView createView(Heading3D sensor)
{
	 
	if (sensor!=null)
	{
		return(new Heading3DDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Heading3D.class.getName()));
	}
}





}