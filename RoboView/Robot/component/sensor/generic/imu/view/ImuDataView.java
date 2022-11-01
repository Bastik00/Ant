package de.hska.lat.robot.component.generic.imu.view;


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

import de.hska.lat.robot.component.generic.imu.Imu;
import de.hska.lat.robot.component.generic.imu.ImuChangeNotifier;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.FloatValue;



public class ImuDataView extends SensorDataView<Imu> implements ImuChangeNotifier
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2829415251479825982L;


	private JLabel [] angle = new JLabel[3];
	
	protected static final String GRAD_TEXT = "grad";
	protected static final String RADIANT_TEXT = "radiant";
	protected static final String GRAPHIC_TEXT	= "graphic";
	protected static final String TOGGLE_RANGE_TEXT	= "toggle range";
	protected static final String CMD_GRAPHIC	= "cmdGrapic";
	protected static final String CMD_TOGGLE_RANGE = "cmdToggleRange";
	
	protected static final int WIDTH = 100;
	protected static final int HEIGHT = 80;
	protected static final int WIDTH_GRAPHIC = 250;
	protected static final int HIEGHT_GRAPHIC = 300;
	
	protected String settingsKey;
	protected String componentName;
	protected static final String graphicModeKey = ".graphicMode"; 
	
	protected JMenuItem showGradItem;
	protected JMenuItem showRadiantItem;
	protected JMenuItem showGraphicMenueItem;
	protected JMenuItem showToggleRangeMenueItem;
	
	protected ImuArtificialHorizon artificialHorizon;
	protected ImuCompass compass;
	protected boolean graphicMode;
	
public ImuDataView(Imu sensor) 
{
	super(sensor);

	sensor.addSensorListener(this);
	
	this.artificialHorizon = new ImuArtificialHorizon(sensor, 250, 250);
	this.compass = new ImuCompass("Compass", sensor, 250, 40, false);
	
	this.componentName = "ImuDataView";
	this.settingsKey=this.getClass().getName()+"."+this.componentName;
	
	this.setGraphicMode(UiSettings.recoverBoolean(settingsKey+ImuDataView.graphicModeKey, false));
	
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
		
		artificialHorizon.setWidth(actualWidth - 10);
		artificialHorizon.setHight(actualWidth - 10);
		
		artificialHorizon.setBounds(insets.left + 5, insets.top + compass.getSize().height + 5, 
				artificialHorizon.getSize().width, artificialHorizon.getSize().height);

		this.add(artificialHorizon);
		this.add(compass);
	} 
	else {
		JLabel tmpLabel;
		
		tmpLabel= new JLabel("pitch");
		tmpLabel.setBounds(insets.left+5, insets.top+5, 40, 20);
		this.add(tmpLabel);
		
		this.angle[Imu.PITCH] = new JLabel("");
		this.angle[Imu.PITCH] .setBounds(insets.left+35, insets.top+5, 60, 20);
		this.angle[Imu.PITCH] .setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Imu.PITCH] );
		
		tmpLabel= new JLabel("roll");
		tmpLabel.setBounds(insets.left+5, insets.top+30, 50, 20);
		this.add(tmpLabel);
		
		this.angle[Imu.ROLL]= new JLabel("");
		this.angle[Imu.ROLL].setBounds(insets.left+35, insets.top+30, 60, 20);
		this.angle[Imu.ROLL].setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Imu.ROLL]);
		
		tmpLabel= new JLabel("yaw");
		tmpLabel.setBounds(insets.left+5, insets.top+55, 50, 20);
		this.add(tmpLabel);
		
		this.angle[Imu.YAW] = new JLabel("");
		this.angle[Imu.YAW].setBounds(insets.left+35, insets.top+55, 60, 20);
		this.angle[Imu.YAW].setBorder(new LineBorder(Color.BLACK));
		this.add(this.angle[Imu.YAW]);
	}
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
	  
	 this.showGraphicMenueItem = this.addCheckBoxMenuItem(this.contextMenue , ImuDataView.GRAPHIC_TEXT, ImuDataView.CMD_GRAPHIC);
	 
	 this.showToggleRangeMenueItem = this.addCheckBoxMenuItem(this.contextMenue , ImuDataView.TOGGLE_RANGE_TEXT, ImuDataView.CMD_TOGGLE_RANGE);
	 
}


	




@Override
protected int getViewWidth()
{
	if (this.resizable)
		return(this.actualWidth);
	
	return(ImuDataView.WIDTH);
}

@Override
protected int getViewHeight()
{
	if (this.resizable)
		return(this.actualHeight);
	
	return(ImuDataView.HEIGHT);
}



@Override
public void imuChanged(Imu sensor)
{
	if (!graphicMode) 
	{
		AngularVector3D angles;
		
		angles = sensor.getAngles();
		
		String unit;
		int fraction;
		if (this.showGradItem.isSelected())
		{
			angles.x = Radiant.convertRadiantToDegree(angles.x);
			angles.y = Radiant.convertRadiantToDegree(angles.y);
			angles.z = Radiant.convertRadiantToDegree(angles.z);
			unit = "°";
			fraction = 1;
		}
		else
		{
			unit = "rad";
			fraction = 3;
		}
	
		String value;
		value = FloatValue.toFormatedFractionString(angles.x,fraction);
		this.angle[Imu.PITCH] .setText((value+unit));
		
		value = FloatValue.toFormatedFractionString(angles.y,fraction);
		this.angle[Imu.ROLL] .setText((value+unit));
		
		value = FloatValue.toFormatedFractionString(angles.z,fraction);
		this.angle[Imu.YAW] .setText((value+unit));
	}

}


public static ComponentView createView(Imu sensor)
{
	 
	if (sensor!=null)
	{
		return(new ImuDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(Imu.class.getName()));
	}
}

@Override
public void actionPerformed(ActionEvent actionEvent) 
{
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(ImuDataView.CMD_GRAPHIC))
	{
		this.setGraphicMode(showGraphicMenueItem.isSelected());
		
	}
	else if (cmd.equals(ImuDataView.CMD_TOGGLE_RANGE))
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
		this.actualWidth = ImuDataView.WIDTH_GRAPHIC + 10;
		this.actualHeight = ImuDataView.HIEGHT_GRAPHIC + 5;
	}
	else
	{
		this.resizable = false;
	}
	this.buildView();	
}

public boolean getGraphicMode() {
	return this.graphicMode;
}

protected void saveSettings()
{
	UiSettings.saveBoolean(this.settingsKey+graphicModeKey, this.getGraphicMode());
}


}