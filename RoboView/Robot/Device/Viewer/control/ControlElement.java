package de.hska.lat.robot.device.viewer.control;

import javax.swing.JPanel;

import de.hska.lat.robot.device.ControlInterface;



public class ControlElement extends JPanel
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected 	ControlInterface listener;	

public  ControlElement()
{
	buildControl();
}
		
protected void buildControl()
{
	
}


public void setControlListener(ControlInterface listener) 
{
		this.listener=listener;
		
}	
	
}
