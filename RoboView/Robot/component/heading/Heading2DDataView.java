package de.hska.lat.robot.component.heading;


import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;

import de.hska.lat.robot.component.generic.heading.Heading2D;
import de.hska.lat.robot.component.generic.heading.HeadingChangeNotifier;
import de.hska.lat.robot.component.generic.imu.view.ImuArtificialHorizon;
import de.hska.lat.robot.component.generic.imu.view.ImuCompass;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.FloatValue;



public class Heading2DDataView extends ComponentView implements HeadingChangeNotifier
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2829415251479825982L;


	private JLabel  angle;
	
	protected static final int GRAPHICS_WIDTH = 100;
	protected static final int GRAPHICS_HEIGHT = 80;
	
	protected static final String GRAD_TEXT = "grad";
	protected static final String RADIANT_TEXT = "radiant";
	protected static final String GRAPHIC_TEXT	= "graphic";
	protected static final String TOGGLE_RANGE_TEXT	= "toggle range";
	
	protected static final String CMD_GRAPHIC	= "cmdGrapic";
	protected static final String CMD_TOGGLE_RANGE = "cmdToggleRange";
	
	protected static final int width = 100;
	protected static final int height = 80;
	
	protected JMenuItem showGradItem;
	protected JMenuItem showRadiantItem;
	protected JMenuItem showGraphicMenueItem;
	protected JMenuItem showToggleRangeMenueItem;
	
	protected ImuArtificialHorizon artificialHorizon;
	
	protected Heading2D.RotationAxis_e headingAxis;
	protected ImuCompass compass;
	protected boolean graphicMode;
	
	protected static final String graphicModeKey = ".graphicMode"; 

	private Heading2DDataView(Heading2D heading) 
{
	super(heading.getComponentName(), false);

	this.headingAxis = heading.getRotationAxis();
	heading.addSensorListener(this);
	
	this.compass = new ImuCompass("Compass", heading.getHeadingValue(), 250, 40, false);
	
	buildView();
}


//2012.02.13
@Override
protected void buildView()
{
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
	if (this.graphicMode) {		
		compass.setWidth(actualWidth - 10);
		compass.setHight((actualWidth - 10) / 6);
		
		compass.setBounds(insets.left+5, insets.top+5,
				compass.getSize().width, compass.getSize().height);
		
		this.add(compass);
	} 
	else{
	
		JLabel tmpLabel;
		
	
		
		tmpLabel= new JLabel("heading");
		tmpLabel.setBounds(insets.left+5, insets.top+5, 40, 20);
		this.add(tmpLabel);
	
		this.angle = new JLabel("");
		this.angle .setBounds(insets.left+35, insets.top+5, 70, 20);
		this.angle .setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle );
	}
	
	
}



/**
 * add items for display unit selection (radiant or grad)
 */

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
	  
	 this.showGraphicMenueItem = this.addCheckBoxMenuItem(this.contextMenue , Heading2DDataView.GRAPHIC_TEXT, Heading2DDataView.CMD_GRAPHIC);
		 
	 this.showToggleRangeMenueItem = this.addCheckBoxMenuItem(this.contextMenue , Heading2DDataView.TOGGLE_RANGE_TEXT, Heading2DDataView.CMD_TOGGLE_RANGE);
		
	  
}


@Override
protected int getViewWidth()
{
	if (this.resizable)
		return(this.actualWidth);
	
	return(Heading2DDataView.GRAPHICS_WIDTH);
}

@Override
protected int getViewHeight()
{
	if (this.resizable)
		return(this.actualHeight);
	
	return(Heading2DDataView.GRAPHICS_HEIGHT);
}




@Override
public void headingChanged(AngularVector3D headingVector)
{

	
	float heading;
		
	
	switch (this.headingAxis)
	{
	case X:
		heading = headingVector.x;
		break;
	case Y:
		heading = headingVector.y;
		break;
	case Z:
		heading = headingVector.z;
		break;
	default:
		heading = 0;
		break;
	
	}
	

	String unit;
	int fraction;
	if (this.showGradItem.isSelected())
	{
		
		heading = Radiant.convertRadiantToDegree(heading);

		unit = "°";
		fraction = 1;
	}
	else
	{
		unit = "rad";
		fraction = 3;
	}
	
	String value;
	value = FloatValue.toFormatedFractionString(heading,fraction);
	this.angle.setText((value+unit));

}


@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(Heading2DDataView.CMD_GRAPHIC))
	{
		this.setGraphicMode(showGraphicMenueItem.isSelected());
		
	}
	else if (cmd.equals(Heading2DDataView.CMD_TOGGLE_RANGE))
	{
		this.compass.setRangeFullCircle(!this.compass.getRangeFullCircle());
		this.buildView();
		
	} else {
		System.out.println(cmd);
		super.actionPerformed(actionEvent);
	}
}

public void setGraphicMode(boolean graphicMode)
{
	this.graphicMode = graphicMode;
	this.saveSettings();
	
	this.showGraphicMenueItem.setSelected(this.graphicMode);
	
	if (this.graphicMode) 
	{
		this.resizable = true;
		this.actualWidth = Heading2DDataView.GRAPHICS_WIDTH + 10;
		this.actualHeight = Heading2DDataView.GRAPHICS_HEIGHT + 5;
	}
	else
	{
		this.resizable = false;
	}
	this.buildView();	
}

public boolean getGraphicMode()
{
	return this.graphicMode;
}



protected void saveSettings()
{
	UiSettings.saveBoolean(this.settingsKey+graphicModeKey, this.getGraphicMode());
}



/**
 * create a new data view for 2D heading (an instance of this class)
 * if the given sensor is a null reference, create an error view. 
 * @param sensor an 2D heading sensor
 * @return an instance of this class, or MissingComponentViewe
 */

public static ComponentView createView(Heading2D sensor)
{
	 
	if (sensor!=null)
	{
		return(new Heading2DDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Heading2D.class.getName()));
	}
}





}