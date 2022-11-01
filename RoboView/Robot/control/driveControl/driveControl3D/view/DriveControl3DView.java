package de.hska.lat.robot.control.driveControl.driveControl3D.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



import de.hska.lat.math.vector.FloatVector3D;

import de.hska.lat.robot.component.generic.heading.Heading3D;

import de.hska.lat.robot.component.generic.locator.Locator3D;
import de.hska.lat.robot.component.generic.motion.MotionController3D;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.ui.JSwitch.JSwitch;

public class DriveControl3DView extends DisplayFrame implements DriveControlListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1859716291636196591L;

	
	protected enum Mode_e {MODE_IDLE,MODE_ROTATE,MODE_ROTATE_TO,MODE_MOVE,MODE_MOVE_TO};
	
	
	protected static final String ROTATE_TEXT = "Rotate";
	protected static final String ROTATE_TO_TEXT = "Rotate to";
	protected static final String MOVE_TEXT = "Move";
	protected static final String MOVE_TO_TEXT = "Move to";
	protected static final String SET_ORIGIN_TEXT = "set origin";
	protected static final String MOVE_TO_ORIGIN_TEXT = "move to origin";
	
	protected static final String CMD_ROTATE			= "cmdRotate";
	protected static final String CMD_ROTATE_TO			= "cmdRotateTo";
	protected static final String CMD_MOVE				= "cmdMove";
	protected static final String CMD_MOVE_TO			= "cmdMoveTo";

	
	
	protected MotionController3D motionController;
	
	
	protected static final int BUTTON_ROTATE		= 0;
	protected static final int BUTTON_ROTATE_TO		= 1;
	protected static final int BUTTON_MOVE			= 2;
	protected static final int BUTTON_MOVE_TO		= 3;
	
	protected JSwitch[] buttons = new JSwitch[4];
	
	protected JButton setOrigin;
	protected JButton moveToOrigin;
	protected Mode_e mode = Mode_e.MODE_IDLE;;
	
	
	protected Heading3D heading;
	protected Locator3D locator;

	
public DriveControl3DView()
{
	
	super("Drive control", true, true, true, true);

	this.buildView();
	
	this.show();
	this.toFront();
}


private void buildView()
{
	
	addPanel();
	
	DriveControlPanel driveControl = new DriveControlPanel(this);
	driveControl.setBorder(new LineBorder(Color.BLACK,1));
	this.add(driveControl,BorderLayout.CENTER);
}


protected void addPanel()
{
	JPanel controlPanel = new JPanel();
	controlPanel.setLayout(new FlowLayout());
	this.add(controlPanel,BorderLayout.NORTH);
	
	
	
	this.buttons[DriveControl3DView.BUTTON_ROTATE] = this.createModeButton(DriveControl3DView.ROTATE_TEXT,DriveControl3DView.CMD_ROTATE);
	controlPanel.add(this.buttons[DriveControl3DView.BUTTON_ROTATE]);

	this.buttons[DriveControl3DView.BUTTON_ROTATE_TO] = this.createModeButton(DriveControl3DView.ROTATE_TO_TEXT,DriveControl3DView.CMD_ROTATE_TO);
	controlPanel.add(this.buttons[DriveControl3DView.BUTTON_ROTATE_TO]);
	
	this.buttons[DriveControl3DView.BUTTON_MOVE] = this.createModeButton(DriveControl3DView.MOVE_TEXT,DriveControl3DView.CMD_MOVE);
	controlPanel.add(this.buttons[DriveControl3DView.BUTTON_MOVE]);

	this.buttons[DriveControl3DView.BUTTON_MOVE_TO] = this.createModeButton(DriveControl3DView.MOVE_TO_TEXT,DriveControl3DView.CMD_MOVE_TO);
	controlPanel.add(this.buttons[DriveControl3DView.BUTTON_MOVE_TO]);


	this.setOrigin = new JButton(SET_ORIGIN_TEXT);
	this.setOrigin.setPreferredSize(new Dimension(100,25));
	this.setOrigin.addActionListener(new  ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (DriveControl3DView.this.heading!=null)
			{
				DriveControl3DView.this.heading.remote_setHeading(new FloatVector3D(0.0f, 0.0f, 0.0f));
			}
			
			if (DriveControl3DView.this.locator!=null)
			{
				DriveControl3DView.this.locator.remote_setActualLocation(new FloatVector3D(0.0f, 0.0f, 0.0f));
			}
		}
		
	});
	controlPanel.add(this.setOrigin);

	this.moveToOrigin = new JButton(MOVE_TO_ORIGIN_TEXT);
	this.moveToOrigin.setPreferredSize(new Dimension(100,25));
	this.moveToOrigin.addActionListener(new  ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			
		}
		
	});
	controlPanel.add(this.moveToOrigin);
	
	
	
	
	//	this.newOrigin
//	this.newOrigin
}



protected JSwitch createModeButton(String name,String command)
{
	JSwitch tmpButton;
	
	tmpButton = new JSwitch(name);
	tmpButton.setPreferredSize(new Dimension(100,25));
	tmpButton.setActionCommand(command);
	tmpButton.addActionListener(this);
	return(tmpButton);

}



@Override
public void valueSelected(DriveControlPanel driveControl)
{
	switch (this.mode)
	{
	case MODE_MOVE:
	//	this.motionController.move(driveControl.getValue(), driveControl.getAngle());
		break;

	case MODE_MOVE_TO:
//		this.motionController.moveTo(driveControl.getXValue(), driveControl.getYValue(), (float)0.1);
		break;
	
	case MODE_ROTATE:
	//		this.motionController.rotate(driveControl.getAngle(), driveControl.getValue());
		break;

	case MODE_ROTATE_TO:
		this.motionController.rotateTo(driveControl.getXValue(), driveControl.getYValue(), (float)0.0, (float)0.1);
		break;
	

		
	default:
		break;
	
	}
	

}


@Override
public void a(DriveControlPanel driveControl)
{
	// TODO Auto-generated method stub
	
}


@Override
public void actionPerformed(ActionEvent actionEvent)
{
	Object button = actionEvent.getSource();
	int index;
	
	for (index =0; index <this.buttons.length;index++)
	{
		if (this.buttons[index]==button)
		{
			this.buttons[index].on(true);
		}
		else
		{
			this.buttons[index].on(false);
			this.buttons[index].repaint();
		}
	}
	
	
	if (this.buttons[DriveControl3DView.BUTTON_ROTATE].isOn())
	{
		this.mode = Mode_e.MODE_ROTATE;
	}
	else if (this.buttons[DriveControl3DView.BUTTON_ROTATE_TO].isOn())
	{
		this.mode = Mode_e.MODE_ROTATE_TO;
	} else 	if (this.buttons[DriveControl3DView.BUTTON_MOVE].isOn())
	{
		this.mode = Mode_e.MODE_MOVE;
	}
	else if (this.buttons[DriveControl3DView.BUTTON_MOVE_TO].isOn())
	{
		this.mode = Mode_e.MODE_MOVE_TO;
	}
	
}

	



}
