package de.hska.lat.robot.pose.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.border.TitledBorder;


import de.hska.lat.math.Radiant;
import de.hska.lat.math.vector.AngularVector3D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;


import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.pose.Pose3D;
import de.hska.lat.robot.pose.PoseListener;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.ComponentValue;

public class PoseDataView extends DisplayFrame implements PoseListener, ActionListener
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4900962911605371114L;

	
	protected enum DisplayUnit_e {MILLIMETER, CENTIMETER, METER};
	
	protected static final String unitKey =".displayUnit";
	
	protected static final String  UNIT_METER_TEXT= "m";
	protected static final String  UNIT_CENTIMETER_TEXT= "cm";
	protected static final String  UNIT_MILLIMETER_TEXT= "mm";
	
	
	protected static final String  CMD_UNIT_MILLIMETER = "cmdUnitMillimeter";
	protected static final String  CMD_UNIT_CENTIMETER = "cmdUnitCentimeter";
	protected static final String  CMD_UNIT_METER = "cmdUnitMeter";
	
	
	protected JMenuItem millimeterItem;
	protected JMenuItem centimeterItem;
	protected JMenuItem meterItem;
	
	protected JLabel [] position = new JLabel[3];
	protected JLabel [] heading = new JLabel[3];
	
	protected Pose3D pose;
	
	protected DisplayUnit_e displayUnit;
	
	protected JPopupMenu contextMenue;
	
	protected String description;
	
public PoseDataView()
{
	super("pose data" , false, true, false, true);

	
	this.setLayout(null);
	

	this.buildView();
	
	
	this.setSize(250,160);
	this.setUnit(UiSettings.recoverInt(this.settingsKey+PoseDataView.unitKey,1));
	this.show();
}

protected void buildView()
{
	this.position[Pose3D.X_AXIS] = this.addLabel(5, 5, 120, "x position");
	this.position[Pose3D.Y_AXIS] = this.addLabel(5, 45, 120, "y position");
	this.position[Pose3D.Z_AXIS] = this.addLabel(5, 85, 120, "z position");
	
	this.heading[Pose3D.X_AXIS] = this.addLabel(150, 5, 80, "x°");
	this.heading[Pose3D.Y_AXIS] = this.addLabel(150, 45, 80, "y°");
	this.heading[Pose3D.Z_AXIS] = this.addLabel(150, 85, 80, "z°");

	this.makePopupMenu();
}




protected void makePopupMenu()
{
	this.contextMenue = new JPopupMenu();
	MouseListener popupListener = new PopupListener();
  
  
	ButtonGroup group = new ButtonGroup();


	  
	this.millimeterItem = this.addRadioButtonMenuItem(this.contextMenue , PoseDataView.UNIT_MILLIMETER_TEXT, PoseDataView.CMD_UNIT_MILLIMETER);
	this.centimeterItem = this.addRadioButtonMenuItem(this.contextMenue , PoseDataView.UNIT_CENTIMETER_TEXT, PoseDataView.CMD_UNIT_CENTIMETER);
	this.centimeterItem.setSelected(false);
	this.displayUnit = DisplayUnit_e.CENTIMETER;
	  
	this.meterItem = this.addRadioButtonMenuItem(this.contextMenue , PoseDataView.UNIT_METER_TEXT, PoseDataView.CMD_UNIT_METER);
	  
	
	  
	group.add(millimeterItem);
	group.add(centimeterItem);
	group.add(meterItem);
  

	this.position[Pose3D.X_AXIS].addMouseListener(popupListener);
	this.position[Pose3D.Y_AXIS].addMouseListener(popupListener);
	this.position[Pose3D.Z_AXIS].addMouseListener(popupListener);
	
}


protected JMenuItem addRadioButtonMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JRadioButtonMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}



@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{

	this.pose =((Robot)robot).getPose();
	this.pose.addPoseListener(this);
	poseChanged(this.pose);
	
	return(true);
}






protected void setUnit(int unit)
{
	switch (unit)
	{
	case 0:
		this.displayUnit=DisplayUnit_e.MILLIMETER;
		this.millimeterItem.setSelected(true);
		break;
		
	case 1:
		this.displayUnit=DisplayUnit_e.CENTIMETER;
		this.centimeterItem.setSelected(true);
		break;
		
	case 2:
		this.displayUnit=DisplayUnit_e.METER;
		this.meterItem.setSelected(true);
		break;
		
	default:
		this.displayUnit=DisplayUnit_e.MILLIMETER;
		this.millimeterItem.setSelected(true);
		break;
	}
	
	
	this.setUnitDescription();
	
}



/**
 * return actual display unit as integer
 * @return display unit as integer
 */
protected int getUnit()
{
	int unit;
	
	switch (this.displayUnit)
	{
	case MILLIMETER:
		unit = 0;
		break;
		
	case CENTIMETER:
		unit = 1;
		break;
		
	case METER:
		unit = 2;
		break;
		
	default :
		unit = 0;
		break;
	}
	
	return(unit);
}

/**
 * save actual unit setting in RoboView UI settings
 */
public void saveActualUnit()
{
	int unit;
	
	unit = this.getUnit();
	
	UiSettings.saveInt(this.settingsKey+PoseDataView.unitKey,unit);
	
}


protected void setUnitDescription()
{

	switch (this.displayUnit)
	{
	case MILLIMETER:
		this.description = UNIT_MILLIMETER_TEXT;
		break;
		
	case CENTIMETER:
		this.description = UNIT_CENTIMETER_TEXT;
		break;
		
	case METER:
		this.description = UNIT_METER_TEXT;
		break;
		
	default :
		this.description = UNIT_MILLIMETER_TEXT;
		break;
	}

}


public JLabel addLabel(int x, int y,int width, String name)
{
	
	JLabel tmpLabel;
	
	tmpLabel = new JLabel();
	tmpLabel.setBorder(new TitledBorder(name));
	tmpLabel.setBounds(x, y, width, 40);
	this.add(tmpLabel);
	
	return(tmpLabel);
}




@Override
public void poseChanged(Pose3D pose)
{

	FloatVector3D location;
	AngularVector3D heading;
	float scaleFactor;
	
	location = pose.getLocation();
	heading = pose.getNormalizedHeading();
	
	switch(this.displayUnit)
	{
	case CENTIMETER:
		scaleFactor = 0.1f;
		break;
		
		
	case METER:
		scaleFactor = 0.001f;
		break;
		
		
	case MILLIMETER:
		scaleFactor = 1;
		break;
		
		
	default:
		scaleFactor = 1;
		break;
	
	}
	
	
	this.position[Pose3D.X_AXIS].setText(ComponentValue.toFormatedFractionString(location.x*scaleFactor,2)+this.description);
	this.position[Pose3D.Y_AXIS].setText(ComponentValue.toFormatedFractionString(location.y*scaleFactor,2)+this.description);
	this.position[Pose3D.Z_AXIS].setText(ComponentValue.toFormatedFractionString(location.z*scaleFactor,2)+this.description);
	
	
	this.heading[Pose3D.X_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading.x),2)+"°");
	this.heading[Pose3D.Y_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading.y),2)+"°");
	this.heading[Pose3D.Z_AXIS].setText(ComponentValue.toFormatedFractionString(Radiant.convertRadiantToDegree(heading.z),2)+"°");
}

	




@Override
protected void onClosing()
{
	this.pose.removePoseListener(this);	
}


class PopupListener extends MouseAdapter 
{
    public void mousePressed(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) 
    {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) 
        {
        	contextMenue.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}



@Override
public void actionPerformed(ActionEvent actionEvent)
{

	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(PoseDataView.CMD_UNIT_MILLIMETER))
	{
		this.displayUnit = DisplayUnit_e.MILLIMETER;
		this.saveActualUnit();
		this.setUnitDescription();
		this.poseChanged(this.pose);
	}
	else if (cmd.equals(PoseDataView.CMD_UNIT_CENTIMETER))
	{
		this.displayUnit = DisplayUnit_e.CENTIMETER;
		this.saveActualUnit();
		this.setUnitDescription();
		this.poseChanged(this.pose);
	}
	else if (cmd.equals(PoseDataView.CMD_UNIT_METER))
	{
		this.displayUnit = DisplayUnit_e.METER;
		this.saveActualUnit();
		this.setUnitDescription();
		this.poseChanged(this.pose);
	}
	
}


}
