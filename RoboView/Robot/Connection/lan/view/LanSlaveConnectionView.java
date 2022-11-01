package de.hska.lat.robot.connection.lan.view;

import javax.swing.JFrame;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;

public class LanSlaveConnectionView extends DisplayFrame {
	private static final long serialVersionUID = 1L;

	private LanSlaveConnectionPanel connectionPanel;

	public LanSlaveConnectionView() {
		super("W-Lan Connection", false, true, false, false);
		connectionPanel = new LanSlaveConnectionPanel();
		add(connectionPanel);
		setSize(250, 180);
		show();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(new LanSlaveConnectionPanel());
		frame.setVisible(true);
	}

@Override
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	connectionPanel.setEmulator(robot);
	return(true);
}

}
