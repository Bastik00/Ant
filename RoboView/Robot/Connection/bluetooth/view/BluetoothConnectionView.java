package de.hska.lat.robot.connection.bluetooth.view;




import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;


public class BluetoothConnectionView extends DisplayFrame
{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7180743603886059090L;



	
	protected AbstractRobot<?,?,?> robot;
	
	protected BluetoothConnectonPannel connectionPanel;
	
public BluetoothConnectionView() 
{
	super("title", false, true, false, false);
	
	
	this.connectionPanel = new BluetoothConnectonPannel();
	this.add(this.connectionPanel);

	this.setSize(200,200);
	this.show();
	

}



@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	this.robot = robot;
	this.connectionPanel.setRobot(robot);
	
	return(true);
}

}
