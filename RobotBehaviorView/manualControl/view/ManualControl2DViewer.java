package de.hska.lat.behavior.manualControl.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.behavior.behavior.manualControl2D.HeadingControl;
import de.hska.lat.behavior.behavior.manualControl2D.ManualControl2D;
import de.hska.lat.behavior.behavior.manualControl2D.RotateControl;
import de.hska.lat.behavior.behavior.manualControl2D.VelocityControl;
import de.hska.lat.behavior.behavior.manualControl2D.XLocationControl;
import de.hska.lat.behavior.behavior.manualControl2D.YLocationControl;
import de.hska.lat.behavior.behavior.manualControl2D.ZLocationControl;
import de.hska.lat.robot.RobotSettings;
import de.hska.lat.ui.JSwitch.JSwitch;

public class ManualControl2DViewer extends ArbiterViewer<ManualControl2D> implements ManualControlListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1859716291636196591L;

	
	protected enum Mode_e {MODE_IDLE, MODE_ROTATE, MODE_ROTATE_TO, MODE_MOVE, MODE_MOVE_TO};
	
	
	protected static final String ROTATE_TEXT = "Rotate";
	protected static final String ROTATE_TO_TEXT = "Rotate to";
	protected static final String MOVE_TEXT = "Move";
	protected static final String MOVE_TO_TEXT = "Move to";
	protected static final String SET_ORIGIN_TEXT = "set origin";
	protected static final String MOVE_TO_ORIGIN_TEXT = "move to origin";
	protected static final String STOP_TEXT = "Stop";
	protected static final String SLIDER_TEXT = "Speed";
	
	protected static final String CMD_ROTATE			= "cmdRotate";
	protected static final String CMD_ROTATE_TO			= "cmdRotateTo";
	protected static final String CMD_MOVE				= "cmdMove";
	protected static final String CMD_MOVE_TO			= "cmdMoveTo";
	protected static final String CMD_STOP				= "cmdStop";
	protected static final String CMD_SLIDER			= "cmdSlider";
	
	
//	protected MotionController2D motionController;
	
	
	protected static final int BUTTON_ROTATE		= 0;
	protected static final int BUTTON_ROTATE_TO		= 1;
	protected static final int BUTTON_MOVE			= 2;
	protected static final int BUTTON_MOVE_TO		= 3;
	
	protected JSwitch[] buttons;
	
	protected JButton setOrigin;
	protected JButton moveToOrigin;
	protected JButton stop;
	protected JSlider speed;
	protected Mode_e mode = Mode_e.MODE_IDLE;;
	
	
	protected VelocityControl velocityControl;
	protected HeadingControl headingControl;
	protected RotateControl rotateControl;
	
	protected XLocationControl xLocationControl;
	protected YLocationControl yLocationControl;
	protected ZLocationControl zLocationControl;
	
//	protected Heading2D heading;
//	protected Locator2D locator;

//	private float x = 1000;
//	private float y = 500;
	
public ManualControl2DViewer(RobotBehavior<?> behavior, ManualControl2D manualControl, RobotSettings settings)
{
	
	super(behavior, manualControl,  settings);

	

	

	this.velocityControl = manualControl.getVelocityControl();
	this.headingControl = manualControl.getHeadingControl();
	this.rotateControl = manualControl.getRotateControl();
	this.xLocationControl = manualControl.getXLocationControl();
	this.yLocationControl = manualControl.getYLocationControl();
	this.zLocationControl = manualControl.getZLocationControl();
	
}


protected void build()
{
	this.setLayout( new BorderLayout());
	this.setSize(500, 400);
	
	
	this.addPanel();
	
	ManualControlPanel manualControl = new ManualControlPanel(this);
	manualControl.setBorder(new LineBorder(Color.BLACK,1));
	this.add(manualControl, BorderLayout.CENTER);
}


protected void addPanel()
{
	JPanel controlPanel = new JPanel();
	controlPanel.setLayout(new FlowLayout());
	this.add(controlPanel,BorderLayout.NORTH);
	
	this.buttons = new JSwitch[4];
	
	this.buttons[ManualControl2DViewer.BUTTON_ROTATE] = this.createModeButton(ManualControl2DViewer.ROTATE_TEXT,ManualControl2DViewer.CMD_ROTATE);
	controlPanel.add(this.buttons[ManualControl2DViewer.BUTTON_ROTATE]);

	this.buttons[ManualControl2DViewer.BUTTON_ROTATE_TO] = this.createModeButton(ManualControl2DViewer.ROTATE_TO_TEXT,ManualControl2DViewer.CMD_ROTATE_TO);
	controlPanel.add(this.buttons[ManualControl2DViewer.BUTTON_ROTATE_TO]);
	
	this.buttons[ManualControl2DViewer.BUTTON_MOVE] = this.createModeButton(ManualControl2DViewer.MOVE_TEXT,ManualControl2DViewer.CMD_MOVE);
	controlPanel.add(this.buttons[ManualControl2DViewer.BUTTON_MOVE]);

	this.buttons[ManualControl2DViewer.BUTTON_MOVE_TO] = this.createModeButton(ManualControl2DViewer.MOVE_TO_TEXT,ManualControl2DViewer.CMD_MOVE_TO);
	controlPanel.add(this.buttons[ManualControl2DViewer.BUTTON_MOVE_TO]);


	this.setOrigin = new JButton(SET_ORIGIN_TEXT);
	this.setOrigin.setPreferredSize(new Dimension(100,25));
	this.setOrigin.addActionListener(new  ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
/*			if (ManualControl2DViewer.this.heading!=null)
			{
				ManualControl2DViewer.this.heading.remote_setHeading(0.0f);
			}
			
			if (ManualControl2DViewer.this.locator!=null)
			{
				ManualControl2DViewer.this.locator.remote_setActualLocation(new FloatVector2D(0.0f,0.0f));
			}
	*/	}
		
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
	
	/*Stops Sphero when clicked*/
	this.stop = new JButton(STOP_TEXT);
	this.stop.setPreferredSize(new Dimension(100,25));
	this.stop.addActionListener(new  ActionListener()
	{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		velocityControl.setValue(0.0f);
		headingControl.setValue(0.0f);
		rotateControl.setValue(0.0f);
	}
	

	
	});
	controlPanel.add(this.stop);
	
	this.speed = new JSlider(JSlider.HORIZONTAL, 100, 1000, 500);
	this.speed.setPreferredSize(new Dimension(150,100));
	this.speed.setPaintLabels(true);
	Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
    table.put (100, new JLabel(new ImageIcon("hase.png")));
    table.put (500, new JLabel("Speed"));
    table.put (1000, new JLabel(new ImageIcon("schild.png")));
    this.speed.setLabelTable (table);
	this.speed.addChangeListener(new  ChangeListener()
	{

		@Override
		public void stateChanged(ChangeEvent e)
		{
		//	motionController.speed(speed.getValue());
			
		}
		
	});
	controlPanel.add(this.speed);
	
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
public void valueSelected(ManualControlPanel driveControl)
{
	switch (this.mode)
	{
	case MODE_MOVE:
		float angle;
		float velocity;
		
		
		angle = driveControl.getAngle();
		velocity = driveControl.getValue();
		System.out.println("angle "+angle);
		
		if ((angle>(Math.PI/2)) || (angle<(-Math.PI/2)))
		{
			velocity *=-1;
		}

		
		this.velocityControl.setValue(velocity);
		this.headingControl.setValue(angle);
		this.rotateControl.setValue(0.0f);

		
		break;

	
	case MODE_MOVE_TO:
		this.velocityControl.setValue(driveControl.getValue());
		this.xLocationControl.setValue(5.0f); // test values
		this.yLocationControl.setValue(10.0f); // test values
		
		break;
	
	case MODE_ROTATE:
		this.velocityControl.setValue(driveControl.getValue());
		this.headingControl.setValue(0.0f);
		this.rotateControl.setValue(driveControl.getAngle());
		break;

	case MODE_ROTATE_TO:
	//	this.motionController.rotateTo(driveControl.getXValue(), driveControl.getYValue(), (float)0.0, (float)0.1);
		break;
		
	default:
		break;
	
	}
	

}


@Override
public void a(ManualControlPanel driveControl)
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
	
	
	if (this.buttons[ManualControl2DViewer.BUTTON_ROTATE].isOn())
	{
		this.mode = Mode_e.MODE_ROTATE;
	}
	else if (this.buttons[ManualControl2DViewer.BUTTON_ROTATE_TO].isOn())
	{
		this.mode = Mode_e.MODE_ROTATE_TO;
	} else 	if (this.buttons[ManualControl2DViewer.BUTTON_MOVE].isOn())
	{
		this.mode = Mode_e.MODE_MOVE;
	}
	else if (this.buttons[ManualControl2DViewer.BUTTON_MOVE_TO].isOn())
	{
		this.mode = Mode_e.MODE_MOVE_TO;
	} 
	
}

	



}
