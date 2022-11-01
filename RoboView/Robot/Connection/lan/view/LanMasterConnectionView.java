package de.hska.lat.robot.connection.lan.view;



import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;


public class LanMasterConnectionView extends DisplayFrame
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423131381490852330L;
	protected LanMasterConnectionPanel connectionPanel;

	
public LanMasterConnectionView() 
{
	super("W-Lan Connection", false, true, false, false);
	
	this.connectionPanel = new LanMasterConnectionPanel();
	
	add(connectionPanel);
	setSize(220, 280);
	
	show();
}

/*
public static void main(String[] args) 
{
	JFrame frame = new JFrame();

	frame.setSize(400, 400);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	frame.add(new LanMasterConnectionPanel());
	frame.setVisible(true);
}
*/


@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	this.connectionPanel.setRobot(robot);
	return(true);
}

}
