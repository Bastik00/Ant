package de.hska.lat.robot.display.generics.map.navigationPath;


import java.awt.Insets;
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

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.displayFrame.DisplayPanel;
import de.hska.lat.robot.ui.settings.UiSettings;
import de.hska.lat.robot.value.FloatValue;



public class PositionPanel extends DisplayPanel implements ActionListener
{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1212858195565579498L;

	
	protected enum DisplayUnit_e {MILLIMETER, CENTIMETER, METER};
	
	protected static final String unitKey =".displayUnit";
	
	protected JTextField xPosition;
	protected JTextField yPosition;
	protected JTextField zPosition;

	
	protected JLabel xUnit;
	protected JLabel yUnit;	
	protected JLabel zUnit;
	
	protected static final String  UNIT_METER_TEXT= "m";
	protected static final String  UNIT_CENTIMETER_TEXT= "cm";
	protected static final String  UNIT_MILLIMETER_TEXT= "mm";
	
	
	protected static final String  CMD_UNIT_MILLIMETER = "cmdUnitMillimeter";
	protected static final String  CMD_UNIT_CENTIMETER = "cmdUnitCentimeter";
	protected static final String  CMD_UNIT_METER = "cmdUnitMeter";
	
	
	
	protected NavigationPoint navPoint;
    
	
	protected JPopupMenu contextMenue;

	protected JMenuItem millimeterItem;
	protected JMenuItem centimeterItem;
	protected JMenuItem meterItem;
	
	
	protected DisplayUnit_e displayUnit;
	
	
	
	
public PositionPanel()
{

	
	this.setLayout(null);
	this.setBorder( new TitledBorder(NavigationPathManager.POSITION_TEXT));
	
	this.buildPanel();
	
	
	
	this.setUnit(UiSettings.recoverInt(this.settingsKey+PositionPanel.unitKey,1));
}



/**
 * set display unit corresponding to given int value
 * @param unit display unit type as int
 */

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


protected float convertValueFromDisplay(float value)
{
	
	float scaleFactor;
	
	switch (this.displayUnit)
	{
	case MILLIMETER:
		scaleFactor = 1;
		break;
		
	case CENTIMETER:
		scaleFactor = 10;
		break;
		
	case METER:
		scaleFactor = 1000;
		break;
		
	default :
		scaleFactor = 1;
		break;
	}
	
	value *= scaleFactor;
	
	return(value);
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
	
	UiSettings.saveInt(this.settingsKey+PositionPanel.unitKey,unit);
	
}


protected void setUnitDescription()
{
	String description;
	
	switch (this.displayUnit)
	{
	case MILLIMETER:
		description = UNIT_MILLIMETER_TEXT;
		break;
		
	case CENTIMETER:
		description = UNIT_CENTIMETER_TEXT;
		break;
		
	case METER:
		description = UNIT_METER_TEXT;
		break;
		
	default :
		description = UNIT_MILLIMETER_TEXT;
		break;
	}
	
	this.xUnit.setText(description);
	this.yUnit.setText(description);
	this.zUnit.setText(description);
	
}


/**
 * build Position panel , with all controls 
 */
protected void buildPanel()
{

	JLabel description;
	
	

	
	Insets insets =this.getBorder().getBorderInsets(this);
	
	description = new JLabel ("x");
	description.setBounds(insets.left+5,insets.top+5,20,25);
	this.add(description);
	
	this.xPosition = new JTextField();
	this.xPosition.setBounds(insets.left+20,insets.top+5,70,25);
	this.xPosition.setEnabled(false);
	this.xPosition.addActionListener( new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent actionEvent)
		{
			try{
				PositionPanel.this.navPoint.setXPosition(
						PositionPanel.this.convertValueFromDisplay(Float.valueOf(xPosition.getText())));
			}
			catch(Exception e)
			{
				System.out.println("nix");
			}
			
		}
		
	});
	
	this.add(this.xPosition);
	
	this.xUnit = new JLabel("");
	this.xUnit.setBounds(insets.left+90,insets.top+5,25,25);
	this.add(this.xUnit);
	
	
	
	
	
	
	description = new JLabel ("y");
	description.setBounds(insets.left+5,insets.top+35,20,25);
	this.add(description);
	
	this.yPosition = new JTextField();
	this.yPosition.setBounds(insets.left+20,insets.top+35,70,25);
	this.yPosition.setEnabled(false);
	this.yPosition.addActionListener( new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent actionEvent)
		{
			try{
				PositionPanel.this.navPoint.setYPosition(
						PositionPanel.this.convertValueFromDisplay(Float.valueOf(yPosition.getText())));
			}
			catch(Exception e)
			{
				System.out.println("nix");
			}
			
		}
		
	});

	this.add(this.yPosition);
	
	this.yUnit = new JLabel("");
	this.yUnit.setBounds(insets.left+90,insets.top+35,25,25);
	this.add(this.yUnit);
	
	
	description = new JLabel ("z");
	description.setBounds(insets.left+5,insets.top+65,20,20);
	this.add(description);
	
	this.zPosition = new JTextField();
	this.zPosition.setBounds(insets.left+20,insets.top+65,70,25);
	this.zPosition.setEnabled(false);
	this.zPosition.addActionListener( new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent actionEvent)
		{
			try{
				PositionPanel.this.navPoint.setZPosition(
						PositionPanel.this.convertValueFromDisplay(Float.valueOf(zPosition.getText())));
			}
			catch(Exception e)
			{
				System.out.println("nix");
			}
			
		}
		
	});

	this.add(this.zPosition);
	
	
	this.zUnit = new JLabel("");
	this.zUnit.setBounds(insets.left+90,insets.top+65,25,25);
	this.add(this.zUnit);
	
	this.makePopupMenu();
}




protected void makePopupMenu()
{
	this.contextMenue = new JPopupMenu();
	  MouseListener popupListener = new PopupListener();
	  
	  
	  ButtonGroup group = new ButtonGroup();


	  
	  millimeterItem = this.addRadioButtonMenuItem(this.contextMenue , PositionPanel.UNIT_MILLIMETER_TEXT, PositionPanel.CMD_UNIT_MILLIMETER);
	  centimeterItem = this.addRadioButtonMenuItem(this.contextMenue , PositionPanel.UNIT_CENTIMETER_TEXT, PositionPanel.CMD_UNIT_CENTIMETER);
	  centimeterItem.setSelected(false);
	  this.displayUnit = DisplayUnit_e.CENTIMETER;
	  
	  meterItem = this.addRadioButtonMenuItem(this.contextMenue , PositionPanel.UNIT_METER_TEXT, PositionPanel.CMD_UNIT_METER);
	  
	
	  
	  group.add(millimeterItem);
	  group.add(centimeterItem);
	  group.add(meterItem);
	  


    this.addMouseListener(popupListener);
}





protected JMenuItem addRadioButtonMenuItem(JPopupMenu popupMenu, String text, String command)
{
	
	  JMenuItem  menuItem = new JRadioButtonMenuItem(text);
	   menuItem.addActionListener(this);
	   menuItem.setActionCommand(command);
	   popupMenu.add(menuItem);

    
	   return(menuItem); 
}



public void setNavPoint(NavigationPoint navPoint)
{
	

	this.navPoint = navPoint;
	
	float scaleFactor;
	
	float x;
	float y;
	float z;
	
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

	x= navPoint.getXPosition() *scaleFactor;
	y= navPoint.getYPosition() *scaleFactor;
	z= navPoint.getZPosition() *scaleFactor;
	
	
	this.xPosition.setText(FloatValue.toFormatedFractionString(x,2));
	this.yPosition.setText(FloatValue.toFormatedFractionString(y,2));
	this.zPosition.setText(FloatValue.toFormatedFractionString(z,2));
	
	this.xPosition.setEnabled(true);
	this.yPosition.setEnabled(true);
	this.zPosition.setEnabled(true);
	
}


public void clear()
{
	this.xPosition.setText("");
	this.yPosition.setText("");
	this.zPosition.setText("");
	
	this.xPosition.setEnabled(false);
	this.yPosition.setEnabled(false);
	this.zPosition.setEnabled(false);
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
        if (e.isPopupTrigger()) {
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
	
	if (cmd.equals(PositionPanel.CMD_UNIT_MILLIMETER))
	{
		this.displayUnit = DisplayUnit_e.MILLIMETER;
		this.saveActualUnit();
		this.setUnitDescription();
	}
	else if (cmd.equals(PositionPanel.CMD_UNIT_CENTIMETER))
	{
		this.displayUnit = DisplayUnit_e.CENTIMETER;
		this.saveActualUnit();
		this.setUnitDescription();
	}
	else if (cmd.equals(PositionPanel.CMD_UNIT_METER))
	{
		this.displayUnit = DisplayUnit_e.METER;
		this.saveActualUnit();
		this.setUnitDescription();
	}
	
}




//this.displayUnit
}	