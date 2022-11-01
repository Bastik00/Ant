package de.hska.lat.robot.device.viewer.control.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.hska.lat.robot.device.viewer.control.ControlElement;

public class TestControl extends ControlElement implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static final String PING_ICON = "(*)";
	private static final String PING_TEXT = "ping device";
	JButton ping;
	
protected void buildControl()
{

	ping = new JButton(PING_ICON);
	ping.setToolTipText(PING_TEXT);
	ping.addActionListener(this);
	ping.setBorder(null);
	//ping.setBounds(1,1,20,20);
	this.add(ping);
	
}
	
	

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	this.listener.remote_pingDevice();
}

}
